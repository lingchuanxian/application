package com.project.application.controller;

import com.project.application.core.Result;
import com.project.application.core.ResultGenerator;
import com.project.application.bean.ProjectSign;
import com.project.application.service.ProjectSignService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import com.project.application.bean.ProjectSign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  @author ling_cx 
 *  @date   2017/12/29.
 */
@RestController
@RequestMapping("admin/projectSign")
public class ProjectSignController {
	private static final Logger logger = LoggerFactory.getLogger(ProjectSignController.class);
    @Resource
    private ProjectSignService projectSignService;
    
    /**
     * 新增
     * @param model
     * @return
     */
    @PostMapping("AddProjectSign")
	public Result AddProjectSign(@ModelAttribute ProjectSign model) {
    	logger.info("model:"+model.toString());
    	projectSignService.save(model);
		return ResultGenerator.genSuccessResult().setMessage("新增成功");
	}
    
    /**
	 * 根据id删除记录
	 * @param ids
	 * @return
	 */
    @PostMapping("DeleteProjectSignById")
	public Result DeleteProjectSignById(@RequestParam String ids) {
    	String[] idString = ids.split(",");
    	for(int i = 0;i < idString.length;i++ ) {
    		projectSignService.deleteById(Integer.parseInt(idString[i]));
    	}
		return ResultGenerator.genSuccessResult().setMessage("删除成功");
	}
	
    /**
	 * 根据id查询记录
	 * @param id
	 * @return
	 */
	@GetMapping("SelectProjectSignById")
	public Result SelectProjectSignById(@RequestParam int id) {
		ProjectSign model = projectSignService.findById(id);
		return ResultGenerator.genSuccessResult(model);
	}

	@PostMapping("SelectProjectSignByProjectId")
	public Result SelectProjectSignByProjectId(@RequestParam int id) {
		List<ProjectSign> signList = projectSignService.SelectProjectSignByProject(id);
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("total", signList.size());
		params.put("rows",signList);
		return ResultGenerator.genSuccessResult(params);
	}
	
}
