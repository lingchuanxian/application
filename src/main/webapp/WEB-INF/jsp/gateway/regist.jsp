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
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<article>
		<div class="tsjb">
			<h2>
				<span class="location">当前位置：<a href="/">首页</a> >> 用户注册
				</span>用户注册
			</h2>
			<div class="note_show">
				<p>本栏目主要受理广安区范围内，公民、法人和其他组织对局机关和工作人员在实施管理和提供服务过程中投诉影响卫生事业发展环境和机关效能问题的各类投诉。






				
				<p>请填写下面表单，您的投诉事项将及时反馈给您，非常感谢你的支持。</p>
				<p class="ps_red">注：如您需要得到有关方面的答复，请您详细填写联系电话或联系地址！</p>
			</div>
			<div class="notebook">
				<form class="form-horizontal" id="regist-form" style="margin-top: 20px;">
					<div class="form-group usLoginname">
						<label for="usLoginname" class="col-sm-2 control-label">登录名：</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="usLoginname"
								name="usLoginname" placeholder="登录名">
								<span id="usLoginname_helpBlock" class="help-block" color="red"></span>
						</div>
					</div>
					<div class="form-group usPwd">
						<label for="usPwd" class="col-sm-2 control-label">密码：</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="usPwd"
								name="usPwd" placeholder="密码">
						</div>
					</div>
					<div class="form-group reUsPwd">
						<label for="reUsPwd" class="col-sm-2 control-label">重复密码：</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="reUsPwd"
								name="reUsPwd" placeholder="重复密码">
								<span id="usPwd_helpBlock" class="help-block" color="red"></span>
						</div>
					</div>
					<div class="form-group usName">
						<label for="usName" class="col-sm-2 control-label">真实姓名：</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="usName"
								name="usName" placeholder="真实姓名">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">性别：</label> <label
							class="radio-inline"> <input type="radio" name="usSex"
							id="sex1" value="0" checked="checked"> 男
						</label> <label class="radio-inline"> <input type="radio"
							name="usSex" id="sex2" value="1"> 女
						</label>
					</div>
					<div class="form-group usPhone">
						<label for="usPhone" class="col-sm-2 control-label">手机号码：</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="usPhone"
								name="usPhone" placeholder="手机号码">
								<span id="usPhone_helpBlock" class="help-block" color="red"></span>
						</div>
					</div>
					<div class="form-group usMail">
						<label for="usMail" class="col-sm-2 control-label">邮箱：</label>
						<div class="col-sm-6">
							<input type="email" class="form-control" id="usMail"
								name="usMail" placeholder="邮箱">
								<span id="usMail_helpBlock" class="help-block" color="red"></span>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10"></div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="button" class="btn btn-primary" id="regist">注册</button>
							<button type="reset" class="btn btn-danger">重置</button>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<span id="result" style="color:#FF0000;"></span>
						</div>
					</div>
				</form>
			</div>
		</div>
	</article>

	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>