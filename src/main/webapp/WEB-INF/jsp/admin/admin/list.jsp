<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.contextPath }/">
<meta charset="UTF-8" />
<title>数字办公系统</title>
<jsp:include page="../include_header.jsp"></jsp:include>
</head>
<body class="easyui-layout" fit="true" border="false">
	<div data-options="region:'north'" style="height: 100px;">
		<fieldset style="border-radius: 10px; border: 1px solid #C3C3C3;">
			<legend style="font-size: 14px;">信息检索</legend>
			<form id="ffSearch" method="post">
				<table cellspacing="0" cellpadding="0">
					<tr>
						<td>管理员名称：</td>
						<td><input type="text" id="name" style="width: 200px" /></td>
					</tr>
					<tr>
						<td>登录帐号：</td>
						<td><input type="text" id="loginName" style="width: 200px" /></td>
						<td colspan="2" style="padding-left: 20px;"><a
							href="javascript:;" class="easyui-linkbutton"
							data-options="iconCls:'icon-search'" id="btnSearch">查询</a></td>
					</tr>
				</table>
			</form>
		</fieldset>
	</div>
	<div data-options="region:'center',split:false">
		<table id="user-tb"></table>
	</div>
	<div class="box" id="user-box" style="display: none;">
		<form id="user-form" method="post">
			<table class="rb-add-user" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td class="td_key">用户名：</td>
					<td class="td_val"><input class="easyui-textbox" type="text"
						name="adName" data-options="required:true" missingMessage="请填写用户名" /></td>
					<td class="td_key">登陆账号：</td>
					<td class="td_val"><input class="easyui-textbox" type="text"
						name="adLoginname"
						data-options="required:true,validType:'isExistAdmin'"
						missingMessage="请填写登录帐号" /></td>
				</tr>
				<tr>
					<td class="td_key">密码：</td>
					<td class="td_val"><input id="pwd" class="easyui-textbox"
						type="password" name="adPwd"
						data-options="required:true,validType:'minLength[6]'"
						missingMessage="请填写登录密码" /></td>
					<td class="td_key">重复密码：</td>
					<td class="td_val"><input class="easyui-textbox"
						type="password" name="readPwd" data-options="required:true"
						validType="equals['#pwd']" missingMessage="请再次填写登录密码" /></td>
				</tr>
			</table>
		</form>
	</div>

	<div id="user_role" style="display: none;">
		<div id="user-role-tb"></div>
	</div>
	<script type="text/javascript" src="static/js/admin-manage.js"></script>
</body>
</html>