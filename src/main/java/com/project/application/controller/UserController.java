package com.project.application.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.project.application.bean.Admin;
import com.project.application.bean.User;
import com.project.application.config.Constant;
import com.project.application.config.SystemControllerLog;
import com.project.application.core.Result;
import com.project.application.core.ResultGenerator;
import com.project.application.service.UserService;

import tk.mybatis.mapper.util.StringUtil;


/**
 *  @author ling_cx 
 *  @date   2017/12/21.
 */
@RestController
@RequestMapping("admin/user/")
public class UserController extends BaseController{
    @Resource
    private UserService mUserService;
	
	/**
	 * 获取用户列表
	 * @param page
	 * @param rows
	 * @param name
	 * @param loginName
	 * @return
	 */
    @PostMapping("GetUserList")
	public Result GetUserList(int page,int rows,String name,String loginName) {
		PageHelper.startPage(page, rows);//设置分页
		Map<String,Object> params1 = new HashMap<String, Object>();
		params1.put("name", name);
		params1.put("loginName", loginName);
		List<User> list = mUserService.selectUserByConditions(params1);
		PageInfo<User> pageData=new PageInfo<User>(list);
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("total", pageData.getTotal());
		params.put("rows", pageData.getList());
		return ResultGenerator.genSuccessResult().setData(params);
	}
	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	@PostMapping("AddUser")
	@SystemControllerLog(description = "新增用户信息")
	public Result AddUser(@ModelAttribute User user) {
		logger.info("user-form:"+user.toString());
		SimpleHash sh = new SimpleHash("MD5",user.getUsPwd(), ByteSource.Util.bytes(user.getUsLoginname()),1024);
		user.setUsPwd(sh.toString());
		user.setUsRegistdate(new Date());
		mUserService.save(user);
		return ResultGenerator.genSuccessResult().setMessage("新增成功");
	}
	
	/**
	 * 校验用户帐号是否已经存在
	 * @param name
	 * @return
	 */
	@PostMapping("CheckExist")
	public Result checkExist(@RequestParam String name) {
		User user = mUserService.selectUserWithRole(name);
		if(user != null) {
			return ResultGenerator.genSuccessResult(1);
		}else {
			return ResultGenerator.genSuccessResult(0);
		}
	}

	/**
	 * 删除指定用户(假删除)
	 * @param id
	 * @return
	 */
	@PostMapping("DeleteUserById")
	@SystemControllerLog(description = "根据id删除置指定用户")
	public Result DeleteUserById(@RequestParam int id) {
		mUserService.falseDeletion(id);
		return ResultGenerator.genSuccessResult().setMessage("删除成功");
	}
	
	/**
	 * 更新用户的状态
	 * @param id
	 * @param state
	 * @return
	 */
	@PostMapping("UpdateUserState")
	@SystemControllerLog(description = "更新用户信息")
	public Result UpdateUserState(@RequestParam int id,@RequestParam int state) {
		User user = new User();
		user.setUsId(id);
		user.setUsState(state);
		mUserService.update(user);
		return ResultGenerator.genSuccessResult().setMessage("修改成功");
	}
	
	/**
	 * 获取指定用户信息
	 * @param id
	 * @return
	 */
	@PostMapping("SelectUserById")
	public Result SelectUserById(@RequestParam int id) {
		User user = mUserService.selectUserById(id);
		return ResultGenerator.genSuccessResult(user);
	}
	
	/**
	 * 重置用户密码为123456
	 * @param id
	 * @return
	 */
	@PostMapping("ResetUserPassword")
	@SystemControllerLog(description = "重置指定用户的密码")
	public Result ResetUserPassword(@RequestParam int id) {
		User user = new User();
		user.setUsId(id);
		User initUser = mUserService.selectUserById(id);
		SimpleHash sh = new SimpleHash(Constant.ENCRYPTION_TYPE,Constant.RESET_PWD, ByteSource.Util.bytes(initUser.getUsLoginname()),1024);
		user.setUsPwd(sh.toString());
		mUserService.update(user);
		return ResultGenerator.genSuccessResult().setMessage("重置成功");
	}
}
