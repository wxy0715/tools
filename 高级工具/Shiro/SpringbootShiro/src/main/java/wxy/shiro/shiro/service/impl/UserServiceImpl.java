package wxy.shiro.shiro.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wxy.shiro.shiro.domain.Users;
import wxy.shiro.shiro.mapper.UserMapper;
import wxy.shiro.shiro.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public Users findByName(String name) {
		return userMapper.findByName(name);
	}

	@Override
	public Users findById(Integer id) {
		return userMapper.findById(id);
	}
}
