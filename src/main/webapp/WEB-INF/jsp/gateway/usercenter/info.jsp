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
	<jsp:include page="../header.jsp"></jsp:include>
	<div class="contain">
		<jsp:include page="left.jsp"></jsp:include>
		<div class="listr right">
			<h2>
				<span>您现在的位置:<a href="javascript:;">我的资料</a></span>
			</h2>
			<ul>
				<li class="a_content">单位名称：${projectGroup.pgName }</li>
				<li class="a_content">单位规模： ${projectGroup.pgScale }</li>
				<li class="a_content">单位地址： ${projectGroup.pgAddress }</li>
				<li class="a_content">单位负责人： ${projectGroup.pgLeader }</li>
				<li class="a_content">负责人联系方式： ${projectGroup.pgLeaderPhone }</li>
				<li class="a_content">负责人邮箱： ${projectGroup.pgLeaderEmail }</li>
				<li class="a_content">单位简介：<br> 
				<textarea rows="8" id="pgBrief"
							style="width: 100%; height: 400px;" name="pgBrief" datatype="*" nullmsg="请输入承包单位简介">${projectGroup.pgBrief }</textarea>
				</li>
				<li>
					<button type="button" style="float:right;" class="btn btn-primary">保存</button>
				</li>
			</ul>
		</div>
	</div>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>