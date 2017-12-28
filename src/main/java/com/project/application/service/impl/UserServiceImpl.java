package com.project.application.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.project.application.bean.User;
import com.project.application.core.AbstractService;
import com.project.application.mapper.UserMapper;
import com.project.application.service.UserService;

@Service("UserService")
public class UserServiceImpl extends AbstractService<User> implements UserService {
	@Resource
	UserMapper mUserMapper;
	@Override
	public User selectUserWithRole(String username) {
		return mUserMapper.selectUserWithRole(username);
	}
	@Override
	public List<User> selectUserByConditions(Map<String,Object> params) {
		return mUserMapper.selectUserByConditions(params);
	}
	@Override
	public User selectUserById(int id) {
		return mUserMapper.selectUserById(id);
	}
	@Override
	public int falseDeletion(int id) {
		return mUserMapper.falseDeletion(id);
	}
	@Override
	public User Userlogin(User user) {
		return mUserMapper.Userlogin(user);
	}
}
