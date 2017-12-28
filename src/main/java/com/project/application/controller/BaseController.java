package com.project.application.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.application.bean.Admin;
import com.project.application.config.Constant;


public class BaseController {
	@Resource 
	HttpSession session;

	public static final Logger logger = LoggerFactory.getLogger(BaseController.class);

	public Admin GetLoginSesseion() {
		Admin admin = (Admin)session.getAttribute(Constant.LOGIN_ADMIN);	
		return admin;
	}

}
