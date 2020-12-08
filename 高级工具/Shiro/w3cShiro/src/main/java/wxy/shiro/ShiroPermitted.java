package wxy.shiro;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import wxy.utils.ShiroUtil;

import java.util.Arrays;

/**
 * @author wxy
 * @description 判断用户具有哪些权限
 * @data 2020/4/20
 */

public class ShiroPermitted {
    public static void main(String[] args) {
        //判断bigbird=123的用户是否具有某个具体的权限
        Subject user = ShiroUtil.loginUtil("classpath:shiro_role_permission.ini", "bigbird", "123");
        boolean flag1 = user.isPermitted("InRoom:xiaoFei");
        System.out.println("flag1="+flag1);

        //判断某个用户是否同时具有多个权限
        boolean[] flags = user.isPermitted("InRoom:xiaoFei", "InRoom:update");
        System.out.println("flags="+ Arrays.toString(flags));

        try {
            user.checkPermission("InRoom:xiaoFei");
            System.out.println("bigbird有消费记录权限");
        } catch (AuthorizationException e) {
            System.out.println("bigbird有没有消费记录权限");
        }
    }
}
