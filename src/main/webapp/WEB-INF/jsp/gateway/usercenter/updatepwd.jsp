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
<title>三明学院工程管理系统</title>
<meta name="Keywords" content="">
<meta name="Description" content="">
<link href="static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="static/gateway/css/index.css" rel="stylesheet">
<link href="static/gateway/css/main.css" rel="stylesheet">
<script src="static/gateway/js/jquery-1.8.3.min.js"></script>
<script charset="UTF-8"
	src="static/gateway/validform/Validform_v5.3.2_min.js"></script>
<!--[if lt IE 9]>
<script src="static/gateway/js/modernizr.js"></script>
<![endif]-->
<!--[if IE 6]>
 <script  src="static/gateway/js/png.js"></script>
 <script type="text/javascript">
    EvPNG.fix('.logo');
 </script>
<![endif]-->
<script type="text/javascript">
	$(function(){
		$("#updatePwd-form").Validform({
			tiptype:3,
			showAllError:true,
			callback:function(form){
				$.ajax({
					url:'ProjectGroupRegist',
					type:'post',
					dataType: 'json',
					data:$("#group-regist-form").serialize(),
					success:function(data){
						if(data.code== 200){
							$("#result").html("注册成功，马上<a href='group-login' style='text-decoration:underline;'>去登录</a>");
							$("#reset").click(); 
						}else{
							$("#result").html(data.message);
						}
					},
					error:function(e){
						alert(e.message);
					}
				});
				return false;
			}
		});	
	});
</script>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
	<div class="contain">
		<jsp:include page="left.jsp"></jsp:include>
		<div class="listr right">
			<h2>
				<span>您现在的位置:<a href="javascript:;">修改密码</a></span>
			</h2>
			<form id="updatePwd-form">
			<ul>
				<li>
					<div class="form-group col-sm-12">
					<label for="orPwd" class="col-sm-3 control-label">原密码：</label>
					<div class="col-sm-6">
						<input type="password" class="form-control" id="orPwd"
							name="orPwd" placeholder="原密码" datatype="*6-15"
							nullmsg="请输入原登录密码" ajaxurl="Center/GroupCheckPwd">
					</div>
				</div>
				</li>
				<li>
					<div class="form-group col-sm-12">
					<label for="pgLeaderPwd" class="col-sm-3 control-label">新密码：</label>
					<div class="col-sm-6">
						<input type="password" class="form-control" id="pgLeaderPwd"
							name="pgLeaderPwd" placeholder="新密码" datatype="*"
							recheck="pgLeaderPwd" nullmsg="请输入新密码" errormsg="两次密码输入不一致">
					</div>
				</div>
				</li>
				<li>
					<div class="form-group col-sm-12">
					<label for="rePgLeaderPwd" class="col-sm-3 control-label">重复新密码：</label>
					<div class="col-sm-6">
						<input type="password" class="form-control" id="rePgLeaderPwd"
							name="rePgLeaderPwd" placeholder="重复新密码" datatype="*"
							recheck="pgLeaderPwd" nullmsg="请再次输入新密码" errormsg="两次密码输入不一致">
					</div>
				</div>
				</li>
				<li>
					<div class="form-group col-sm-12">
					<div class="col-sm-offset-3 col-sm-10">
						<button type="submit" class="btn btn-primary">立即修改</button>
						<button type="reset" class="btn btn-danger">重置</button>
					</div>
				</div>
				</li>
			</ul>
			</form>
			<div class="blank"></div>
			
		</div>
	</div>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>