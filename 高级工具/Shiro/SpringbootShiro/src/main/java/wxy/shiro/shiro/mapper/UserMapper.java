package wxy.shiro.shiro.mapper;

import org.apache.ibatis.annotations.Select;
import wxy.shiro.shiro.domain.Users;

/**
 * @author wxy
 */
public interface UserMapper {
	/**
	 * @author wxy
	 */
	@Select("SELECT `id`,`username`, `password` FROM `users` where `username` = #{name}")
	public Users findByName(String name);
	@Select("SELECT `id`,`username`, `perm`,`password` FROM `users` where `id` = #{id}")
	public Users findById(Integer id);
}
