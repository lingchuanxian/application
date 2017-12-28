package com.project.application.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.application.bean.ProjectType;
import com.project.application.config.SystemControllerLog;
import com.project.application.core.Result;
import com.project.application.core.ResultGenerator;
import com.project.application.service.ProjectTypeService;

/**
 *  @author ling_cx 
 *  @date   2017/12/22.
 */
@RestController
@RequestMapping("admin/projectType")
public class ProjectTypeController extends BaseController{
    @Resource
    private ProjectTypeService projectTypeService;
    
    /**
     * 查询文章类别
     * @return
     */
    @PostMapping("SelectProjectTypeOfAll")
    public Result SelectProjectTypeOfAll() {
		List<ProjectType> artList = projectTypeService.findAll();
		return ResultGenerator.genSuccessResult(artList);
	}
    
    /**
     * 新增或更新文章分类
     * @param type
     * @return
     */
    @PostMapping("AddOrUpdateProjectType")
    @SystemControllerLog(description = "新增或更新工程分类")   
    public Result AddOrUpdateProjectType(@ModelAttribute ProjectType type) {
		if(type.getPtId() == null) {
			projectTypeService.save(type);
		}else {
			projectTypeService.update(type);
		}
		return ResultGenerator.genSuccessResult().setMessage("成功");
	}
    
    /**
     * 根据id删除指定文章分类
     * @param id
     * @return
     */
    @PostMapping("DeleteProjectTypeById")
    @SystemControllerLog(description = "根据id删除指定工程分类")   
	public Result DeleteProjectTypeById(@RequestParam int id) {
    	projectTypeService.deleteById(id);
		return ResultGenerator.genSuccessResult().setMessage("删除成功");
	}

}
