package com.longersec.passwordsafe.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.longersec.passwordsafe.pojo.entity.PetUser;
import org.apache.ibatis.annotations.Param;

public interface PetUserMapper extends BaseMapper<PetUser> {
	/** 查询手机号**/
	int selectPhone(@Param("phone") String phone, @Param("id") Integer id);

	/** 查询重复用户名**/
	int selectName(@Param("username") String username, @Param("id") Integer id);
}
