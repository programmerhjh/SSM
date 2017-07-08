<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<!--[if lt IE 7]><html class="no-js lt-ie9 lt-ie8 lt-ie7"><![endif]-->
<!--[if IE 7]><html class="no-js lt-ie9 lt-ie8"><![endif]-->
<!--[if IE 8]><html class="no-js lt-ie9"><![endif]-->
<!--[if gt IE 8]><!--><html class="no-js"><!--<![endif]-->
	<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Login</title>
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
                     <div class="fh5co-form animate-box" data-animate-effect="fadeInLeft">
                    <!-- Start Sign In Form -->
                        <h2>Sign In</h2>
                        <div class="form-group">
                            <label for="username" class="sr-only">Username</label>
                            <input type="text" class="form-control" name="name" id="username" placeholder="Username" autocomplete="off">
                        </div>
                        <div class="form-group">
                            <label for="password" class="sr-only">Password</label>
                            <input type="password" class="form-control" name="password" id="password" placeholder="Password" autocomplete="off">
                        </div>
                        <div class="form-group">
                            <label for="remember"><input type="checkbox" id="remember"> Remember Me</label>
                        </div>
                        <div class="form-group">
                            <p>Not registered? <a href="registered">Sign Up</a> | <a href="forgot-password">Forgot Password?</a></p>
                        </div>
                        <div class="form-group">
                            <input type="submit" value="Sign In" class="btn btn-primary" id="submit">
                        </div>
                    <!-- END Sign In Form -->
                    </div>
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

	<script type="text/javascript">
		$("#submit").click(function checkUser() {
			var username = $("#username").val();
			var password = $("#password").val();
            var w=300; //模态窗口宽度
            var h=300;//模态窗口高度
            var iTop2 = (window.screen.availHeight - 20 - h) / 2;
            var iLeft2 = (window.screen.availWidth - 10 - w) / 2;
			var loginData = JSON.stringify({"name":username,"password":password});
			$.ajax({
				url:'loginning',
				data:loginData,
				contentType:'application/json;charset=utf-8',
				type:'POST',
                dataType:'json',
				success:function (data) {
				    if (data['OK'] == "OK"){
                        if (data['MessageValidation'] == true){
                            window.showModalDialog('checkPhone','newWindow',  'menubar:no;dialogHeight=' + h + 'px;dialogWidth=' + w + 'px;dialogLeft=' + iLeft2 + 'px;dialogTop=' + iTop2 + 'px;resizable=yes;scroll=1;resizeable=0;center=yes;location:no;status:no')
                            window.location.href = "show-page";
                        }else{
                            window.location.href = "show-page";
                        }
                    }else{
				        alert("用户名或密码错误，请重新输入");
                    }
                }
			})
        })
	</script>

	</body>
</html>

