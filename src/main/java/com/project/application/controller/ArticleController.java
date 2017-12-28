package com.project.application.controller;

import com.project.application.core.Result;
import com.project.application.core.ResultGenerator;
import com.project.application.bean.Article;
import com.project.application.config.SystemControllerLog;
import com.project.application.service.ArticleService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  @author ling_cx 
 *  @date   2017/12/22.
 */
@RestController
@RequestMapping("admin/article")
public class ArticleController extends BaseController{
    @Resource
    private ArticleService articleService;
    
    
    /**
     * 获取文章列表
     * @param page 当前页码
     * @param rows 单页显示条数
     * @param stype 搜索类型
     * @param skey 搜索关键词
     * @return
     */
	@PostMapping("SelectArticleOfAll")
    public Result SelectArticleOfAll(int page,int rows,String stype,String skey) {
    	logger.debug("stype:"+stype+"---skey:"+skey);
    	PageHelper.startPage(page, rows);//设置分页
    	Map<String,Object> params1 = new HashMap<String, Object>();
		params1.put("stype", stype);
		params1.put("skey", skey);
		List<Article> artList = articleService.SelectArticleByCondition(params1);
		PageInfo<Article> pageData=new PageInfo<Article>(artList);
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("total", pageData.getTotal());
		params.put("rows",pageData.getList());
		return ResultGenerator.genSuccessResult(params);
	}
	
	/**
	 * 根据id查询记录
	 * @param id
	 * @return
	 */
	@PostMapping("SelectArticleById")
	public Result SelectArticleById(@RequestParam int id) {
		Article article = articleService.SelectArticlebyId(id);
		return ResultGenerator.genSuccessResult(article);
	}
	
	/**
	 * 根据id删除记录
	 * @param ids
	 * @return
	 */
    @PostMapping("DeleteArticleById")
    @SystemControllerLog(description = "根据ids删除文章")   
	public Result DeleteArticleById(@RequestParam String ids) {
    	String[] idString = ids.split(",");
    	for(int i = 0;i < idString.length;i++ ) {
    		articleService.deleteById(Integer.parseInt(idString[i]));
    	}
		return ResultGenerator.genSuccessResult().setMessage("删除成功");
	}
    
    /**
     * 新增
     * @param article
     * @return
     */
    @PostMapping("AddArticle")
    @SystemControllerLog(description = "新增文章")   
	public Result AddArticle(@ModelAttribute Article article) {
    	logger.info("article:"+article.toString());
    	article.setArtDate(new Date());
    	article.setArtTimes(0);
    	article.setArtUser(GetLoginSesseion().getAdId());
    	articleService.save(article);
		return ResultGenerator.genSuccessResult().setMessage("新增成功");
	}

    @PostMapping("UpdateArticle")
    @SystemControllerLog(description = "更新文章")   
    public Result UpdateArticle(@ModelAttribute Article article) {
    	logger.info("article:"+article.toString());
    	articleService.update(article);
    	return ResultGenerator.genSuccessResult().setMessage("更新成功");
    }

}
