package com.project.application.mapper;

import java.util.List;
import java.util.Map;

import com.project.application.bean.ProjectGroup;
import com.project.application.core.Mapper;

public interface ProjectGroupMapper extends Mapper<ProjectGroup> {
	int falseDeletion(int id);
	ProjectGroup SelectProjectGroupByPhone(String _parameter);
	ProjectGroup SelectProjectGroupbyId(int id);
	List<ProjectGroup> SelectProjectGroupByCondition(Map<String,Object> params);
	ProjectGroup GroupLogin(ProjectGroup projectGroup);
}