package com.project.application.mapper;

import com.project.application.bean.ProjectType;
import com.project.application.core.Mapper;

public interface ProjectTypeMapper extends Mapper<ProjectType> {
	ProjectType SelectProjectTypeById(int id);
}