package com.project.application.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.project.application.bean.SystemLog;
import com.project.application.core.Result;
import com.project.application.core.ResultGenerator;
import com.project.application.service.SystemLogService;

import tk.mybatis.mapper.util.StringUtil;

/**
 *  @author ling_cx 
 *  @date   2017/12/21.
 */
@RestController
@RequestMapping("admin/systemlog")
public class SystemLogController extends BaseController{
	
    @Resource
    private SystemLogService systemLogService;
    
    @PostMapping("selectSystemLogOfAll")
    public Result selectSystemLogOfAll(int page,int rows,String stype,String skey) {
    	logger.debug("stype:"+stype+"---skey:"+skey);
    	PageHelper.startPage(page, rows);//设置分页
    	Map<String,Object> params1 = new HashMap<String, Object>();
    	if(!StringUtil.isEmpty(stype)) {
    		if(stype.equals("2")) {
    			logger.debug(skey.split(",")[0].toString());
        		params1.put("stype", stype);
        		params1.put("skey1", skey.split(",")[0].toString());
        		params1.put("skey2", skey.split(",")[1].toString());
        	}else {
        		params1.put("stype", stype);
        		params1.put("skey", skey);
        	}
    	}
		List<SystemLog> artList = systemLogService.selectSystemLogByCondition(params1);
		PageInfo<SystemLog> pageData=new PageInfo<SystemLog>(artList);
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("total", pageData.getTotal());
		params.put("rows",pageData.getList());
		return ResultGenerator.genSuccessResult(params);
	}
    
    /**
	 * 根据id删除记录
	 * @param ids
	 * @return
	 */
    @PostMapping("DeleteSystemLogById")
	public Result DeleteSystemLogById(@RequestParam String ids) {
    	String[] idString = ids.split(",");
    	for(int i = 0;i < idString.length;i++ ) {
    		systemLogService.deleteById(Integer.parseInt(idString[i]));
    	}
		return ResultGenerator.genSuccessResult().setMessage("删除成功");
	}
	
    /**
	 * 根据id查询记录
	 * @param id
	 * @return
	 */
	@GetMapping("SelectSystemLogById")
	public Result SelectSystemLogById(@RequestParam int id) {
		SystemLog model = systemLogService.selectSystemLogById(id);
		return ResultGenerator.genSuccessResult(model);
	}

}
