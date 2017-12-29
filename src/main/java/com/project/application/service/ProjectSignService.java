package com.project.application.service;
import java.util.List;

import com.project.application.bean.ProjectSign;
import com.project.application.core.Service;

/**
 *  @author ling_cx 
 *  @date   2017/12/29.
 */
public interface ProjectSignService extends Service<ProjectSign> {
	List<ProjectSign> SelectProjectSignByProject(int id);
	List<ProjectSign> SelectProjectSignByPGroup(int id);
	ProjectSign SelectProjectSignByPGroupAndProject(ProjectSign projectSign);
}
