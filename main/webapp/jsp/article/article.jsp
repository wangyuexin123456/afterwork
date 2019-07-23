<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<!doctype html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/kindeditor-all.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/lang/zh-CN.js"></script>
<html lang="en">
</head>
<script type="text/javascript">
        function showContent1(o){
                $("#showContent").empty();
               $.post("${pageContext.request.contextPath}/article/queryOne?id="+o,function (date) {
                       $("#showContent").append(date.content);
                       $('#myModa2').modal('show');
               })
        }
        function addArticle(){
                alert("------")
                $.post("${pageContext.request.contextPath}/article/addArticle",$("#articleForm").serialize(),function (result) {
                        alert(result);
                        if(result==1){
                                $("#table4").trigger("reloadGrid");
                                $('#myModal').modal('hide');
                        }
                        Error:"错误";
                },"json")
        }
        $('#myModal').on('show.bs.modal', function (e) {
                $.post("${pageContext.request.contextPath}/guru/query",function (data) {
                        var t="";
                      for(var i=0;i<data.length;i++){
                               t +="<option value='"+data[i].guruId+"'>"+data[i].name+"</option>"
                        }
                      alert(t)
                      $("#selectId").append(t);
                })
        })
        $(function () {
                KindEditor.create('#editor_id',{
                        width : '300px',
                        uploadJson:'${pageContext.request.contextPath}/article/upload',
                        fileManagerJson:'${pageContext.request.contextPath}/article/showAll',
                        allowFileManager:true,
                        filePostName:'file',
                        afterBlur:function(){
                                this.sync();
                        }
                });
                $("#table4").jqGrid({
                    styleUI:"Bootstrap",
                    url:"${pageContext.request.contextPath}/article/queryAll",
                    datatype:"json",
                    autowidth:true,
                    colNames:[
                     "编号","大师id","大师名称","名称","发表时间","操作"
                    ],
                    colModel:[
                        {name:"articleId",key:true},
                        {name:"guruId"},
                        {name:"guru.name"},
                        {name:"title"},
                        {name:"publishTime"},
                        {name:"content",
                              formatter:function (cellvalue, options, rowObject) {
                                     return "<a class='btn btn-default' onclick='showContent1(\""+rowObject.articleId+"\")' >预览</a>";
                              }
                        }
                    ],
                    editurl:"${pageContext.request.contextPath}/article/operation",
                    pager:"#page4",
                    viewrecords:true,
                    rowNum:3,
                    rowList:[2,5,10],
                    multiselect : true,
                    rownumbers:true,
                    height : "auto",
                }).jqGrid("navGrid","#page4",{add:false,edit:false});
            })
        </script>
</head>
<body>
<div class="page-header">
        <h1>文章管理系统</h1>
</div>
<table id="table4"></table>
<div id="page4"></div>
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
        文章上传
</button>
<div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
                <div class="modal-content" style="width:800px">
                        <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="myModalLabel">文件上传</h4>
                        </div>
                        <form action="javascript:void(0);" id="articleForm">
                                <div class="modal-body">
                                        名称标题<input type="text" name="title"><br>
                                        内容<textarea id="editor_id" name="content" style="width:700px;height:300px;">
                                                &lt;strong&gt;HTML内容&lt;/strong&gt;
                                            </textarea>
                                        发布时间<input type="date" name="publishTime"><br>
                                        大师名称<select id="selectId" name="guruId">
                                               <option>-请选择-</option><span class="caret"></span>
                                                </select>
                                </div>
                                <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                        <button type="button" class="btn btn-primary" onclick="addArticle()">提交</button>
                                </div>
                        </form>
                </div>
        </div>
</div>
<div class="modal fade" id="myModa2" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
                <div class="modal-content" style="width:800px">
                        <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="myModalLabe2">内容展示</h4>
                        </div>
                        <div class="modal-body">
                                <p id="showContent"></p>
                        </div>
                        <div class="modal-footer">
                             <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        </div>
                </div>
        </div>
</div>
</body>

</html>
