<%--
  Created by IntelliJ IDEA.
  User: tianym
  Date: 2015/5/5
  Time: 7:20
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
	<title>PaiPai</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="shortcut icon" type="image/x-icon" href="static/image/sys/thumb.png"/>
  	<link rel="stylesheet" href="static/css/flow.css"/>
  	<link rel="stylesheet" href="static/css/reset.css"/>
  	<link rel="stylesheet" href="static/css/media_common.css"/>
	<link rel="stylesheet" href="static/css/footer_common.css"/>
	<script src="static/js/jquery-2.1.3.min.js" type="text/javascript"></script>
</head>
<body>
	<jsp:include page="top_common.jsp"/> 
	
	<div class="content">
		<div class="wrap">
			<div id="main" role="main">
			    <ul id="tiles">
			    <c:forEach items="${currentImages }" var="image">
			       <li onclick="location.href='image/${image.imageId}/detail';">
			       		<img src="${image.location }" width="200" height="200">
			        	<div class="post-info">
			        		<div class="post-basic-info">
				        		<h3><a href="#">${image.title }</a></h3>
				        		<span><a href="#"><label></label>${image.cataloge }</a></span>
				        		<p>${image.describe }</p>
			        		</div>
			        		<div class="post-info-rate-share">
			        			<div class="rateit">
			        				<span></span>
			        			</div>
			        			<div class="download">
			        				<a href="image/${image.imageId }/download"><span></span></a>
			        			</div>
			        			<div class="clear"></div>
			        		</div>
			        	</div>
			        </li>
			     </c:forEach>
			     </ul>
			  </div>
		</div>
	</div>
	
	<jsp:include page="upload_common.jsp"></jsp:include>
	
	<div class="footer showbtn">
		<p>上传 <a href="javascript:void(0);">图片</a></p>
	</div>
	
	<script src="static/js/jquery.imagesloaded.js"></script>
	<script src="static/js/jquery.wookmark.js"></script>
	<script type="text/javascript">
		$(function() {
			var $tiles = $('#tiles'),
		    $handler = $('li', $tiles),
		    $main = $('#main'),
		    $window = $(window),
		    $document = $(document),
		    options = {
		      autoResize: true, 
		      container: $main, 
		      offset: 20, 
		      itemWidth:280 
		    };
		    function applyLayout() {
		      $tiles.imagesLoaded(function() {
		        if ($handler.wookmarkInstance) {
		          $handler.wookmarkInstance.clear();
		        }
		        $handler = $('li', $tiles);
		        $handler.wookmark(options);
		      });
		    }
		    function onScroll() {
		      var winHeight = window.innerHeight ? window.innerHeight : $window.height(),
		          closeToBottom = ($window.scrollTop() + winHeight > $document.height() - 100);
		      if (closeToBottom) {
		          var $items = $('li', $tiles),
		              $firstTen = $items.slice(0, 10);
		          $tiles.append($firstTen.clone());
		
		          applyLayout();
		        }
		    };
		    applyLayout();
		    $window.bind('scroll.wookmark', onScroll);
		}); 
	</script>
	
</body>
</html>

