package com.longersec.passwordsafe.common.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @ClassName: CustomUsernamePasswordToken
 */
public class CustomUsernamePasswordToken  extends UsernamePasswordToken {
    private String jwtToken;

    public CustomUsernamePasswordToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    @Override
    public Object getPrincipal() {
        return jwtToken;
    }

    @Override
    public Object getCredentials() {
        return jwtToken;
    }
}
