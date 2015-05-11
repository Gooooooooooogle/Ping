<%--
  Created by IntelliJ IDEA.
  User: tianym
  Date: 2015/5/5
  Time: 21:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
	<base href="<%=basePath%>">  
	<link rel="stylesheet" href="static/css/uploadify.css">  
	<script src="static/js/jquery-2.1.3.min.js"></script>
	<script src="static/js/jquery.uploadify.min.js"></script>
	<script type="text/javascript"></script>
	<style type="text/css">
	.outbox{
		left:50%;
		height:auto;
		z-index:100;
		background-color:#fff;
		border:2px #ddd solid;
		padding:3px;
		margin:15em auto 0;
		width: 45%;
		text-align: center;
	}
	.boxclose{
		padding-left:550px;
		padding-top:10px;
	}
	.boxclose span{
		width:35px;
		height:35px;
		display:inline-block;
		background:url(static/image/sys/close.png) no-repeat right top;
	}
	.boxclose span:hover{
		background:url(static/image/sys/close_h.png) no-repeat right top;
	}
	#bg{
		background-color:#666666;position:absolute;z-index:99;left:0;top:0;
		display:none;width:100%;height:100%;opacity:0.5;filter: alpha(opacity=50);-moz-opacity: 0.5;
	}
	.contentbox{
		padding-right:45px;
		padding-top:10px;
	}
	.btnupload{
		background-color: #1a5ca6;
		color:#fff;
		width: 110px;
		height:40px;
		padding-top:15px;
		padding-bottom:10px;
	}
	.titlebox{
		width: 550px;
		heigth:50px;
		padding-bottom : 10px;
	}
	.imagebox{
		width: 550px;
		heigth:50px;
		padding-bottom : 12px;
	}
	</style>
</head>
<body>

	<div id="bg"></div>
	<div class="box outbox" style="display:none;">
		<div class="boxclose">
			<span></span>
		</div>
		<div class="contentbox">
			<div class="titlebox">
				<label for="title">标题</label>
				<input type="text" placeholder="图片标题" id="title">
			</div>
			<div class="titlebox">
				<label for="description">描述</label>
				<textarea class="form-control" rows="3" placeholder="图片描述" id="description"></textarea>
			</div>
  			<div class="titlebox">
  				<label for="cataloge">请选择图片所属的分类</label>
  				<select id="cataloge"> 
					<c:forEach var="cataloge" items="${cataloges }">
						<option>${cataloge.catalogeName }</option>
					</c:forEach>
				</select>
				<input type="hidden" id="currentUsername" value="${currentAccount.username }"/>  
				<input type="hidden" id="sessionId" value="${pageContext.session.id}"/>  
  			</div>	
  		<div class="imagebox">
  			<input type="file" id="exampleInputFile">
  			<label for="exampleInputFile">请选择所要上传的图片</label>
  		</div>
  		<div class="imagebox">
  			<label for="autoDelete"></label>
  			<input type="checkbox" value="1" id="autoDelete"> 阅后即焚？
  		</div>
  		<div>
      		<button type="button" class="btnupload" onclick="$('#exampleInputFile').uploadify('upload','*')">开始上传</button>
      		<button type="button" class="btnupload" onclick="$('#exampleInputFile').uploadify('cancel','*')">取消上传</button>
      		<button type="button" class="btnupload" onclick="$('#exampleInputFile').uploadify('stop','*')" hidden=true id="stopUpload">停止上传</button>
  		</div>
  		</div>
	</div>	

	<script type="text/javascript">
	$(function() {
		$(".showbtn").click(function () {
		    $("#bg").css({
		        display: "block", height: $(document).height()
		    });
		    var $outbox = $('.outbox');
		    $outbox.css({
		        //设置弹出层距离左边的位置
		        left: ($(window).height() - $outbox.width()) / 2 - 20 + "px",
		        //设置弹出层距离上面的位置
		        top: ($(window).height() - $outbox.height()) / 18 + $(window).scrollTop() +"px",
		        display: "block"
		    });
		});
		//点击关闭按钮的时候，遮罩层关闭
		$(".boxclose").click(function () {
		    $("#bg,.outbox").css("display", "none");
		});
		
		$("#exampleInputFile").uploadify({
			'uploader' : 'image/upload',
			'height' : 27,
			'width' : 50,
			'buttonText' : '浏 览',
			'buttonCursor' : 'hand',
			'auto' : false, 
			'multi' : true,
			'method' : 'post',
			'swf' : 'static/js/uploadify.swf',
			'cancelImg' : 'static/image/sys/close.jpg',
			'fileTypeDesc' : 'jpg、png、gif、bmp',
			'fileTypeExts' : '*.jpg;*.png;*.gif;*.bmp;*.doc;*.txt',
			'fileSizeLimit' : '50MB',
			'fileObjName' : 'imageFile',
			'progressData' : 'all', // 'percentage''speed''all'//队列中显示文件上传进度的方式：all-上传速度+百分比，percentage-百分比，speed-上传速度
			'preventCaching' : true,//若设置为true，一个随机数将被加载swf文件URL的后面，防止浏览器缓存。默认值为true
			'timeoutuploadLimit' : 5,//能同时上传的文件数目
			'removeCompleted' : true,//默认是True，即上传完成后就看不到上传文件进度条了。
			'removeTimeout' : 5,//上传完成后多久删除队列中的进度条，默认为3，即3秒。
			'requeueErrors' : true,//若设置为True，那么在上传过程中因为出错导致上传失败的文件将被重新加入队列。
			'successTimeout' : 30,//表示文件上传完成后等待服务器响应的时间。不超过该时间，那么将认为上传成功。默认是30，表示30秒。
			'uploadLimit' : 999,
			'onUploadStart' : function(file) {
				//$("#file_upload").uploadify("settings", "formData", {'userName':name,'qq':qq});  
				//$("#file_upload").uploadify("settings", "buttonText", "aaa");
				//alert(file.name + " is ready to go!")
				var username = $('#currentUsername').val();
				var title = $('#title').val();
				var description = $('#description').val();
				var autoDelete = $('#autoDelete').val();
				var cataloge = $("#cataloge  option:selected").text();
				
	            if(title.replace(/\s/g,'') == ''){  
	                  alert("名称不能为空！");  
	                  return false;  
	            }    
	            $('#exampleInputFile').uploadify("settings", "formData", {
	            		'username' : $('currentUsername').text,
	            		'jsessionid' : $("#sessionId").val(),
	            		'title':$('#title').val(),
	            		'describe' : $('#description').val(),
	    				'cataloge' : $("#cataloge").find("option:selected").text(),
	    				'autoDelete' : document.getElementById("autoDelete").checked,
	    				'autoDeleteTime' : ''
	            	});  
	            //上传  
	            //$('#exampleInputFile').uploadify('upload','*');
				$("#stopUpload").removeAttr("hidden");
			},
			'onUploadSuccess' : function(file, data, response) {
				//alert(file.name + " upload success !");
				//alert(data + "----" + response);
				$("#stopUpload").attr("hidden",true);
			}
	
		}); 
	}); 
	</script>
	
</body>	