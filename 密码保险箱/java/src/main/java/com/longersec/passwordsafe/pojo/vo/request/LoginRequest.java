package com.longersec.passwordsafe.pojo.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wxy
 * @description 登录传参
 * @data 2020/10/19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LoginRequest {

	@ApiModelProperty("用户名")
	private String username;

	@ApiModelProperty("密码")
	private String password;

	@ApiModelProperty("手机号")
	private String phone;

	@ApiModelProperty("验证码")
	private String code;

	@ApiModelProperty("手机验证码")
	private String phoneCode;

	@ApiModelProperty(value = "登录方式")
	private String value;
}
