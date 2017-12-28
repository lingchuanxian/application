package com.project.application.service.impl;

import com.project.application.mapper.ProjectTypeMapper;
import com.project.application.bean.ProjectType;
import com.project.application.service.ProjectTypeService;
import com.project.application.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 *  @author ling_cx 
 *  @date   2017/12/22.
 */
@Service("ProjectTypeService")
@Transactional
public class ProjectTypeServiceImpl extends AbstractService<ProjectType> implements ProjectTypeService {
    @Resource
    private ProjectTypeMapper apProjectTypeMapper;

	@Override
	public ProjectType SelectProjectTypeById(int id) {
		return apProjectTypeMapper.SelectProjectTypeById(id);
	}

}
