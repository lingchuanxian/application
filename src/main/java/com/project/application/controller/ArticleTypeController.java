package com.project.application.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.application.bean.ArticleType;
import com.project.application.config.SystemControllerLog;
import com.project.application.core.Result;
import com.project.application.core.ResultGenerator;
import com.project.application.service.ArticleTypeService;

/**
 *  @author ling_cx 
 *  @date   2017/12/22.
 */
@RestController
@RequestMapping("admin/articleType")
public class ArticleTypeController extends BaseController{
    @Resource
    private ArticleTypeService articleTypeService;
    
    /**
     * 查询文章类别
     * @return
     */
    @PostMapping("SelectArticleTypeOfAll")
    public Result GetMenuById() {
		List<ArticleType> artList = articleTypeService.findAll();
		return ResultGenerator.genSuccessResult(artList);
	}
    
    /**
     * 新增或更新文章分类
     * @param type
     * @return
     */
    @PostMapping("AddOrUpdateArticleType")
    @SystemControllerLog(description = "新增或更新文章分类")   
    public Result AddOrUpdateArticleType(@ModelAttribute ArticleType type) {
		if(type.getAtId() == null) {
			articleTypeService.save(type);
		}else {
			articleTypeService.update(type);
		}
		return ResultGenerator.genSuccessResult().setMessage("成功");
	}
    
    /**
     * 根据id删除指定文章分类
     * @param id
     * @return
     */
    @PostMapping("DeleteArtTypeById")
    @SystemControllerLog(description = "根据id删除指定文章分类")   
	public Result DeleteArtTypeById(@RequestParam int id) {
    	articleTypeService.deleteById(id);
		return ResultGenerator.genSuccessResult().setMessage("删除成功");
	}


}
