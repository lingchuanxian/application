package com.project.application.controller;

import com.project.application.core.Result;
import com.project.application.core.ResultGenerator;
import com.project.application.bean.Project;
import com.project.application.bean.ScheduleJob;
import com.project.application.config.SystemControllerLog;
import com.project.application.service.ProjectService;
import com.project.application.service.ScheduleJobService;
import com.project.application.service.impl.ScheduleJobServiceImpl;
import com.project.application.utils.CronDateUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang3.StringUtils;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  @author ling_cx 
 *  @date   2017/12/22.
 */
@RestController
@RequestMapping("admin/project")
public class ProjectController {
	private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);
    @Resource
    private ProjectService projectService;
    @Resource
    private ScheduleJobService scheduleService;
    @Resource
    private ScheduleJobServiceImpl scheduleJobServiceImpl;
	@InitBinder  
	public void initBinder(WebDataBinder binder) {  
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		dateFormat.setLenient(false);  
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
	}
    
    /**
     * 获取项目列表
     * @param page 当前页码
     * @param rows 单页显示条数
     * @param stype 搜索类型
     * @param skey 搜索关键词
     * @return
     */
	@PostMapping("SelectProjectOfAll")
    public Result SelectProjectOfAll(int page,int rows,String name) {
		logger.info("name:"+name);
    	PageHelper.startPage(page, rows);//设置分页
    	Map<String,Object> params1 = new HashMap<String, Object>();
    	params1.put("stype", 0);
    	if(name == null) {
    		params1.put("skey", "");
    	}else {
    		params1.put("skey", name);
    	}
		List<Project> artList = projectService.SelectProjectByCondition(params1);
		PageInfo<Project> pageData=new PageInfo<Project>(artList);
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("total", pageData.getTotal());
		params.put("rows",pageData.getList());
		return ResultGenerator.genSuccessResult(params);
	}
	
    
    /**
     * 新增
     * @param model
     * @return
     * @throws Exception 
     */
    @PostMapping("AddProject")
	public Result AddProject(@ModelAttribute Project model) throws Exception {
    	logger.info("model:"+model.toString());
    	//保存项目内容
    	model.setPrAdddate(new Date());
    	model.setPrState(0);
    	projectService.save(model);
    	
    	//设置报名开始的任务调度器
    	ScheduleJob job = new ScheduleJob();
    	job.setJobCreateDate(new Date());
    	job.setJobName("修改项目报名状态为--报名中");
    	job.setJobDescription("修改项目报名状态为--报名中");
    	job.setJobGroup("分组1");
    	job.setJobBeanClass("com.project.application.quartz.UpdateProjectStatusTask");
    	job.setJobReserved1(""+model.getPrId());
    	job.setJobMethodName("ChangeToStart");
    	job.setJobIsConcurrent(ScheduleJob.CONCURRENT_IS);
    	job.setJobStatus(ScheduleJob.STATUS_RUNNING);
    	job.setJobCronExpression(CronDateUtils.getCron(model.getPrSignStartDate()));
    	scheduleService.save(job);
    	//将任务添加到内存中
    	scheduleJobServiceImpl.addJob(job);
    	
    	//设置报名结束的任务调度器
    	ScheduleJob job2 = new ScheduleJob();
    	job2.setJobCreateDate(new Date());
    	job2.setJobName("修改项目报名状态为--报名结束");
    	job2.setJobDescription("修改项目报名状态为--报名结束");
    	job2.setJobGroup("分组2");
    	job2.setJobBeanClass("com.project.application.quartz.UpdateProjectStatusTask");
    	job2.setJobReserved1(""+model.getPrId());
    	job2.setJobCronExpression(CronDateUtils.getCron(model.getPrSignEndDate()));
    	job2.setJobMethodName("ChangeToEnd");
    	job2.setJobIsConcurrent(ScheduleJob.CONCURRENT_IS);
    	job2.setJobStatus(ScheduleJob.STATUS_RUNNING);
    	scheduleService.save(job2);
    	//将任务添加到内存中
    	scheduleJobServiceImpl.addJob(job2);
    	
		return ResultGenerator.genSuccessResult().setMessage("新增成功");
	}
    
    /**
	 * 根据id删除记录
	 * @param ids
	 * @return
	 */
	@PostMapping("DeleteProjectById")
	@SystemControllerLog(description = "根据id删除置指定项目")
	public Result DeleteProjectById(@RequestParam int id) {
		projectService.falseDeletion(id);
		return ResultGenerator.genSuccessResult().setMessage("删除成功");
	}
	
    /**
	 * 根据id查询记录
	 * @param id
	 * @return
	 */
	@PostMapping("SelectProjectById")
	public Result SelectProjectById(@RequestParam int id) {
		Project model = projectService.findById(id);
		return ResultGenerator.genSuccessResult(model);
	}

}
