package com.project.application.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.application.bean.SystemLog;
import com.project.application.core.AbstractService;
import com.project.application.mapper.SystemLogMapper;
import com.project.application.service.SystemLogService;

/**
 *  @author ling_cx 
 *  @date   2017/12/06.
 */
@Service("SystemLogService")
@Transactional
public class SystemLogServiceImpl extends AbstractService<SystemLog> implements SystemLogService {
    @Resource
    private SystemLogMapper oaSystemLogMapper;

	@Override
	public List<SystemLog> selectSystemLogByCondition(Map<String, Object> params) {
		return oaSystemLogMapper.selectSystemLogByCondition(params);
	}

	@Override
	public SystemLog selectSystemLogById(int id) {
		return oaSystemLogMapper.selectSystemLogById(id);
	}

}
