<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!doctype html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="${pageContext.request.contextPath}/boot/js/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/boot/js/bootstrap.3.3.7.min.js"></script>
    <script src="${pageContext.request.contextPath}/jqgrid/js/i18n/grid.locale-cn.js"></script>
    <script src="${pageContext.request.contextPath}/jqgrid/extend/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="${pageContext.request.contextPath}/boot/js/ajaxfileupload.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/boot/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jqgrid/extend/css/trirand/ui.jqgrid-bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jqgrid/css/css/cupertino/jquery-ui-1.8.16.custom.css">

    <title>持明法洲后台管理系统</title>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="top.jsp"></jsp:include>
</div>
<%--主体部分--%>
<div class="container-fluid">
    <%--左栏手风琴--%>
    <div class="col-sm-2">
        <jsp:include page="left.jsp"></jsp:include>
    </div>
    <%--右栏展示页面--%>
    <div class="col-sm-10" id="rightlist">
        <jsp:include page="body.jsp"></jsp:include>
    </div>
</div>
<div class="container-fluid" align="center">
    <jsp:include page="foot.jsp"></jsp:include>
</div>
</body>
</html>