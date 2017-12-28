package com.project.application.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.project.application.bean.Admin;
import com.project.application.config.Constant;
import com.project.application.core.Result;
import com.project.application.core.ResultGenerator;
import com.project.application.service.AdminService;
import com.project.application.service.UserService;
import com.project.application.utils.IPUtils;

/**
 * @author ling_cx
 * @date 2017/09/26.
 */
@Controller
public class CommonController extends BaseController{

	@Resource
	UserService mUserService;
	
	@Resource
	AdminService mAdminService;
	
	@Resource
	HttpSession mSession;

	/**
	 * 跳转到登录页
	 * @return
	 */
	@GetMapping("login")
	public String login() {
		return "login";
	}

	@GetMapping("404")
	public String errotPage() {
		return "404";
	}

	@GetMapping("refuse")
	public String refuse() {
		return "refuse";
	}

	@GetMapping("admin/index")
	public String index() {
		return "admin/index";
	}

	@GetMapping("admin/user/list")
	public String userList() {
		return "admin/user/list";
	}
	
	@GetMapping("admin/admin/list")
	public String adminList() {
		return "admin/admin/list";
	}
	
	@GetMapping("admin/articletype/list")
	public String articletypeList() {
		return "admin/article/type/list";
	}
	
	@GetMapping("admin/article/list")
	public String articleList() {
		return "admin/article/list";
	}
	
	@GetMapping("admin/projecttype/list")
	public String projecttype() {
		return "admin/project/type/list";
	}
	
	@GetMapping("admin/projectgroup/list")
	public String projectgroup() {
		return "admin/project/group/list";
	}
	
	@GetMapping("admin/project/list")
	public String project() {
		return "admin/project/list";
	}
	
	
	@GetMapping("admin/systemlog/list")
	public String systemlogList() {
		return "admin/systemlog/list";
	}
	
	/**
	 * 管理员登录操作
	 * @param username 登录名
	 * @param password 密码
	 * @return
	 */
	@PostMapping("AdminLoginAction")
	@ResponseBody
	public Result AdminLoginAction(@RequestParam String username, @RequestParam String password){
		//新建一个admin对象来存储登录信息
		Admin admin = new Admin();
		admin.setAdLoginname(username);
		//使用Constant.ENCRYPTION_TYPE类型对密码进行1024次的盐值加密，盐值为登录名
		SimpleHash sh1 = new SimpleHash(Constant.ENCRYPTION_TYPE,password, ByteSource.Util.bytes(username),1024);
		admin.setAdPwd(sh1.toString());
		//查询数据库
		Admin loginAdmin =  mAdminService.AdminLogin(admin);
		if(loginAdmin != null) {
			//当管理员存在且管理员的状态为0时，可以正常登录，为1时则是禁用状态，不能进行登录
			if(loginAdmin.getAdState() == 0) {
				mSession.setAttribute(Constant.LOGIN_ADMIN,loginAdmin);
				loginAdmin.setAdLastlogindate(new Date());
				HttpServletRequest  request = ((ServletRequestAttributes)RequestContextHolder  
		                .getRequestAttributes())  
		                .getRequest();  
				loginAdmin.setAdLastloginip(IPUtils.getClientIp(request));
				mAdminService.update(loginAdmin);
				return ResultGenerator.genSuccessResult().setMessage("登录成功");
			}else {
				return ResultGenerator.genFailResult("帐号已被禁用，请联系管理员！");
			}
		}else {
			return ResultGenerator.genFailResult("用户名或密码错误！");
		}
	}


	@GetMapping("logout")
	@ResponseBody
	public Result logout(){
		mSession.invalidate();
		return ResultGenerator.genSuccessResult().setMessage("退出成功");
	}
	
}
