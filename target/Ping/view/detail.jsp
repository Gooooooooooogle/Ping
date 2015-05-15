<%--
  Created by IntelliJ IDEA.
  User: tianym
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
	<link rel="stylesheet" href="static/css/detail.css" /> 
	<script src="static/js/jquery-2.1.3.min.js"></script>
    <script type="text/javascript">
		var $ = jQuery.noConflict();
		$(function() {
			$('#activator').click(function(){
				$('#box').animate({'top':'0px'},500);
			});
			$('#boxclose').click(function(){
				$('#box').animate({'top':'-700px'},500);
			});
		});
		$(document).ready(function(){
		$(".toggle_container").hide(); 
		$(".trigger").click(function(){
			$(this).toggleClass("active").next().slideToggle("slow");
				return false; 
		});
									
	});
	</script>
    <title>详情页</title>
</head>
<body>
	<jsp:include page="top_common.jsp"/>
	
    <div class="content">
    	<div class="wrap">
        	<div class="single-page">
          		<div class="single-page-artical">
            		<div class="artical-content">
              			<img src="${currentImage.location }" title="${currentImage.title }">
              			<h3><a href="#">${currentImage.title }</a></h3>
              			<p>${currentImage.describe }</p>
            		</div>
            		<div class="share-artical">
              			<ul>
                			<li><a href="#"><img src="static/image/sys/facebook.png" title="facebook">Facebook</a></li>
              			</ul>
            		</div>
            		<div class="clear"></div>
          		</div>
          		<div class="comment-section">
					<div class="ds-thread" data-thread-key="Ping" data-title="${currentImage.title }" data-url="${currentImage.location }"></div>
            	</div>
        	</div>
      	</div>
    </div>

</body>
</html>
