<%--
  Created by IntelliJ IDEA.
  User: tianym
  Date: 2015/5/11
  Time: 21:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%   
   	String path = request.getContextPath();   
    String basePath = request.getScheme() + "://"+request.getServerName()+":" + request.getServerPort() + path + "/";   
%>  
<html>
<head>
	<base href="<%=basePath%>">  
	<link rel="stylesheet" href="static/css/reset_common.css">
	<link rel="stylesheet" href="static/css/top_common.css">
	<script src="static/js/jquery-2.1.3.min.js" type="text/javascript"></script>
	<script type="text/javascript">
    	function $(elementid) {
        	return document.getElementById(elementid);
    	}
    	
    	function checkSearch(value) {
    		var xmlHttp;
    		try {
    			xmlHttp = new XMLHttpRequest();
    		} catch(e) {
    			try {
                    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");                        
                } catch (e) {
                    alert("您的浏览器不支持AJAX，请更新浏览器版本");
                }
    		}
    		xmlHttp.onreadystatechange = function() {
    			if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
    				var result = xmlHttp.responseText;
    				alert(result);
    			}
    			if(xmlHttp.readyState == 4 && xmlHttp.status == 200) {
    	            var result = xmlHttp.responseText;
    	            //alert(typeof(xmlHttp.responseText));
    	            //$("username").innerText = xmlHttp.responseText;
    	            var userinfo = eval("("+result+")");
    	           // alert(userinfo);
    	            
    	            $("username").innerText = userinfo.username;
    	            $("password").innerText = userinfo.password;
    	            $("age").innerText = userinfo.age;
    	            $("gender").innerText = userinfo.gender;
    	        } 
    		};
    		xmlHttp.open("post", "search/asy/value=" + value, true);
    		xmlHttp.setRequestHeader("Content-Type" , "application/x-www-form-urlencoded");
    		xmlHttp.send(null);
    	} 
    	addEventListener("load", function() { 
    		setTimeout(hideURLbar, 0); 
    	}, false); 
		function hideURLbar() { 
			window.scrollTo(0,1); 
		} 
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
</head>
<body>

	<div class="header">
		<div class="wrap">
			<div class="logo">
				<a href="index"><img src="static/image/sys/logo.png" title="拍怕网" /></a>
			</div>
			<div class="nav-icon">
				<a class="right_bt" id="activator"><span></span></a>
			</div>
			<div class="box" id="box">
				<div class="box_content">        					                         
					<div class="box_content_center">
						 <div class="form_content">
							<div class="menu_box_list">
								<ul>
									<li><a href="/"><span>首页</span></a></li>
									<li><a href="/recommend"><span>推荐</span></a></li>
									<li><a href="/find"><span>发现</span></a></li>
									<li><a href="/ppteam"><span>关于</span></a></li>
									<c:forEach var="menu" items="${menus }">
										<li><a href="${menu.resourceUrl }" target="content">${menu.resourceName }</a></li>
									</c:forEach>
								</ul>
								<div class="clear"></div>
							</div>
							<a class="boxclose" id="boxclose"> <span> </span></a>
						</div>                                  
					</div> 	
				</div> 
			</div>       	  
			<div class="top-searchbar">
				<form>
					<input type="text" onchange="checkSearch(this.value)"/><input type="submit" value=""/>
				</form>
			</div>
			<div class="userinfo">
				<div class="user">
					<ul>
					<c:choose>
						<c:when test="${currentAccount == null}">
							<li><a href="login"><img src="static/image/sys/user-pic.png" title="未登录" /><span></span></a></li>
						</c:when>
						<c:otherwise>
							<li><a href="logout"><img src="${currentAccount.thumb }" title="${currentAccount.username }" /><span>${currentAccount.username }</span></a></li>
						</c:otherwise>
					</c:choose>
					</ul>
				</div>
			</div>
			<div class="clear"></div>
		</div>
	</div>
	
</body>
</html>
	