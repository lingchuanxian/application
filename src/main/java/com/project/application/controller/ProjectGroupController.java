package com.project.application.controller;

import com.project.application.core.Result;
import com.project.application.core.ResultGenerator;
import com.project.application.bean.ArticleType;
import com.project.application.bean.Project;
import com.project.application.bean.ProjectGroup;
import com.project.application.bean.User;
import com.project.application.config.SystemControllerLog;
import com.project.application.service.ProjectGroupService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import com.project.application.bean.ProjectGroup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  @author ling_cx 
 *  @date   2017/12/22.
 */
@RestController
@RequestMapping("admin/projectGroup")
public class ProjectGroupController {
	private static final Logger logger = LoggerFactory.getLogger(ProjectGroupController.class);
    @Resource
    private ProjectGroupService projectGroupService;
    
    /**
     * 获取工程队列表
     * @return
     */
    @PostMapping("/GetProjectGroupList")
	public Result GetProjectGroupList(int page,int rows,String name,String loginName) {
		PageHelper.startPage(page, rows);//设置分页
		Map<String,Object> params1 = new HashMap<String, Object>();
		params1.put("name", name);
		params1.put("loginName", loginName);
		List<ProjectGroup> list = projectGroupService.SelectProjectGroupByCondition(params1);
		PageInfo<ProjectGroup> pageData=new PageInfo<ProjectGroup>(list);
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("total", pageData.getTotal());
		params.put("rows", pageData.getList());
		return ResultGenerator.genSuccessResult().setData(params);
	}
    
    /**
     * 新增
     * @param model
     * @return
     */
    @PostMapping("AddProjectGroup")
	public Result AddProjectGroup(@ModelAttribute ProjectGroup model) {
    	logger.info("model:"+model.toString());
    	projectGroupService.save(model);
		return ResultGenerator.genSuccessResult().setMessage("新增成功");
	}
    
    @PostMapping("CheckPhoneExist")
	public Result CheckPhoneExist(@RequestParam String phone) {
		ProjectGroup user = projectGroupService.SelectProjectGroupByPhone(phone);
		if(user != null) {
			return ResultGenerator.genSuccessResult(1);
		}else {
			return ResultGenerator.genSuccessResult(0);
		}
	}
    
    /**
     * 新增或更新文章分类
     * @param type
     * @return
     */
    @PostMapping("AddOrUpdateProjectGroup")
    @SystemControllerLog(description = "新增或更新工程队信息")   
    public Result AddOrUpdateArticleType(@ModelAttribute ProjectGroup type) {
		if(type.getPgId() == null) {
			projectGroupService.save(type);
		}else {
			projectGroupService.update(type);
		}
		return ResultGenerator.genSuccessResult().setMessage("成功");
	}
    
    /**
     * 根据id删除指定文章分类
     * @param id
     * @return
     */
    @PostMapping("DeleteProjectGroupById")
    @SystemControllerLog(description = "根据id删除指定工程队")   
	public Result DeleteProjectGroupById(@RequestParam int id) {
    	projectGroupService.falseDeletion(id);
		return ResultGenerator.genSuccessResult().setMessage("删除成功");
	}
    
    /**
	 * 获取指定用户信息
	 * @param id
	 * @return
	 */
	@PostMapping("SelectProjectGroupById")
	public Result SelectProjectGroupById(@RequestParam int id) {
		ProjectGroup user = projectGroupService.SelectProjectGroupbyId(id);
		return ResultGenerator.genSuccessResult(user);
	}
}
