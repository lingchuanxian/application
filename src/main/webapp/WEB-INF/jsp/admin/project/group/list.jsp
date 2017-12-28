<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.contextPath }/">
<meta charset="UTF-8" />
<title>数字办公系统</title>
<jsp:include page="../../include_header.jsp"></jsp:include>
<link rel="stylesheet"
	href="static/kindeditor/themes/default/default.css" />
<link rel="stylesheet"
	href="static/kindeditor/plugins/code/prettify.css" />
<script src="static/kindeditor/kindeditor.js"></script>
<script src="static/kindeditor/kindeditor-all-min.js"></script>
<script charset="UTF-8" src="static/kindeditor/kindeditor-min.js"></script>
<script charset="UTF-8" src="static/kindeditor/lang/zh_CN.js"></script>
<script charset="UTF-8" src="static/kindeditor/plugins/code/prettify.js"></script>
<script>
	KindEditor
			.ready(function(K) {
				var editor1 = K
						.create(
								'textarea[name="pgBrief"]',
								{
									cssPath : '${pageContext.request.contextPath }/static/kindeditor/plugins/code/prettify.css',
									uploadJson : '${pageContext.request.contextPath }/static/kindeditor/jsp/upload_json.jsp',
									fileManagerJson : '${pageContext.request.contextPath }/static/kindeditor/jsp/file_manager_json.jsp',
									allowFileManager : true,
									afterCreate : function() {
										var self = this;
										K.ctrl(document, 13, function() {
											self.sync();
											document.forms['example'].submit();
										});
										K.ctrl(self.edit.doc, 13, function() {
											self.sync();
											document.forms['example'].submit();
										});
									},
									afterBlur : function() {
										this.sync();
									}
								});
				prettyPrint();
			});
</script>
</head>
<body class="easyui-layout" fit="true" border="false">
	<div data-options="region:'north'" style="height: 100px;">
		<fieldset style="border-radius: 10px; border: 1px solid #C3C3C3;">
			<legend style="font-size: 14px;">信息检索</legend>
			<form id="article-search-form" method="post">
				<table cellspacing="0" cellpadding="0">
					<tr>
						<td>单位名称：</td>
						<td><input type="text" id="name" style="width: 200px" /></td>
						<td colspan="2" style="padding-left: 20px;"><a
							href="javascript:;" class="easyui-linkbutton"
							data-options="iconCls:'icon-search'" id="btnSearch">查询</a></td>
					</tr>
				</table>
			</form>
		</fieldset>
	</div>

	<div data-options="region:'center',split:false">
		<table id="article-tb"></table>
	</div>

	<div class="box" id="article-add-box" style="display: none;">
		<form id="article-form" method="post">
			<table class="rb-add-user" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td class="td_key">承包单位名称：</td>
					<td class="td_val" colspan="3" style="width: 120px;"><input
						class="easyui-textbox" id-="pgName" type="text" name="pgName"
						style="width: 588px;" data-options="required:true"
						missingMessage="请填写工程队名称" /></td>
				</tr>
				<tr>
					<td class="td_key">承包单位规模：</td>
					<td class="td_val" colspan="3"><select class="easyui-combobox"
						name="pgScale" id="search-type" editable="false"
						style="width: 588px;">
							<option value="5人以下">5人以下</option>
							<option value="5 ~ 10人">5 ~ 10人</option>
							<option value="10 ~ 20人">10 ~ 20人</option>
							<option value="20 ~ 30人">20 ~ 30人</option>
							<option value="30 ~ 50人">30 ~ 50人</option>
							<option value="10 ~ 100人">50 ~ 100人</option>
							<option value="100 ~ 200人">100 ~ 200人</option>
							<option value="200人以上">200人以上</option>
					</select></td>
				</tr>
				<tr>
				<tr>
					<td class="td_key">承包单位地址：</td>
					<td class="td_val" colspan="3"><input class="easyui-textbox"
						id-="artTitle" type="text" name="pgAddress" style="width: 588px;"
						data-options="required:true" missingMessage="请填写工程队地址" /></td>
				</tr>
				<tr>
					<td class="td_key">负责人：</td>
					<td class="td_val" colspan="3"><input class="easyui-textbox"
						id-="artTitle" type="text" name="pgLeader" style="width: 588px;"
						data-options="required:true" missingMessage="请填写工程队负责人" /></td>
				</tr>
				<tr>
					<td class="td_key">负责人联系方式：<br>
					<font color="#FF0000">(承包单位登陆账号,默认密码为123456)</font></td>
					<td class="td_val" colspan="3"><input class="easyui-textbox"
						id-="artTitle" type="text" name="pgLeaderPhone"
						style="width: 588px;"
						data-options="required:true,validType:['mobile','isExistPhone']"
						missingMessage="请填写工程队负责人联系方式" /></td>
				</tr>
				<tr>
					<td class="td_key">负责人邮箱：</td>
					<td class="td_val" colspan="3"><input class="easyui-textbox"
						id-="artTitle" type="text" name="pgLeaderEmail"
						style="width: 588px;"
						data-options="required:true,validType:'email'"
						missingMessage="请填写工程队负责人邮箱" /></td>
				</tr>
				<tr>
					<td class="td_key">承包单位简介：</td>
					<td class="td_val" colspan="3"><textarea rows="8" id="pgBrief"
							style="width: 100%; height: 400px;" name="pgBrief"></textarea></td>
				</tr>
			</table>
		</form>
	</div>

	<div id="article-detail-box" style="display: none;">
		<table class="rb-add-user" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td class="td_key" style="width: 120px;">承包单位名称：</td>
				<td class="td_val" colspan="3"><span class="pgName"></span></td>
			</tr>
			<tr>
				<td class="td_key">承包单位规模：</td>
				<td class="td_val" colspan="3"><span class="pgScale"></span></td>
			</tr>
			<tr>
			<tr>
				<td class="td_key">承包单位地址：</td>
				<td class="td_val" colspan="3"><span class="pgAddress"></span>
				</td>
			</tr>
			<tr>
				<td class="td_key">负责人：</td>
				<td class="td_val" colspan="3"><span class="pgLeader"></span></td>
			</tr>
			<tr>
				<td class="td_key">负责人联系方式：</td>
				<td class="td_val" colspan="3"><span class="pgLeaderPhone"></span>
				</td>
			</tr>
			<tr>
				<td class="td_key">负责人邮箱：</td>
				<td class="td_val" colspan="3"><span class="pgLeaderEmail"></span>
				</td>
			</tr>
			<tr>
				<td class="td_key">承包单位简介：</td>
				<td class="td_val" colspan="3"><span class="pgBrief"></span></td>
			</tr>
		</table>
	</div>

	<script type="text/javascript" src="static/js/project-group-manage.js"></script>
</body>
</html>