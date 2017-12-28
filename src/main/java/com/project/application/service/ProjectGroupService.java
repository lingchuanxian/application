package com.project.application.service;
import java.util.List;
import java.util.Map;

import com.project.application.bean.ProjectGroup;
import com.project.application.core.Service;

/**
 *  @author ling_cx 
 *  @date   2017/12/22.
 */
public interface ProjectGroupService extends Service<ProjectGroup> {
	int falseDeletion(int id);
	ProjectGroup SelectProjectGroupByPhone(String _parameter);
	ProjectGroup SelectProjectGroupbyId(int id);
	List<ProjectGroup> SelectProjectGroupByCondition(Map<String,Object> params);
	ProjectGroup GroupLogin(ProjectGroup projectGroup);
}

