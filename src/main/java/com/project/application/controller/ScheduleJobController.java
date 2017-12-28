package com.project.application.controller;

import com.project.application.core.Result;
import com.project.application.core.ResultGenerator;
import com.project.application.bean.ScheduleJob;
import com.project.application.service.ScheduleJobService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import com.project.application.bean.ScheduleJob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 *  @author ling_cx 
 *  @date   2017/12/25.
 */
@RestController
@RequestMapping("/schedule/job")
public class ScheduleJobController {
	private static final Logger logger = LoggerFactory.getLogger(ScheduleJobController.class);
    @Resource
    private ScheduleJobService scheduleJobService;
    
    /**
     * 新增
     * @param model
     * @return
     */
    @PostMapping("AddScheduleJob")
	public Result AddScheduleJob(@ModelAttribute ScheduleJob model) {
    	logger.info("model:"+model.toString());
    	scheduleJobService.save(model);
		return ResultGenerator.genSuccessResult().setMessage("新增成功");
	}
    
    /**
	 * 根据id删除记录
	 * @param ids
	 * @return
	 */
    @PostMapping("DeleteScheduleJobById")
	public Result DeleteScheduleJobById(@RequestParam String ids) {
    	String[] idString = ids.split(",");
    	for(int i = 0;i < idString.length;i++ ) {
    		scheduleJobService.deleteById(Integer.parseInt(idString[i]));
    	}
		return ResultGenerator.genSuccessResult().setMessage("删除成功");
	}
	
    /**
	 * 根据id查询记录
	 * @param id
	 * @return
	 */
	@GetMapping("SelectScheduleJobById")
	public Result SelectScheduleJobById(@RequestParam int id) {
		ScheduleJob model = scheduleJobService.findById(id);
		return ResultGenerator.genSuccessResult(model);
	}

}
