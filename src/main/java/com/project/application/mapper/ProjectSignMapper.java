package com.project.application.mapper;

import java.util.List;

import com.project.application.bean.ProjectSign;
import com.project.application.core.Mapper;

public interface ProjectSignMapper extends Mapper<ProjectSign> {
	List<ProjectSign> SelectProjectSignByProject(int id);
	List<ProjectSign> SelectProjectSignByPGroup(int id);
	ProjectSign SelectProjectSignByPGroupAndProject(ProjectSign projectSign);
}