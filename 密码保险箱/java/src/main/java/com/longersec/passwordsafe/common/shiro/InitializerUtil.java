package com.longersec.passwordsafe.common.shiro;

import org.springframework.stereotype.Component;

/**
 * @author wxy
 * @description 初始化jwt
 * @data 2020/10/19
 */
@Component
public class InitializerUtil {
	public InitializerUtil(TokenSetting tokenSetting) {
		JwtTokenUtil.setJwtProperties(tokenSetting);
	}
}
