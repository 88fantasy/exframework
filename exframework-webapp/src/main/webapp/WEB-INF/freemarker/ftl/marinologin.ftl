
<!doctype html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>登录界面</title>
	<meta name="description"
		content="Login View">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="icon" href="${basePath}/img/gzmpc.ico">
	<!--[if IE]>
	<script src="${basePath}/js/plugins/html5shiv/html5shiv.min.js"></script>
	<![endif]-->
	<!-- 全局css -->
	<link href="${basePath}/css/plugins/bootstrap4/bootstrap.min.css"
		rel="stylesheet">
	<link
		href="${basePath}/css/plugins/font-awesome/css/fontawesome-all.min.css"
		rel="stylesheet">
	<link
		href="${basePath}/css/plugins/material-design-iconic-font/css/material-design-iconic-font.min.css"
		rel="stylesheet">
	
	<link rel="stylesheet" href="${basePath}/css/plugins/marino/main.css">
	
	<script>
  		var basePath = '${basePath}';
  	</script>
</head>
<body data-layout="empty-layout" data-palette="palette-0"
	data-direction="none">
	<div class="fullsize-background-image-1">
		<img src="${basePath}/img/background/bg11.png" />
	</div>
	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<div class="login-page text-center animated fadeIn delay-2000">
					<h1>欢迎登录</h1>

					<div class="row">
						<div
							class="offset-xs-2 col-xs-8 offset-sm-3 col-sm-6 offset-md-3 col-md-6 offset-lg-4 col-lg-4">

							<form name="form" novalidate class="form" onSubmit="return doLogin()">

								<div class="row">
									<div class="col-xs-12">
										<div class="form-group floating-labels">
											<label for="user">帐号</label> <input id="user"
												autocomplete="off" type="text" name="username">
											<p class="error-block"></p>
										</div>
									</div>
								</div>
								<div class="row m-b-40">
									<div class="col-xs-12">
										<div class="form-group floating-labels">
											<label for="password">密码</label> <input id="password"
												autocomplete="off" type="password" name="password">
											<p class="error-block"></p>
										</div>
									</div>
								</div>

								<div class="row buttons">
									<div class="col-xs-12 col-md-6">
										<input type="submit"
											class="btn-login btn btn-lg btn-info btn-block m-b-20"
											value="登录">
									</div>
									<div class="col-xs-12 col-md-6">
										<a href="pages-register.html"
											class="btn btn-lg btn-danger btn-block m-b-20">忘记密码</a>
									</div>
								</div>
							</form>
						</div>
					</div>
					<p class="copyright text-sm">&copy; Copyright 2016</p>
				</div>
			</div>
		</div>
	</div>
	<!-- global scripts -->
	<script src="${basePath}/js/plugins/bootstrap4/jquery.slim.min.js" ></script>
    <script src="${basePath}/js/plugins/bootstrap4/popper.min.js"></script>
    <script src="${basePath}/js/plugins/bootstrap4/bootstrap.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script
		src="${basePath}/js/plugins/bootstrap4/ie10-viewport-bug-workaround.js"></script>
	<script src="${basePath}/js/plugins/pace/pace.js"></script>
	<script src="${basePath}/js/plugins/jquery-storage-api/jquery.storageapi.min.js"></script>
	<script src="${basePath}/js/plugins/wow/wow.min.js"></script>
	<script src="${basePath}/js/plugins/marino/functions.js"></script>
	<script src="${basePath}/js/plugins/marino/colors.js"></script>
	<script src="${basePath}/js/plugins/marino/main.js"></script>
	<script src="${basePath}/js/plugins/marino/components/floating-labels.js"></script>
	<script src="${basePath}/js/plugins/marino/pages-login.js"></script>
	<script src="${basePath}/js/prototype/login.js"></script>
	
</body>
</html>
