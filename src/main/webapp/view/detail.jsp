<%--
  Created by IntelliJ IDEA.
  User: ex
  Date: 2015/5/10
  Time: 23:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+request.getServerName()+":" + request.getServerPort() + path + "/";
%>
<html>
<head>
	<base href="<%=basePath%>">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="shortcut icon" type="image/x-icon" href="static/image/sys/thumb.png" />
	<title>详情页</title>
	<style type="text/css">
		body{
			font-family: 'Open Sans', sans-serif;
			background:#F0EFEE;
		}
		.wrap{
			width:70%;
			margin:0 auto;
		}
		.artical-content h3 a{
			font: 400 28px/28px 'Open Sans', sans-serif;
			color: #626262;
			text-align: left;
			font-weight: 400;
			padding: 0.9em 0 0;
			display: block;
		}
		.artical-content p{
			font: normal 16px/26px 'Open Sans', sans-serif,Helvetica,sans-serif;
			color: #9b9b9b;
			padding: 17px 0px;
			font-weight: 400;
		}
		.artical-content img{
			width:100%;
		}
	</style>
</head>
<body>
<jsp:include page="bar_common.jsp"/>

<div class="content">
	<div class="wrap">
		<div class="single-page">
			<div class="single-page-artical">
				<div class="artical-content">
					<img src="${currentImage.location }" title="${currentImage.title }">
					<h3><a href="#">${currentImage.title }</a></h3>
					<p>${currentImage.describe }</p>
				</div>
			</div>
			<div class="ds-thread" data-thread-key="Ping" data-title="请替换成文章的标题" data-url="请替换成文章的网址"></div>
		</div>
	</div>
</div>

<script type="text/javascript">
	var duoshuoQuery = {short_name:"tianexping"};
	(function() {
		var ds = document.createElement('script');
		ds.type = 'text/javascript';ds.async = true;
		ds.src = (document.location.protocol == 'https:' ? 'https:' : 'http:') + '//static.duoshuo.com/embed.js';
		ds.charset = 'UTF-8';
		(document.getElementsByTagName('head')[0]
		|| document.getElementsByTagName('body')[0]).appendChild(ds);
	})();
</script>

</body>
</html>
