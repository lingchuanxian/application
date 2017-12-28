package com.project.application.service;
import java.util.List;
import java.util.Map;

import com.project.application.bean.Admin;
import com.project.application.core.Service;

/**
 *  @author ling_cx 
 *  @date   2017/12/21.
 */
public interface AdminService extends Service<Admin> {
	/**
	 * 根据条件查询管理员信息
	 * @param params
	 * @return
	 */
	List<Admin> selectAdminByConditions(Map<String,Object> params);
	Admin AdminLogin(Admin admin);
	Admin SelectAdminById(int id);
	int falseDeletion(int id);
	Admin SelectAdminByName(String name);
}
