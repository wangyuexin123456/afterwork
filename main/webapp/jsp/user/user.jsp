<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<!doctype html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
</head>
        <script type="text/javascript">
            function alarmFormatter(cellvalue, options, rowdata) {
                var t="<img style='width:30px;height:30px;' src='${pageContext.request.contextPath}/userHead/";
                var m=cellvalue+" '/> ";
                return t+m;
            }
           function updateStatus(id,status) {
                $.post("${pageContext.request.contextPath}/user/updateStatus?id="+id+"&status="+status,function (date) {
                        $("#table3").trigger("reloadGrid");
                },"json")
               $("#table3").trigger("reloadGrid");
            }
            $(function () {
                $("#table3").jqGrid({
                    styleUI:"Bootstrap",
                    url:"${pageContext.request.contextPath}/user/queryAll",
                    datatype:"json",
                    autowidth:true,
                    colNames:[
                     "编号","头像","手机号（账号）","密码","盐","法名","所在省","所在市","性别","个性签名","计数器","状态","注册时间","关注大师的id"
                    ],
                    colModel:[
                        {name:"userId",key:true},
                        {name:"head",editable:true,formatter: alarmFormatter,
                            edittype:'file'},
                        {name:"phone",editable:true},
                        {name:"password",editable:true},
                        {name:"salt",editable:true},
                        {name:"dharmaName",editable:true},
                        {name:"province",editable:true},
                        {name:"city",editable:true},
                        {name:"gender",editable:true},
                        {name:"personalSign",editable:true},
                        {name:"profile",editable:true},
                        {name:"status",
                                formatter:function (cellvalue, options, rowObject) {
                                        return "<button class='btn btn-default' onclick=\"updateStatus('"+rowObject.userId+"','"+cellvalue+"')\">"+cellvalue+"</button>"
                                       // return "<button class='btn btn-default' href='${pageContext.request.contextPath}/user/updateStatus?id="+rowObject.userId+"&status="+rowObject.status+"'>"+rowObject.status+"</button>";
                                }
                        },
                        {name:"registTime",editable:true,edittype:"date"},
                        {name:"guruId",editable:true}
                    ],
                    editurl:"${pageContext.request.contextPath}/user/operation",
                    pager:"#page3",
                    viewrecords:true,
                    rowNum:5,
                    rowList:[2,5,10],
                    multiselect : true,
                    rownumbers:true,
                    height : "auto",
                }).jqGrid("navGrid","#page3",{},{
                        //修改的部分
                        closeAfterEdit:true,
                        afterSubmit:function(response){
                                $.ajaxFileUpload({
                                        url:"${pageContext.request.contextPath}/user/upload",
                                        fileElementId:"head",
                                        data:{"id":response.responseText},
                                        type:"post",
                                        success:function(){
                                                $("#table3").trigger("reloadGrid");
                                        }
                                })
                                return "[true]";
                        }
                },{
                        //添加的部分
                        closeAfterAdd:true,
                        afterSubmit:function(response){
                                $.ajaxFileUpload({
                                        url:"${pageContext.request.contextPath}/user/upload",
                                        fileElementId:"head",
                                        data:{"id":response.responseText},
                                        type:"post",
                                        success:function(){
                                                $("#table3").trigger("reloadGrid");
                                        }
                                })
                                return "[true]";
                        }
                });
            })
                function exeImport() {
                    $("#myModal").modal("show");
                }
                function aaa () {
                    alert("进入")
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/user/importTo",
                        type:"post",
                        cache:false,
                        fileElementId:"file",
                        //date: new FormData($('#userForm')[0]),
                        processData:false,
                        contentType:false,
                        success:function(){
                            $("#myModal").modal("hide");
                        }
                    })
                }
                function onExport() {
                    location.href="${pageContext.request.contextPath}/user/onexport";
                }
        </script>
</head>
<body>
<div class="page-header">
        <h1>用户管理系统</h1>
</div>
<a type="button" class="btn btn-primary" onclick="onExport()">下载表格</a>
<a type="button" class="btn btn-primary" onclick="exeImport()">上传表格</a>
<table id="table3"></table>
<div id="page3"></div>
<div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width:800px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabe">上传表格</h4>
            </div>
            <form id="userForm" enctype="multipart/form-data">
            <div class="modal-body">
                <input type="file" name="file" id="file">
            </div>
            <div class="modal-footer">
                <input id="uploadButton" onclick="aaa()" type="submit" class="btn btn-default" />
            </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
