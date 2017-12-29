package com.project.application.service;
import java.util.List;
import java.util.Map;

import com.project.application.bean.Project;
import com.project.application.core.Service;

/**
 *  @author ling_cx 
 *  @date   2017/12/22.
 */
public interface ProjectService extends Service<Project> {
	List<Project> SelectProjectByCondition(Map<String,Object> params);
	Project SelectProjectbyId(int id);
	int falseDeletion(int id);
	int UpdateProjectStatus(Map<String,Object> params);
	int UpdateProjectSelectGroup(Project project);
}
