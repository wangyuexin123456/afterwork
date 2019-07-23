
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<div class="panel-group" id="group1">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title" >
                <a data-toggle="collapse" data-parent="#group1" href="#collapse1">
                    轮播图
                </a>
            </h4>
        </div>
        <div class="panel-collapse collapse in" id="collapse1">
            <div class="panel-body list-group">
                <a class="list-group-item" href="javascript:$('#rightlist').load('${pageContext.request.contextPath}/jsp/carousel/carousel.jsp');">轮播图管理</a>
            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title"data-toggle="collapse" data-target="#collapse2"  data-parent="#group1">
                <a href="#">专辑</a>
            </h4>
        </div>
        <div class="panel-collapse collapse" id="collapse2">
            <div class="panel-body list-group">
                <a class="list-group-item" href="javascript:$('#rightlist').load('${pageContext.request.contextPath}/jsp/album/album.jsp');">专辑和章节管理</a>
            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title"data-toggle="collapse" data-target="#collapse3"  data-parent="#group1">
                <a href="#">文章</a>
            </h4>
        </div>
        <div class="panel-collapse collapse" id="collapse3">
            <div class="panel-body list-group">
                <a class="list-group-item" href="javascript:$('#rightlist').load('${pageContext.request.contextPath}/jsp/article/article.jsp');">文章管理</a>
            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title"data-toggle="collapse" data-target="#collapse4"  data-parent="#group1">
                <a href="#">用户</a>
            </h4>
        </div>
        <div class="panel-collapse collapse" id="collapse4">
            <div class="panel-body list-group">
                <a class="list-group-item" href="javascript:$('#rightlist').load('${pageContext.request.contextPath}/jsp/user/user.jsp');">用户管理</a>
            </div>
            <div class="panel-body list-group">
                <a class="list-group-item" href="javascript:$('#rightlist').load('${pageContext.request.contextPath}/user/login.jsp');">用户登录与注册</a>
            </div>
            <div class="panel-body list-group">
                <a class="list-group-item" href="javascript:$('#rightlist').load('${pageContext.request.contextPath}/jsp/user/echarts.jsp');">用户图示</a>
            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title"data-toggle="collapse" data-target="#collapse5"  data-parent="#group1">
                <a href="#">上师</a>
            </h4>
        </div>
        <div class="panel-collapse collapse" id="collapse5">
            <div class="panel-body list-group">
                <a class="list-group-item" href="javascript:$('#rightlist').load('${pageContext.request.contextPath}/jsp/guru/guru.jsp');">上师管理</a>
            </div>
        </div>
    </div>
</div>
