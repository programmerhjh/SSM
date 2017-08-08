<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
	<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Forgot</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="Free HTML5 Template by FreeHTML5.co" />
	<meta name="keywords" content="free html5, free template, free bootstrap, html5, css3, mobile first, responsive" />
	<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
	<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,300' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" href="../css/bootstrap.min.css">
	<link rel="stylesheet" href="../css/animate.css">
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
					<div class="fh5co-form animate-box" data-animate-effect="fadeInLeft">
						<h2>Forgot Password</h2>
						<div class="form-group">
							<div class="alert alert-success" role="alert">通过您注册时的用户名和手机号找回.</div>
						</div>
						<div class="form-group">
							<input type="text" class="form-control" id="name" placeholder="Name" autocomplete="off">
							<input type="text" class="form-control" id="phone" placeholder="Phone" autocomplete="off">
						</div>

						<div class="form-group">
							<input type="submit" value="Send Phone" id="submitPhone" class="btn btn-primary">
						</div>

						<div class="form-group" id="addNewPassword">
							<input type="text" class="form-control" id="newPassword" placeholder="newPassword">
							<input type="text" class="form-control" id="reNewPassword" placeholder="re-Password">

							<div class="form-group">
								<input type="submit" value="Send" id="submit" class="btn btn-primary">
							</div>
						</div>

						<div class="form-group">
							<p><a href="registered.html">Sign In</a> or <a href="login-page.html">Sign Up</a></p>
						</div>
					</div>

					<!-- END Sign In Form -->
				</div>
			</div>
		</div>

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

	</body>
</html>
<script>
    $(document).ready(function () {
        document.getElementById("addNewPassword").style.display = 'none'
    })

	$("#submitPhone").click(function checkIsExistUser() {
	    var username = $("#name").val();
	    var phone = $("#phone").val();
        $.ajax({
            url:"checkUserIsExist",
			data:JSON.stringify({"name":username,"phone":phone}),
            contentType:'application/json;charset=utf-8',
            type:'POST',
			success:function (data) {
				if(data == "exist"){
				    alert("确认成功");
                    document.getElementById("addNewPassword").style.display = ''
				}else if(data == "no"){
				    alert("该用户不存在");
				}else{
				    alert("系统错误")
				}
            }
        })
    })

	$("#submit").click(function addNewPassword() {
        var username = $("#name").val();
		var password = $("#newPassword").val();
		var repassword = $("#reNewPassword").val();
		if(password != repassword){
		    alert("密码重复不一致");
		}else{
		    $.ajax({
				url:'addNewPassword',
				data:JSON.stringify({"name":username,"password":password}),
                contentType:'application/json;charset=utf-8',
				type:'POST',
				success:function (data) {
					if(data == "success"){
					    alert("成功");
					    window.location.href = "login-page";
					}else{
					    alert("系统错误，请稍后再试");
					}
                }
			})
		}
    })
</script>
