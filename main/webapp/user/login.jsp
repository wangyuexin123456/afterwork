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
    <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/boot/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jqgrid/extend/css/trirand/ui.jqgrid-bootstrap.css">
    <script src="${pageContext.request.contextPath}/boot/js/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/boot/js/bootstrap.3.3.7.min.js"></script>
    <script src="${pageContext.request.contextPath}/jqgrid/js/i18n/grid.locale-cn.js"></script>
    <script src="${pageContext.request.contextPath}/jqgrid/extend/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="${pageContext.request.contextPath}/boot/js/ajaxfileupload.js"></script>--%>
    <title></title>
    <script type="text/javascript">
        $('#echartsButton').click(function () {
            $("#myModal6").modal("hide");
        })
        function changeImage(){
            window.location.href="${pageContext.request.contextPath}/jsp/login.jsp";
        }
        function submitForm(){
            var code= $("#code").val();
            $.post("${pageContext.request.contextPath}/user/login",$('#login').serialize(),function (date) {
                if(date.status=="200"){
                    alert(date.User)
                    $('#id2').append(date.User.userId)
                    $('#phone2').append(date.User.phone)
                    $('#password2').append(date.User.password)
                    $('#province2').append(date.User.province)
                    $('#city2').append(date.User.city)
                    $('#gender2').append(date.User.gender)
                    $('#personalSign2').append(date.User.personalSign)
                    $('#profile2').append(date.User.profile)
                    $('#dharmaName2').append(date.User.dharmaName)
                    $('#registTime2').append(date.User.registTime)
                    $("#myModal6").modal("show");
                }else{
                    $("#span").text(date.status+date.message);
                }
            },"json")
        }
        function registTo(){
            $("#myModal5").modal("show");
        }
        function ddddd(id) {
            $.ajaxFileUpload({
                url:"${pageContext.request.contextPath}/user/upload",
                fileElementId:"head",
                data:{"id":id},
                type:"post",
                success:function(){
                }
            })
        }
        function bbb() {
            var a = $("#phone1").val();
            var b = $("#password1").val();
            var c=$("#dharmaName1").val();
            var d=$("#province1").val();
            var e=$("#city1").val();
            var f=$("#sex1").val();
            var g=$("#profile").val();
            if(a!=null&&b!=null&&c!=null&&d!=null&&e!=null&&f!=null){
                alert(11111111111111)
                $.post("${pageContext.request.contextPath}/user/regist",$("#userForm").serialize(),function (date) {
                    alert(22222)
                    if(date.status==200) {
                        $('#id2').append(date.User.userId)
                        $('#phone2').append(date.User.phone)
                        $('#password2').append(date.User.password)
                        $('#province2').append(date.User.province)
                        $('#city2').append(date.User.city)
                        $('#gender2').append(date.User.gender)
                        $('#personalSign2').append(date.User.personalSign)
                        $('#profile2').append(date.User.profile)
                        $('#dharmaName2').append(date.User.dharmaName)
                        $('#registTime2').append(date.User.registTime)
                        $("#myModal5").modal("hide");
                        $("#myModal6").modal("show");
                    }else{
                        $('#span6').text(date.status+date.message);
                    }
                },"json")
            }else{
                if(a==null){$("#span").append("用户名不能为空")}
                if(b==null){$("#span").append("密码不能为空")}
                if(c==null){$("#span").append("关注大师不能为空")}
                if(d==null){$("#span").append("所在省不能为空")}
                if(e==null){$("#span").append("所在市不能为空")}
                if(f==null){$("#span").append("性别不能为空")}
            }
        }
    </script>
</head>
<div id="ttttttt">
    <div >
        <h3 align="center">登录</h3>
    </div>
    <form id="login" action="javascript:void(0) ">
        <div class="form-group">
            <label for="username">用户名</label>
            <input type="text" name="phone" class="form-control" id="username">
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
        <button onclick="submitForm()" class="btn btn-default">登录</button>
        <button onclick="registTo()" class="btn btn-default">注册</button>
        <span id="span" style="color: red"></span>
    </form>
</div>
<div class="modal fade" id="myModal5" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width:800px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabe">注册用户</h4>
            </div>
            <form method="post" id="userForm" enctype="multipart/form-data">
                <div class="modal-body">
                    <span id="span6" style="color: red"></span><br>
                    (必填)用户名/手机号<input type="text" name="phone" id="phone1"><br>
                    (必填)密码<input type="text" name="password" id="password1"><br>
                    (必填)上师名称<input type="text" name="dharmaName" id="dharmaName1"><br>
                    (必填)省份<input type="text" name="province" id="province1"><br>
                    (必填)城市<input type="text" name="city" id="city1"><br>
                    (必填)性别<input type="text" name="gender" id="sex1"><br>
                    (选填)个性签名<input type="text" name="personalSign"><br>
                    (选填)头像路径<input type="text" name="profile" id="profile" ><br>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button id="registButton" onclick="bbb()" type="button" class="btn btn-default" >提交</button>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="modal fade" id="myModal6" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width:800px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabe6">结果展示</h4>
            </div>
                <div class="modal-body">
                    id：<span id='id2'></span><br>
                    用户名/手机号：<span  id="phone2"></span><br>
                    密码：<span  id="password2"></span><br>
                    上师名称：<span  id="dharmaName2"></span><br>
                    省份：<span  id="province2"></span><br>
                    城市：<span  id="city2"></span><br>
                    性别：<span  id="sex2"></span><br>
                    个性签名：<span  id="personalSign2"></span><br>
                    头像路径：<span  id="profile2" ></span><br>
                    注册时间：<span  id="registTime2" ></span><br>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <a id="echartsButton" href="javascript:$('#ttttttt').load('${pageContext.request.contextPath}/jsp/user/user.jsp');" class="btn btn-default">继续</a>
                </div>
        </div>
    </div>
</div>
</body>
</html>
