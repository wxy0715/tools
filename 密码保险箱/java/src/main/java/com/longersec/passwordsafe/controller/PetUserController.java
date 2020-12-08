package com.longersec.passwordsafe.controller;

import com.longersec.passwordsafe.common.result.BusinessException;
import com.longersec.passwordsafe.common.result.DataResult;
import com.longersec.passwordsafe.common.result.code.BaseResponseCode;
import com.longersec.passwordsafe.common.utils.Constant;
import com.longersec.passwordsafe.common.utils.VertifyCode;
import com.longersec.passwordsafe.pojo.vo.request.LoginRequest;
import com.longersec.passwordsafe.pojo.vo.request.LostRequest;
import com.longersec.passwordsafe.pojo.vo.request.RegisterRequest;
import com.longersec.passwordsafe.pojo.vo.response.LoginResp;
import com.longersec.passwordsafe.service.PetUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.OutputStream;

/**
 *  前端控制器
 * @author wxy
 * @since 2020-10-16
 */
@RestController
@RequestMapping("/api")
@Api(value = "用户模块")
@Slf4j
public class PetUserController {
	@Autowired
	private PetUserService petUserService;

	@PostMapping("/login")
	@ApiOperation(value = "登录")
	public DataResult<LoginResp> login(@RequestBody LoginRequest loginRequest){
		//1.判断是手机短信登录还是普通登录
		String value = loginRequest.getValue();
		LoginResp loginResp;
		String number = "1";
		if (number.equals(value)){
			//3.调用普通登录service
			loginResp = petUserService.userNameLogin(loginRequest);
		}else{
			//2.调用手机短信登录service
			loginResp = petUserService.phoneLogin(loginRequest);
		}
		//4.成功返回前台数据
		if (loginResp == null){
			throw new BusinessException(BaseResponseCode.SYSTEM_ERROR);
		}
		return DataResult.success(loginResp);
	}

	@GetMapping("/sendLoginCode")
	@ApiOperation(value = "登录发送验证码")
	public DataResult<Integer> sendLoginCode(String phone){
		petUserService.sendCode(phone);
		return DataResult.success();
	}

	/**校检验证码 **/
	@GetMapping(value="/checkVerifyCode/{code}")
	@ResponseBody
	public DataResult<Integer> checkVerifyCode(@PathVariable(value="code")int code) {
		VertifyCode vertifyCode = new VertifyCode();
		boolean verifyCode = vertifyCode.checkVerifyCode(code);
		if (!verifyCode) {
			throw new BusinessException(BaseResponseCode.VERTIFY_CODE_ERROR);
		}
		DataResult<Integer> success = DataResult.success();
		success.setData(code);
		return success;
	}
	/** 生成验证码 **/
	@GetMapping(value="/verifyCode")
	public DataResult<Integer> verifyCode(HttpServletResponse response) {
		VertifyCode vertifyCode = new VertifyCode();
		try {
			BufferedImage image  = vertifyCode.createVerifyCode();
			OutputStream out = response.getOutputStream();
			ImageIO.write(image, "JPEG", out);
			out.flush();
			out.close();
			DataResult<Integer> success = DataResult.success();
			success.setData(Constant.VERTIFY_CODE);
			log.info(String.valueOf(success));
			return success;
		}catch(Exception e) {
			e.printStackTrace();
			return new DataResult<>(BaseResponseCode.OPERATION_ERROR);
		}
	}

	@GetMapping("/sendRegisterCode")
	@ApiOperation(value = "注册发送验证码")
	public DataResult<Integer> sendRegisterCode(String phone){
		petUserService.sendCode1(phone);
		return DataResult.success();
	}

	@PostMapping("/register")
	@ApiOperation(value = "注册")
	public DataResult<Integer> register(@RequestBody RegisterRequest registerRequest){
		//注册service
		petUserService.register(registerRequest);
		return DataResult.success();
	}

	@PostMapping("/lost")
	@ApiOperation(value = "找回密码")
	public DataResult<String> lost(@RequestBody LostRequest lostRequest){
		//注册service
		String lost = petUserService.lost(lostRequest);
		return DataResult.success(lost);
	}
}
