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
	<title>Registered</title>
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
					<!-- Start Sign In Form -->
					<div class="fh5co-form animate-box" data-animate-effect="fadeInLeft">
						<h2>Sign Up</h2>
						<div class="form-group">
							<div class="alert alert-success" role="alert">Your info has been saved.</div>
						</div>
						<div class="form-group">
							<label for="name" class="sr-only">用户名</label>
							<input type="text" class="form-control" id="name" placeholder="用户名" autocomplete="off">
						</div>
						<div class="form-group">
							<label for="password" class="sr-only">密码</label>
							<input type="password" class="form-control" id="password" placeholder="密码" autocomplete="off">
						</div>
						<div class="form-group">
							<label for="re-password" class="sr-only">Re-type Password</label>
							<input type="password" class="form-control" id="re-password" placeholder="再次输入密码" autocomplete="off">
						</div>
						<div class="form-group">
							<label for="remember"><input type="checkbox" id="remember"> Remember Me</label>
						</div>
						<div class="form-group">
							<p>Already registered? <a href="login-page.html">Sign In</a></p>
						</div>
						<div class="form-group">
							<input type="submit" value="注册" class="btn btn-primary" onclick="submitRegister()">
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

	<script type="text/javascript">
        var flag1,flag2;
		$("#name").change(function checkUsernameExist() {
            var username = $("#name").val();
			$.ajax({
				url:'checkUsernameIsExist',
                data:JSON.stringify({"username":username}),
                contentType:'application/json;charset=utf-8',
                type:'POST',
                dataType:'text',
                success:function (data) {
                    if(data == "true" && username != ""){
                        var tipNode = document.getElementsByTagName("li")[0];
                        if(tipNode){
                            if(tipNode.parentNode){
                                tipNode.parentNode.removeChild(tipNode);
                            }
                        }
                        var liNode = document.createElement("li");
                        var tip = document.createTextNode("用户名可用！");
                        flag1 = true;
                        liNode.appendChild(tip);
                        liNode.style.color="#4cae4c";
                        insertAfter(liNode ,document.getElementById("name"));
                    }else if(username == ""){
                        var tipNode = document.getElementsByTagName("li")[0];
                        if(tipNode){
                            if(tipNode.parentNode){
                                tipNode.parentNode.removeChild(tipNode);
                            }
                        }
                        var liNode = document.createElement("li");
                        var tip = document.createTextNode("用户名不可为空！");
                        flag1 = false;
                        liNode.appendChild(tip);
                        liNode.style.color = "#ac2925";
                        insertAfter(liNode ,document.getElementById("name"));
                    }else {
                        var tipNode = document.getElementsByTagName("li")[0];
                        if(tipNode){
                            if(tipNode.parentNode){
                                tipNode.parentNode.removeChild(tipNode);
                            }
                        }
                        var liNode = document.createElement("li");
                        var tip = document.createTextNode("用户名不可用！");
                        flag1 = false;
                        liNode.appendChild(tip);
                        liNode.style.color = "#ac2925";
                        insertAfter(liNode ,document.getElementById("name"));
                    }
                }
			})
        })


        $("#re-password,#password").change(function checkPassword() {
            if(!$("#name").val()){
                var tipNode = document.getElementsByTagName("li")[1];
                if(tipNode){
                    if(tipNode.parentNode){
                        tipNode.parentNode.removeChild(tipNode);
                    }
                }
                var liNode = document.createElement("li");
                var tip = document.createTextNode("用户名不能为空！");
                flag1 = false;
                liNode.appendChild(tip);
                liNode.style.color="#ac2925";
                insertAfter(liNode ,document.getElementById("name"));
            }
            var password = $("#password").val();
            var rePassword = $("#re-password").val();
            if(rePassword != password){
                var tipNode = document.getElementsByTagName("li")[1];
                if(tipNode){
                    if(tipNode.parentNode){
                        tipNode.parentNode.removeChild(tipNode);
                    }
                }
                var liNode = document.createElement("li");
                var tip = document.createTextNode("前后密码不一致！");
                flag2 = false;
                liNode.appendChild(tip);
                liNode.style.color="#ac2925";
                insertAfter(liNode ,document.getElementById("re-password"));
            }else{
                var tipNode = document.getElementsByTagName("li")[1];
                flag2 = true;
                if(tipNode){
                    if(tipNode.parentNode){
                        tipNode.parentNode.removeChild(tipNode);
                    }
                }
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

        function submitRegister() {
            if(!flag1){
                alert("用户名有误");
                return false;
            }
            if(!flag2){
                alert("两次密码不一致");
                return false;
            }
            var username = $("#name").val();
            var password = $("#password").val();
            $.ajax({
                url:'registerUser',
                data:JSON.stringify({"username":username,"password":password}),
                contentType:'application/json;charset=utf-8',
                type:'POST',
                dataType:'text',
                success:function (data) {
                    if(data == "true"){
                        window.location.href = "show-page";
                    }else{
                        alert("参数错误");
                        return false;
                    }
                }
            })
        }
    </script>
	</body>
</html>

