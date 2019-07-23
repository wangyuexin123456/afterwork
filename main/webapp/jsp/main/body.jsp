
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<div class="container-fluid">
    <div class="jumbotron">
        <h3>欢迎来到持明法洲后台管理系统</h3>
    </div>
    <div id="myCarousel" class="carousel slide">
        <!-- 轮播（Carousel）指标 -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>
        <!-- 轮播（Carousel）项目 -->
        <div class="carousel-inner">
            <div class="item active ">
                <img style="width: 70%" class="img-responsive center-block"
                     src="${pageContext.request.contextPath}/img/1.png" alt="First slide">
            </div>
            <div class="item">
                <img style="width: 70%" class="img-responsive center-block"
                     src="${pageContext.request.contextPath}/img/2.png" alt="Second slide">
            </div>
            <div class="item">
                <img style="width: 70%" class="img-responsive center-block"
                     src="${pageContext.request.contextPath}/img/3.png" alt="Third slide">
            </div>
        </div>
        <!-- 轮播（Carousel）导航 -->
        <a class="carousel-control left" href="#myCarousel" data-slide="prev">
            <span _ngcontent-c3="" aria-hidden="true" class="glyphicon glyphicon-chevron-right">
            </span>
        </a>
        <a class="carousel-control right" href="#myCarousel" data-slide="next">
           <span _ngcontent-c3="" aria-hidden="true" class="glyphicon glyphicon-chevron-left">
            </span>
        </a>
    </div>

</div>