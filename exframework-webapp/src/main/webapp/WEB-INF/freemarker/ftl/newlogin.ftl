<!doctype html>
	<html lang="en">
	<head>
	<meta charset="utf-8">
	<title>登录界面</title>
	<meta name="description" content="Login View">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="icon" href="${basePath}/img/gzmpc.ico">
	<!--[if IE]>
		<script src="${basePath}/js/plugins/html5shiv/html5shiv.min.js"></script>
		<![endif]-->
	<!-- 全局css -->
	<link href="${basePath}/css/plugins/material-design-icons/iconfont/material-icons.css" rel="stylesheet">
	<link href="${basePath}/css/plugins/font-awesome/css/fontawesome-all.min.css" rel="stylesheet">
	
	
	<link rel="stylesheet" href="${basePath}/css/plugins/material-dashboard/material-dashboard.min.css">
	<link rel="stylesheet" href="${basePath}/css/prototype/exframe-bootstrap.css">
	
	<script>
		var basePath = '${basePath}';
	</script>
</head>
<body class="login-page" >
	<div class="page-header header-filter"  style="background-image: url(&apos;img/background/bg11.png&apos;); background-size: cover; background-position: top center;">
		<div class="container" >
			<div class="row">
				<div class="col-md-4 ml-auto mr-auto">
					<div class="card card-signup">
						<form class="form" action="" onSubmit="return doLogin()">
							<div class="card-header card-header-primary text-center">
								<h2>欢迎登录</h2>
							</div>
							<div class="card-body">
                                <div class="form-group bmd-form-group">
                                    <div class="input-group">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text">
                                                <i class="material-icons">face</i>
                                            </span>
                                        </div>
                                        <input type="text" class="form-control" name="username" placeholder="请输入帐号..." value="supervisor">
                                    </div>
                                </div>
                                <div class="form-group bmd-form-group">
                                    <div class="input-group">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text">
                                                <i class="material-icons">lock_outline</i>
                                            </span>
                                        </div>
                                        <input type="password" name="password" placeholder="请输入密码..." class="form-control" value="test">
                                    </div>
                                </div>
                                <div class="form-check bmd-form-group" style="padding-left:20px;">
                                     <label class="form-check-label">
                                         <input class="form-check-input" type="checkbox" value="">
                                         <span class="form-check-sign">
                                             <span class="check"></span>
                                         </span>
                                         请记住我
                                     </label>
                                 </div>
                            </div>
							<div class="card-footer justify-content-center">
								<button class="btn btn-primary btn-round" type="submit"><i class="material-icons">fingerprint</i>&nbsp;&nbsp;&nbsp;登&nbsp;&nbsp;&nbsp;录</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<footer class="footer">
            <div class="container">
                <div class="copyright float-right">
                    ©2018, by <a href="http://www.gzmpc.com" target="_blank">广州医药有限公司</a>
                </div>
            </div>
        </footer>
		
	</div>
	<!-- global scripts -->
	<script src="${basePath}/js/plugins/bootstrap4/jquery.min.js"></script>
	<script src="${basePath}/js/plugins/bootstrap4/popper.min.js"></script>
	<script src="${basePath}/js/plugins/bootstrap-material-design/bootstrap-material-design.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="${basePath}/js/plugins/bootstrap4/ie10-viewport-bug-workaround.js"></script>
	<script src="${basePath}/js/plugins/jquery-storage-api/jquery.storageapi.min.js"></script>
	<script src="${basePath}/js/prototype/login.js"></script>
</body>
</html>
