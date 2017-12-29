package com.project.application.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.project.application.bean.Article;
import com.project.application.bean.ArticleType;
import com.project.application.bean.Project;
import com.project.application.bean.ProjectGroup;
import com.project.application.bean.ProjectSign;
import com.project.application.bean.ProjectType;
import com.project.application.bean.User;
import com.project.application.config.Constant;
import com.project.application.core.Result;
import com.project.application.core.ResultGenerator;
import com.project.application.service.ArticleService;
import com.project.application.service.ArticleTypeService;
import com.project.application.service.ProjectGroupService;
import com.project.application.service.ProjectService;
import com.project.application.service.ProjectSignService;
import com.project.application.service.ProjectTypeService;
import com.project.application.service.UserService;

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
	@Resource
	private UserService userService;
	@Resource
	private HttpSession session;
	@Resource
	private ProjectGroupService projectGroupService;
	@Resource
	private ProjectSignService projectSignService;
	/**
	 * 首页显示数据的查询
	 * @param model
	 * @return
	 */
	@GetMapping("index")
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

	/**
	 * 文章列表
	 * @param type 类别
	 * @param page 页码
	 * @param model
	 * @return
	 */
	@GetMapping("article/list/{type}-{page}")
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

	/**
	 * 文章详情
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("article/detail/{id}")
	public String articledetail(@PathVariable int id,Model model) {
		Article article = articleService.SelectArticlebyId(id);
		articleService.UpdateBrTimes(article.getArtId());
		model.addAttribute("article", article);
		return "gateway/detail";
	}

	/**
	 * 项目公告列表
	 * @param type
	 * @param page
	 * @param model
	 * @return
	 */
	@GetMapping("project/list/{type}-{page}")
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

	/**
	 * 项目详情
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("project/detail/{id}")
	public String projectdetail(@PathVariable int id,Model model) {
		Project project = projectService.SelectProjectbyId(id);
		if(GetLoginProjectGroupSesseion() != null) {
			ProjectSign ps = new ProjectSign();
			ps.setPsPid(id);
			ps.setPsGid(GetLoginProjectGroupSesseion().getPgId());
			ProjectSign result = projectSignService.SelectProjectSignByPGroupAndProject(ps);
			model.addAttribute("result", result);
		}
		
		model.addAttribute("project", project);
		return "gateway/project-detail";
	}

	/**
	 * 项目报名
	 * @param model
	 * @return
	 */
	@PostMapping("loginGroup/ProjectSign")
	@ResponseBody
	public Result AddProjectSign(@RequestParam int pid) {
		ProjectGroup pg = GetLoginProjectGroupSesseion();
		if(pg == null) {
			if(GetLoginUserSession() == null) {
				return ResultGenerator.genFailResult("您还未登录,请先登录后再操作");
			}else {
				return ResultGenerator.genFailResult("您当前登录的帐号为用户账号，不能报名参与项目。");
			}
		}else {
			ProjectSign ps = new ProjectSign();
			ps.setPsGid(pg.getPgId());
			ps.setPsPid(pid);
			projectSignService.save(ps);
			return ResultGenerator.genSuccessResult().setMessage("报名成功");
		}
	}

	/**
	 * 承包单位列表
	 * @param page
	 * @param model
	 * @return
	 */
	@GetMapping("group/list/{page}")
	public String grouplist(@PathVariable int page,Model model) {
		if(page == 0) {
			page = 1;
		}
		Map<String,Object> params1 = new HashMap<String, Object>();
		params1.put("name", "");
		PageHelper.startPage(page, 20);//设置分页
		List<ProjectGroup> pgList = projectGroupService.SelectProjectGroupByCondition(params1);
		PageInfo<ProjectGroup> pageData=new PageInfo<ProjectGroup>(pgList);
		model.addAttribute("list", pageData);

		/**
		 * 区分是文章或者是项目
		 * 1  文章
		 * 2  项目
		 * 3 承包单位
		 */
		model.addAttribute("flag", 3);

		return "gateway/list";
	}
	
	@GetMapping("group/detail/{id}")
	public String group(@PathVariable int id,Model model) {
		ProjectGroup project = projectGroupService.SelectProjectGroupbyId(id);
		model.addAttribute("projectGroup", project);
		return "gateway/group-detail";
	}

	@GetMapping("user-login")
	public String user_login(){
		return "gateway/login";
	}

	@GetMapping("user-regist")
	public String user_regist(){
		return "gateway/regist";
	}

	@GetMapping("group-login")
	public String group_login(){
		return "gateway/group-login";
	}

	@GetMapping("group-regist")
	public String group_regist(){
		return "gateway/group-regist";
	}

	/**
	 * 校验用户帐号是否已经存在
	 * @param name
	 * @return
	 */
	@PostMapping("CheckUserExist")
	@ResponseBody
	public Result CheckUserExist(@RequestParam String name) {
		User user = userService.selectUserWithRole(name);
		if(user != null) {
			return ResultGenerator.genSuccessResult(1);
		}else {
			return ResultGenerator.genSuccessResult(0);
		}
	}

	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	@PostMapping("UserRegist")
	@ResponseBody
	public Result UserRegist(@ModelAttribute User user) {
		logger.info("user-form:"+user.toString());
		SimpleHash sh = new SimpleHash("MD5",user.getUsPwd(), ByteSource.Util.bytes(user.getUsLoginname()),1024);
		user.setUsPwd(sh.toString());
		user.setUsRegistdate(new Date());
		user.setUsState(0);
		userService.save(user);
		return ResultGenerator.genSuccessResult().setMessage("新增成功");
	}

	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	@PostMapping("UserLogin")
	@ResponseBody
	public Result UserLogin(@ModelAttribute User user) {
		logger.info("user-form:"+user.toString());
		SimpleHash sh = new SimpleHash("MD5",user.getUsPwd(), ByteSource.Util.bytes(user.getUsLoginname()),1024);
		user.setUsPwd(sh.toString());
		User loginUser = userService.Userlogin(user);
		if(loginUser != null ) {
			if(loginUser.getUsState() == 0) {//用户状态正常
				session.setAttribute(Constant.LOGIN_USER,loginUser);
				loginUser.setUsLastlogindate(new Date());
				userService.update(loginUser);
				return ResultGenerator.genSuccessResult().setMessage("登录成功");
			}else {//帐号禁用
				return ResultGenerator.genFailResult("帐号已被禁用，请联系管理员！");
			}
		}else {
			return ResultGenerator.genFailResult("账号或者密码错误，请重新输入。");
		}
	}

	/**
	 * 承包单位登录
	 * @param user
	 * @return
	 */
	@PostMapping("GroupLogin")
	@ResponseBody
	public Result GroupLogin(@ModelAttribute ProjectGroup pg) {
		logger.info("pg-form:"+pg.toString());
		SimpleHash sh = new SimpleHash("MD5",pg.getPgLeaderPwd(), ByteSource.Util.bytes(pg.getPgLeaderPhone()),1024);
		pg.setPgLeaderPwd(sh.toString());
		ProjectGroup loginGroup = projectGroupService.GroupLogin(pg);
		if(loginGroup != null ) {
			session.setAttribute(Constant.LOGIN_GROUP,loginGroup);
			return ResultGenerator.genSuccessResult().setMessage("登录成功");
		}else {
			return ResultGenerator.genFailResult("账号或者密码错误，请重新输入。");
		}
	}

	/**
	 * 承包单位注册时校验手机号是否存在
	 * @param phone
	 * @return
	 */
	@RequestMapping("GroupRegistCheckPhoneExist")
	@ResponseBody
	public Map<String,Object> GroupRegistCheckPhoneExist(String pgLeaderPhone,String param) {
		ProjectGroup user = projectGroupService.SelectProjectGroupByPhone(param);
		Map<String,Object> map=new HashMap<String,Object>();  
		if(user == null) {
			map.put("status", "y");  
			map.put("info", "手机号可以正常使用");  
		}else {
			map.put("status", "n");  
			map.put("info","该手机号已注册，请更换。" );  
		}
		return map;
	}

	/**
	 * 承包单位注册
	 * @param model
	 * @return
	 */
	@PostMapping("ProjectGroupRegist")
	@ResponseBody
	public Result ProjectGroupRegist(@ModelAttribute ProjectGroup model) {
		System.out.println("model:"+model.toString());
		SimpleHash sh = new SimpleHash("MD5",model.getPgLeaderPwd(), ByteSource.Util.bytes(model.getPgLeaderPhone()),1024);
		model.setPgLeaderPwd(sh.toString());
		projectGroupService.save(model);
		return ResultGenerator.genSuccessResult().setMessage("注册成功");
	}

	/**
	 * 前台注销登录
	 * @return
	 */
	@GetMapping("user-logout")
	public String User_logout(){
		session.invalidate();
		return "redirect:index";
	}
	
	@GetMapping("Center/info")
	public String centerInfo(Model model) {
		model.addAttribute("projectGroup",projectGroupService.SelectProjectGroupbyId(GetLoginProjectGroupSesseion().getPgId()));
		return "gateway/usercenter/info";
	}
	
	@GetMapping("Center/pwd")
	public String centerPwd(Model model) {
		return "gateway/usercenter/updatepwd";
	}
	
	@RequestMapping("Center/GroupCheckPwd")
	@ResponseBody
	public Map<String,Object> GroupCheckPwd(String yPgLeaderPwd,String param) {
		ProjectGroup pg = GetLoginProjectGroupSesseion();
		SimpleHash sh = new SimpleHash("MD5",param, ByteSource.Util.bytes(pg.getPgLeaderPhone()),1024);
		Map<String,Object> map=new HashMap<String,Object>();  
		if(sh.toString().equals(pg.getPgLeaderPwd())) {
			map.put("status", "y");  
			map.put("info", "原密码正确");  
		}else {
			map.put("status", "n");  
			map.put("info","原密码错误，请重新输入。" );  
		}
		return map;
	}


}
