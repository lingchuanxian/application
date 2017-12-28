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
<link rel="stylesheet" href="static/gateway/css/slide.css">
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
	<div class="banner box">
		<img src="static/gateway/images/banner.png">
	</div>
	<div class="box">
		<div class="news left">
			<h2>
				<span class="more"><a href="list/${xwzxId }" target="_blank">更多..</a></span>新闻中心
			</h2>
			<ul>
				<li>
					<div class="pic_news left">
						<div class="ck-slide">
							<ul class="ck-slide-wrapper">
								<li><a href="javascript:"><img
										src="static/gateway/images/newspic.jpg" alt=""></a></li>
								<li style="display: none"><a href="javascript:"><img
										src="images/2.jpg" alt=""></a></li>
								<li style="display: none"><a href="javascript:"><img
										src="images/3.jpg" alt=""></a></li>
								<li style="display: none"><a href="javascript:"><img
										src="images/4.jpg" alt=""></a></li>
								<li style="display: none"><a href="javascript:"><img
										src="images/5.jpg" alt=""></a></li>
							</ul>
							<a href="javascript:;" class="ctrl-slide ck-prev">上一张</a> <a
								href="javascript:;" class="ctrl-slide ck-next">下一张</a>
							<div class="ck-slidebox">
								<div class="slideWrap">
									<ul class="dot-wrap">
										<li class="current"><em>1</em></li>
										<li><em>2</em></li>
										<li><em>3</em></li>
										<li><em>4</em></li>
										<li><em>5</em></li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<div class="center_news right">
						<c:if test="${fn:length(xwzxList) > 0}">
							<section class="c_n_top">
								<h3>${xwzxList[0].artTitle }</h3>
								<p>
									<c:choose>
										<c:when test="${fn:length(xwzxList[0].artContent ) > 60}">
											<c:out
												value="${fn:substring(xwzxList[0].artContent, 0, 60)}..." />
										</c:when>
										<c:otherwise>
											<c:out value="${xwzxList[0].artContent }" />
										</c:otherwise>
									</c:choose>
									[<a href="/" target="_blank">详情</a>]
								</p>
							</section>
						</c:if>
						<ul>
							<c:forEach items="${xwzxList }" var="article">
								<li><a href="/" target="_blank">${article.artTitle }</a></li>
							</c:forEach>
						</ul>
					</div>
				</li>
			</ul>
		</div>
		<div class="announce right">
			<h2>
				<a href="/">通知公告</a>
			</h2>
			<ul>
				<marquee onmouseover="this.stop()" onmouseout="this.start()"
					scrollamount="2" direction="up" height="240">
					
					<c:forEach items="${tzggList }" var="article">
								<li><a href="/" target="_blank">${article.artTitle }</a></li>
							</c:forEach>
				</marquee>
			</ul>
		</div>
		<div class="blank"></div>
		<div class="ad left">
			<img src="static/gateway/images/ad01.png">
		</div>
		<div class="ad right">
			<img src="static/gateway/images/ad02.png">
		</div>
		<div class="blank"></div>
		<div class="linews left">
			<h3>
				<span><a href="/" class="more">更多..</a></span>医政中医
			</h3>
			<ul>
				<li><a href="/" target="_blank">广安区喜摘全市人口计生统计与信息岗位技能竞赛桂冠开业贸易争端
						矛头直指</a></li>
				<li><a href="/" target="_blank">广安区卫计局开展“道德讲堂”活动</a></li>
				<li><a href="/" target="_blank">广安区中医院开展“全国高血压日”义诊宣传活动</a></li>
				<li><a href="/" target="_blank">广安区“五心”做好孕前优生健康检查工作</a></li>
				<li><a href="/" target="_blank">广安区实施“五项工程”帮助计生家庭发家致富</a></li>
				<li><a href="/" target="_blank">美国挑起对华贸易争端 矛头直指</a></li>
			</ul>
		</div>
		<div class="linews left ln">
			<h3>
				<span><a href="/" class="more">更多..</a></span>公共卫生
			</h3>
			<ul>
				<li><a href="/" target="_blank">广安区喜摘全市人口计生统计与信息岗位技能竞赛桂冠</a></li>
				<li><a href="/" target="_blank">广安区卫计局开展“道德讲堂”活动</a></li>
				<li><a href="/" target="_blank">广安区中医院开展“全国高血压日”义诊宣传活动</a></li>
				<li><a href="/" target="_blank">广安区“五心”做好孕前优生健康检查工作</a></li>
				<li><a href="/" target="_blank">广安区实施“五项工程”帮助计生家庭发家致富</a></li>
				<li><a href="/" target="_blank">美国挑起对华贸易争端 矛头直指</a></li>
			</ul>
		</div>
		<div class="linews right">
			<h3>
				<span><a href="/" class="more">更多..</a></span>疾病控制
			</h3>
			<ul>
				<li><a href="/" target="_blank">广安区喜摘全市人口计生统计与信息岗位技能竞赛桂冠</a></li>
				<li><a href="/" target="_blank">广安区卫计局开展“道德讲堂”活动</a></li>
				<li><a href="/" target="_blank">广安区中医院开展“全国高血压日”义诊宣传活动</a></li>
				<li><a href="/" target="_blank">广安区“五心”做好孕前优生健康检查工作</a></li>
				<li><a href="/" target="_blank">广安区实施“五项工程”帮助计生家庭发家致富</a></li>
				<li><a href="/" target="_blank">美国挑起对华贸易争端 矛头直指</a></li>
			</ul>
		</div>
		<div class="blank"></div>
		<div class="linews left">
			<h3>
				<span><a href="/" class="more">更多..</a></span>科教爱卫
			</h3>
			<ul>
				<li><a href="/" target="_blank">广安区喜摘全市人口计生统计与信息岗位技能竞赛桂冠</a></li>
				<li><a href="/" target="_blank">广安区卫计局开展“道德讲堂”活动</a></li>
				<li><a href="/" target="_blank">广安区中医院开展“全国高血压日”义诊宣传活动</a></li>
				<li><a href="/" target="_blank">广安区“五心”做好孕前优生健康检查工作</a></li>
				<li><a href="/" target="_blank">广安区实施“五项工程”帮助计生家庭发家致富</a></li>
				<li><a href="/" target="_blank">美国挑起对华贸易争端 矛头直指</a></li>
			</ul>
		</div>
		<div class="linews left ln">
			<h3>
				<span><a href="/" class="more">更多..</a></span>计划生育
			</h3>
			<ul>
				<li><a href="/" target="_blank">广安区喜摘全市人口计生统计与信息岗位技能竞赛桂冠</a></li>
				<li><a href="/" target="_blank">广安区卫计局开展“道德讲堂”活动</a></li>
				<li><a href="/" target="_blank">广安区中医院开展“全国高血压日”义诊宣传活动</a></li>
				<li><a href="/" target="_blank">广安区“五心”做好孕前优生健康检查工作</a></li>
				<li><a href="/" target="_blank">广安区实施“五项工程”帮助计生家庭发家致富</a></li>
				<li><a href="/" target="_blank">美国挑起对华贸易争端 矛头直指</a></li>
			</ul>
		</div>
		<div class="linews right">
			<h3>
				<span><a href="/" class="more">更多..</a></span>新农合工作
			</h3>
			<ul>
				<li><a href="/" target="_blank">广安区喜摘全市人口计生统计与信息岗位技能竞赛桂冠</a></li>
				<li><a href="/" target="_blank">广安区卫计局开展“道德讲堂”活动</a></li>
				<li><a href="/" target="_blank">广安区中医院开展“全国高血压日”义诊宣传活动</a></li>
				<li><a href="/" target="_blank">广安区“五心”做好孕前优生健康检查工作</a></li>
				<li><a href="/" target="_blank">广安区实施“五项工程”帮助计生家庭发家致富</a></li>
				<li><a href="/" target="_blank">美国挑起对华贸易争端 矛头直指</a></li>
			</ul>
		</div>
		<div class="blank"></div>
		<div class="ad">
			<img src="images/ad03.jpg">
		</div>
		<div class="blank"></div>
		<div class="zhishu left">
			<h3>
				直属单位
				<ul id="tab">
					<li class="current"><a href="/">人民医院（区中医院)</a></li>
					<li><a href="/">疾控中心</a></li>
					<li><a href="/">保健院</a></li>
					<li><a href="/">卫校</a></li>
					<li><a href="/">执法大队</a></li>
					<li><a href="/">新合办</a></li>
					<li><a href="/">健教所</a></li>
					<li><a href="/">计生指导中心</a></li>
				</ul>
			</h3>
			<section>
				<div id="content">
					<ul style="display: block;">
						<div class="zs_pic left">
							<img src="images/newspic1.jpg">
						</div>
						<div class="zs_news right">
							<ol>
								<li><a href="/" target="_blank"><span>2013-12-24</span>广安区喜摘全市人口计生统计与信息岗位技能竞赛桂冠</a></li>
								<li><a href="/" target="_blank"><span>2013-12-24</span>广安区卫计局开展“道德讲堂”活动</a></li>
								<li><a href="/" target="_blank"><span>2013-12-24</span>广安区中医院开展“全国高血压日”义诊宣传活动</a></li>
								<li><a href="/" target="_blank"><span>2013-12-24</span>广安区“五心”做好孕前优生健康检查工作</a></li>
								<li><a href="/" target="_blank"><span>2013-12-24</span>广安区实施“五项工程”帮助计生家庭发家致富</a></li>
								<li><a href="/" target="_blank"><span>2013-12-24</span>美国挑起对华贸易争端
										矛头直指</a></li>
								<li><a href="/" target="_blank"><span>2013-12-24</span>广安区中医院开展“全国高血压日”义诊宣传活动</a></li>
							</ol>
						</div>
					</ul>
					<ul>
						<div class="zs_pic left">
							<img src="images/newspic1.jpg">
						</div>
						<div class="zs_news right">
							<ol>
								<li><a href="/" target="_blank"><span>2013-12-24</span>广安区中医院开展“全国高血压日”义诊宣传活动</a></li>
								<li><a href="/" target="_blank"><span>2013-12-24</span>广安区“五心”做好孕前优生健康检查工作</a></li>
								<li><a href="/" target="_blank"><span>2013-12-24</span>广安区实施“五项工程”帮助计生家庭发家致富</a></li>
								<li><a href="/" target="_blank"><span>2013-12-24</span>广安区喜摘全市人口计生统计与信息岗位技能竞赛桂冠</a></li>
								<li><a href="/" target="_blank"><span>2013-12-24</span>广安区卫计局开展“道德讲堂”活动</a></li>
								<li><a href="/" target="_blank"><span>2013-12-24</span>美国挑起对华贸易争端
										矛头直指</a></li>
								<li><a href="/" target="_blank"><span>2013-12-24</span>广安区中医院开展“全国高血压日”义诊宣传活动</a></li>
							</ol>
						</div>
					</ul>
				</div>
			</section>
		</div>
		<div class="hd right">
			<h3>互动交流</h3>
			<ul>
				<li><a href="/">投诉举报</a></li>
				<li><a href="/">局长信箱</a></li>
			</ul>
		</div>
		<div class="blank"></div>
		<div class="links">
			<p>相关链接:</p>
			<ul>
				<li><a href="/">四川机构网</a></li>
				<li><a href="/">岳池县人口和计划生育局</a></li>
				<li><a href="/">广安市人民政府</a></li>
				<li><a href="/">国家计生委</a></li>
				<li><a href="/">四川省人口和计划生育委员会</a></li>
			</ul>
			<ul style="display: none">
				<a href="/"><img src="images/ad01.jpg"></a>
				<a href="/"><img src="images/ad02.png"></a>
			</ul>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
	<!-- js -->
	<script src="static/gateway/js/jquery-1.8.3.min.js"></script>
	<script src="static/gateway/js/slide.js"></script>
	<script>
		$('.ck-slide').ckSlide({
			autoPlay : true,//默认为不自动播放，需要时请以此设置
			dir : 'x',//默认效果淡隐淡出，x为水平移动，y 为垂直滚动
			interval : 3000
		//默认间隔2000毫秒

		});
	</script>
</body>
</html>