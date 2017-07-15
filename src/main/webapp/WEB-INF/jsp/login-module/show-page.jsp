<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 2017/6/26
  Time: 17:53
  To change this template use File | Settings | File Templates.
--%>
<script src="../js/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="../js/bootstrap.min.js"></script>
<!-- Placeholder -->
<script src="../js/jquery.placeholder.min.js"></script>
<!-- Waypoints -->
<script src="../js/jquery.waypoints.min.js"></script>
<!-- Main JS -->
<script src="../js/main.js"></script>
<script src="../js/modernizr-2.6.2.min.js"></script>
<!-- FOR IE9 below -->
<script src="../js/respond.min.js"></script>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<script type="text/javascript">
    $(document).ready(function () {
        $.ajax({
            url:'hasCompleteUserFormation',
            data:{},
            contentType:'application/json;charset=utf-8',
            type:'POST',
            dataType:'text',
            success:function (data) {
                if(data == "yes"){
                    return true;
                }else if(data == "no"){
                    var flag = confirm("检测到您还未完善个人信息，后续功能有些无法使用，是否前去完善");
                    if(flag == true){
                        window.location.href = "organizing_data";
                    }
                    return true;
                }else{
                    alert("您还没有登录");
                    window.location.href = "login-page";
                }
            }
        })
    })
</script>
<html>
<head>
    <title>首页</title>
</head>
<body>
    <h1>首页</h1> <div style="text-align: right"><a href="loginOut">注销</a></div>
</body>
</html>
