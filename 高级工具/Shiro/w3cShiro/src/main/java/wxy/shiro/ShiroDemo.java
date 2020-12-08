package wxy.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * description：通过jdbcRealm工具类连接数据库
 * author：wxy
 * date：12:23
 */
public class ShiroDemo {
    public static void main(String[] args) {
        //核心类:SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:jdbc_realm.ini");
        SecurityManager securityManager = factory.getInstance();
        //当前用户Subject
        SecurityUtils.setSecurityManager(securityManager);
        Subject user = SecurityUtils.getSubject();
        //模拟用户输入用户名与密码
        UsernamePasswordToken token = new UsernamePasswordToken("admin","123");
        try {
            user.login(token);
            System.out.println("登录成功");
        } catch (AuthenticationException e) {
            System.out.println("登录失败");
        }
    }
}
