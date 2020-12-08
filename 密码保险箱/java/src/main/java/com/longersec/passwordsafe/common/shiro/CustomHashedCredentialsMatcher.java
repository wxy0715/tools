package com.longersec.passwordsafe.common.shiro;

import com.longersec.passwordsafe.common.config.RedisService;
import com.longersec.passwordsafe.common.result.BusinessException;
import com.longersec.passwordsafe.common.result.code.BaseResponseCode;
import com.longersec.passwordsafe.common.utils.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: CustomHashedCredentialsMatcher
 * @Version: 0.0.1
 */
@Slf4j
public class CustomHashedCredentialsMatcher extends HashedCredentialsMatcher {
    @Autowired
    private RedisService redisService;
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        CustomUsernamePasswordToken customUsernamePasswordToken= (CustomUsernamePasswordToken) token;
        String accessToken= (String) customUsernamePasswordToken.getCredentials();
        String userId= JwtTokenUtil.getUserId(accessToken);
        log.info("doCredentialsMatch....userId={}",userId);
        //判断用户是否被删除
        if(redisService.hasKey(Constant.DELETED_USER_KEY+userId)){
            throw new BusinessException(BaseResponseCode.ACCOUNT_HAS_DELETED_ERROR);
        }
        //判断是否被锁定
        if(redisService.hasKey(Constant.ACCOUNT_LOCK_KEY+userId)){
            throw new BusinessException(BaseResponseCode.ACCOUNT_LOCK);
        }
        //校验token
        if(!JwtTokenUtil.validateToken(accessToken)){
            throw new BusinessException(BaseResponseCode.TOKEN_PAST_DUE);
        }
        /**
         * 判断用户是否被标记了
         */
        if(redisService.hasKey(Constant.JWT_REFRESH_KEY+userId)){
            /**
             * 判断用户是否已经刷新过
             */
            if(redisService.getExpire(Constant.JWT_REFRESH_KEY+userId, TimeUnit.MILLISECONDS)>JwtTokenUtil.getRemainingTime(accessToken)){
                throw new BusinessException(BaseResponseCode.TOKEN_PAST_DUE);
            }
        }
        return true;
    }
}
