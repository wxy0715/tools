package wxy.shiro.shiro.service;
import wxy.shiro.shiro.domain.Users;

public interface UserService {

	public Users findByName(String name);
	
	public Users findById(Integer id);
}
