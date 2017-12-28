package com.project.application.mapper;

import java.util.List;
import java.util.Map;

import com.project.application.bean.Admin;
import com.project.application.core.Mapper;

public interface AdminMapper extends Mapper<Admin> {
	List<Admin> selectAdminByConditions(Map<String,Object> params);
	Admin AdminLogin(Admin admin);
	Admin SelectAdminById(int id);
	int falseDeletion(int id);
	Admin SelectAdminByName(String name);
}