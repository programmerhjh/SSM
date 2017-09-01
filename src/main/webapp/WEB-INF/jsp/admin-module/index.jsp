<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>管理员管理界面</title>
    <meta name="description" content="这是一个 index 页面">
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="icon" type="image/png" href="../i/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="../i/app-icon72x72@2x.png">
    <meta name="apple-mobile-web-app-title" content="Amaze UI" />
    <link rel="stylesheet" href="../css/amazeui.min.css" />
    <link rel="stylesheet" href="../css/admin.css">
    <link rel="stylesheet" href="../css/app.css">
    <script src="../js/echarts.min.js"></script>
</head>

<body data-type="index" id="body">

    <header class="am-topbar am-topbar-inverse admin-header">
        <div class="am-topbar-brand">
            <a href="javascript:;" class="tpl-logo">
                <img src="../images/logo.png" alt="">
            </a>
        </div>
        <div class="am-icon-list tpl-header-nav-hover-ico am-fl am-margin-right">

        </div>

        <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span></button>

        <div class="am-collapse am-topbar-collapse" id="topbar-collapse">

            <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list tpl-header-list">

                <li class="am-hide-sm-only"><a href="javascript:;" id="admin-fullscreen" class="tpl-header-list-link"><span class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span></a></li>

                <li class="am-dropdown" data-am-dropdown data-am-dropdown-toggle>
                    <span class="tpl-header-list-user-nick">你好，管理员 ${admin.adminName}</span>
                    <span class="tpl-header-list-user-ico"> <img src="../images/user01.png"></span>
                </li>
                <li><a href="loginOut" class="tpl-header-list-link"><span class="am-icon-sign-out tpl-header-list-ico-out-size"></span></a></li>
            </ul>
        </div>
    </header>

    <div class="tpl-page-container tpl-page-header-fixed">

        <div class="tpl-left-nav tpl-left-nav-hover">
            <div class="tpl-left-nav-title">
                管理操作列表
            </div>
            <div class="tpl-left-nav-list">
                <ul class="tpl-left-nav-menu">


                    <li class="tpl-left-nav-item">
                        <a href="javascript:;" id="userManageButton" class="nav-link tpl-left-nav-link-list">
                            <i class="am-icon-table"></i>
                            <span>用户管理</span>
                        </a>
                    </li>

                    <li class="tpl-left-nav-item">
                        <a href="javascript:;" id="postManageButton" class="nav-link tpl-left-nav-link-list">
                            <i class="am-icon-wpforms"></i>
                            <span>帖子管理</span>
                        </a>
                    </li>

                    <li class="tpl-left-nav-item" >
                        <a href="javascript:;" id="commentManageButton" class="nav-link tpl-left-nav-link-list">
                            <i class="am-icon-bar-chart"></i>
                            <span>评论管理</span>
                        </a>
                    </li>

                    <li class="tpl-left-nav-item">
                        <a href="javascript:;" class="nav-link tpl-left-nav-link-list" id="upFile">
                            <i class="am-icon-key"></i>
                            <span>通知文件上传 / 下载</span>
                        </a>
                    </li>

                </ul>
            </div>
        </div>

        <div class="tpl-content-wrapper" id="userManage" style="display: none" >
            <div class="tpl-content-page-title">
                用户管理
            </div>
            <div class="tpl-portlet-components">
                <div class="portlet-title">

                    <div class="caption font-green bold">
                        <span class="am-icon-code"></span> 用户列表
                    </div>

                    <div class="tpl-portlet-input tpl-fz-ml">
                    </div>

                </div>


                <div class="tpl-block">
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6">
                            <div class="am-btn-toolbar">
                                <div class="am-btn-group am-btn-group-xs">
                                    <button type="button" id="deleteAllUser" class="am-btn am-btn-default am-btn-danger"><span class="am-icon-trash-o"></span> 批量删除</button>
                                </div>
                            </div>
                        </div>

                        <div class="am-u-sm-12">
                            <form class="am-form">
                                <table class="am-table am-table-striped am-table-hover table-main">
                                    <thead>
                                    <tr>
                                        <th class="table-check"><input type="checkbox" id="userAllCheckbox" class="tpl-table-fz-check"></th>
                                        <th class="table-id">ID</th>
                                        <th class="table-title">姓名</th>
                                        <th class="table-type">年龄</th>
                                        <th class="table-type">电话</th>
                                        <th class="table-type">邮箱</th>
                                        <th class="table-date am-hide-sm-only">生日</th>
                                        <th class="table-date am-hide-sm-only">注册日期</th>
                                        <th class="table-date am-hide-sm-only">住址</th>
                                        <th class="table-set">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody id="userManageData">

                                    </tbody>
                                </table>
                                <hr>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="tpl-alert">

                </div>
            </div>

            <div id="paginationUser" style="text-align: right">
                <select  class="btn" id="selectUserPage">

                </select>
            </div>

        </div>

        <div class="tpl-content-wrapper" id="commentManage"style="display: none" >
            <div class="tpl-content-page-title">
                评论管理
            </div>
            <div class="tpl-portlet-components">
                <div class="portlet-title">

                    <div class="caption font-green bold">
                        <span class="am-icon-code"></span> 评论列表
                    </div>

                    <div class="tpl-portlet-input tpl-fz-ml">
                    </div>

                </div>


                <div class="tpl-block">
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6">
                            <div class="am-btn-toolbar">
                                <div class="am-btn-group am-btn-group-xs">
                                    <button type="button" class="am-btn am-btn-default am-btn-danger" id="deleteAllComment"><span class="am-icon-trash-o"></span>批量删除</button>
                                </div>
                            </div>
                        </div>

                        <div class="am-u-sm-12">
                            <form class="am-form">
                                <table class="am-table am-table-striped am-table-hover table-main">
                                    <thead>
                                    <tr>
                                        <th class="table-check"><input type="checkbox" id="commentAllCheckbox" class="tpl-table-fz-check"></th>
                                        <th class="table-id">ID</th>
                                        <th class="table-title">回复人</th>
                                        <th class="table-type">回复时间</th>
                                        <th class="table-text">回复帖子</th>
                                        <th class="table-type">回复内容</th>
                                        <th class="table-set">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody id="commentManageData">

                                    </tbody>
                                </table>
                                <hr>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="tpl-alert">

                </div>
            </div>

            <div id="paginationComment" style="text-align: right">
                <select  class="btn" id="selectCommentPage">

                </select>
            </div>

        </div>

        <div class="tpl-content-wrapper" id="postManage"style="display: none">
                <div class="tpl-content-page-title">
                    帖子管理
                </div>
                <div class="tpl-portlet-components">
                    <div class="portlet-title">
                        <div class="caption font-green bold">
                            <span class="am-icon-code"></span> 帖子列表
                        </div>
                        <div class="tpl-portlet-input tpl-fz-ml">

                        </div>
                    </div>
                    <div class="tpl-block">
                        <div class="am-g">
                            <div class="am-u-sm-12 am-u-md-6">
                                <div class="am-btn-toolbar">
                                    <div class="am-btn-group am-btn-group-xs">

                                        <button type="button" class="am-btn am-btn-default am-btn-danger" id="deleteAllPost" ><span class="am-icon-trash-o"></span>批量删除</button>

                                        <div class="div1">

                                            <div class="div2">导入帖子Excel数据</div>

                                            <input type="file" name="fileExcelTran" class="inputstyle" id="uploadExcel" onchange="uploadExcel()">

                                            <style>
                                                .div1{

                                                    float: right;

                                                    height: 50px;

                                                    background: #f5696c;

                                                    width: 50px;

                                                    position:relative;

                                                }

                                                .div2{

                                                    text-align:right;

                                                    padding-top:12px;

                                                    font-size:15px;

                                                    font-weight:100

                                                }

                                                .inputstyle{

                                                    width: 50px;

                                                    height: 41px;

                                                    cursor: pointer;

                                                    font-size: 20px;

                                                    outline: medium none;

                                                    position: absolute;

                                                    filter:alpha(opacity=0);

                                                    -moz-opacity:0;

                                                    opacity:0;

                                                    left:0px;

                                                    top: 0px;

                                                }

                                            </style>
                                        </div>
                                        <div class="div1">

                                            <div class="div2">导出数据库帖子数据</div>

                                            <input type="button" class="inputstyle" id="downloadPostExcel" onclick="return downloadPostExcel()">

                                            <style>
                                                .div1{

                                                    float: right;

                                                    height: 50px;

                                                    background: #f5696c;

                                                    width: 50px;

                                                    position:relative;

                                                }

                                                .div2{

                                                    text-align:right;

                                                    padding-top:12px;

                                                    font-size:15px;

                                                    font-weight:100

                                                }

                                                .inputstyle{

                                                    width: 50px;

                                                    height: 41px;

                                                    cursor: pointer;

                                                    font-size: 20px;

                                                    outline: medium none;

                                                    position: absolute;

                                                    filter:alpha(opacity=0);

                                                    -moz-opacity:0;

                                                    opacity:0;

                                                    left:0px;

                                                    top: 0px;

                                                }

                                            </style>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="am-u-sm-12">
                                <form class="am-form">
                                    <table class="am-table am-table-striped am-table-hover table-main">
                                        <thead>
                                        <tr>
                                            <th class="table-check"><input type="checkbox" id="postAllCheckbox" class="tpl-table-fz-check"></th>
                                            <th class="table-id">ID</th>
                                            <th class="table-title">文章标题</th>
                                            <th class="table-type">作者</th>
                                            <th class="table-type">类别</th>
                                            <th class="table-date am-hide-sm-only">创建日期</th>
                                            <th class="table-date am-hide-sm-only">点击量</th>
                                            <th class="table-set">操作</th>
                                        </tr>
                                        </thead>
                                        <tbody id="postManageData">

                                        </tbody>
                                    </table>
                                    <hr>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="tpl-alert"></div>
                </div>
                <div id="paginationPost" style="text-align: right">
                <select  class="btn" id="selectPostPage">
                </select>
            </div>

        </div>

        <div class="tpl-content-wrapper" id="fileUploadDownload"style="display: none" >
            <div class="tpl-content-page-title">
                文件上传下载
            </div>
                <div class="tpl-portlet-components">
                    <div class="portlet-title">
                        <div class="caption font-green bold">
                            <span class="am-icon-code"></span> 文件列表
                        </div>
                        <div class="tpl-portlet-input tpl-fz-ml">

                        </div>
                    </div>


                <div class="tpl-block">
                    <div class="am-g">

                        <div class="am-u-sm-12 am-u-md-6">
                            <div class="am-btn-toolbar">
                                <div class="am-btn-group am-btn-group-xs">
                                    <div class="div1">

                                        <div class="div2">上传</div>

                                        <input type="file" name="fileTran" class="inputstyle" id="fileUploadText" onchange="upload()">

                                        <style>
                                            .div1{

                                                float: left;

                                                height: 41px;

                                                background: #f5696c;

                                                width: 144px;

                                                position:relative;

                                            }

                                            .div2{

                                                text-align:center;

                                                padding-top:12px;

                                                font-size:15px;

                                                font-weight:800

                                            }

                                            .inputstyle{

                                                width: 144px;

                                                height: 41px;

                                                cursor: pointer;

                                                font-size: 30px;

                                                outline: medium none;

                                                position: absolute;

                                                filter:alpha(opacity=0);

                                                -moz-opacity:0;

                                                opacity:0;

                                                left:0px;

                                                top: 0px;

                                            }

                                        </style>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="am-u-sm-12">
                            <form class="am-form">
                                <table class="am-table am-table-striped am-table-hover table-main">
                                    <thead>
                                        <tr>
                                            <th class="table-id">ID</th>
                                            <th class="table-title">文件名</th>
                                            <th class="table-type">大小</th>
                                            <th class="table-date am-hide-sm-only">创建日期</th>
                                            <th class="table-set">操作</th>
                                        </tr>
                                    </thead>
                                    <tbody id="files">

                                    </tbody>
                                </table>
                                <hr>
                            </form>
                        </div>
                 </div>
                <div class="tpl-alert"></div>
            </div>
        </div>

            <div class="tpl-content-wrapper" id="tableGuide" style="display: none">

            </div>

            <div id="paginationFile" style="text-align: right">
                <select  class="btn" id="selectFilePage">
                </select>
            </div>

        </div>

    </div>

    <script type='text/javascript' src='../js/jquery-1.8.3.min.js'></script>
    <script src="../js/amazeui.min.js"></script>
    <script src="../js/iscroll.js"></script>
    <script src="../js/app.js"></script>
</body>


<script type="text/javascript">

    <%-- 批量删除用户按钮 --%>
    $("#deleteAllUser").live('click',function () {
        var temp = new Array();
        var tempHtmlId = new Array();
        $("input:checkbox[id^='userCheckbox']").each(function () {
            if(this.checked){
                var htmlId = this.id.substring(12,this.id.length)
                var id = document.getElementById("userId"+htmlId).value
                temp.push(id)
                tempHtmlId.push(htmlId)
            }
        })

        $.ajax({
            url:'deleteAllUser',
            data:JSON.stringify({"data":temp.toString()}),
            type:'POST',
            contentType:'application/json;charset=utf-8',
            success:function (data) {
                if(data){
                    tempHtmlId.forEach(function (value,index,array) {
                        var current = document.getElementById("user"+value)
                        current.remove()
                    })
                    document.getElementById("userManageButton").click
                }

            }
        })
    })

    <%-- 批量删除帖子按钮 --%>
    $("#deleteAllPost").live('click',function () {
        var temp = new Array();
        var tempHtmlId = new Array();

        $("input:checkbox[id^='postCheckbox']").each(function () {
            if(this.checked){
                var htmlId = this.id.substring(12,this.id.length)
                var id = document.getElementById("postId"+htmlId).value
                temp.push(id)
                tempHtmlId.push(htmlId)
            }
        })

        $.ajax({
            url:'deleteAllPost',
            data:JSON.stringify({"data":temp.toString()}),
            type:'POST',
            contentType:'application/json;charset=utf-8',
            success:function (data) {
                if(data){
                    tempHtmlId.forEach(function (value,index,array) {
                        var current = document.getElementById("post"+value)
                        current.remove()
                    })
                    document.getElementById("postManageButton").click
                }
            }
        })
    })

    <%-- 批量删除评论按钮 --%>
    $("#deleteAllComment").live('click',function () {
        var temp = new Array();
        var tempHtmlId = new Array();
        $("input:checkbox[id^='commentCheckbox']").each(function () {
            if(this.checked){
                var htmlId = this.id.substring(15,this.id.length)
                var id = document.getElementById("commentId"+htmlId).value
                temp.push(id)
                tempHtmlId.push(htmlId)
            }
        })


        $.ajax({
            url:'deleteAllComment',
            data:JSON.stringify({"data":temp.toString()}),
            type:'POST',
            contentType:'application/json;charset=utf-8',
            success:function (data) {
                if(data){
                    tempHtmlId.forEach(function (value,index,array) {
                        var current = document.getElementById("comment"+value)
                        current.remove()
                    })
                    document.getElementById("commentManageButton").click
                }
            }
        })
    })

    $("input:checkbox[id^='commentCheckbox']").live('click',function () {

        var s = true

        $("input:checkbox[id^='commentCheckbox']").each(function () {
            if(!this.checked){
                s = false
            }
        })

        if(s){
            $("#commentAllCheckbox").attr('checked',true)
        }else {
            $("#commentAllCheckbox").attr('checked',false)
        }

    })

    $("input:checkbox[id^='userCheckbox']").live('click',function () {
        var s = true
        $("input:checkbox[id^='userCheckbox']").each(function () {
            if(!this.checked){
                s = false
            }
        })
        if(s){
            $("#userAllCheckbox").attr('checked',true)
        }else {
            $("#userAllCheckbox").attr('checked',false)
        }
    })

    $("input:checkbox[id^='postCheckbox']").live('click',function () {
            var s = true;
            $("input:checkbox[id^='postCheckbox']").each(function () {
                if(!this.checked){
                    s = false
                }
            })
            if(s){
                $("#postAllCheckbox").attr('checked',true)
            }else {
                $("#postAllCheckbox").attr('checked',false)
            }
    })

    $("#userAllCheckbox").click(function () {
        if(this.checked){
            $("input:checkbox[id^='userCheckbox']").each(function () {
                this.checked=true
            })
        }else {
            $("input:checkbox[id^='userCheckbox']").each(function () {
                this.checked=false
            })
        }
    })

    $("#postAllCheckbox").click(function () {
        if(this.checked){
            $("input:checkbox[id^='postCheckbox']").each(function () {
                this.checked=true
            })
        }else {
            $("input:checkbox[id^='postCheckbox']").each(function () {
                this.checked=false
            })
        }
    })

    $("#commentAllCheckbox").click(function () {
        if(this.checked){
            $("input:checkbox[id^='commentCheckbox']").each(function () {
                this.checked=true
            })
        }else {
            $("input:checkbox[id^='commentCheckbox']").each(function () {
                this.checked=false
            })
        }
    })

    $("option[id^='chooseUserPage']").live('click',function () {
        var targetPage = this.value
        $.ajax({
            url:'userManagePage',
            data:JSON.stringify({"targetPage":parseInt(targetPage)}),
            type:'POST',
            contentType:'application/json;charset=utf-8',
            dataType:'json',
            success:function (data) {
                var list = data['list']
                var list2 = data['navigatepageNums']
                $("#userManageData").empty()
                $("#selectUserPage").empty()
                list.forEach(function (value,index,array)
                {
                    $("#userManageData").append(
                        "<tr id='user"+index+"'>"+
                        "<td>"+"<input type='checkbox' id='userCheckbox"+index+"'>"+"</td>"+
                        "<td>"+(index+1)+"</td>"+
                        "<input type='hidden' id='userId"+index+"' value='"+list[index]['id']+"'>"+
                        "<td>"+list[index]['name']+"</td>"+
                        "<td>"+list[index]['age']+"</td>"+
                        "<td>"+list[index]['phone']+"</td>"+
                        "<td>"+list[index]['email']+"</td>"+
                        "<td class='am-hide-sm-only'>"+new Date(list[index]['birth']).Format("yyyy-MM-dd")+"</td>"+
                        "<td class='am-hide-sm-only'>"+list[index]['createtime']+"</td>"+
                        "<td>"+list[index]['address']+"</td>"+
                        "<td>"+
                        "<div class='am-btn-toolbar'>"+
                        "<div class='am-btn-group am-btn-group-xs'>"+
                        "<button class='am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only' id='deleteUser"+index+"'>"+"<span class='am-icon-trash-o'>"+"</span>删除</button>"+
                        "</div>"+
                        "</div>"+
                        "</td>"+
                        "<tr>"
                    )

                })

                $("#selectUserPage").append(
                    "<option selected='selected' id='currentPage' value='" + data['pageNum'] +"'>"+
                    "当前第"+ data['pageNum']+" 页 / 共"+ data['pages']+"页"+
                    "</option>"
                )

                list2.forEach(function (e) {
                    if(e != data['pageNum']){
                        $("#selectUserPage").append(
                            "<option value='"+ e +"' id='chooseUserPage" + e + "' >"+"第" + e + " 页</option>"
                        )
                    }
                })
            }
        })
    })

    $("option[id^='choosePostPage']").live('click',function () {
        var targetPage = this.value
        $.ajax({
            url:'postManagePage',
            data:JSON.stringify({"targetPage":parseInt(targetPage)}),
            type:'POST',
            contentType:'application/json;charset=utf-8',
            dataType:'json',
            success:function (data) {
                var list = data['list']
                var list2 = data['navigatepageNums']
                $("#postManageData").empty()
                $("#selectPostPage").empty()
                list.forEach(function (value,index,array)
                {
                    $("#postManageData").append(
                        "<tr id='post"+index+"'>"+
                        "<td>"+"<input type='checkbox' id='postCheckbox"+index+"'>"+"</td>"+
                        "<input type='hidden' id='postId"+index+"' value='"+list[index]['postId']+"'>"+
                        "<td>"+(index+1)+"</td>"+
                        "<td>"+list[index]['postName']+"</td>"+
                        "<td>"+list[index]['user']['name']+"</td>"+
                        "<td>"+list[index]['postCategory']+"</td>"+
                        "<td class='am-hide-sm-only'>"+list[index]['postCreatetime']+"</td>"+
                        "<td class='am-hide-sm-only'>"+list[index]['postClicktimes']+"</td>"+
                        "<td>"+
                        "<div class='am-btn-toolbar'>"+
                        "<div class='am-btn-group am-btn-group-xs'>"+
                        "<button class='am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only' id='deletePost"+index+"'>"+"<span class='am-icon-trash-o'>"+"</span>删除</button>"+
                        "</div>"+
                        "</div>"+
                        "</td>"+
                        "<tr>"
                    )
                })

                $("#selectPostPage").append(
                    "<option selected='selected' id='currentPage' value='" + data['pageNum'] +"'>"+
                    "当前第"+ data['pageNum']+" 页 / 共"+ data['pages']+"页"+
                    "</option>"
                )

                list2.forEach(function (e) {
                    if(e != data['pageNum']){
                        $("#selectPostPage").append(
                            "<option value='"+ e +"' id='choosePostPage" + e + "' >"+"第" + e + " 页</option>"
                        )
                    }
                })

            }
        })
    })

    $("option[id^='chooseCommentPage']").live('click',function () {
        var targetPage = this.value
        $.ajax({
            url:'commentManagePage',
            data:JSON.stringify({"targetPage":parseInt(targetPage)}),
            type:'POST',
            contentType:'application/json;charset=utf-8',
            dataType:'json',
            success:function (data) {
                var list = data['list']
                var list2 = data['navigatepageNums']
                $("#commentManageData").empty()
                $("#selectCommentPage").empty()
                list.forEach(function (value,index,array) {
                    $("#commentManageData").append(
                        "<tr id='comment"+index+"'>"+
                        "<td>"+"<input type='checkbox' id='commentCheckbox"+index+"'>"+"</td>"+
                        "<input type='hidden' id='commentId"+index+"' value='"+list[index]['commentId']+"'>"+
                        "<td>"+(index+1)+"</td>"+
                        "<td>"+list[index]['user']['name']+"</td>"+
                        "<td>"+list[index]['commentCreatetime']+"</td>"+
                        "<td>"+list[index]['post']['postName']+"</td>"+
                        "<td>"+list[index]['commentComment']+"</td>"+
                        "<td>"+
                        "<div class='am-btn-toolbar'>"+
                        "<div class='am-btn-group am-btn-group-xs'>"+
                        "<button id='deleteComment"+index+"' class='am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only'>"+"<span class='am-icon-trash-o'>"+"</span>删除</button>"+
                        "</div>"+
                        "</div>"+
                        "</td>"+
                        "<tr>"
                    )
                })

                $("#selectCommentPage").append(
                    "<option selected='selected' id='currentPage' value='" + data['pageNum'] +"'>"+
                    "当前第"+ data['pageNum']+" 页 / 共"+ data['pages']+"页"+
                    "</option>"
                )

                list2.forEach(function (e) {
                    if(e != data['pageNum']){
                        $("#selectCommentPage").append(
                            "<option value='"+ e +"' id='chooseCommentPage" + e + "' >"+"第" + e + " 页</option>"
                        )
                    }
                })
            }
        })
    })

    $("option[id^='chooseFilePage']").live("click",function () {
        var targetPage = this.value
        $.ajax({
            cache:false,
            url:'upDownFilesOrTable',
            data:JSON.stringify({"pointPage":parseInt(targetPage)}),
            type:'POST',
            contentType:'application/json;charset=utf-8',
            dataType:'json',
            success:function (data) {
                var fileNameList = data['data'][0]
                var fileTimeList = data['data'][1]
                var fileSize = data['data'][2]
                var pages = data['pages']
                $("#files").empty()
                $("#selectFilePage").empty()
                fileNameList.forEach(function (value,index,array) {
                    $("#files").append(
                        "<tr id='file"+ index +"'>"+
                        "<td>"+(index+1)+"</td>"+
                        "<td id='fileName"+index+"'>"+value+"</td>"+
                        "<td class='am-hide-sm-only'>"+fileSize[index]+"字节</td>"+
                        "<td class='am-hide-sm-only'>"+fileTimeList[index]+"</td>"+
                        "<td>"+
                        "<div class='am-btn-toolbar'>"+
                        "<div class='am-btn-group am-btn-group-xs'>"+
                        "<button class='am-btn am-btn-primary am-btn-xs am-text-warning am-hide-sm-only' id='downFile"+index+"' onclick='return downloadFile(this.id);'>"
                        +"<span class='am-icon-arrow-circle-o-down'><a id='pageFile"+index+"' href='http://39.108.68.200:8088/uploadFiles/"+value+"'/> "+"</span>下载</button>"+
                        "<button class='am-btn am-btn-primary am-btn-xs am-text-danger am-hide-sm-only' id='removeFile"+index+"' onclick='" +
                        "var RemoveId = this.id;return removeFile(RemoveId); " +
                        "'>"
                        +"<span class='am-icon-check'>"+"</span>删除</button>"+
                        "</div>"+
                        "</div>"+
                        "</td>"+
                        "</tr>"
                    )
                })

                $("#selectFilePage").append(
                    "<option selected='selected' id='currentPage' value='" + data['currentPage'] +"'>"+
                    "当前第"+ data['currentPage']+" 页 / 共"+ data['pages']+"页"+
                    "</option>"
                )


                for (var i=1;i<=data['pages'];i++){
                    if(i != data['currentPage']){
                        $("#selectFilePage").append(
                            "<option value='"+ i +"' id='chooseFilePage" + i + "' >"+"第" + i + " 页</option>"
                        )
                    }
                }

            }
        })
    });

    $("#commentManageButton").live("click",function () {
        checkDisplay("commentManage")
        $("#commentAllCheckbox").attr('checked',false)
        $.ajax({
            url:'commentManage',
            data:{},
            type:'POST',
            contentType:'application/json;charset=utf-8',
            dataType:'json',
            success:function (data) {
                var list = data['list']
                var list2 = data['navigatepageNums']
                $("#commentManageData").empty()
                $("#selectCommentPage").empty()
                list.forEach(function (value,index,array) {
                    $("#commentManageData").append(
                        "<tr id='comment"+index+"'>"+
                        "<td>"+"<input type='checkbox' id='commentCheckbox"+index+"'>"+"</td>"+
                        "<input type='hidden' id='commentId"+index+"' value='"+list[index]['commentId']+"'>"+
                        "<td>"+(index+1)+"</td>"+
                        "<td>"+list[index]['user']['name']+"</td>"+
                        "<td>"+list[index]['commentCreatetime']+"</td>"+
                        "<td>"+list[index]['post']['postName']+"</td>"+
                        "<td>"+list[index]['commentComment']+"</td>"+
                        "<td>"+
                            "<div class='am-btn-toolbar'>"+
                                "<div class='am-btn-group am-btn-group-xs'>"+
                                    "<button id='deleteComment"+index+"' class='am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only'>"+"<span class='am-icon-trash-o'>"+"</span>删除</button>"+
                                "</div>"+
                            "</div>"+
                        "</td>"+
                        "<tr>"
                    )
                })

                $("#selectCommentPage").append(
                    "<option selected='selected' id='currentPage' value='" + data['pageNum'] +"'>"+
                    "当前第"+ data['pageNum']+" 页 / 共"+ data['pages']+"页"+
                    "</option>"
                )

                list2.forEach(function (e) {
                    if(e != data['pageNum']){
                        $("#selectCommentPage").append(
                            "<option value='"+ e +"' id='chooseCommentPage" + e + "' >"+"第" + e + " 页</option>"
                        )
                    }
                })
            }
        })
    });

    $("#postManageButton").live("click",function () {
        checkDisplay("postManage")
        $("#postAllCheckbox").attr('checked',false)
        $.ajax({
            url:'postManage',
            data:{},
            type:'POST',
            contentType:'application/json;charset=utf-8',
            dataType:'json',
            success:function (data) {
                var list = data['list']
                var list2 = data['navigatepageNums']
                $("#postManageData").empty()
                $("#selectPostPage").empty()
                list.forEach(function (value,index,array)
                {
                    $("#postManageData").append(
                        "<tr id='post"+index+"'>"+
                            "<td>"+"<input type='checkbox' id='postCheckbox"+index+"'>"+"</td>"+
                            "<input type='hidden' id='postId"+index+"' value='"+list[index]['postId']+"'>"+
                            "<td>"+(index+1)+"</td>"+
                            "<td>"+list[index]['postName']+"</td>"+
                            "<td>"+list[index]['user']['name']+"</td>"+
                            "<td>"+list[index]['postCategory']+"</td>"+
                            "<td class='am-hide-sm-only'>"+list[index]['postCreatetime']+"</td>"+
                            "<td class='am-hide-sm-only'>"+list[index]['postClicktimes']+"</td>"+
                            "<td>"+
                                "<div class='am-btn-toolbar'>"+
                                    "<div class='am-btn-group am-btn-group-xs'>"+
                                        "<button class='am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only' id='deletePost"+index+"'>"+"<span class='am-icon-trash-o'>"+"</span>删除</button>"+
                                    "</div>"+
                                "</div>"+
                            "</td>"+
                        "<tr>"
                    )
                })

                $("#selectPostPage").append(
                    "<option selected='selected' id='currentPage' value='" + data['pageNum'] +"'>"+
                    "当前第"+ data['pageNum']+" 页 / 共"+ data['pages']+"页"+
                    "</option>"
                )

                list2.forEach(function (e) {
                    if(e != data['pageNum']){
                        $("#selectPostPage").append(
                            "<option value='"+ e +"' id='choosePostPage" + e + "' >"+"第" + e + " 页</option>"
                        )
                    }
                })

            }
        })
    });

    $("#userManageButton").live("click",function () {
        checkDisplay("userManage")
        $("#userAllCheckbox").attr('checked',false)
        $.ajax({
            url:'userManage',
            data:{},
            type:'POST',
            contentType:'application/json;charset=utf-8',
            dataType:'json',
            success:function (data) {
                var list = data['list']
                var list2 = data['navigatepageNums']
                $("#userManageData").empty()
                $("#selectUserPage").empty()
                list.forEach(function (value,index,array)
                {
                    $("#userManageData").append(
                        "<tr id='user"+index+"'>"+
                            "<td>"+"<input type='checkbox' id='userCheckbox"+index+"'>"+"</td>"+
                            "<td>"+(index+1)+"</td>"+
                            "<input type='hidden' id='userId"+index+"' value='"+list[index]['id']+"'>"+
                            "<td>"+list[index]['name']+"</td>"+
                            "<td>"+list[index]['age']+"</td>"+
                            "<td>"+list[index]['phone']+"</td>"+
                            "<td>"+list[index]['email']+"</td>"+
                            "<td class='am-hide-sm-only'>"+new Date(list[index]['birth']).Format("yyyy-MM-dd")+"</td>"+
                            "<td class='am-hide-sm-only'>"+list[index]['createtime']+"</td>"+
                            "<td>"+list[index]['address']+"</td>"+
                            "<td>"+
                                "<div class='am-btn-toolbar'>"+
                                    "<div class='am-btn-group am-btn-group-xs'>"+
                                        "<button class='am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only' id='deleteUser"+index+"'>"+"<span class='am-icon-trash-o'>"+"</span>删除</button>"+
                                    "</div>"+
                                "</div>"+
                            "</td>"+
                        "<tr>"
                    )

                })

                $("#selectUserPage").append(
                    "<option selected='selected' id='currentPage' value='" + data['pageNum'] +"'>"+
                    "当前第"+ data['pageNum']+" 页 / 共"+ data['pages']+"页"+
                    "</option>"
                )

                list2.forEach(function (e) {
                    if(e != data['pageNum']){
                        $("#selectUserPage").append(
                            "<option value='"+ e +"' id='chooseUserPage" + e + "' >"+"第" + e + " 页</option>"
                        )
                    }
                })
            }
        })
    });

    $("button[id^='deleteUser']").live('click',function deleteUser() {
        var htmlId = this.id.substring(10,this.id.length)
        var id = document.getElementById("userId"+htmlId).value
        $.ajax({
            url:'deleteUser',
            data:JSON.stringify({"id":id}),
            type:'POST',
            contentType:'application/json;charset=utf-8',
            success:function (data) {
                if(data){
                    var current = document.getElementById("user"+htmlId)
                    current.remove()
                }
            },
            error:function () {
                alert("删除失败")
            }
        })

        return false
    })

    $("button[id^='deletePost']").live('click',function deletePost() {
        var htmlId = this.id.substring(10,this.id.length)
        var id = document.getElementById("postId"+htmlId).value
        $.ajax({
            url:'deletePost',
            data:JSON.stringify({"id":id}),
            type:'POST',
            contentType:'application/json;charset=utf-8',
            success:function (data) {
                if(data){
                    var current = document.getElementById("post"+htmlId)
                    current.remove()
                }
            },
            error:function () {
                alert("删除失败")
            }
        })

        return false
    })

    $("button[id^='deleteComment']").live('click',function deleteComment() {
        var htmlId = this.id.substring(13,this.id.length)
        var id = document.getElementById("commentId"+htmlId).value
        $.ajax({
            url:'deleteComment',
            data:JSON.stringify({"id":id}),
            type:'POST',
            contentType:'application/json;charset=utf-8',
            success:function (data) {
                if(data){
                    var current = document.getElementById("comment"+htmlId)
                    current.remove()
                }
            },
            error:function () {
                alert("删除失败")
            }
        })

        return false
    })

    $("#upFile").live("click",function () {
        checkDisplay("fileUploadDownload")
        $.ajax({
            url:'upDownFilesOrTable',
            data:{},
            type:'POST',
            contentType:'application/json;charset=utf-8',
            dataType:'json',
            success:function (data) {
                var fileNameList = data['data'][0]
                var fileTimeList = data['data'][1]
                var fileSize = data['data'][2]
                var pages = data['pages']
                $("#files").empty()
                $("#selectFilePage").empty()
                fileNameList.forEach(function (value,index,array) {
                    $("#files").append(
                        "<tr id='file"+ index +"'>"+
                        "<td>"+(index+1)+"</td>"+
                        "<td id='fileName"+index+"'>"+value+"</td>"+
                        "<td class='am-hide-sm-only'>"+fileSize[index]+"字节</td>"+
                        "<td class='am-hide-sm-only'>"+fileTimeList[index]+"</td>"+
                        "<td>"+
                        "<div class='am-btn-toolbar'>"+
                        "<div class='am-btn-group am-btn-group-xs'>"+
                        "<button class='am-btn am-btn-primary am-btn-xs am-text-warning am-hide-sm-only' id='downFile"+index+"' onclick='return downloadFile(this.id);'>"
                        +"<span class='am-icon-arrow-circle-o-down'><a id='pageFile"+index+"' href='http://39.108.68.200:8088/uploadFiles/"+value+"'/> "+"</span>下载</button>"+
                        "<button class='am-btn am-btn-primary am-btn-xs am-text-danger am-hide-sm-only' id='removeFile"+index+"' onclick='" +
                        "var RemoveId = this.id;return removeFile(RemoveId); " +
                        "'>"
                        +"<span class='am-icon-check'>"+"</span>删除</button>"+
                        "</div>"+
                        "</div>"+
                        "</td>"+
                        "</tr>"
                    )
                })

                $("#selectFilePage").append(
                    "<option selected='selected' id='currentPage' value='" + data['currentPage'] +"'>"+
                    "当前第"+ data['currentPage']+" 页 / 共"+ data['pages']+"页"+
                    "</option>"
                )


                for (var i=1;i<=data['pages'];i++){
                    if(i != data['currentPage']){
                        $("#selectFilePage").append(
                            "<option value='"+ i +"' id='chooseFilePage" + i + "' >"+"第" + i + " 页</option>"
                        )
                    }
                }

            }
        })
    });

    function downloadPostExcel() {
        $.ajax({
            url:'downloadExcelFile',
            data:{},
            type:'POST',
            contentType:'application/json;charset=utf-8',
            success:function (data) {
                $("#body").append(
                    "<a href='"+data+"' id='excelFile'/>"
                )
                $("#excelFile")[0].click();
                $("#excelFile").remove();
            }
        })

        return false;
    }

    function downloadFile(downFileId) {
        var id = downFileId.substring(8,downFileId.length)
        $("#body").append(
            "<a href='"+$("#pageFile"+id)[0]+"' id='pageClickFile'/>"
        )
        $("#pageClickFile")[0].click();
        $("#pageClickFile").remove();
        return false
    }

    function removeFile(removeFileId) {
        var removeId = removeFileId.substring(10,removeFileId.length)
        var removeFileName = document.getElementById("fileName"+removeId).innerHTML
        $.ajax({
            url:'removeFile',
            data:JSON.stringify({"fileName":removeFileName}),
            type:'POST',
            contentType:'application/json;charset=utf-8',
            success:function (data) {
                if(data=="success"){
                    var current = document.getElementById("file"+removeId)
                    current.remove()
                    return false
                }else{
                    alert("文件删除失败")
                    return false
                }
            },error:function (e) {
                alert(e)
            }
        })

        return false
    };

    Date.prototype.Format = function (fmt) {
        var o = {
            "M+": this.getMonth() + 1, //月份
            "d+": this.getDate(), //日
            "h+": this.getHours(), //小时
            "m+": this.getMinutes(), //分
            "s+": this.getSeconds(), //秒
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            "S": this.getMilliseconds() //毫秒
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    };

    function checkDisplay(id) {
        document.getElementById("userManage").style.display='none'
        document.getElementById("commentManage").style.display='none'
        document.getElementById("postManage").style.display='none'
        document.getElementById("fileUploadDownload").style.display='none'
        document.getElementById(id).style.display=''
    };

    function upload() {
        var s = new FormData();
        var files = $('input[name="fileTran"]').prop('files');//获取到文件列表
        if(files.length == 0){
            alert('请选择文件');
            return;
        }else{
            s.append("file", files[0]);
        }
        $.ajax({
            url:'uploadFile',
            type:'POST',
            data:s,
            processData:false,
            contentType:false,
            success:function (data) {
                if(data){
                    alert("上传成功")
                }else {
                    alert("上传失败")
                }
            }
        })
    };

    function uploadExcel() {
        var s = new FormData();
        var files = $('input[name="fileExcelTran"]').prop('files');//获取到文件列表
        if(files.length == 0){
            alert('请选择文件');
            return;
        }else{
            var suffix = files[0].name.split(".")[1]
            if(suffix == "xlsx"){
                alert("请把文件保存为xls格式，不要保存为xlsx格式")
                return
            }

            if(suffix != "xls"){
                alert("所选文件格式非xls格式")
                return
            }
            s.append("file", files[0]);
        }
        $.ajax({
            url:'uploadExcel',
            type:'POST',
            data:s,
            processData:false,
            contentType:false,
            success:function (data) {
                if(data){
                    alert("上传成功")
                }else {
                    alert("上传失败")
                }
            }
        })
    };

</script>

</html>