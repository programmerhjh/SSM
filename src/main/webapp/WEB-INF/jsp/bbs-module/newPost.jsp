<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
        <!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7" lang="en-US"> <![endif]-->
        <!--[if IE 7]>    <html class="lt-ie9 lt-ie8" lang="en-US"> <![endif]-->
        <!--[if IE 8]>    <html class="lt-ie9" lang="en-US"> <![endif]-->
        <!--[if gt IE 8]><!--> <html lang="en-US"> <!--<![endif]-->
        <head>
                <!-- META TAGS -->
                <meta charset="UTF-8" />
                <meta name="viewport" content="width=device-width, initial-scale=1.0">

                <title>发表帖子</title>

                <link rel="shortcut icon" href="../images/favicon.png" />


                

                <!-- Style Sheet-->
                <link rel="stylesheet" href="../css/style.css"/>
                <link rel='stylesheet' id='bootstrap-css-css'  href='../css/bootstrap5152.css?ver=1.0' type='text/css' media='all' />
                <link rel='stylesheet' id='responsive-css-css'  href='../css/responsive5152.css?ver=1.0' type='text/css' media='all' />
                <link rel='stylesheet' id='pretty-photo-css-css'  href='../js/prettyphoto/prettyPhotoaeb9.css?ver=3.1.4' type='text/css' media='all' />
                <link rel='stylesheet' id='main-css-css'  href='../css/main5152.css?ver=1.0' type='text/css' media='all' />
                <link rel='stylesheet' id='custom-css-css'  href='../css/custom5152.html?ver=1.0' type='text/css' media='all' />


                <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
                <!--[if lt IE 9]>
                <![endif]-->
        </head>
        <body>

                <div class="header-wrapper">
                        <header>
                                <div class="container">

                                        <div class="logo-container">
                                                <!-- Website Logo -->
                                                <h4>
                                                        <a href="../bbs-module/index" style="color: #a9dba9">
                                                                问坛 &nbsp;&nbsp;&nbsp;
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
                <!-- End of Search Wrapper -->

                <!-- Start of Page Container -->
                <div class="page-container">
                        <div class="container">
                                <div class="row">
                                        <!-- start of page content -->
                                        <div class="span8 page-content">
                                                <article class="type-page hentry clearfix">
                                                        <h1 class="post-title">
                                                                <a href="#">发表帖子</a>
                                                        </h1>
                                                        <hr>
                                                        <p>发帖要讲究内涵和精神，好的帖子更容易上热门噢</p>
                                                </article>

                                                <form id="post-form" class="row" action="addPost" method="post">

                                                        <input type="hidden" id="postAuthor" name="postAuthor" value="${user.id}">

                                                        <div class="span2">
                                                                <label for="name" >作者： <span>*</span> </label>
                                                        </div>

                                                        <div class="span6">
                                                                <input type="text" name="name" id="name" class="required input-xlarge" disabled="true" value="${user.name}">
                                                        </div>


                                                        <div class="span2">
                                                                <label for="postName">帖子标题： <span>*</span></label>
                                                        </div>
                                                        <div class="span6">
                                                                <input type="text" name="postName" id="postName" class="input-xlarge" title="* Please provide a valid email address">
                                                        </div>

                                                        <div class="span2">
                                                                <label for="postCategory">种类：</label>
                                                        </div>
                                                        <div class="span6">
                                                                <input type="text" name="postCategory" id="postCategory" class="input-xlarge" value="">
                                                        </div>

                                                        <div class="span2">
                                                                <label>帖子内容 <span>*</span> </label>
                                                        </div>

                                                        <div class="span6" id="postPostDiv">

                                                        </div>

                                                        <div class="span6 offset2 bm30">
                                                                <input type="submit" name="submit" id="submit" value="发表帖子" class="btn btn-inverse" onclick="return sb()">
                                                                <img src="../images/loading.gif" id="contact-loader" alt="Loading...">
                                                        </div>


                                                        <div class="span6 offset2 error-container"></div>
                                                        <div class="span8 offset2" id="message-sent"></div>

                                                </form>
                                        </div>
                                        <!-- end of page content -->


                                        <!-- start of sidebar -->
                                        <aside class="span4 page-sidebar">

                                                <section class="widget">
                                                        <div class="support-widget">
                                                                <h3 class="title">Tip</h3>
                                                                <p class="intro">对本网站有问题?<a href="">可以与管理员联系噢~</a></p>
                                                        </div>
                                                </section>

                                                <section class="widget">
                                                        <h3 class="title">最新文章</h3>
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


                                        </aside>
                                        <!-- end of sidebar -->
                                </div>
                        </div>
                </div>
                <!-- End of Page Container -->

                <!-- Start of Footer -->
                <footer id="footer-wrapper">
                        <div id="footer" class="container">

                        </div>
                </footer>
                <!-- End of Footer -->

                <a href="#top" id="scroll-top"></a>

                <!-- script -->
                <script type='text/javascript' src='../js/jquery-1.8.3.min.js'></script>
                <script type='text/javascript' src='../js/jquery.easing.1.34e44.js?ver=1.3'></script>
                <script type='text/javascript' src='../js/prettyphoto/jquery.prettyPhotoaeb9.js?ver=3.1.4'></script>
                <script type='text/javascript' src='../js/jquery.liveSearchd5f7.js?ver=2.0'></script>
				<script type='text/javascript' src='../js/jflickrfeed.js'></script>
                <script type='text/javascript' src='../js/jquery.formd471.js?ver=3.18'></script>
                <script type='text/javascript' src='../js/jquery.validate.minfc6b.js?ver=1.10.0'></script>
                <script type='text/javascript' src='../js/custom5152.js?ver=1.0'></script>

        </body>
</html>
<script type="text/javascript" src="../js/wangEditor.js"></script>
<script type="text/javascript">

    $(document).ready(function checkLogin() {
        if(${sessionScope.user == null}){
            alert("您还未登陆")
            window.location.href = '../login-module/login-page'
        }
    })

    var E = window.wangEditor;
    var editor = new E('#postPostDiv');
    editor.customConfig.menus = [
        'bold',  // 粗体
        'italic',  // 斜体
        'underline',  // 下划线
        'strikeThrough',  // 删除线
        'foreColor',  // 文字颜色
        'backColor',  // 背景颜色
        'link',  // 插入链接
        'list',  // 列表
        'justify',  // 对齐方式
        'quote',  // 引用
        'emoticon',  // 表情
        'image',  // 插入图片
        'table',  // 表格
        'video',  // 插入视频
        'undo',  // 撤销
        'redo'  // 重复
    ]
    editor.customConfig.uploadImgServer = 'upload';
    editor.create();

    function sb() {

        var s = document.getElementById("postPostDiv").getElementsByTagName("div")[18].innerHTML
        var liNode = document.createElement("input");
        if(document.getElementById("postPost")){
            document.getElementById("postPostDiv").removeChild(document.getElementById("postPostDiv").lastChild)
        }
        liNode.value = s
        liNode.name = "postPost"
        liNode.id = "postPost"
        liNode.type = "hidden"
        insertAfter(liNode,document.getElementById("postPostDiv").lastChild)
        if(document.getElementById("postName").value == "" || editor.txt.text() == ""){
            alert("帖子名称或内容不能为空")
            return false
        }

        return confirm("确认提交？")

    }


    function insertAfter( newElement, targetElement ){
        var parent = targetElement.parentNode;
        if( parent.lastChild == targetElement ){
            parent.appendChild(newElement);
        }else{
            parent.insertBefore( newElement, targetElement.nextSibling );
        }
    }
</script>

