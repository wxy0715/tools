package com.longersec.passwordsafe.pojo.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wxy
 * @description 忘记密码请求
 * @data 2020/10/19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LostRequest {
	@ApiModelProperty("手机号")
	private String phone;

	@ApiModelProperty("手机验证码")
	private String phoneCode;
}
