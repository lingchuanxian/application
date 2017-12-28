package com.project.application.quartz;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.project.application.bean.Project;
import com.project.application.bean.ScheduleJob;
import com.project.application.controller.BaseController;
import com.project.application.service.ProjectService;
import com.project.application.service.ScheduleJobService;
import com.project.application.utils.SpringBeanFactoryUtils;
@Component
public class UpdateProjectStatusTask {
	public static final Logger logger = LoggerFactory.getLogger(BaseController.class);

	/**
	 * 修改项目报名的状态为“报名中”
	 * @param scheduleJob
	 * @throws Exception
	 */
	public void ChangeToStart(ScheduleJob scheduleJob)throws Exception{
		ProjectService mProjectService = (ProjectService)SpringBeanFactoryUtils.getBean("ProjectService");
		ScheduleJobService mScheduleJobService = (ScheduleJobService)SpringBeanFactoryUtils.getBean("ScheduleJobService");
		Map<String,Object> params = new HashMap<>();
		params.put("id", Integer.parseInt(scheduleJob.getJobReserved1()));
		params.put("state", Project.STATE_SIGNING);
		int result = mProjectService.UpdateProjectStatus(params);
		if(result == 1) {
			
			scheduleJob.setJobIsConcurrent(ScheduleJob.CONCURRENT_NOT);
			scheduleJob.setJobStatus(ScheduleJob.STATUS_NOT_RUNNING);
			scheduleJob.setJobUpdateDate(new Date());
			mScheduleJobService.update(scheduleJob);
			
		}
	}
	
	/**
	 * 修改项目报名的状态为“报名结束”
	 * @param scheduleJob
	 * @throws Exception
	 */
	public void ChangeToEnd(ScheduleJob scheduleJob)throws Exception{
		ProjectService mProjectService = (ProjectService)SpringBeanFactoryUtils.getBean("ProjectService");
		ScheduleJobService mScheduleJobService = (ScheduleJobService)SpringBeanFactoryUtils.getBean("ScheduleJobService");
		Map<String,Object> params = new HashMap<>();
		params.put("id", Integer.parseInt(scheduleJob.getJobReserved1()));
		params.put("state", Project.STATE_SIGNEND);
		int result = mProjectService.UpdateProjectStatus(params);
		if(result == 1) {
			
			scheduleJob.setJobIsConcurrent(ScheduleJob.CONCURRENT_NOT);
			scheduleJob.setJobStatus(ScheduleJob.STATUS_NOT_RUNNING);
			scheduleJob.setJobUpdateDate(new Date());
			mScheduleJobService.update(scheduleJob);
			
		}
	
	}
}
