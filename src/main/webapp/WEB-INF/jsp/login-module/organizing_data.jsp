<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<!--[if lt IE 7]><html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]><html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]><html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
<script type="text/javascript">
    Date.prototype.format = function(fmt) {
        var o = {
            "M+" : this.getMonth()+1,                 //月份
            "d+" : this.getDate(),                    //日
            "h+" : this.getHours(),                   //小时
            "m+" : this.getMinutes(),                 //分
            "s+" : this.getSeconds(),                 //秒
            "q+" : Math.floor((this.getMonth()+3)/3), //季度
            "S"  : this.getMilliseconds()             //毫秒
        };
        if(/(y+)/.test(fmt)) {
            fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
        }
        for(var k in o) {
            if(new RegExp("("+ k +")").test(fmt)){
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
            }
        }
        return fmt;
    };
</script>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>完善个人资料</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Free HTML5 Template by FreeHTML5.co" />
    <meta name="keywords" content="free html5, free template, free bootstrap, html5, css3, mobile first, responsive" />

    <!-- Facebook and Twitter integration -->
    <meta property="og:title" content=""/>
    <meta property="og:image" content=""/>
    <meta property="og:url" content=""/>
    <meta property="og:site_name" content=""/>
    <meta property="og:description" content=""/>
    <meta name="twitter:title" content="" />
    <meta name="twitter:image" content="" />
    <meta name="twitter:url" content="" />
    <meta name="twitter:card" content="" />

    <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->


    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,300' rel='stylesheet' type='text/css'>
    <link rel='stylesheet' id='main-css-css'  href='../css/main5152.css?ver=1.0' type='text/css' media='all' />
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/animate.css">
    <link rel="stylesheet" href="../css/layui.css" media="all" type="text/css">
    <link rel="stylesheet" href="../css/style.css">

    <!-- Modernizr JS -->
    <script src="../js/modernizr-2.6.2.min.js"></script>
    <!-- FOR IE9 below -->
    <!--[if lt IE 9]>
    <script src="../js/respond.min.js"></script>
    <![endif]-->

</head>
<body class="style-2">

<div class="container">

    <div class="row">
        <div class="col-md-4">
            <!-- Start Sign In Form -->
            <form action="addUserData" class="fh5co-form animate-box" data-animate-effect="fadeInLeft" method="post">
                <input type="hidden" name="headAddress" id="headAddress" value="${user.headAddress}">
            <img src="${user.headAddress}" id="imgUser" class="flickr-photos" height="100" width="100">

                <div class="form-group">
                    <br>
                    <input type="file" name="fileTrans" id="uploadFile" />
                </div>
                <div class="form-group">
                    <input type="hidden" name="id" value="${user.id}">
                    <input type="hidden" name="password" value="${user.password}">
                    <h4>用户名</h4><input type="text" name="name" value="${user.name}" id="name" disabled="true">
                    <c:choose>
                        <c:when test="${user.phone == null}" >
                            <h4>手机号码</h4><input type="text" class="form-control" name="phone" value="还未进行短信验证" id="phone1" placeholder="Phone" autocomplete="off" disabled="true"><br>
                            <a href="checkPhone">请进行短信验证再重新修改个人资料</a>
                        </c:when>
                        <c:otherwise>
                            <h4>手机号码</h4><input type="text" class="form-control" name="phone" value="${user.phone}" id="phone1" placeholder="Phone" autocomplete="off" disabled="true"><br>
                        </c:otherwise>
                    </c:choose>
                    <h4>年龄</h4><input type="text" class="form-control" name="age" placeholder="Age" value="${user.age}">
                    <h4>生日</h4><input type="text" class="form-control" name="birth" placeholder="xxxx-xx-xx" value="<fmt:formatDate value="${user.birth}" pattern="yyyy-MM-dd"/>" id="Birth" >
                    <h4>Email</h4><input type="email" value="${user.email}" class="form-control" name="email" placeholder="Email" id="Email">
                    <h4>地址</h4><input type="text" class="form-control" value="${user.address}" name="address" placeholder="Address">
                    <h4>自我描述</h4><input type="text" class="form-control" name="description" value="${user.description}" placeholder="Description"><br>
                    <button type="submit" id="submit">提交</button>
                </div>
            </form>
            <!-- END Sign In Form -->
        </div>
    </div>
</div>
</body>
<!-- jQuery -->
<script src="../js/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="../js/bootstrap.min.js"></script>
<!-- Placeholder -->
<script src="../js/jquery.placeholder.min.js"></script>
<!-- Waypoints -->
<script src="../js/jquery.waypoints.min.js"></script>
<!-- Main JS -->
<script src="../js/main.js"></script>
<!-- Laydate.js -->
<script src="../js/laydate.js"></script>

<script type="text/javascript">
    laydate.render({ elem: '#Birth',max: new Date().format("yyyy-MM-dd")});
    var flag1= true,flag2= true,flag3 = true;

    var validateNumber;
    $("#send").click(function checkPhone(){
        var phone = $("#phone").val();
        if(!(/^1[34578]\d{9}$/.test(phone))){
            alert("手机号码有误，请重填");
        }else{
            $.ajax({
                url:'validatePhone',
                data:phone,
                contentType:'text/xml;charset=utf-8',
                type:'POST',
                dataType:'text',
                success:function (data) {
                    if(data){
                        validateNumber = data;
                        document.getElementById("send").setAttribute("disabled",true);
                    }else{
                        alert("请求错误，请重新点击发送");
                        document.getElementById("send").removeAttribute("disabled");
                    }
                }
            })
        }
    })

    $("#uploadFile").change(function () {
        var s = new FormData();
        var files = $('input[name="fileTrans"]').prop('files');//获取到文件列表
        if(files.length == 0){
            alert('请选择文件');
            return;
        }else if(files.length > 1){
            alert("请选择1张图片")
            return;
        }else{
            s.append("img", files[0]);
        }
        $.ajax({
            url:'upload',
            type:'POST',
            data:s,
            processData:false,
            contentType:false,
            success:function (data) {
                if(data){
                    document.getElementById("imgUser").src = data;
                    document.getElementById("headAddress").value = data;
                }
            }

        })
    })

    $("#Birth").change(function checkBirth() {
        var birth = $("#Birth").val();
        var temp = "生日";
        if(birth == "" || birth == null){
            var tipNode = document.getElementById("Birth");
            if (tipNode.nextElementSibling.tagName == "LI") {
                tipNode.parentNode.removeChild(tipNode.nextElementSibling);
            }
            flag1 = true;
        }else if(!(/^((?!0000)[0-9]{4}-((0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-8])|(0[13-9]|1[0-2])-(29|30)|(0[13578]|1[02])-31)|([0-9]{2}(0[48]|[2468][048]|[13579][26])|(0[48]|[2468][048]|[13579][26])00)-02-29)$/.test(birth))){
            addTip(temp,"Birth","false");
            flag1 = false;
        }else{
            addTip(temp,"Birth","true");
            flag1 = true;
        }
    })

    $("#Email").change(function checkEmail() {
        var email = $("#Email").val();
        var temp = "邮箱地址";
        if(email == ""){
            var tipNode = document.getElementById("Email");
            if (tipNode.nextElementSibling.tagName == "LI") {
                tipNode.parentNode.removeChild(tipNode.nextElementSibling);
            }
            flag2 = true;
        }else if(!(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/.test(email))){
            addTip(temp,"Email","false");
            flag2 = false;
        }else{
            addTip(temp,"Email","true");
            flag2 = true;
        }
    })

    $("#ValidateNumber").change(function checkPhoneValidate() {
        var userValidateNumber = document.getElementById('ValidateNumber').value;
        var temp = "验证码";
        if (userValidateNumber == validateNumber){
            addTip(temp,"ValidateNumber","true");
            flag3 = false;
        }else{
            addTip(temp,"ValidateNumber","false");
            flag3 = true;
        }
    })

    function insertAfter( newElement, targetElement ){
        var parent = targetElement.parentNode;
        if( parent.lastChild == targetElement ){
            parent.appendChild(newElement);
        }else{
            parent.insertBefore( newElement, targetElement.nextSibling );
        }
    }

    function addTip(text,id,flag) {
        var tipNode = document.getElementById(id);
        if (tipNode.nextElementSibling.tagName == "LI") {
            tipNode.parentNode.removeChild(tipNode.nextElementSibling);
        }
        var liNode = document.createElement("li");
        if (flag == "true"){
            text += "正确";
            var tip = document.createTextNode(text);
            liNode.style.color="#4cae4c";
        }else {
            text += "不正确";
            var tip = document.createTextNode(text);
            liNode.style.color = "#ac2925";
        }

        liNode.appendChild(tip);
        insertAfter(liNode ,document.getElementById(id));
    }

    $("#submit").click(function submitData() {
        if(flag1 && flag2 && flag3){
            document.getElementById("name").removeAttribute("disabled");
            document.getElementById("phone1").removeAttribute("disabled");
            return true;
        }else{
            alert("表单信息有误");
            return false;
        }
    })

</script>
</html>