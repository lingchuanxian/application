<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.contextPath }/">
<meta charset="UTF-8" />
<title>数字办公系统</title>
<jsp:include page="include_header.jsp"></jsp:include>
<script type="text/javascript" src="static/js/index.js"></script>
</head>
<body class="easyui-layout" data-options="fit:true,scroll:'no'">
	<div class="header" data-options="region:'north',border:false">
		<div class="header-left"></div>
		<div class="header-right">
			<ul class="user-menu">
				<li>欢迎您， <a href="#" class="easyui-menubutton"
					data-options="menu:'#usermenu',iconCls:'icon-user-business-boss'">${LOGIN_ADMIN.adName }</a>
				</li>
			</ul>
			<div id="usermenu" style="width: 100px;">
				<div class="modify-password" data-options="iconCls:'icon-key'">
					<a>修改密码</a>
				</div>
				<div data-options="iconCls:'icon-cog-go'" class="logout">
					<a>退出</a>
				</div>
			</div>
			<div id="modify-password-dlg" class="box" style="display: none;">
				<form id="modify-password-form" method="post">
					<table class="rb-tb2" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="td_key">帐号：</td>
							<td class="td_val" colspan="3">${LOGIN_ADMIN.adName }</td>
						</tr>
						<tr>
							<td class="td_key">原密码：</td>
							<td class="td_val"><input class="easyui-textbox"
								type="password" id="password" style="width: 200px;"
								name="password"
								data-options="required:true,validType:'minLength[6]'"
								missingMessage="请输入原密码" /></td>
						</tr>
						<tr>
							<td class="td_key">新密码：</td>
							<td class="td_val"><input class="easyui-textbox"
								type="password" id="new-password" style="width: 200px;"
								name="newpassword"
								data-options="required:true,validType:'minLength[6]'"
								missingMessage="请输入新密码" /></td>
						</tr>
						<tr>
							<td class="td_key">重复新密码：</td>
							<td class="td_val"><input class="easyui-textbox"
								type="password" id="re-new-password" style="width: 200px;"
								name="repassword" data-options="required:true"
								validType="equals['#new-password']" missingMessage="请再次输入登录密码" /></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
	<div data-options="region:'west',split:true,title:'导航菜单'"
		style="width: 200px;">
		<div id="layout_west_accordion" class="easyui-accordion"
			data-options="fit:true,nimate:true,lines:true">

			<div title="信息管理" data-options="iconCls:'icon-application-view-tile'">
				<div style="margin-top: 8px;">
					<ul class="easyui-tree">
						<li data-options="iconCls:'icon-application-form'"><a
							href="javascript:void(0);"
							onclick="addTab('类别管理','admin/articletype/list','icon-application-form')">类别管理</a></li>
						<li data-options="iconCls:'icon-application-view-list'"><a
							href="javascript:void(0);"
							onclick="addTab('文章管理','admin/article/list','icon-application-view-list')">文章管理</a></li>
					</ul>
				</div>
			</div>

			<div title="工程管理" data-options="iconCls:'icon-package'">
				<div style="margin-top: 8px;">
					<ul class="easyui-tree">
						<li data-options="iconCls:'icon-users'"><a
							href="javascript:void(0);"
							onclick="addTab('工程队管理','admin/projectgroup/list','icon-users')">工程队管理</a></li>
						<li data-options="iconCls:'icon-package-green'"><a
							href="javascript:void(0);"
							onclick="addTab('工程类别管理','admin/projecttype/list','icon-package-green')">工程类别管理</a></li>
						<li data-options="iconCls:'icon-package-start'"><a
							href="javascript:void(0);"
							onclick="addTab('工程管理','admin/project/list','icon-package-start')">项目管理</a></li>
					</ul>
				</div>
			</div>

			<div title="用户管理" data-options="iconCls:'icon-user-group'">
				<div style="margin-top: 8px;">
					<ul class="easyui-tree">
						<li data-options="iconCls:'icon-user-gray-cool'"><a
							href="javascript:void(0);"
							onclick="addTab('管理员管理','admin/admin/list','icon-user-gray-cool')">管理员管理</a></li>
						<li data-options="iconCls:'icon-user-mature'"><a
							href="javascript:void(0);"
							onclick="addTab('注册用户管理','admin/user/list','icon-user-mature')">注册用户管理</a></li>
					</ul>
				</div>
			</div>

			<div title="系统管理" data-options="iconCls:'icon-wrench'">
				<div style="margin-top: 8px;">
					<ul class="easyui-tree">
						<li data-options="iconCls:'icon-database-table'"><a
							href="javascript:void(0);"
							onclick="addTab('系统操作日志','admin/systemlog/list','icon-database-table')">系统操作日志</a></li>
						<li data-options="iconCls:'icon-application-side-list'"><a
							href="javascript:void(0);"
							onclick="addTab('系统更新日志','http://www.baidu.com','icon-application-side-list')">系统更新日志</a></li>
					</ul>
				</div>
			</div>


		</div>
	</div>

	<div data-options="region:'center'">
		<div id="tabs" class="easyui-tabs"
			data-options="tools:'#tab-tools',fit:true,border:false">
			<div title="主页" data-options="iconCls:'icon-house',closable:false"
				style="padding: 10px">这是主页</div>
		</div>
	</div>

	<div data-options="region:'south',border:false"
		style="height: 30px; line-height: 30px;">
		<div class="footer">@Copyright 2017 ling_cx</div>
	</div>

	<div id="mm" class="easyui-menu" style="display: none;">
		<div id="refresh" data-options="iconCls:'icon-arrow-refresh'">刷新</div>
		<div class="menu-sep"></div>
		<div id="close" data-options="iconCls:'icon-cross'">关闭</div>
		<div id="closeall" data-options="iconCls:'icon-close-page'">全部关闭</div>
		<div id="closeother" data-options="iconCls:'icon-close-other'">关闭其他</div>
	</div>
</body>
</html>