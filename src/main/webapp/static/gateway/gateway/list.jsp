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
	<div class="contain">
		<div class="listl left">
			<h2>栏目导航</h2>
			<ul>
			
			<c:forEach items="${typeList }" var="type">
				<li><a href="list/${type.atId }-1">${type.atName }</a></li>
				</c:forEach>
			</ul>
		</div>
		<div class="listr right">
			<h2>
				<span>您现在的位置: <a href="/">广安普法网</a>>><a href="/">司法行政</a>>><a
					href="/">基层工作</a></span>公司新闻
			</h2>
			<ul>
				<c:forEach items="${list.list }" var="article">
					<li><a href="/" target="_blank"><span> <fmt:formatDate
									value="${article.artDate }" pattern="yyyy-MM-dd" /></span>${article.artTitle }
							<b>[图]</b></a></li>
				</c:forEach>
			</ul>
			<div class="blank"></div>
			<div class="page">
					<a href="list/${typeId }-1">首页</a>
					<a href="list/${typeId }-${list.pageNum - 1 }">上一页</a>
					总   / ${list.pageNum } ${list.pages } 页
					<a href="list/${typeId }-${list.pageNum + 1 }">下一页</a>
					<a href="list/${typeId }-${list.pages }">末页</a>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>