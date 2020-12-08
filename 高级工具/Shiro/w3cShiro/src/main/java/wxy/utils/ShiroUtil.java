package wxy.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * description：工具类
 * @author ：wxy
 * date：16:30
 */
public class ShiroUtil {

    /**
     * 身份认证
     * @param configPath
     * @param username
     * @param password
     * @return
     */
    public static Subject loginUtil(String configPath,String username,String password) {
        //找资源：ctrl+shift+n
        //核心类:SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(configPath);
        SecurityManager securityManager = factory.getInstance();
        //当前用户Subject
        SecurityUtils.setSecurityManager(securityManager);
        Subject user = SecurityUtils.getSubject();
        //模拟用户输入用户名与密码
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try {
            user.login(token);
            System.out.println("登录成功");
        } catch (AuthenticationException e) {
            System.out.println("登录失败");
        }
        return user;
    }
}
