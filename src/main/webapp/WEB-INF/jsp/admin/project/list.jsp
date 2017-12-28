<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.contextPath }/">
<meta charset="UTF-8" />
<title>数字办公系统</title>
<jsp:include page="../include_header.jsp"></jsp:include>
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
								'textarea[name="prDescription"]',
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
						<td>项目名称：</td>
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
					<td class="td_key">项目名称：</td>
					<td class="td_val" colspan="3"><input class="easyui-textbox"
						id-="artTitle" type="text" name="prName" style="width: 588px;" /></td>
				</tr>
				<tr>
					<td class="td_key">项目类别：</td>
					<td class="td_val"><input class="easyui-combobox"
						editable="false" id="type-combox" style="width: 588px;"
						name="prType" /></td>
				</tr>
				<tr>
					<td class="td_key">项目预算：</td>
					<td class="td_val" colspan="3"><input class="easyui-textbox"
						id="artTitle" type="text" name="prBudget" style="width: 588px;" /></td>
				</tr>
				<tr>
					<td class="td_key">项目预计时间：</td>
					<td class="td_val" colspan="3"><input class="easyui-textbox"
						id-="artTitle" type="text" name="prExpectDate" style="width: 588px;" /></td>
				</tr>
				<tr>
					<td class="td_key">项目描述：</td>
					<td class="td_val" colspan="3"><textarea rows="8"
							id="artContent" style="width: 100%; height: 400px;"
							name="prDescription"></textarea></td>
				</tr>
				<tr>
					<td class="td_key">项目联系人：</td>
					<td class="td_val" colspan="3"><input class="easyui-textbox"
						id-="artTitle" type="text" name="prContacts" style="width: 588px;" /></td>
				</tr>
				<tr>
					<td class="td_key">联系方式：</td>
					<td class="td_val" colspan="3"><input class="easyui-textbox"
						id-="artTitle" type="text" name="prContactsPhone" style="width: 588px;" /></td>
				</tr>
				<tr>
					<td class="td_key">报名开始时间：</td>
					<td class="td_val" colspan="3">
						<input type="text" class="easyui-datetimebox" name="prSignStartDate" required="required" style="width: 588px;">
					</td>
				</tr>
				<tr>
					<td class="td_key">报名结束时间：</td>
					<td class="td_val" colspan="3">
						<input type="text" class="easyui-datetimebox" name="prSignEndDate" required="required" style="width: 588px;">
					</td>
				</tr>
			</table>
		</form>
	</div>

	<div  id="article-detail-box" style="display: none;">
		<table class="rb-add-user" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td class="td_key" style="width: 120px;">项目名称：</td>
				<td class="td_val" colspan="3"><span class="prName"></span></td>
			</tr>
			<tr>
				<td class="td_key">项目类别：</td>
				<td class="td_val" colspan="3"><span class="prType"></span></td>
			</tr>
			<tr>
			<tr>
				<td class="td_key">项目预算：</td>
				<td class="td_val" colspan="3"><span class="prBudget"></span>
				</td>
			</tr>
			<tr>
				<td class="td_key">项目预计时间：</td>
				<td class="td_val" colspan="3"><span class="prExpectDate"></span></td>
			</tr>
			<tr>
				<td class="td_key">项目描述：</td>
				<td class="td_val" colspan="3"><span class="prDescription"></span>
				</td>
			</tr>
			<tr>
				<td class="td_key">项目联系人：</td>
				<td class="td_val" colspan="3"><span class="prContacts"></span>
				</td>
			</tr>
			<tr>
				<td class="td_key">联系方式：</td>
				<td class="td_val" colspan="3"><span class="prContactsPhone"></span></td>
			</tr>
			<tr>
				<td class="td_key">报名开始时间：</td>
				<td class="td_val" colspan="3"><span class="prSignStartDate"></span></td>
			</tr>
			<tr>
				<td class="td_key">报名结束时间：</td>
				<td class="td_val" colspan="3"><span class="prSignEndDate"></span></td>
			</tr>
		</table>
	</div>

	<script type="text/javascript" src="static/js/project-manage.js"></script>
</body>
</html>