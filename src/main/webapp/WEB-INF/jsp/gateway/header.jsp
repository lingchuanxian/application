<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<header>
	<div class="headtop">
		<div class="timer box">
			<c:if test="${LOGIN_USER == null && LOGIN_GROUP == null }">
				<span>
					<a href="group-login">承包单位登录</a> | <a href="group-regist">承包单位注册</a>
					|<a href="user-login">用户登录</a> | <a href="user-regist">用户注册</a>
				</span>
			</c:if>
			<c:if test="${LOGIN_USER != null }">
				<span>欢迎您，<a title="个人中心" href="">${LOGIN_USER.usName }</a>，<a href="user-logout" style="color:#FF0000;">注销登录</a></span>
			</c:if>
			
			<c:if test="${LOGIN_GROUP != null }">
				<span>欢迎您，<a title="个人中心" href="Center/info">${LOGIN_GROUP.pgName }</a>，<a href="user-logout" style="color:#FF0000;">注销登录</a></span>
				
			</c:if>

			<script type="text/javascript" src="static/gateway/js/timer.js"></script>
		</div>
	</div>
	<div class="logo box"></div>
	<nav id="nav" class="box">
		<ul>
			<li><a href="index">网站首页</a></li>
			<li><a href="project/list/0-1">工程公告</a></li>
			<li><a href="group/list/1">承包单位</a></li>
			<li><a href="article/list/1-1">新闻中心</a></li>
			<li><a href="article/list/2-1">通知公告</a></li>
			<li><a href="article/list/14-1">下载专区</a></li>
			<li><a href="book.html">留言板</a></li>
		</ul>
	</nav>
</header>