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
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<article>
<div class="info_text">
    <h2><span>您现在的位置: <a href="group/list/1">承包单位</a>>><a href="/">正文</a></span>承包单位</h2>
    <ul>
      <li>
      	<p class="a_title">${projectGroup.pgName }</p>
      </li>
       <li>
       <p class="box_p"><span>发布时间：<fmt:formatDate value="${project.prAdddate }" pattern="yyyy-MM-dd HH:mm:ss"/></span>
       </p>
       </li>
      <li class="a_content">单位规模： ${projectGroup.pgScale }</li>
      <li class="a_content">单位地址： ${projectGroup.pgAddress }</li>
      <li class="a_content">单位负责人： ${projectGroup.pgLeader }</li>
      <li class="a_content">负责人联系方式： ${projectGroup.pgLeaderPhone }</li>
      <li class="a_content">负责人邮箱： ${projectGroup.pgLeaderEmail }</li>
      <li class="a_content">单位简介：<br>
       ${projectGroup.pgBrief }
      </li>
    </ul>

  </div>
  
</article>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>