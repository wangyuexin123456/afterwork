<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!doctype html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/boot/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jqgrid/extend/css/trirand/ui.jqgrid-bootstrap.css">
    <script src="${pageContext.request.contextPath}/boot/js/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/boot/js/bootstrap.3.3.7.min.js"></script>
    <script src="${pageContext.request.contextPath}/jqgrid/js/i18n/grid.locale-cn.js"></script>
    <script src="${pageContext.request.contextPath}/jqgrid/extend/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="${pageContext.request.contextPath}/boot/js/ajaxfileupload.js"></script>
    <title></title>
    <script type="text/javascript">
        function changeImage(){
            window.location.href="${pageContext.request.contextPath}/jsp/login.jsp";
        }
       function submitForm(){
            var code= $("#code").val();
            $.post("${pageContext.request.contextPath}/admin/login",$('#login').serialize(),function (date) {
                if(date.status=="200"){
                    window.location.href="${pageContext.request.contextPath}/jsp/main/main.jsp"
                }else{
                    $("#span").text(date.message);
                }
            },"json")
        }
    </script>
</head>
<body>
    <div>
        <h3 align="center">登录</h3>
    </div>
    <form id="login" action="javascript:void(0) ">
    <div class="form-group">
        <label for="username">用户名</label>
        <input type="text" name="username" class="form-control" id="username">
    </div>
    <div class="form-group">
        <label for="password">密码</label>
        <input type="password"  name="password" class="form-control" id="password">
    </div>
    <div class="form-group">
        <div class="code" style="width:100px;">
           验证码： <input type="text" name="code" id="code" />
        </div>
                <a class="code_pic" id="vcodeImgWrap" name="change_code_img" href="javascript:void(0);">
                    <img  src="${pageContext.request.contextPath}/code" onclick="changeImage()">
                </a>
                <a href="javascript:changeImage()">换张图</a>
                <span  style="display: none;"></span>
        </div>
    </div>
    <button onclick="submitForm()" class="btn btn-default">submit</button>
        <span id="span" style="color: red"></span>
</form>
</body>
</html>
