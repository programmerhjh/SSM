<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 2017/7/7
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%>
<!-- Modernizr JS -->
<script src="../js/modernizr-2.6.2.min.js"></script>
<!-- FOR IE9 below -->
<!--[if lt IE 9]>
<script src="../js/jquery-1.8.3.min.js"></script>
<!-- Bootstrap -->
<script src="../js/bootstrap.min.js"></script>
<!-- Placeholder -->
<script src="../js/jquery.placeholder.min.js"></script>
<!-- Waypoints -->
<script src="../js/jquery.waypoints.min.js"></script>
<!-- Main JS -->
<script src="../js/main.js"></script>
<script src="../js/jquery-1.8.3.min.js"/>
<script src="../js/respond.min.js"></script>
<![endif]-->
<html>
<head>
    <title>短信验证</title>
</head>
<body>
    <div style="color: #ac2925"><h3>检测到您还未进行手机号码绑定，请先绑定在进行后续操作</h3></div>
    手机号码：<input type="text" id="phone" placeholder="请输入正确的手机号码">&nbsp;&nbsp;&nbsp;<button id="send" onclick="checkPhone()">发送验证码</button><br>
    短信验证码：<input type="text" id="validateNumber" placeholder="请输入短信验证码"><input type="submit" id="submit" value="Submit">
</body>

<script type="text/javascript">
    var validateNumber;
    function checkPhone(){
        var phone = document.getElementById("phone").value;
        if(!(/^1[34578]\d{9}$/.test(phone))){
            alert("手机号码有误，请重填");
            return false;
        }else{
            validate(phone)
            return false
        }
    };

    function validate(phone) {
        $.ajax({
            url:'validatePhone',
            data:phone,
            type:'POST',
            contentType:'application/json;charset=utf-8',
            success:function (data) {
                if(data){
                    document.getElementById("send").setAttribute("disabled",true);
                    validateNumber = data;
                }else{
                    alert("请求错误，请重新点击发送或维护中");
                    document.getElementById("send").removeAttribute("disabled");
                    return false;
                }
            }
        })
    }


    $("#submit").click(function checkPhoneValidate() {
        var userValidateNumber = document.getElementById('validateNumber').value;
        if (userValidateNumber == validateNumber){
            $.ajax({
                url:'addPhone',
                data:{},
                contentType:'text/xml;charset=utf-8',
                type:'POST',
                dataType:'text',
                success:function (data) {
                    if(data == "false"){
                        alert("参数错误....请重新登录");
                        window.location.href = "login-page";
                    }
                },
                <!--
                error: function(XMLHttpRequest, textStatus, errorThrown) {
                    alert(XMLHttpRequest.status);
                    alert(XMLHttpRequest.readyState);
                    alert(textStatus);
                 }
                -->
            })
            alert("验证通过");
            window.location.href = "../bbs-module/index";
        }else {
            alert("验证码有误，请核对后再进行输入");
        }
    })

</script>
</html>
