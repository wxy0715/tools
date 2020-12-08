package com.longersec.passwordsafe.pojo.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wxy
 * @description 手机号登陆请求
 * @data 2020/10/19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PhoneLoginRequest {
	@ApiModelProperty("手机号")
	private String phone;
}
