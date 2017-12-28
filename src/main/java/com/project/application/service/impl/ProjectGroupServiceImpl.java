package com.project.application.service.impl;

import com.project.application.mapper.ProjectGroupMapper;
import com.project.application.bean.ProjectGroup;
import com.project.application.service.ProjectGroupService;
import com.project.application.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 *  @author ling_cx 
 *  @date   2017/12/22.
 */
@Service("ProjectGroupService")
@Transactional
public class ProjectGroupServiceImpl extends AbstractService<ProjectGroup> implements ProjectGroupService {
    @Resource
    private ProjectGroupMapper apProjectGroupMapper;

	@Override
	public int falseDeletion(int id) {
		return apProjectGroupMapper.falseDeletion(id);
	}

	@Override
	public ProjectGroup SelectProjectGroupByPhone(String _parameter) {
		return apProjectGroupMapper.SelectProjectGroupByPhone(_parameter);
	}

	@Override
	public ProjectGroup SelectProjectGroupbyId(int id) {
		return apProjectGroupMapper.SelectProjectGroupbyId(id);
	}

	@Override
	public List<ProjectGroup> SelectProjectGroupByCondition(Map<String, Object> params) {
		return apProjectGroupMapper.SelectProjectGroupByCondition(params);
	}

}
