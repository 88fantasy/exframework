<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="${basePath}/img/gzmpc.ico">

    <title>登录界面</title>

    <!-- Bootstrap core CSS -->
    <link href="${basePath}/css/plugins/bootstrap/bootstrap.min.css" rel="stylesheet">
	<link href="${basePath}/css/plugins/glyphicons/css/glyphicon.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <style>
    	body {
		  width: 100%;
		  background:white;
		  color: #337ab7;
		}
		
		.form-signin {
		  max-width: 330px;
		  padding: 15px;
		  margin: 0 auto;
		}
		.form-signin .form-signin-heading,
		.form-signin .checkbox {
		  margin-bottom: 30px;
		}
		.form-signin .checkbox {
		  font-weight: normal;
		  margin-top: 15px;
		}
		.form-signin .form-control {
		  position: relative;
		  height: auto;
		  -webkit-box-sizing: border-box;
		          box-sizing: border-box;
		  padding: 10px;
		  font-size: 16px;
		}
		.form-signin .form-control:focus {
		  z-index: 2;
		}
		.form-signin input[type="email"] {
		  border-bottom-right-radius: 0;
		  border-bottom-left-radius: 0;
		}
		.form-signin input[type="password"] {
		  border-top-left-radius: 0;
		  border-top-right-radius: 0;
		}
		
		.line {
			border-bottom: 1.5px solid #DDD;
			width: 100%;
			height: 80px; padding-top : 30px;
			
			font-size: 25px;
			margin-bottom: 25px;
			
			background:white;
		}
		
		.title {
			float: left;
			margin-right: 70px;
			font-size: 22px;
			line-height: 49px;
			margin-left: 50px;
		}
		
		.frame {
			width: 430px;
		    position: relative;
		    margin-top: 70px;
		    display: block;
		    border-radius: 8px;
		    border:1px solid #AAA;
		    box-shadow: -2px -2px 2px #CCC, 1px 1px 2px #CCC, 4px 4px 5px #aaa, 4px 4px 5px #aaa;
		}
    </style>
    
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="${basePath}/js/plugins/bootstrap/ie10-viewport-bug-workaround.js"></script>
    
    <script>
  		var basePath = '${basePath}';
  	</script>
  </head>

  <body>
  
  	<div class="line">
		<div class="center">
			<div class="title">
				<img src="${basePath}/img/gzmpclogo.jpg" width="220" style="">
			</div>
			<div class="title" style="margin-top: 6px;">
				<span class="glyphicon glyphicon-leaf" aria-hidden="true"></span><span>   系统名</span>
			</div>
			<div
				style="float: right; line-hright: 49px; margin-top: 20px; font-size: 18px; margin-right:50px;">
				<span class="glyphicon glyphicon-user" aria-hidden="true"></span> <span>用户登录</span>
			</div>
		</div>
	</div>

    <div class="container frame">

      <form class="form-signin" role="form" action="" onSubmit="return doLogin()">
        <h2 class="form-signin-heading">欢迎登陆</h2>
        <div class="input-group">
	        <label for="inputEmail" class="sr-only">帐号</label>
	        <span class="input-group-addon"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> </span>
	        <input type="text" name="username" id="inputEmail" class="form-control" placeholder="帐号" value="supervisor" required autofocus>
        </div>
        <div class="input-group">
	        <label for="inputPassword" class="sr-only">密码</label>
	        <span class="input-group-addon"><span class="glyphicon glyphicon-lock" aria-hidden="true"></span> </span>
	        <input type="password" name="password" id="inputPassword" class="form-control" placeholder="密码" value="test" required>
	    </div>
        
        
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember"> 记住我
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录</button>
      </form>

    </div> <!-- /container -->
    
    <script src="${basePath}/js/plugins/bootstrap/jquery.min.js" ></script>
    <script src="${basePath}/js/plugins/bootstrap/tether.min.js"></script>
    <script src="${basePath}/js/plugins/bootstrap/bootstrap.min.js"></script>
    
    <script src="${basePath}/js/prototype/login.js"></script>
    
  </body>
</html>
