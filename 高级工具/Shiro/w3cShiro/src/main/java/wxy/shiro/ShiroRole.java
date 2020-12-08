package wxy.shiro;

import org.apache.shiro.subject.Subject;
import wxy.utils.ShiroUtil;

import java.util.Arrays;
import java.util.List;

/**
 * @author wxy
 * @description 判用户具有哪些角色
 * @data 2020/4/20
 */

public class ShiroRole {
    public static void main(String[] args) {
        Subject user = ShiroUtil.loginUtil("classpath:shiro_role_permission.ini", "bigbird", "123");
        //判断用户是否拥有某个角色
        boolean flag1 = user.hasRole("system");
        System.out.println("flag1="+flag1);
        List<String> roles = Arrays.asList("role1", "role2","system");
        boolean[] flags = user.hasRoles(roles);
        System.out.println(Arrays.toString(flags));

    }
}
