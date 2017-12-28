package com.project.application.service.impl;

import com.project.application.mapper.AdminMapper;
import com.project.application.bean.Admin;
import com.project.application.service.AdminService;
import com.project.application.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 *  @author ling_cx 
 *  @date   2017/12/21.
 */
@Service("AdminService")
@Transactional
public class AdminServiceImpl extends AbstractService<Admin> implements AdminService {
    @Resource
    private AdminMapper apAdminMapper;

	@Override
	public Admin AdminLogin(Admin admin) {
		return apAdminMapper.AdminLogin(admin);
	}

	@Override
	public Admin SelectAdminById(int id) {
		return apAdminMapper.SelectAdminById(id);
	}

	@Override
	public List<Admin> selectAdminByConditions(Map<String, Object> params) {
		return apAdminMapper.selectAdminByConditions(params);
	}

	@Override
	public Admin SelectAdminByName(String name) {
		return apAdminMapper.SelectAdminByName(name);
	}

	@Override
	public int falseDeletion(int id) {
		return apAdminMapper.falseDeletion(id);
	}

}
