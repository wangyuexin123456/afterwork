<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<!doctype html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
</head>
        <script type="text/javascript">
            function alarmFormatter(cellvalue, options, rowdata) {
                var t="<img style='width:30px;height:30px;' src='${pageContext.request.contextPath}/upload/";
                var m=cellvalue+" '/> ";
                return t+m;
            }
            $(function () {
                $("#table1").jqGrid({
                    styleUI:"Bootstrap",
                    url:"${pageContext.request.contextPath}/carousel/queryAll",
                    datatype:"json",
                    autowidth:true,
                    colNames:[
                     "编号","轮播图名称","图片","状态","创建时间"
                    ],
                    colModel:[
                        {name:"carouselId",key:true},
                        {name:"title",editable:true},
                        {name:"imgpath",editable:true,formatter: alarmFormatter,
                                edittype:'file'},
                        {name:"status",editable:true},
                        {name:"createTime",editable:true,edittype:'date'}
                    ],
                    editurl:"${pageContext.request.contextPath}/carousel/operation",
                    pager:"#page",
                    viewrecords:true,
                    rowNum:5,
                    rowList:[2,5,10],
                    multiselect : true,
                    rownumbers:true,
                    height : "auto",
                }).jqGrid("navGrid","#page",{},{
                        //修改的部分
                        closeAfterEdit:true,
                        afterSubmit:function(response){
                                $.ajaxFileUpload({
                                        url:"${pageContext.request.contextPath}/carousel/upload",
                                        fileElementId:"imgpath",
                                        data:{"id":response.responseText},
                                        type:"post",
                                        success:function(){
                                                $("#table1").trigger("reloadGrid");
                                        }
                                })
                                return "[true]";
                        }
                },{
                        //添加的部分
                        closeAfterAdd:true,
                        afterSubmit:function(response){
                                $.ajaxFileUpload({
                                        url:"${pageContext.request.contextPath}/carousel/upload",
                                        fileElementId:"imgpath",
                                        data:{"id":response.responseText},
                                        type:"post",
                                        success:function(){
                                                $("#table1").trigger("reloadGrid");
                                        }
                                })
                                return "[true]";
                        }
                });
            })
        </script>
</head>
<body>
<div class="page-header">
        <h1>轮播图管理系统</h1>
</div>
<table id="table1"></table>
<div id="page"></div>
</body>
</html>
