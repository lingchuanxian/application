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
<script type="text/javascript">
function sign(id){
	$.ajax({
		url:'loginGroup/ProjectSign',
		type:'post',
		dataType: 'json',
		data:{"pid":id},
		success:function(data){
			if(data.code== 200){
				alert("报名成功");
				 window.location.reload();
			}else{
				alert(data.message);
			}
		},
		error:function(e){
			alert(e.message);
		}
	});
}
</script>
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
    <h2><span>您现在的位置: <a href="project/list/${project.projectType.ptId }-1">${project.projectType.ptName }</a>>><a href="/">正文</a></span>工程公告</h2>
    <ul>
      <li>
      	<p class="a_title">${project.prName }</p>
      </li>
       <li>
       <p class="box_p"><span>发布时间：<fmt:formatDate value="${project.prAdddate }" pattern="yyyy-MM-dd HH:mm:ss"/></span>
       </p>
       </li>
      <li class="a_content">项目名称： ${project.prName }</li>
      <li class="a_content">项目进展： <font color="#FF0000">
		<c:if test="${project.prState == 0 }">报名未开始。</c:if>
		<c:if test="${project.prState == 1 }">报名中。（承包单位登录后即可报名）
			<c:if test="${LOGIN_GROUP !=null && project.prState == 1 }">
      	 		<c:if test="${result ==null }">
      	 			<button type="button" style="float:right" class="btn btn-danger btn-lg btn-block" onclick="sign('${project.prId }')">我要报名</button>
      	 		</c:if>
      	 		<c:if test="${result !=null }">
      	 			<button type="button" style="float:right" class="btn btn-danger btn-lg btn-block">已报名</button>
      	 		</c:if>
      		</c:if>
      	</c:if>
		<c:if test="${project.prState == 2 }">报名结束，待分配承包商。</c:if>
		<c:if test="${project.prState == 3 }">已分配承包商（${project.projectGroup.pgName }），待开工。</c:if>
		<c:if test="${project.prState == 4 }">正在建设中。</c:if>
		<c:if test="${project.prState == 5 }">完工，承包商已提交验收申请，待验收。</c:if>
		<c:if test="${project.prState == 6 }"> 验收中。</c:if>
		<c:if test="${project.prState == 7 }"> 验收通过。</c:if>
		<c:if test="${project.prState == 8 }"> 验收未通过，待返修。</c:if>
		<c:if test="${project.prState == 9 }"> 返修中。</c:if>
		<c:if test="${project.prState == 10 }"> 项目完成。</c:if>
		</font>
      </li>
      <li class="a_content">项目预算： ${project.prBudget }</li>
      <li class="a_content">项目预计周期： ${project.prExpectDate }</li>
      <li class="a_content">项目报名开始时间： <fmt:formatDate value="${project.prSignStartDate }" pattern="yyyy-MM-dd HH:mm:ss"/></li>
      <li class="a_content">项目报名截止时间： <fmt:formatDate value="${project.prSignEndDate }" pattern="yyyy-MM-dd HH:mm:ss"/></li>
      <li class="a_content">联系人： ${project.prContacts }</li>
      <li class="a_content">联系方式： ${project.prContactsPhone }</li>
      <li class="a_content">项目简介：<br>
       ${project.prDescription }
      </li>
    </ul>

  </div>
  
</article>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>