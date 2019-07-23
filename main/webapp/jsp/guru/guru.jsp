<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<!doctype html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
</head>
        <script type="text/javascript">
                function showContent2(o){
                        $("#showContent").empty();
                        $.post("${pageContext.request.contextPath}/article/queryOne?id="+o,function (date) {
                                $("#showContent3").append(date.content);
                                $('#myModa3').modal('show');
                        })
                }
                function updateGuruStatus(id,status) {
                        $.post("${pageContext.request.contextPath}/guru/updateStatus?id="+id+"&status="+status,function (date) {
                                alert(date)
                                $("#table5").trigger("reloadGrid");
                        },"json")
                        $("#table5").trigger("reloadGrid");
                }
            function alarmFormatter(cellvalue, options, rowdata) {
                var t="<img style='width:30px;height:30px;' src='${pageContext.request.contextPath}/upload/";
                var m=cellvalue+" '/> ";
                return t+m;
            }
            $(function () {
                $("#table5").jqGrid({
                    styleUI:"Bootstrap",
                    url:"${pageContext.request.contextPath}/guru/queryAll",
                    datatype:"json",
                    autowidth:true,
                    colNames:[
                     "编号","上师姓名","头像","状态","性别"
                    ],
                    colModel:[
                        {name:"guruId",key:true},
                        {name:"name",editable:true},
                        {name:"profile",editable:true,formatter: alarmFormatter,
                                edittype:'file'},
                        {name:"status",formatter:function (cellvalue, options, rowObject) {
                            return "<button class='btn btn-default' onclick=\"updateGuruStatus('"+rowObject.guruId+"','"+cellvalue+"')\">"+cellvalue+"</button>"
                    }},
                        {name:"sex",editable:true}
                    ],
                    editurl:"${pageContext.request.contextPath}/guru/operation",
                    pager:"#page5",
                    viewrecords:true,
                    rowNum:5,
                    rowList:[2,5,10],
                    multiselect : true,
                    rownumbers:true,
                    subGrid : true,
                    height : "auto",
                        subGridRowExpanded : function(subgrid_id, row_id) {
                                // we pass two parameters
                                // subgrid_id is a id of the div tag created whitin a table data
                                // the id of this elemenet is a combination of the "sg_" + id of the row
                                // the row_id is the id of the row
                                // If we wan to pass additinal parameters to the url we can use
                                // a method getRowData(row_id) - which returns associative array in type name-value
                                // here we can easy construct the flowing
                                var subgrid_table_id, pager_id;
                                subgrid_table_id = subgrid_id + "_t";
                                pager_id = "p_" + subgrid_table_id;
                                $("#" + subgrid_id).html(
                                        "<table id='" + subgrid_table_id
                                        + "' class='scroll'></table><div id='"
                                        + pager_id + "' class='scroll'></div>");
                                jQuery("#" + subgrid_table_id).jqGrid(
                                        {
                                                url : "${pageContext.request.contextPath}/article/queryAllId?id=" + row_id,
                                                datatype : "json",
                                                styleUI:"Bootstrap",
                                                autowidth:true,
                                                colNames : [ "编号","大师id","大师名称","名称","发表时间","操作"],
                                                colModel : [
                                                        {name:"articleId",key:true},
                                                        {name:"guruId"},
                                                        {name:"guru.name"},
                                                        {name:"title"},
                                                        {name:"publishTime"},
                                                        {name:"content",
                                                                formatter:function (cellvalue, options, rowObject) {
                                                                        return "<a class='btn btn-default' onclick='showContent2(\""+rowObject.articleId+"\")' >预览</a>";
                                                                }
                                                        }
                                                ],
                                                editurl:"${pageContext.request.contextPath}/article/operation",
                                                pager : pager_id,
                                                rowNum:5,
                                                rowList:[2,5,10],
                                                multiselect : true,
                                                rownumbers:true,
                                                height : "auto"
                                        });
                                jQuery("#" + subgrid_table_id).jqGrid('navGrid',
                                        "#" + pager_id, {add:false,edit:false,delete:false},{

                                        }
                                );
                        },
                }).jqGrid("navGrid","#page5",{},{
                        //修改的部分
                        closeAfterEdit:true,
                        afterSubmit:function(response){
                                $.ajaxFileUpload({
                                        url:"${pageContext.request.contextPath}/guru/upload",
                                        fileElementId:"profile",
                                        data:{"id":response.responseText},
                                        type:"post",
                                        success:function(){
                                                $("#table5").trigger("reloadGrid");
                                        }
                                })
                                return "[true]";
                        }
                },{
                        //添加的部分
                        closeAfterAdd:true,
                        afterSubmit:function(response){
                                $.ajaxFileUpload({
                                        url:"${pageContext.request.contextPath}/guru/upload",
                                        fileElementId:"profile",
                                        data:{"id":response.responseText},
                                        type:"post",
                                        success:function(){
                                                $("#table5").trigger("reloadGrid");
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
        <h1>上师管理系统</h1>
</div>
<table id="table5"></table>
<div id="page5"></div>
<div class="modal fade" id="myModa3" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
                <div class="modal-content" style="width:800px">
                        <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="myModalLabe2">内容展示</h4>
                        </div>
                        <div class="modal-body">
                                <p id="showContent3"></p>
                        </div>
                        <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        </div>
                </div>
        </div>
</div>
</body>
</html>
