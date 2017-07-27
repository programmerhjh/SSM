<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

                <ul class="breadcrumb">
                    <li><a href="#" title="View all posts in ${sessionScope.post.postCategory}">${sessionScope.post.postCategory}</a> <span class="divider">/</span></li>
                    <li class="active">${sessionScope.post.postName}</li>
                    <input type="hidden" id="postId" value="${sessionScope.post.postId}">
                </ul>

                <article class=" type-post format-standard hentry clearfix">

                    <h1 class="post-title"><a href="#">${sessionScope.post.postName}</a></h1>

                    <div class="post-meta clearfix">
                        <span class="date"><fmt:formatDate value="${sessionScope.post.postCreatetime}" pattern="yyyy-MM-dd"/></span>
                        <span class="category"><a href="#" title="View all posts in ${sessionScope.post.postCategory}">${sessionScope.post.postCategory}</a></span>
                        <span class="comments"><a href="#" title="Comment on ${sessionScope.post.postName}">${fn:length(sessionScope.post.commentAndReplyVos)} 条回复</a></span>
                    </div><!-- end of post meta -->

                    ${sessionScope.post.postPost}

                </article>

                <br>

                <div class="like-btn">

                    <form id="like-it-form" action="#" method="post">
                        <span class="like-it " id="pc">${sessionScope.post.postClicktimes}</span>
                    </form>

                    <span class="tags">
                            <strong>Tags:&nbsp;&nbsp;</strong><a href="#" rel="tag">${sessionScope.post.postCategory}</a>
                    </span>

                </div>

                <section id="comments">

                    <h3 id="comments-title">${fn:length(sessionScope.post.commentAndReplyVos)} Comments</h3>

                    <ol class="commentlist">
                    <c:choose>
                        <c:when test="${sessionScope.post.commentAndReplyVos != null}">
                            <c:forEach items="${sessionScope.post.commentAndReplyVos}" var="status" varStatus="s">
                                <input type="hidden" id="trueCommentId${s.count}" value="${status.commentId}">
                                <li class="comment even thread-even depth-1" id="li-comment-${s.count}">
                                    <article id="comment-${s.count}">

                                        <a href="#">
                                            <img alt="" src="${status.commentUserVo.headAddress}" class="avatar avatar-60 photo" height="60" width="60">
                                        </a>


                                        <div class="comment-meta">

                                            <h5 class="author">
                                                <cite class="fn">
                                                    <a href="#" rel="external nofollow" class="url">${status.commentUserVo.name}</a>
                                                </cite>
                                                - <a class="comment-reply-link" href="#">评论</a>
                                            </h5>

                                            <p class="date">
                                                <a href="#">
                                                    <time><fmt:formatDate value="${status.commentCreatetime}" pattern="yyyy-MM-dd HH:mm:ss"/></time>
                                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" id="commentComment${s.count}" style="color: #0e0e0e">回复</a>
                                                </a>
                                                <br>
                                                <br>
                                                <input id="commentCommentText${s.count}" type="text" style="height: 50px;width: 800px;display: none">
                                                <br>
                                                <input type="submit" id="commentCommentSubmit${s.count}" style="display: none" value="回复">
                                            </p>

                                        </div><!-- end .comment-meta -->

                                        <div class="comment-body">
                                            <p>${status.commentComment}</p>
                                        </div><!-- end of comment-body -->

                                    </article>

                                    <!-- end of comment -->
                                    <ul class="children" id="children${s.count}">
                                        <c:if test="${status.replys != null}">
                                            <c:forEach items="${status.replys}" var="rstatus" varStatus="rs">
                                                <input type="hidden" value="${fn:length(status.replys)}" id="temp${s.count}">
                                                <input type="hidden" value="${rstatus.replyUser.name}" id="tempName${s.count}">
                                                <li class="comment byuser comment-author-saqib-sarwar bypostauthor odd alt depth-2" id="li-comment-${s.count}-${rs.count}">
                                                    <article id="comment-${s.count}-${rs.count}">
                                                        <a href="#">
                                                            <img alt="" src="${rstatus.replyUser.headAddress}" class="avatar avatar-60 photo" height="60" width="60">
                                                        </a>

                                                        <div class="comment-meta">

                                                            <h5 class="author">
                                                                <cite class="fn">${rstatus.replyUser.name}</cite>
                                                                - <a class="comment-reply-link" href="#"> 的回复</a><h4 style="text-align: right">${rs.count}楼</h4>
                                                            </h5>

                                                            <p class="date">
                                                                <a href="#">
                                                                    <time><fmt:formatDate value="${rstatus.replyCreatetime}" pattern="yyyy-MM-dd HH:mm:ss"/></time>
                                                                </a>
                                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" id="replyComment${s.count}-${rs.count}" style="color: #0e0e0e">回复</a>
                                                                <br>
                                                                <br>
                                                                <input id="replyCommentText${s.count}-${rs.count}" type="text" style="height: 50px;width: 800px;display: none">
                                                                <br>
                                                                <input type="submit" id="replyCommentSubmit${s.count}-${rs.count}" style="display: none" value="回复">
                                                            </p>

                                                        </div><!-- end .comment-meta -->

                                                        <div class="comment-body">
                                                            <p>${rstatus.replyReply}</p>
                                                        </div><!-- end of comment-body -->

                                                    </article><!-- end of comment -->

                                                    </li>
                                            </c:forEach>
                                            <hr>
                                        </c:if>
                                    </ul>
                                </li>
                            </c:forEach>
                        </c:when>

                        <c:otherwise>
                            <p class="comment-notes"><h3 style="text-align: center">暂无评论，沙发虚席以待</h3><span class="required"></span></p>
                        </c:otherwise>
                    </c:choose>
                    </ol>
                    <div id="respond">

                           <h3>评论一下</h3>
                                <c:choose>
                                    <c:when test="${sessionScope.user != null}">

                                        <div action="#" method="post" id="commentform">



                                            <p class="comment-notes">回复能考究一个人的素质和精神境界，好的回复内容更容易被作者欣赏并回复<span class="required"></span></p>

                                            <div>
                                                <label for="author">To</label>
                                                <input disabled="true" class="span4" type="text" id="author" value="${sessionScope.post.user.name}" size="22">
                                            </div>

                                            <div>
                                                <label for="userId">用户名</label>
                                                <input class="span4" disabled="true" type="text" name="userId" id="userId" value="${sessionScope.user.name}" size="22">
                                            </div>

                                            <div>
                                                <label for="comment">评论内容</label>
                                                <textarea class="span8" name="comment" id="comment" cols="58" rows="10"></textarea>
                                            </div>

                                            <p class="allowed-tags">你可以使用一些HTML代码加入评论<small><code>&lt;a href="" title=""&gt; &lt;abbr title=""&gt; &lt;acronym title=""&gt; &lt;b&gt; &lt;blockquote cite=""&gt; &lt;cite&gt; &lt;code&gt; &lt;del datetime=""&gt; &lt;em&gt; &lt;i&gt; &lt;q cite=""&gt; &lt;strike&gt; &lt;strong&gt; </code></small></p>

                                            <div>
                                                <input class="btn" name="submit" type="submit" id="submit"  value="提交评论">
                                            </div>

                                        </div>

                                    </c:when>

                                    <c:otherwise>
                                        <form action="#" method="post" id="commentform">

                                            <p class="comment-notes"><a href="../login-module/login-page"><h3 style="text-align: center">请先登录再进行操作</h3><span class="required"></span></p>

                                        </form>
                                    </c:otherwise>
                                </c:choose>

                    </div>

                </section><!-- end of comments -->

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
                            <li><a href="articles-list">文章列表</a></li>
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
    var currentBuildNum = 0;
    var currentCommentReplyTotal = 0;
    var currentCommentName = 0;
    var count = 0;
    var tempCommentId = 0;

    $("#submit").click(function () {
        var commentText = document.getElementById("comment").value
        var postId = document.getElementById("postId").value

        if (commentText == ""){
            alert("输入内容不能为空")
            return false
        }
        var userId = ${sessionScope.user.id}

        $.ajax({
            url:'addComment',
            data:JSON.stringify({"userId":userId,"postId":postId,"commentText":commentText}),
            type:'POST',
            contentType:'application/json;charset=utf-8',
            success:function (data) {
                if(data){
                    window.location.reload(false)
                }
            },
            error:function () {
                alert("系统出错")
            }
        })
        return false
    })

    $("input[id^='commentCommentSubmit']").click(function () {
        var id = this.id.substring(20,this.id.length)
        var text = document.getElementById("commentCommentText" + id).value
        var postId = document.getElementById("postId").value
        var trueCommentId = document.getElementById("trueCommentId"+id).value
        $.ajax({
            url:'addReplyForComment',
            data:JSON.stringify({"replyReply":text,"postId":postId,"commentId":trueCommentId}),
            contentType:'application/json;charset=utf-8',
            type:'POST',
            success:function (data) {
                if(data){
                    window.location.reload(false)
                }else{
                    alert("参数出错")
                }
            },
            error:function () {
                alert("系统出错")
            }
        })
    })

    $("input[id^='replyCommentSubmit']").click(function () {
        var id = this.id.substring(18,this.id.length)
        var text = "<h3 style='color: #33cccc'>回复了" + currentBuildNum + "楼</h3><br><br>"+document.getElementById("replyCommentText"+id).value
        var postId = document.getElementById("postId").value
        var commentId = id.split("-")[0]
        var trueCommentId = document.getElementById("trueCommentId"+commentId).value
        if(count == 0){
            tempCommentId = commentId
        }else{
            if(tempCommentId != commentId){
                tempCommentId = commentId
                count = 0
            }
        }
        if(text){
            $.ajax({
                url:'addReplyForComment',
                type:'POST',
                data:JSON.stringify({"replyReply":text,"postId":postId,"commentId":trueCommentId}),
                contentType:'application/json;charset=utf-8',
                dataType:'json',
                success:function (data) {
                    if(data!=null){
                        var newReplyCreatetime = data['replyCreatetime']
                        var newReplyId = data['replyId']
                        var newReplyReply = data['replyReply']
                        var newHeadAddress = data['replyUser']['headAddress']
                        var newUserId = data['replyUser']['id']
                        var newUserName = data['replyUser']['name']

                        if(count == 0){
                            currentCommentReplyTotal = document.getElementById("temp" + commentId).value
                            currentCommentName = document.getElementById("tempName" + commentId).value
                        }else{
                            currentCommentReplyTotal = currentCommentReplyTotal + 1
                        }
                        $("#children"+commentId).append("" +
                            "<li class='comment byuser comment-author-saqib-sarwar bypostauthor odd alt depth-2' id='li-comment-"+ commentId +"-"+currentCommentReplyTotal+"'>"
                            +"<article id='comment-"+commentId+"-"+currentCommentReplyTotal+"'>"

                            +"<a href='#'>"
                                +"<img src='"+newHeadAddress+"' class='avatar avatar-60 photo' height='60' width='60'>"
                            +"</a>"

                            +"<div class='comment-meta'>"

                            +"<h5 class='author'>"
                            +"<cite class='fn'>"+newUserName+"</cite>"
                            +"-"+ "<a class='comment-reply-link' href='#'>的回复</a><h4 style='text-align: right'>"+ (parseInt(currentCommentReplyTotal)+1) +"楼</h4>"
                            +"</h5>"

                            +"<p class='date'>"
                            +"<a href='#'>"
                            +"<time>"+newReplyCreatetime+"</time>"
                            +"</a>"
                            +"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='#' id='replyComment"+commentId+"-"+currentCommentReplyTotal+"' style='color: #0e0e0e'>回复</a>"
                            +"<br>"
                            +"<br>"
                            +"<input id='replyCommentText"+commentId+"-"+currentCommentReplyTotal+"' type='text' style='height: 50px;width: 800px;display: none'>"
                            +"<br>"
                            +"<input type='submit' id='replyCommentSubmit"+commentId+"-"+currentCommentReplyTotal+"' style='display: none' value='回复'>"
                            +"</p>"

                            +"</div>"

                            +"<div class='comment-body'>"
                            +"<p>"+newReplyReply+"</p>"
                            +"</div>"

                            +"</article>"

                            +"</li>" +
                            "")

                    }else {
                        alert("参数错误")
                    }
                },
                error:function () {
                    alert("您还未登陆")
                    window.location.href = '../login-module/login-page'
                }
            })
        }else {
            alert("你还未输入内容")
            return false
        }
    })

    $("a[id^='replyComment']").click(function () {
        var id = this.id.substring(12,this.id.length)
        currentBuildNum = this.id.substring(this.id.length-1,this.id.length)
        if(document.getElementById("replyCommentText"+id).style.display == 'none' && document.getElementById("replyCommentSubmit"+id).style.display == 'none'){
            document.getElementById("replyCommentText"+id).style.display = ""
            document.getElementById("replyCommentSubmit"+id).style.display = ""
        }else{
            document.getElementById("replyCommentText"+id).style.display = "none"
            document.getElementById("replyCommentSubmit"+id).style.display = "none"
        }
        return false
    })

    $("a[id^='commentComment']").click(function () {
        var id = this.id.substring(14,this.id.length)
        if(document.getElementById("commentCommentText"+id).style.display == 'none' && document.getElementById("commentCommentSubmit"+id).style.display == 'none'){
            document.getElementById("commentCommentText"+id).style.display = ""
            document.getElementById("commentCommentSubmit"+id).style.display = ""
        }else{
            document.getElementById("commentCommentText"+id).style.display = "none"
            document.getElementById("commentCommentSubmit"+id).style.display = "none"
        }
        return false
    })

    document.getElementById("pc").click(function () {
        var tmp = this.innerHTML
        $.ajax({
            url:'addClickTime',
            data:JSON.stringify({"username":username,"postname":postname}),
            contentType:'application/json;charset=utf-8',
            type:'POST',
            success:function (data) {
                if(data){
                    document.getElementById("pc").innerHTML = (parseInt(tmp)+1).toString();
                }
            }
        })
    })



</script>