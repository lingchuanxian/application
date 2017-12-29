package com.project.application.service.impl;

import com.project.application.mapper.ProjectSignMapper;
import com.project.application.bean.ProjectSign;
import com.project.application.service.ProjectSignService;
import com.project.application.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.Resource;

/**
 *  @author ling_cx 
 *  @date   2017/12/29.
 */
@Service
@Transactional
public class ProjectSignServiceImpl extends AbstractService<ProjectSign> implements ProjectSignService {
    @Resource
    private ProjectSignMapper apProjectSignMapper;

	@Override
	public List<ProjectSign> SelectProjectSignByProject(int id) {
		return apProjectSignMapper.SelectProjectSignByProject(id);
	}

	@Override
	public List<ProjectSign> SelectProjectSignByPGroup(int id) {
		return apProjectSignMapper.SelectProjectSignByPGroup(id);
	}

	@Override
	public ProjectSign SelectProjectSignByPGroupAndProject(ProjectSign projectSign) {
		return apProjectSignMapper.SelectProjectSignByPGroupAndProject(projectSign);
	}

}
