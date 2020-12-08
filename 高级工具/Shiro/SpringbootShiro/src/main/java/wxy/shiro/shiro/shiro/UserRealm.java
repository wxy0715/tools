package wxy.shiro.shiro.shiro;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import wxy.shiro.shiro.domain.Users;
import wxy.shiro.shiro.mapper.UserMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * 自定义Realm
 * @author lenovo
 *
 */
public class UserRealm extends AuthorizingRealm {
	@Autowired
	private UserMapper userMapper;
	/**
	 * 执行授权逻辑
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		System.out.println("执行授权逻辑");
		//给资源进行授权
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//添加资源的授权字符串
		//info.addStringPermission("user:add");

		//到数据库查询当前登录用户的授权字符串
		Subject subject = SecurityUtils.getSubject();
		Users user = (Users)subject.getPrincipal();
		Users dbUser = userMapper.findById(user.getId());
		System.out.println(dbUser.getPerm());
		String[] list = dbUser.getPerm().split(",");
		//List<String> permissionList=new ArrayList<String>();
		for (String s: list) {
			info.addStringPermission(s);
			//permissionList.add(s);
		}
		//info.addStringPermissions(permissionList);
		return info;
	}

	/**
	 * 执行认证逻辑
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		System.out.println("执行认证逻辑");
		//编写shiro判断逻辑，判断用户名和密码
		//1.判断用户名
		UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
		Users user = userMapper.findByName(token.getUsername());
		if(user==null){
			//用户名不存在
			return null;
		}
		//2.判断密码
		return new SimpleAuthenticationInfo(user,user.getPassword(),user.getUsername());
	}
}
