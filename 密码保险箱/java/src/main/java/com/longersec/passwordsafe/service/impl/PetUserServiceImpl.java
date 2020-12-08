package com.longersec.passwordsafe.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longersec.passwordsafe.common.config.RedisService;
import com.longersec.passwordsafe.mapper.PetUserMapper;
import com.longersec.passwordsafe.pojo.entity.PetConfigPasswordEncryptKey;
import com.longersec.passwordsafe.pojo.entity.PetUser;
import com.longersec.passwordsafe.pojo.vo.request.LoginRequest;
import com.longersec.passwordsafe.pojo.vo.request.LostRequest;
import com.longersec.passwordsafe.pojo.vo.request.RegisterRequest;
import com.longersec.passwordsafe.pojo.vo.response.LoginResp;
import com.longersec.passwordsafe.common.result.BusinessException;
import com.longersec.passwordsafe.common.result.code.BaseResponseCode;
import com.longersec.passwordsafe.service.PetConfigPasswordEncryptKeyService;
import com.longersec.passwordsafe.service.PetUserService;
import com.longersec.passwordsafe.common.shiro.JwtTokenUtil;
import com.longersec.passwordsafe.common.utils.Constant;
import com.longersec.passwordsafe.common.utils.Sm4Utils;
import com.longersec.passwordsafe.common.utils.Sms;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 *  服务实现类
 * @author wxy
 * @since 2020-10-16
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class PetUserServiceImpl extends ServiceImpl<PetUserMapper, PetUser> implements PetUserService {

	@Autowired
	private PetConfigPasswordEncryptKeyService petConfigPasswordEncryptKeyService;

	@Autowired
	private RedisService redisService;

	/**普通登录**/
	@Override
	public LoginResp userNameLogin(LoginRequest loginRequest) {

		LoginResp loginResp = new LoginResp();
		PetUser petUser;
		//1.根据用户名或手机号查询用户集合
		QueryWrapper<PetUser> objectQueryWrapper = new QueryWrapper<>();
		objectQueryWrapper.eq("phone",loginRequest.getUsername()).or().eq("username",loginRequest.getUsername());
		petUser = baseMapper.selectOne(objectQueryWrapper);
		//2.判端账号是否存在
		if(petUser==null){
			throw new BusinessException(BaseResponseCode.ACCOUNT_ERROR);
		}
		//3.判断密码是否正确
		//查询秘钥
		PetConfigPasswordEncryptKey petConfigPasswordEncryptKey = petConfigPasswordEncryptKeyService.getById(1);
		String decryptEcb = Sm4Utils.decryptEcb(petConfigPasswordEncryptKey.getWxyKey(), petUser.getPassword());
		if (!decryptEcb.equals(loginRequest.getPassword())){
			throw new BusinessException(BaseResponseCode.ACCOUNT_PASSWORD_ERROR);
		}
		//4.判断用户是否禁用
		if(petUser.getStatu()==0){
			throw new BusinessException(BaseResponseCode.ACCOUNT_LOCK);
		}
		//5.生成token
		Map<String,Object> claims=new HashMap<>(16);
		claims.put(Constant.JWT_USER_PHONE,petUser.getPhone());
		//claims.put(Constant.ROLES_INFOS_KEY,"");
		//claims.put(Constant.PERMISSIONS_INFOS_KEY,"");
		String accessToken = JwtTokenUtil.getAccessToken(String.valueOf(petUser.getId()), claims);
		log.info("token剩余时间为:"+JwtTokenUtil.getRemainingTime(accessToken));
		//6.返回数据给前台
		loginResp.setId(petUser.getId());
		loginResp.setUsername(petUser.getUsername());
		loginResp.setToken(accessToken);
		loginResp.setPhoto_url(petUser.getPhotoUrl());
		return loginResp;
	}

	/**手机短信登录**/
	@Override
	public LoginResp phoneLogin(LoginRequest loginRequest) {
		//判断手机是否注册
		Boolean hasPhone = redisService.hasKey(Constant.PHONE_CODE_REGISTER + loginRequest.getPhone());
		if (!hasPhone){
			throw new BusinessException(BaseResponseCode.PHONE_IS_NULL);
		}
		LoginResp loginResp = new LoginResp();
		PetUser petUser;
		//1.根据手机号去查用户
		QueryWrapper<PetUser> objectQueryWrapper = new QueryWrapper<>();
		objectQueryWrapper.eq("phone",loginRequest.getPhone());
		petUser = baseMapper.selectOne(objectQueryWrapper);
		//2.判端账号是否存在
		if(petUser==null){
			throw new BusinessException(BaseResponseCode.ACCOUNT_ERROR);
		}
		//3.判断是否锁定
		if(petUser.getStatu()==0){
			throw new BusinessException(BaseResponseCode.ACCOUNT_LOCK);
		}
		//判断是否发送验证码
		Boolean hasPhoneSend = redisService.hasKey(Constant.PHONE_CODE_SEND + loginRequest.getPhone());
		if (!hasPhoneSend){
			throw new BusinessException(BaseResponseCode.PHONE_CODE_NOT_SEND);
		}
		//判断验证码是否正确
		String phoneCode = (String) redisService.get(Constant.PHONE_CODE_SEND + loginRequest.getPhone());
		if (!phoneCode.equals(loginRequest.getPhoneCode())){
			throw new BusinessException(BaseResponseCode.PHONE_CODE_MISTAKE);
		}
		//4.生成token
		Map<String,Object> claims=new HashMap<>(16);
		claims.put(Constant.JWT_USER_NAME,petUser.getUsername());
		claims.put(Constant.JWT_USER_PHONE,petUser.getPhone());
		//claims.put(Constant.ROLES_INFOS_KEY,"");
		//claims.put(Constant.PERMISSIONS_INFOS_KEY,"");
		String accessToken = JwtTokenUtil.getAccessToken(String.valueOf(petUser.getId()), claims);
		log.info("token剩余时间为:"+JwtTokenUtil.getRemainingTime(accessToken));
		//5.返回数据给前台
		loginResp.setId(petUser.getId());
		loginResp.setUsername(petUser.getUsername());
		loginResp.setToken(accessToken);
		loginResp.setPhoto_url(petUser.getPhotoUrl());
		return loginResp;
	}

	/**
	 * 登录发送验证码
	 * @param phone
	 **/
	@Override
	public void sendCode(String phone) {
		//判断手机号是否注册
		Boolean hasPhone = redisService.hasKey(Constant.PHONE_CODE_REGISTER + phone);
		if (!hasPhone){
			throw new BusinessException(BaseResponseCode.PHONE_IS_NULL);
		}
		//发送短信
		Sms.sendCode(phone, redisService);
	}

	/**
	 * 注册发送验证码
	 * @param phone
	 **/
	@Override
	public void sendCode1(String phone) {
		//判断手机号是否注册
		Boolean hasPhone = redisService.hasKey(Constant.PHONE_CODE_REGISTER + phone);
		if (hasPhone){
			throw new BusinessException(BaseResponseCode.PHONE_IS_EXISTS);
		}
		//发送短信
		Sms.sendCode(phone, redisService);
	}

	@Override
	public void register(RegisterRequest registerRequest) {
		//判断手机号是否已被注册
		Boolean hasPhone = redisService.hasKey(Constant.PHONE_CODE_REGISTER + registerRequest.getPhone());
		if (hasPhone){
			throw new BusinessException(BaseResponseCode.PHONE_IS_EXISTS);
		}
		//判端是否发送验证码
		Boolean hasCode = redisService.hasKey(Constant.PHONE_CODE_SEND + registerRequest.getPhone());
		if (!hasCode){
			throw new BusinessException(BaseResponseCode.PHONE_CODE_NOT_SEND);
		}
		//判断验证码是否正确
		String code = (String) redisService.get(Constant.PHONE_CODE_SEND + registerRequest.getPhone());
		if (!code.equals(registerRequest.getPhoneCode())){
			throw new BusinessException(BaseResponseCode.PHONE_CODE_MISTAKE);
		}
		//判断用户名是否存在
		int i = baseMapper.selectPhone(registerRequest.getUsername(), null);
		if (i == 1){
			throw new BusinessException(BaseResponseCode.ACCOUNT_EXISTS);
		}
		//密码加密
		PetConfigPasswordEncryptKey petConfigPasswordEncryptKey = petConfigPasswordEncryptKeyService.getById(1);
		PetUser petUser = new PetUser();
		BeanUtils.copyProperties(registerRequest,petUser);
		petUser.setPassword(Sm4Utils.encryptEcb(petConfigPasswordEncryptKey.getWxyKey(),registerRequest.getPassword()));
		//存储数据库
		int insert = baseMapper.insert(petUser);
		if (insert != 1){
			throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
		}
		//手机号存储到redis
		redisService.set(Constant.PHONE_CODE_REGISTER+registerRequest.getPhone(),registerRequest.getPhone());
	}

	/**
	 * 找回密码
	 * @param lostRequest
	 **/
	@Override
	public String lost(LostRequest lostRequest) {
		//判断手机号是否已被注册
		Boolean hasPhone = redisService.hasKey(Constant.PHONE_CODE_REGISTER + lostRequest.getPhone());
		if (!hasPhone){
			throw new BusinessException(BaseResponseCode.PHONE_IS_NULL);
		}
		//判端是否发送验证码
		Boolean hasCode = redisService.hasKey(Constant.PHONE_CODE_SEND + lostRequest.getPhone());
		if (!hasCode){
			throw new BusinessException(BaseResponseCode.PHONE_CODE_NOT_SEND);
		}
		//判断验证码是否正确
		String code = (String) redisService.get(Constant.PHONE_CODE_SEND + lostRequest.getPhone());
		if (!code.equals(lostRequest.getPhoneCode())){
			throw new BusinessException(BaseResponseCode.PHONE_CODE_MISTAKE);
		}
		//查询数据
		QueryWrapper<PetUser> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("phone",lostRequest.getPhone());
		PetUser petUser = baseMapper.selectOne(queryWrapper);
		PetConfigPasswordEncryptKey petConfigPasswordEncryptKey = petConfigPasswordEncryptKeyService.getById(1);
		String name = "您的用户名为:"+petUser.getUsername()+"您的密码为:"+Sm4Utils.decryptEcb(petConfigPasswordEncryptKey.getWxyKey(),petUser.getPassword());
		log.info("找回信息为:"+name);
		return name;

	}
}
