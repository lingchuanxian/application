package com.project.application.mapper;

import java.util.List;
import java.util.Map;

import com.project.application.bean.Project;
import com.project.application.core.Mapper;

public interface ProjectMapper extends Mapper<Project> {
	List<Project> SelectProjectByCondition(Map<String,Object> params);
	Project SelectProjectbyId(int id);
	int falseDeletion(int id);
	int UpdateProjectStatus(Map<String,Object> params);
}