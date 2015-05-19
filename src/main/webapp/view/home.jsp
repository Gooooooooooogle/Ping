<%--
  Created by IntelliJ IDEA.
  User: ex
  Date: 2015/5/18
  Time: 22:46
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
  <link rel="stylesheet" href="static/css/bootstrap.css"/>
  <script type="text/javascript" src="static/js/jquery-2.1.3.min.js"></script>
  <script type="text/javascript" src="static/js/bootstrap.js"></script>
  <title>${homeAccount.username }的主页</title>
  <style type="text/css">
    .wrap {
      margin-top:60px;
      margin-left:199px;
    }
    ._sidebar {
      width:250px;
      height:600px;
      border:1px solid gray;
      float:left;
    }
    ._thumb {
      width:20px;
      height:20px;
      padding:30px 0 30px 77px;
    }
    ._user {
      padding:70px 0 30px 20px;
    }
    ._user span {
      padding-right:10px;
    }
    ._content {
      margin-left:30px;
      width:690px;
      height:900px;
      border:1px solid gray;
      /* float:left; */
      display: inline-block;
    }
    ._topcontent {
      margin:30px 0 30px 35px;
    }
    ._topcontent button {
      margin-left:15px;
    }
    ._main {
      margin-left:25px;
      border-top:1px solid gray;
    }
    ._main _mainText {
      margin-left:15px;
    }
    ._imagecontent {
      margin-top:18px;
    }
    ._imageround {
      width:150px;
      height:180px;
      margin-right:15px;
      margin-bottom:25px;
      border:1px solid gray;

    }
    ._imageround span {
      margin-top:5px;
    }
    ._imageround p {
      font-size: 1em;
      margin-top:5px;
      margin-bottom:5px;
    }
    ._pager {
      text-align: center;
      clear:both;
    }
    ._user {
      width: 500px;
    }
    ._user div {
      margin-top: 15px;
    }
  </style>
</head>
<body>

  <jsp:include page="bar_common.jsp"/>

  <div class="wrap">
    <div class="_sidebar">
      <div style="height:40px;background-color:#f9f9f9;">
        <div style="float:left;padding:10px 0 0 13px;">
          <span class="glyphicon glyphicon-cog" aria-hidden="true" data-toggle="tooltip" data-placement="top" title="设置"></span>
        </div>
        <div style="float:right;padding:10px 13px 0 0;">
          <span class="glyphicon glyphicon-edit" aria-hidden="true" data-toggle="tooltip" data-placement="right" title="编辑资料"></span>
        </div>
      </div>
      <div class="_thumb">
        <img src="${homeAccount.thumb }" alt="ffff" class="img-rounded"/>
      </div>
      <div class="_user">
        <span class="glyphicon glyphicon-user" aria-hidden="true"> ${homeAccount.username }</span>
        <span class="glyphicon glyphicon-map-marker" aria-hidden="true"> 在线</span>
        <span class="glyphicon glyphicon-sound-7-1" aria-hidden="true"> ${homeAccount.sex }</span>
        <div style="margin-top:10px;margin-bottom:10px;">
          <span class="glyphicon glyphicon-check" aria-hidden="true"> 邮箱</span>
            ${homeAccount.email }
        </div>
        <div>
          粉丝 <span class="badge">${homeAccount.fans }</span>
        </div>
      </div>
      <div>
        <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
          <div class="panel panel-default">
            <div class="panel-heading" role="tab" id="headingOne">
              <h4 class="panel-title">
                <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                  签名
                </a>
              </h4>
            </div>
            <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
              <div class="panel-body">
                ${homeAccount.introduction }
              </div>
            </div>
          </div>
          <div class="panel panel-default">
            <div class="panel-heading" role="tab" id="headingTwo">
              <h4 class="panel-title">
                <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                  访客
                </a>
              </h4>
            </div>
            <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
              <div class="panel-body">
                <c:forEach var="guest" items="${visitedGuest }">
                  <div class="media">
                    <div class="media-left media-middle">
                      <img class="media-object" src="${guest.thumb }" style="width:30px;height:30px;">
                    </div>
                    <div class="media-body">
                      <h4 class="media-heading">${guest.username }</h4>
                        ${guest.lastVisitedTime }
                    </div>
                  </div>
                </c:forEach>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="_content">
      <div class="_topcontent">
        <button type="button" class="btn btn-default btn-lg" id="userCenter">
          <span class="glyphicon glyphicon-th-list" aria-hidden="true"></span> 用户中心
        </button>
        <button type="button" class="btn btn-default btn-lg" id="allImage">
          <span class="glyphicon glyphicon-picture" aria-hidden="true"></span> 所有图片
        </button>
        <button type="button" class="btn btn-default btn-lg" id="collectImage">
          <span class="glyphicon glyphicon-pushpin" aria-hidden="true"></span> 收藏图片
        </button>
        <button type="button" class="btn btn-default btn-lg" id="dynamicCenter">
          <span class="glyphicon glyphicon-star" aria-hidden="true"></span> 动态中心
        </button>
      </div>
      <div class="_main">
        <div class="media" id="_dynamicCenter">
          <c:forEach var="dynamicImage" items="${dynamicImages }">
            <div>
              <div class="media-left media-middle _mainText">
                <img class="media-object" src="${dynamicImage.location }" alt="${dynamicImage.title }" style="width:70px;height:70px;">
                <button type="button" class="btn btn-default btn-lg" style="border:none;margin:8px 0 0 480px;height:90px;">
                  <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> 详情
                </button>
              </div>
              <div class="media-body" style="float:left;margin-bottom: 20px;">
                <h4 class="media-heading">${dynamicImage.title }</h4>
                <p>${dynamicImage.describe }</p>
                <span style="margin-right:300px;">上传于 ${dynamicImage.uploadTime }</span>
                <button type="button" class="btn btn-default" data-toggle="button" aria-pressed="false" autocomplete="off" style="border:none">
                  收藏 <span class="badge">${dynamicImage.collectCount }</span>
                </button>
                <button type="button" class="btn btn-default" aria-label="Left Align" style="border:none">
                  <span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
                  <span class="badge">${dynamicImage.like }</span>
                </button>
                <button type="button" class="btn btn-default" aria-label="Left Align" style="border:none">
                                      <span class="glyphicon glyphicon-share-alt" aria-hidden="true">
                                      </span> <span class="badge">${dynamicImage.share }</span>
                </button>
              </div>
            </div>
          </c:forEach>
        </div>

        <div style="display: none" id="_collectImage">

        </div>

        <div style="display: none;" id="_allImage">
          <div class="_imagecontent">
            <c:forEach var="allImage" items="${allImages }">
              <div class="_imageround" style="float:left;padding-left:35px;padding-top:15px;">
                <img src="${allImage.location }" alt="ttt" class="img-rounded">
                <span class="glyphicon glyphicon-tags" aria-hidden="true"> ${allImage.cataloge }</span>
                <p>${allImage.title }</p>
                <a href="image/${allImage.imageId }/detail" class="btn btn-default" role="button" style="margin-left:58px;">详情</a>
              </div>
            </c:forEach>
          </div>
          <div class="_pager">
            <nav>
              <ul class="pagination pagination-lg">
                <li>
                  <a href="#" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                  </a>
                </li>
                <li><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
                <li>
                  <a href="#" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                  </a>
                </li>
              </ul>
            </nav>
          </div>
        </div>
        <div class="_user" style="display: none" id="_userCenter">
          <h4>修改用户信息：</h4>
          <div class="input-group">
            <span class="input-group-addon" id="basic-addon1">昵称</span>
            <input type="text" class="form-control" placeholder="用户名" aria-describedby="basic-addon1">
          </div>
          <div class="form-group">
            <label for="thumbImage">选择图片</label>
            <input type="file" id="thumbImage">
            <p class="help-block">请选择要上传的图片作为头像</p>
          </div>
          <div class="input-group">
            <span class="input-group-addon" id="_email">邮箱</span>
            <input type="text" class="form-control" placeholder="邮箱" aria-describedby="basic-addon1">
          </div>
          <div class="input-group">
            <span class="input-group-addon" id="_age">年龄</span>
            <input type="text" class="form-control" placeholder="年龄" aria-describedby="basic-addon1">
          </div>
          <div class="input-group">
            <span class="input-group-addon" id="_sex">性别</span>
            <input type="text" class="form-control" placeholder="性别" aria-describedby="basic-addon1">
          </div>
          <div class="input-group">
            <span class="input-group-addon" id="_describe">签名</span>
            <input type="text" class="form-control" placeholder="个人简介" aria-describedby="basic-addon1">
          </div>
          <button type="button" id="saveEdit" data-loading-text="保存中..." class="btn btn-primary" autocomplete="off"
                style="margin-top:20px;" onclick="saveEdit()">
            保存修改
          </button>
        </div>
      </div>
    </div>
  </div>

  <jsp:include page="footer_common.jsp"/>

  <script type="text/javascript">
    $(function () {
      $('[data-toggle="tooltip"]').tooltip();

      function saveEdit() {
        ////logic....
      }
    });
    $('#saveEdit').on('click', function () {
      var $btn = $(this).button('loading')
      // business logic...
      $btn.button('reset')
    });
    $('#userCenter').on('click', function () {
      $('#_collectImage').css('display','none');
      $('#_allImage').css('display','none');
      $('#_dynamicCenter').css('display','none');
      $("#_userCenter").css('display','block');
    });
    $('#allImage').on('click', function () {
      $('#_collectImage').css('display','none');
      $('#_dynamicCenter').css('display','none');
      $("#_userCenter").css('display','none');
      $('#_allImage').css('display','block');
    });
    $('#dynamicCenter').on('click', function () {
      $('#_collectImage').css('display','none');
      $("#_userCenter").css('display','none');
      $('#_allImage').css('display','none');
      $('#_dynamicCenter').css('display','block');
    });
    $('#collectImage').on('click', function () {
      $("#_userCenter").css('display','none');
      $('#_allImage').css('display','none');
      $('#_dynamicCenter').css('display','none');
      $('#_collectImage').css('display','block');
    });
  </script>

</body>
