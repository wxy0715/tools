package com.longersec.passwordsafe.pojo.vo.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wxy
 * @description 登录成功返回数据
 * @data 2020/10/19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LoginResp {
	@ApiModelProperty("用户名")
	private String username;

	@ApiModelProperty("用户id")
	private Integer id;

	@ApiModelProperty("token")
	private String token;

	@ApiModelProperty("头像地址")
	private String photo_url;
}
