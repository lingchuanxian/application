package com.project.application.mapper;

import java.util.List;
import java.util.Map;

import com.project.application.bean.SystemLog;
import com.project.application.core.Mapper;


public interface SystemLogMapper extends Mapper<SystemLog> {
	List<SystemLog>  selectSystemLogByCondition(Map<String,Object> params);
	SystemLog selectSystemLogById(int id);
}