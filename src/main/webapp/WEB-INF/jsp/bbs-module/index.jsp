<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
        <!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7" lang="zh-CN"> <![endif]-->
        <!--[if IE 7]>    <html class="lt-ie9 lt-ie8" lang="zh-CN"> <![endif]-->
        <!--[if IE 8]>    <html class="lt-ie9" lang="zh-CN"> <![endif]-->
        <!--[if gt IE 8]><!--> <html lang="zh-CN"> <!--<![endif]-->
        <head>
                <!-- META TAGS -->
                <meta charset="UTF-8" />
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>问坛</title>
                <link rel="shortcut icon" href="../images/favicon.png" />
                <!-- Style Sheet-->
                <link rel="stylesheet" href="../css/style.css"/>
                <link rel='stylesheet' id='bootstrap-css-css'  href='../css/bootstrap5152.css?ver=1.0' type='text/css' media='all' />
                <link rel='stylesheet' id='responsive-css-css'  href='../css/responsive5152.css?ver=1.0' type='text/css' media='all' />
                <link rel='stylesheet' id='pretty-photo-css-css'  href='../js/prettyphoto/prettyPhotoaeb9.css?ver=3.1.4' type='text/css' media='all' />
                <link rel='stylesheet' id='main-css-css'  href='../css/main5152.css?ver=1.0' type='text/css' media='all' />
                <link rel='stylesheet' id='custom-css-css'  href='../css/custom5152.html?ver=1.0' type='text/css' media='all' />
        </head>
        <body>
                <!-- Start of Header -->
                <div class="header-wrapper">
                        <header>
                                <div class="container">

                                        <div class="logo-container">
                                                <!-- Website Logo -->
                                                <h4>
                                                    <a href="../bbs-module/index">
                                                        问坛&nbsp;&nbsp;&nbsp;
                                                        <span class="tag-line">聚焦与你有关的问题</span>
                                                    </a>
                                                </h4>
                                        </div>

                                        <nav class="main-nav">
                                                <div class="menu-top-menu-container">
                                                        <ul id="menu-top-menu" class="clearfix">
                                                                <c:choose>
                                                                        <c:when test="${sessionScope.user == null}">
                                                                        <li>
                                                                                <a href="../login-module/login-page" style="color: #2f96b4">您还未登录</a>
                                                                        </li>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                        <li><a href="#">More</a>
                                                                                <ul class="sub-menu">
                                                                                        <li><a href="../login-module/organizing_data">修改个人资料</a></li>
                                                                                        <li><a href="../login-module/forgot-password">修改密码</a></li>
                                                                                        <li><a href="../login-module/loginOut">注销</a></li>
                                                                                </ul>
                                                                        </li>
                                                                        <li>
                                                                                <span style="color: #a9dba9">${sessionScope.user.name},你好</span>
                                                                        </li>
                                                                        </c:otherwise>
                                                                </c:choose>
                                                        </ul>
                                                </div>
                                        </nav>
                                </div>
                        </header>
                </div>
                <!-- End of Header -->

                <!-- Start of Search Wrapper -->
                <div class="search-area-wrapper">
                        <div class="search-area container">
                                <h3 class="search-header">有问题?当然是上问坛啊！</h3>
                                <p class="search-tag-line">在这里可以看到你所感兴趣方面的知识或者得到一些额外的资源</p>

                                <form:form id="search-form" class="search-form clearfix" method="get" action="search" autocomplete="off">
                                        <input class="search-term1" type="text" id="search" name="search" placeholder="开始搜索" title="搜索中.." />
                                        <input class="search-btn" type="submit" value="Search" />
                                        <div id="search-error-container"></div>
                                </form:form>
                        </div>
                </div>

                <!-- Start of Page Container -->
                <div class="page-container">
                        <div class="container">
                                <div class="row">
                                        <!-- start of page content -->
                                        <div class="span8 page-content">
                                                <!-- Basic Home Page Template -->
                                                <div class="row separator">
                                                        <section class="span4 articles-list">
                                                                <h3>热点帖子</h3>
                                                                <ul class="articles" id="0">
                                                                    <c:forEach items="${sessionScope.hotPost}" var="status" varStatus="s">
                                                                        <li class="article-entry standard">
                                                                            <h4><a href="postSpecific?postId=${status.postId}">${status.postName}</a></h4><span class="alignright">${status.postCategory}</span>
                                                                            <span class="article-meta"><fmt:formatDate value="${status.postCreatetime}" pattern="yyyy-MM-dd"/> By ${status.user.name}</span>
                                                                            <span class="like-count" id="clickTime0${s.count}">${status.postClicktimes}</span>
                                                                            <span style="display: none">${s.count}</span>
                                                                        </li>
                                                                        <input type="hidden" id="username0${s.count}" value="${status.user.name}">
                                                                        <input type="hidden" id="postname0${s.count}" value="${status.postName}">
                                                                    </c:forEach>
                                                                </ul>
                                                        </section>

                                                        <section class="span4 articles-list">
                                                                <h3>最新帖子</h3>
                                                                <ul class="articles" id="1">
                                                                        <c:forEach items="${sessionScope.lastPost}" var="status" varStatus="s">
                                                                            <li class="article-entry standard">
                                                                                <h4><a href="postSpecific?postId=${status.postId}">${status.postName}</a></h4><span class="alignright">${status.postCategory}</span>
                                                                                <span class="article-meta"><fmt:formatDate value="${status.postCreatetime}" pattern="yyyy-MM-dd"/> By ${status.user.name}</span>
                                                                                <span class="like-count" id="clickTime1${s.count}">${status.postClicktimes}</span>
                                                                                <span style="display: none">${s.count}</span>
                                                                            </li>
                                                                            <input type="hidden" id="username1${s.count}" value="${status.user.name}">
                                                                            <input type="hidden" id="postname1${s.count}" value="${status.postName}">
                                                                        </c:forEach>
                                                                </ul>
                                                        </section>
                                                </div>
                                        </div>
                                        <!-- end of page content -->

                                        <!-- start of sidebar -->
                                        <aside class="span4 page-sidebar">

                                                <section class="widget">
                                                        <div class="support-widget">
                                                                <h3 class="title">没有找到感兴趣的？</h3>
                                                                <p class="intro">发帖求助各路人才</p>
                                                                <a href="newPost"><button type="button" class="btn-primary">发帖</button></a>
                                                        </div>
                                                </section>

                                                <section class="widget">
                                                        <div class="quick-links-widget">
                                                                <h3 class="title">Quick Links</h3>
                                                                <ul id="menu-quick-links" class="menu clearfix">
                                                                        <li><a href="index">主页</a></li>
                                                                        <li><a href="article-list">文章列表</a></li>
                                                                        <c:if test="${sessionScope.user != null}">
                                                                                <li><a href="myPost">我的帖子</a></li>
                                                                                <li><a href="myReply">我的回复</a></li>
                                                                        </c:if>
                                                                </ul>
                                                        </div>
                                                </section>

                                        </aside>
                                </div>
                        </div>
                </div>
                <!-- Start of Footer -->
                <footer id="footer-wrapper">
                        <div id="footer" class="container">
                                <div class="row">
                                </div>
                        </div>
                        <!-- end of #footer -->

                </footer>
                <!-- End of Footer -->

                <a href="#top" id="scroll-top"></a>

                <!-- script -->
                <script type='text/javascript' src='../js/jquery-1.8.3.min.js'></script>
                <script type='text/javascript' src='../js/jquery.easing.1.3.js'></script>
                <script type='text/javascript' src='../js/prettyphoto/jquery.prettyPhoto.js'></script>
                <script type='text/javascript' src='../js/jflickrfeed.js'></script>
				<script type='text/javascript' src='../js/jquery.liveSearch.js'></script>
                <script type='text/javascript' src='../js/jquery.form.js'></script>
                <script type='text/javascript' src='../js/jquery.validate.min.js'></script>
                <script type='text/javascript' src='../js/custom.js'></script>

        </body>
</html>
<script type="text/javascript">
    $("span[id^='clickTime']").live('click',function changeClickTimes() {
        var n = this.innerHTML;
        var s = $(this).next()[0].innerHTML
        var c = $(this).parent().parent()[0].id
        var username = document.getElementById("username"+c+s).value
        var postname = document.getElementById("postname"+c+s).value
        $.ajax({
            url:'addClickTime',
            data:JSON.stringify({"username":username,"postname":postname}),
            contentType:'application/json;charset=utf-8',
            type:'POST',
            success:function (data) {
                if(data){
                    document.getElementById("clickTime"+c+s).innerHTML = (parseInt(n)+1).toString();
                }
            }
        })
    })

</script>