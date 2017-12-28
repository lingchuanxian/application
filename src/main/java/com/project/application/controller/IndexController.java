package com.project.application.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.project.application.bean.Article;
import com.project.application.bean.ArticleType;
import com.project.application.bean.Project;
import com.project.application.bean.ProjectType;
import com.project.application.service.ArticleService;
import com.project.application.service.ArticleTypeService;
import com.project.application.service.ProjectService;
import com.project.application.service.ProjectTypeService;

@Controller
public class IndexController extends BaseController{
	@Resource
	private ArticleService articleService;
	@Resource
	private ArticleTypeService articleTypeService;
	@Resource
	private ProjectService projectService;
	@Resource
	private ProjectTypeService projectTypeService;
	
	@RequestMapping("index")
	public String index(Model model) {
		List<ArticleType> articleType = articleTypeService.findAll();
		for(ArticleType type:articleType) {
			if("新闻中心".equals(type.getAtName())) {
				PageHelper.startPage(1, 6);//设置分页
				Map<String,Object> params1 = new HashMap<String, Object>();
				params1.put("stype", 1);
				params1.put("skey", type.getAtId());
				List<Article> artList = articleService.SelectArticleByCondition(params1);
				PageInfo<Article> pageData=new PageInfo<Article>(artList);
				List<Article> xwzxList = pageData.getList();

				int xwzxSize = xwzxList.size();
				for(int i = 0;i < xwzxSize;i++){
					String str = xwzxList.get(i).getArtContent();
					Document doc = Jsoup.parse(str);
					Elements elements=doc.select("img");
					for (int j = 0; j < elements.size(); j++) {
						String path = elements.get(j).attr("src");
						xwzxList.get(i).setArtPic(path);
					}
					xwzxList.get(i).setArtContent(xwzxList.get(i).getArtContent().replaceAll("\\<.*?>",""));
					xwzxList.get(i).setArtContent(xwzxList.get(i).getArtContent().replaceAll("&nbsp;", ""));
				}
				model.addAttribute("xwzxList", xwzxList);
				model.addAttribute("xwzxId", type.getAtId());
			}
			
			if("通知公告".equals(type.getAtName())) {
				Map<String,Object> params1 = new HashMap<String, Object>();
				params1.put("stype", 1);
				params1.put("skey", type.getAtId());
				List<Article> artList = articleService.SelectArticleByCondition(params1);
				model.addAttribute("tzggList", artList);
				model.addAttribute("tzggId", type.getAtId());
			}
		}

		return "gateway/index";
	}

	@RequestMapping("article/list/{type}-{page}")
	public String articlelist(@PathVariable int type,@PathVariable int page,Model model) {
		if(page == 0) {
			page = 1;
		}
		PageHelper.startPage(page, 20);//设置分页
		Map<String,Object> params1 = new HashMap<String, Object>();
		params1.put("stype", 1);
		params1.put("skey", type);
		List<Article> artList = articleService.SelectArticleByCondition(params1);
		PageInfo<Article> pageData=new PageInfo<Article>(artList);
		model.addAttribute("list", pageData);
		
		model.addAttribute("currentType", articleTypeService.findById(type));
		
		List<ArticleType> typeList = articleTypeService.findAll();
		model.addAttribute("typeList", typeList);
		/**
		 * 区分是文章或者是项目
		 * 1  文章
		 * 2  项目
		 */
		model.addAttribute("flag", 1);
		
		return "gateway/list";
	}

	@RequestMapping("article/detail/{id}")
	public String articledetail(@PathVariable int id,Model model) {
		Article article = articleService.SelectArticlebyId(id);
		articleService.UpdateBrTimes(article.getArtId());
		model.addAttribute("article", article);
		return "gateway/detail";
	}
	
	@RequestMapping("project/list/{type}-{page}")
	public String projectlist(@PathVariable int type,@PathVariable int page,Model model) {
		if(page == 0) {
			page = 1;
		}
		PageHelper.startPage(page, 20);//设置分页
		List<Project> proList = new ArrayList<>();
		if(type == 0) {
			proList = projectService.findAll();
			model.addAttribute("currentType", new ProjectType());
		}else {
			Map<String,Object> params1 = new HashMap<String, Object>();
			params1.put("stype", 1);
			params1.put("skey", type);
			proList = projectService.SelectProjectByCondition(params1);
			model.addAttribute("currentType", projectTypeService.findById(type));
		}
		PageInfo<Project> pageData=new PageInfo<Project>(proList);
		model.addAttribute("list", pageData);
		model.addAttribute("typeId", type);
		List<ProjectType> typeList = projectTypeService.findAll();
		model.addAttribute("typeList", typeList);
		/**
		 * 区分是文章或者是项目
		 * 1  文章
		 * 2  项目
		 */
		model.addAttribute("flag", 2);
		
		return "gateway/list";
	}
}
