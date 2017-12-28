package com.project.application.service;
import com.project.application.bean.ProjectType;
import com.project.application.core.Service;

/**
 *  @author ling_cx 
 *  @date   2017/12/22.
 */
public interface ProjectTypeService extends Service<ProjectType> {
	ProjectType SelectProjectTypeById(int id);
}
