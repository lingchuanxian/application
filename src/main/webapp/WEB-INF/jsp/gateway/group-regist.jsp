<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<base href="${pageContext.request.contextPath }/">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>三明学院工程管理系统</title>
<meta name="Keywords" content="">
<meta name="Description" content="">
<link href="static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="static/gateway/css/index.css" rel="stylesheet">
<link href="static/gateway/css/main.css" rel="stylesheet">
<script src="static/gateway/js/jquery-1.8.3.min.js"></script>
<script charset="UTF-8" src="static/gateway/validform/Validform_v5.3.2_min.js"></script>
<script src="static/gateway/js/index.js"></script>
<!--[if lt IE 9]>
<script src="static/gateway/js/modernizr.js"></script>
<![endif]-->
<!--[if IE 6]>
 <script  src="static/gateway/js/png.js"></script>
 <script type="text/javascript">
    EvPNG.fix('.logo');
 </script>
<![endif]-->

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
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<article>
		<div class="tsjb">
			<h2>
				<span class="location">当前位置：<a href="/">首页</a> >> 承包单位注册
				</span>承包单位注册
			</h2>
			<div class="note_show">
				<p>本栏目主要受理广安区范围内，公民、法人和其他组织对局机关和工作人员在实施管理和提供服务过程中投诉影响卫生事业发展环境和机关效能问题的各类投诉。</p>
				<p>请填写下面表单，您的投诉事项将及时反馈给您，非常感谢你的支持。</p>
				<p class="ps_red">注：如您需要得到有关方面的答复，请您详细填写联系电话或联系地址！</p>
			</div>
			<div class="notebook">
				<form class="form-horizontal" id="group-regist-form"
					style="margin-top: 20px;">
					<div class="form-group pgName">
						<label for="pgName" class="col-sm-3 control-label">承包单位名称：</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="pgName"
								name="pgName" placeholder="承包单位名称" datatype="*" nullmsg="请输入承包单位名称">
						</div>
					</div>
					<div class="form-group pgScale">
						<label for="pgScale" class="col-sm-3 control-label">承包单位规模：</label>
						<div class="col-sm-6">
							<select class="form-control" name="pgScale" id="pgScale">
								<option value="5人以下">5人以下</option>
								<option value="5 ~ 10人">5 ~ 10人</option>
								<option value="10 ~ 20人">10 ~ 20人</option>
								<option value="20 ~ 30人">20 ~ 30人</option>
								<option value="30 ~ 50人">30 ~ 50人</option>
								<option value="10 ~ 100人">50 ~ 100人</option>
								<option value="100 ~ 200人">100 ~ 200人</option>
								<option value="200人以上">200人以上</option>
							</select> <span id="usLoginname_helpBlock" class="help-block" style="color:#FF0000;"></span>
						</div>
					</div>
					<div class="form-group pgAddress">
						<label for="pgAddress" class="col-sm-3 control-label">承包单位地址：</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="pgAddress"
								name="pgAddress" placeholder="承包单位地址" datatype="s" nullmsg="请输入承包单位地址">
						</div>
					</div>
					<div class="form-group pgLeader">
						<label for="pgLeader" class="col-sm-3 control-label">负责人：</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="pgLeader"
								name="pgLeader" placeholder="负责人" datatype="s" nullmsg="请输入负责人">
						</div>
					</div>
					<div class="form-group pgLeaderPhone">
						<label for="pgLeaderPhone" class="col-sm-3 control-label">负责人联系方式：</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="pgLeaderPhone" name="pgLeaderPhone"
								placeholder="负责人联系方式" datatype="m" nullmsg="请输入负责人联系方式" ajaxurl="GroupRegistCheckPhoneExist">
						</div>
					</div>
					
					<div class="form-group pgLeaderPwd">
						<label for="pgLeaderPwd" class="col-sm-3 control-label">登录密码：</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="pgLeaderPwd"
								name="pgLeaderPwd" placeholder="登录密码" datatype="*6-15" nullmsg="请输入登录密码" errormsg="密码范围在6~15位之间！" >
						</div>
					</div>
					<div class="form-group rePgLeaderPwd">
						<label for="rePgLeaderPwd" class="col-sm-3 control-label">重复密码：</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="rePgLeaderPwd"
								name="rePgLeaderPwd" placeholder="重复密码" datatype="*" recheck="pgLeaderPwd" nullmsg="请再次输入登录密码" errormsg="两次密码输入不一致">
						</div>
					</div>
					
					<div class="form-group pgLeaderEmail">
						<label for="pgLeaderEmail" class="col-sm-3 control-label">负责人邮箱：</label>
						<div class="col-sm-6">
							<input type="email" class="form-control" id="pgLeaderEmail"
								name="pgLeaderEmail" placeholder="负责人邮箱" datatype="e" nullmsg="请输入负责人邮箱" errormsg="请输入正确的邮箱">
						</div>
					</div>
					
					<div class="form-group usMail">
						<label for="pgBrief" class="col-sm-3 control-label">承包单位简介：</label>
					</div>
					
					<div class="form-group pgBrief">
					<label for="pgBrief" class="col-sm-1 control-label"></label>
						<div class="col-sm-9">
							<textarea rows="8" id="pgBrief"
							style="width: 100%; height: 400px;" name="pgBrief" datatype="*" nullmsg="请输入承包单位简介"></textarea>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-3 col-sm-10"></div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-3 col-sm-10">
							<button type="submit" class="btn btn-primary" id="btn-group-regist">注册</button>
							<button type="reset" class="btn btn-danger" id="reset">重置</button>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<span id="result" style="color: #FF0000;"></span>
						</div>
					</div>
				</form>
			</div>
		</div>
	</article>

	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>