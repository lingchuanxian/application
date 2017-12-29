package com.project.application.service.impl;

import com.project.application.mapper.ProjectMapper;
import com.project.application.bean.Project;
import com.project.application.service.ProjectService;
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
@Service("ProjectService")
@Transactional
public class ProjectServiceImpl extends AbstractService<Project> implements ProjectService {
    @Resource
    private ProjectMapper apProjectMapper;

	@Override
	public List<Project> SelectProjectByCondition(Map<String, Object> params) {
		return apProjectMapper.SelectProjectByCondition(params);
	}

	@Override
	public Project SelectProjectbyId(int id) {
		return apProjectMapper.SelectProjectbyId(id);
	}

	@Override
	public int falseDeletion(int id) {
		return apProjectMapper.falseDeletion(id);
	}

	@Override
	public int UpdateProjectStatus(Map<String,Object> params) {
		return apProjectMapper.UpdateProjectStatus(params);
	}

	@Override
	public int UpdateProjectSelectGroup(Project project) {
		return apProjectMapper.UpdateProjectSelectGroup(project);
	}

}
