package com.longersec.passwordsafe.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.longersec.passwordsafe.pojo.entity.PetUser;
import com.longersec.passwordsafe.pojo.vo.request.LoginRequest;
import com.longersec.passwordsafe.pojo.vo.request.LostRequest;
import com.longersec.passwordsafe.pojo.vo.request.RegisterRequest;
import com.longersec.passwordsafe.pojo.vo.response.LoginResp;

/**
 *  服务类
 * @author wxy
 * @since 2020-10-16
 */
public interface PetUserService extends IService<PetUser> {
	/** 普通登录**/
	LoginResp userNameLogin(LoginRequest loginRequest);

	/** 手机短信登录**/
	LoginResp phoneLogin(LoginRequest loginRequest);

	/** 登录发送验证码**/
	void sendCode(String phone);

	/** 注册发送验证码**/
	void sendCode1(String phone);

	/** 注册**/
	void register(RegisterRequest registerRequest);

	/** 找回密码**/
	String lost(LostRequest lostRequest);
}
