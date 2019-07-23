<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<!doctype html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
</head>
        <script type="text/javascript">
            function alarmFormatter(cellvalue, options, rowdata) {
                var t="<img style='width:30px;height:30px;' src='${pageContext.request.contextPath}/uploadCover/";
                var m=cellvalue+" '/> ";
                return t+m;
            }
            $(function () {
                $("#table2").jqGrid({
                    styleUI:"Bootstrap",
                    url:"${pageContext.request.contextPath}/album/queryAll",
                    datatype:"json",
                    autowidth:true,
                    colNames:[
                     "编号","专辑名称","专辑封面","章节数量","专辑得分","专辑作者","播音员","专辑简介","出版时间"
                    ],
                    colModel:[
                        {name:"albumId",key:true},
                        {name:"title",editable:true},
                        {name:"cover",editable:true,formatter: alarmFormatter,
                                edittype:'file'},
                        {name:"count",editable:true},
                        {name:"score",editable:true},
                        {name:"author",editable:true},
                        {name:"broadcast",editable:true},
                        {name:"brief",editable:true},
                        {name:"publishTime",editable:true,edittype:'date'}
                    ],
                    editurl:"${pageContext.request.contextPath}/album/operation",
                    pager:"#page2",
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
                                                url : "${pageContext.request.contextPath}/chapter/queryAll?id=" + row_id,
                                                datatype : "json",
                                                styleUI:"Bootstrap",
                                                autowidth:true,
                                                colNames : [ '编号', '专辑编号', '音频名称', '音频大小','音频路径','上传时间','操作' ],
                                                colModel : [
                                                        {name : "chapterId",key : true},
                                                        {name : "albumId"},
                                                        {name : "title",editable:true},
                                                        {name : "size",editable:true},
                                                        {name : "downPath",editable:true,edittype:"file"},
                                                        {name : "uploadTime",editable:true,edittype:"date"},
                                                        {width:"500px",
                                                                formatter:function (cellvalue, options, rowObject) {
                                                                        alert(rowObject.downPath)
                                                               // return "<audio controls src='${pageContext.request.contextPath}/uploadDownPath/"+rowObject.downPath+"'></audio>";
                                                                return "<a class='btn btn-default' href='${pageContext.request.contextPath}/chapter/download?downPath="+rowObject.downPath+"'>下载</audio>";
                                                            }
                                                        }
                                                         ],
                                                editurl:"${pageContext.request.contextPath}/chapter/operation",
                                                pager : pager_id,
                                                rowNum:5,
                                                rowList:[2,5,10],
                                                multiselect : true,
                                                rownumbers:true,
                                                height : "auto"
                                        });
                                jQuery("#" + subgrid_table_id).jqGrid('navGrid',
                                        "#" + pager_id, {},{
                                                closeAfterEdit:true,
                                                afterSubmit:function(response){
                                                        $.ajaxFileUpload({
                                                                url:"${pageContext.request.contextPath}/chapter/upload?albumId="+row_id,
                                                                fileElementId:"downPath",
                                                                data:{"id":response.responseText},
                                                                type:"post",
                                                                success:function(){
                                                                        $("#" + subgrid_table_id).trigger("reloadGrid");
                                                                }
                                                        })
                                                        return "[true]";
                                                }
                                        },{
                                                closeAfterAdd:true,
                                                afterSubmit:function(response){
                                                        $.ajaxFileUpload({
                                                                url:"${pageContext.request.contextPath}/chapter/upload?albumId="+row_id,
                                                                fileElementId:"downPath",
                                                                data:{"id":response.responseText},
                                                                type:"post",
                                                                success:function(){
                                                                        $("#" + subgrid_table_id).trigger("reloadGrid");
                                                                }
                                                        })
                                                        return "[true]";
                                                }
                                        }
                                        );
                        },
                }).jqGrid("navGrid","#page2",{},{
                        //修改的部分
                        closeAfterEdit:true,
                        afterSubmit:function(response){
                                $.ajaxFileUpload({
                                        url:"${pageContext.request.contextPath}/album/upload",
                                        fileElementId:"cover",
                                        data:{"id":response.responseText},
                                        type:"post",
                                        success:function(){
                                                $("#table2").trigger("reloadGrid");
                                        }
                                })
                                return "[true]";
                        }
                },{
                        //添加的部分
                        closeAfterAdd:true,
                        afterSubmit:function(response){
                                $.ajaxFileUpload({
                                        url:"${pageContext.request.contextPath}/album/upload",
                                        fileElementId:"cover",
                                        data:{"id":response.responseText},
                                        type:"post",
                                        success:function(){
                                                $("#table2").trigger("reloadGrid");
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
        <h1>专辑管理系统</h1>
</div>
<table id="table2"></table>
<div id="page2"></div>
</body>
</html>
