package com.project.application.controller;

import com.project.application.core.Result;
import com.project.application.core.ResultGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.project.application.bean.Admin;
import com.project.application.bean.User;
import com.project.application.config.Constant;
import com.project.application.config.SystemControllerLog;
import com.project.application.service.AdminService;

import tk.mybatis.mapper.util.StringUtil;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 *  @author ling_cx 
 *  @date   2017/12/21.
 */
@RestController
@RequestMapping("admin/admin")
public class AdminController extends BaseController{
    @Resource
    private AdminService mAdminService;
    
    /**
	 * 获取管理员列表
	 * @param page
	 * @param rows
	 * @param name
	 * @param loginName
	 * @return
	 */
	@PostMapping("GetAdminList")
	public Result GetUserList(int page,int rows,String name,String loginName) {
		PageHelper.startPage(page, rows);//设置分页
		Map<String,Object> params1 = new HashMap<String, Object>();
		params1.put("name", name);
		params1.put("loginName", loginName);
		List<Admin> list = mAdminService.selectAdminByConditions(params1);
		PageInfo<Admin> pageData=new PageInfo<Admin>(list);
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
    @PostMapping("AddAdmin")
	public Result AddAdmin(@ModelAttribute Admin model) {
    	logger.info("model:"+model.toString());
    	model.setAdType(1);
    	model.setAdState(0);
    	model.setAdAdddate(new Date());
    	mAdminService.save(model);
		return ResultGenerator.genSuccessResult().setMessage("新增成功");
	}
    
    /**
	 * 根据id查询记录
	 * @param id
	 * @return
	 */
	@PostMapping("SelectAdminById")
	public Result SelectAdminById(@RequestParam int id) {
		Admin model = mAdminService.findById(id);
		return ResultGenerator.genSuccessResult(model);
	}
	

	/**
	 * 校验用户帐号是否已经存在
	 * @param name
	 * @return
	 */
	@PostMapping("CheckExist")
	public Result checkExist(@RequestParam String name) {
		Admin admin = mAdminService.SelectAdminByName(name);
		if(admin != null) {
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
	@PostMapping("DeleteAdminById")
	@SystemControllerLog(description = "根据id删除置指定管理员")
	public Result DeleteAdminById(@RequestParam int id) {
		mAdminService.falseDeletion(id);
		return ResultGenerator.genSuccessResult().setMessage("删除成功");
	}
	
	/**
	 * 更新用户的状态
	 * @param id
	 * @param state
	 * @return
	 */
	@PostMapping("UpdateAdminState")
	@SystemControllerLog(description = "更新管理员状态")
	public Result UpdateAdminState(@RequestParam int id,@RequestParam int state) {
		Admin admin = new Admin();
		admin.setAdId(id);
		admin.setAdState(state);
		mAdminService.update(admin);
		return ResultGenerator.genSuccessResult().setMessage("修改成功");
	}
	
	/**
	 * 重置用户密码为123456
	 * @param id
	 * @return
	 */
	@PostMapping("ResetAdminPassword")
	@SystemControllerLog(description = "重置指定管理员的密码")
	public Result ResetAdminPassword(@RequestParam int id) {
		Admin admin = new Admin();
		admin.setAdId(id);
		Admin initAdmin = mAdminService.SelectAdminById(id);
		SimpleHash sh = new SimpleHash(Constant.ENCRYPTION_TYPE,Constant.RESET_PWD, ByteSource.Util.bytes(initAdmin.getAdLoginname()),1024);
		admin.setAdPwd(sh.toString());
		mAdminService.update(admin);
		return ResultGenerator.genSuccessResult().setMessage("重置成功");
	}
	
	/**
	 * 修改密码
	 * @param id
	 * @return
	 */
	@PostMapping("ModifyAdminPassword")
	@SystemControllerLog(description = "修改密码信息")
	public Result ModifyUserPassword(@RequestParam String password,@RequestParam String newpassword,@RequestParam String repassword) {
		Admin currentUser = GetLoginSesseion();
		if(StringUtil.isNotEmpty(password)&&StringUtil.isNotEmpty(repassword)&&StringUtil.isNotEmpty(newpassword)) {
			SimpleHash sh1 = new SimpleHash(Constant.ENCRYPTION_TYPE,password, ByteSource.Util.bytes(currentUser.getAdLoginname()),1024);
			if(currentUser.getAdPwd().equals(sh1.toString())) {
				if(newpassword.equals(repassword)) {
					SimpleHash sh2 = new SimpleHash(Constant.ENCRYPTION_TYPE,newpassword, ByteSource.Util.bytes(currentUser.getAdName()),1024);
					currentUser.setAdPwd(sh2.toString());
					mAdminService.update(currentUser);
					return ResultGenerator.genSuccessResult().setMessage("重置成功");
				}else {
					return ResultGenerator.genFailResult("密码修改失败，密码不一致。");
				}
			}else {
				return ResultGenerator.genFailResult("密码修改失败，原密码错误。");
			}
		}else {
			return ResultGenerator.genFailResult("密码修改失败，密码不能为空。");
		}
		
	}

}
