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
    <h2><span>您现在的位置: <a href="article/list/${article.articleType.atId }-1">${article.articleType.atName }</a>>><a href="/">正文</a></span>${article.articleType.atName }</h2>
    <ul>
      <li>
      	<p class="a_title">${article.artTitle }</p>
      </li>
       <li>
       <p class="box_p"><span>发布时间：<fmt:formatDate value="${article.artDate }" pattern="yyyy-MM-dd"/></span><span>发布者：${article.admin.adName } </span><span>点击：${article.artTimes }</span></p>
       </li>
      <li class="a_content">
       ${article.artContent }
      </li>
    </ul>

  </div>
  
</article>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>