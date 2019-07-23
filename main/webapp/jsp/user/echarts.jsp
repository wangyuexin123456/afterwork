<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<script type="text/javascript" src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
        <%--引入jq--%>
<%--<script type="text/javascript" src="${pageContext.request.contextPath}/boot/js/jquery-3.3.1.min.js"></script>--%>
        <%--引入echarts--%>
<script src="${pageContext.request.contextPath}/echarts/echarts.js"></script>
<script src="https://www.echartsjs.com/gallery/vendors/echarts/map/js/china.js?_v_=1553896255267"></script>
<script type="text/javascript">
    var goEasy = new GoEasy({
        appkey: 'BC-60bc04e03c8f4072976612989be0da54'
    });
    goEasy.subscribe({
        channel:'content',
        onMessage: function(message){
            alert('收到：'+message.content);
            var t=eval('('+message.content+')')
            alert(t)
            tt(t);
        },
    });
    function aaaaa() {
        $.post("${pageContext.request.contextPath}/user/text",function (date) {
            tt(date);
        },"json")
    }
        function tt(date) {
            var b=[];
            var c=[];
            var bb=[];
            var cc=[];
            var a = date.User;
            for (var i = 0; i < a.length; i++) {
                b.push(a[i].data);
                c.push(a[i].count);
            }
            var aa = date.User2;
            for (var i = 0; i < aa.length; i++) {
                if (aa[i].gender == "男") {
                    bb.push({name: aa[i].province, value: aa[i].count});
                } else {
                    cc.push({name: aa[i].province, value: aa[i].count});
                }
            }
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));
            var myChart2 = echarts.init(document.getElementById('main2'));
            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: '月份统计柱状图'
                },
                tooltip: {},
                legend: {
                    data:['月份']
                },
                xAxis: {
                    data: b
                },
                yAxis: {},
                series: [{
                    name: '数量',
                    type: 'bar',
                    data: c
                }]
            };
            var option2 = {
                title : {
                    text: '地区注册人数统计',
                    left: 'center'
                },
                tooltip : {
                    trigger: 'item'
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data:['男','女']
                },
                visualMap: {
                    min: 0,
                    max: 2500,
                    left: 'left',
                    top: 'bottom',
                    text:['高','低'],           // 文本，默认为数值文本
                    calculable : true
                },
                toolbox: {
                    show: true,
                    orient : 'vertical',
                    left: 'right',
                    top: 'center',
                    feature : {
                        mark : {show: true},
                        dataView : {show: true, readOnly: false},
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                series : [
                    {
                        name: '男',
                        type: 'map',
                        mapType: 'china',
                        roam: false,
                        label: {
                            normal: {
                                show: false
                            },
                            emphasis: {
                                show: true
                            }
                        },
                        data:bb
                    },
                    {
                        name: '女',
                        type: 'map',
                        mapType: 'china',
                        label: {
                            normal: {
                                show: false
                            },
                            emphasis: {
                                show: true
                            }
                        },
                        data:cc
                    },
                ]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
            myChart2.setOption(option2);
        }
        $(function () {
            $.post("${pageContext.request.contextPath}/user/text",function (date) {
                tt(date);
            },"json")
        })
</script>
</head>
<body>
    <div id="main" style="width: 600px;height:400px;"></div>
    <div id="main2" style="width: 600px;height:400px;"></div>
</body>
</html>
