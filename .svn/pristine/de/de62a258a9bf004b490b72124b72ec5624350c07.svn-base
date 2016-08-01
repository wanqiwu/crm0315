package com.atguigu.crm.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.atguigu.crm.entity.User;

public interface UserMapper {

	@Select("Select u.id,u.enabled,u.name,password,salt,r.name \"role.name\" "
			+ "From users u "
			+ "Left outer join roles r "
			+ "on u.role_id=r.id "
			+ "Where u.name=#{name}")
	User getByName(@Param("name") String name);
	
}
