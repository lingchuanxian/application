package com.project.application.mapper;

import java.util.List;
import java.util.Map;

import com.project.application.bean.User;
import com.project.application.core.Mapper;


public interface UserMapper extends Mapper<User>{
	User selectUserWithRole(String username);
	List<User> selectUserByConditions(Map<String,Object> params);
	User selectUserById(int id);
	int falseDeletion(int id);
	User Userlogin(User user);
}
