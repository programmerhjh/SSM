<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<body id="body">
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
            <input class="search-btn" type="submit" value="Search" onclick="return $(this).prev().val() != null" />
            <div id="search-error-container"></div>
        </form:form>
    </div>
</div>

<!-- Start of Page Container -->
<div class="page-container">
    <div class="container">
        <div class="row">

            <h2 style="text-align: center">搜索结果：</h2>
            <!-- start of page content -->
            <div class="span8 main-listing" id="list">

                <c:choose>
                    <c:when test="${requestScope.page.list == null}">
                        <h2>搜索结果为空</h2>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${requestScope.page.list}" var="status" varStatus="s">

                            <article class="format-standard type-post hentry clearfix">

                                <header class="clearfix">

                                    <h3 class="post-title">
                                        <a href="postSpecific?postId=${status.postId}">${status.postName}</a>
                                    </h3>

                                    <div class="post-meta clearfix">
                                        <span class="date"><fmt:formatDate value="${status.postCreatetime}"/></span>
                                        <span class="category"><a href="#" title="${status.postCategory}">${status.postCategory}</a></span>
                                        <span class="comments"><a href="#" title="Comment on ${status.postName}">${fn:length(status.comments)} Comments</a></span>
                                        <span class="like-count">${status.postClicktimes}</span>
                                    </div><!-- end of post meta -->

                                </header>

                                <p>${fn:substring(status.postPost, 0, fn:length(status.postPost)-fn:length(status.postPost)/2)} . . . <a class="readmore-link" href="postSpecific?postId=${status.postId}">Read more</a></p>

                            </article>

                        </c:forEach>
                    </c:otherwise>
                </c:choose>

            </div>

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
        <div id="pagination">

            <select  class="btn" id="selectPage" >
                <option selected="selected" id="currentPage" value="${requestScope.page.pageNum}">
                    当前第 ${requestScope.page.pageNum} 页 / 共 ${requestScope.page.pages} 页
                </option>
                <c:forEach items="${requestScope.page.navigatepageNums}" var="pg" varStatus="pgs">
                    <c:if test="${pgs.count != page.pageNum}">
                        <option value="${pgs.count}" id="choosePage${pgs.count}" >第 ${pgs.count} 页</option>
                    </c:if>
                </c:forEach>
            </select>
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


    $("option[id^='choosePage']").live('click',function () {
        var targetPage = this.value
        $.ajax({
            cache:false,
            url:'searchPage',
            data:JSON.stringify({"targetPage":parseInt(targetPage)}),
            type:'POST',
            contentType:'application/json;charset=utf-8',
            dataType:'json',
            success:function (page) {
                var list = page['list']
                var list2 = page['navigatepageNums']
                $("#list").empty()
                $("#selectPage").empty()
                list.forEach(function (e) {
                    if(e != null) {
                        $("#list").append(
                            "<article class='format-standard type-post hentry clearfix'>" +

                            "<header class='clearfix'>" +

                            "<h3 class='post-title'>" +

                            "<a href='postSpecific?postId=" + e['postId'] + "'>" + e['postName'] + "</a>" +

                            "</h3>" +

                            "<div class='post-meta clearfix'>" +
                            "<span class='date'>" + e['postCreatetime'] + "</span>" +
                            "<span class='category'>" + "<a href='#' title='" + e['postCategory'] + "'>" + e['postCategory'] + "</a>" + "</span>" +
                            "<span class='comments'>" + "<a href='#' title='Comment on " + e['postName'] + "'>" + e['comments'].length + "  Comments" + "</a>" + "</span>" +
                            "<span class='like-count'>" + e['postClicktimes'] + "</span>" +
                            "</div>" + "<!-- end of post meta -->" +

                            "</header>" +

                            "<p>" + e['postPost'].substring(0, e['postPost'].length / 2) + " . . . <a class='readmore-link' href='postSpecific?postId=" + e['postId'] + "'>" + "Read more</a>" + "</p>" +

                            "</article>"
                        )

                    }

                    })


                list2.forEach(function (e) {
                    if(e!=null){
                        $("#selectPage").append(
                            "<option selected='selected' id='currentPage' value='" + page['pageNum'] +"'>"+
                            "当前第"+ page['pageNum']+" 页 / 共"+ page['pages']+"页"+
                            "</option>"
                        )
                        if(e != page['pageNum']){
                            $("#selectPage").append(
                                "<option value='"+ e +"' id='choosePage" + e + "' >"+"第" + e + " 页</option>"
                            )
                        }
                    }

                })
            },
            error:function(xhr){alert(xhr.responseText)}
        })
    })

</script>