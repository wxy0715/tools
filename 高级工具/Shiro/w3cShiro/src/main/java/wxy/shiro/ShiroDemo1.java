package wxy.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * @description 通过读取ini文件来模拟登陆
 * @author wxy
 */
public class ShiroDemo1 {
    public static void main(String[] args) {
        //SecurutyManager---->Factory
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //ctrl+alt+v：快速生成变量及对应的数据类型
        SecurityManager securityManager = factory.getInstance();
        //当前用户：Subject--->SecurityUtils
        SecurityUtils.setSecurityManager(securityManager);
        //当前用户
        Subject user = SecurityUtils.getSubject();
        //通过UsernamePasswordToken来模拟html/jsp传递过来的用户名与密码
        UsernamePasswordToken token = new UsernamePasswordToken("aaa","123456");
        //通过shiro来判断用户是否登录成功:ctrl+alt+t
        try {
            user.login(token);
            System.out.println("登录成功");
        } catch (AuthenticationException e) {
            System.out.println("登录失败");
        }
    }
}
