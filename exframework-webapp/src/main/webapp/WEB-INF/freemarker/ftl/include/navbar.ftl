<nav class="navbar bg-white fixed-top navbar-expand-lg">
	<div class="container">
		<span class="navbar-brand">${appname}</span>
		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav mr-auto toggle-layout">
				<li class="nav-item"><a class="nav-link"
					data-click="toggle-layout"> <i class="zmdi zmdi-menu"></i>
				</a></li>
			</ul>

			<ul class="nav navbar-nav ">
				<li class="nav-item">
					<div class="navbar-drawer  hidden-lg-down active">
						<div class="form-inline navbar-form ">
							<input class="form-control" type="text" placeholder="搜索..."
								oninput="searchfunc(this);">
						</div>
					</div>
				</li>
				<li class="nav-item">
                    <button class="btn btn-white btn-raised btn-fab btn-round">
                                        <i class="material-icons">search</i>
                                    </button>
                </li>
				<li class="nav-item dropdown">
				    <button class="btn btn-white btn-raised btn-fab btn-round dropdown"
				        data-toggle="dropdown" aria-expanded="false">
                                        <i class="material-icons md-36">account_circle</i>
                                    </button>
					<div class="dropdown-menu dropdown-menu-right ">
						<a class="dropdown-item animated fadeIn" href="email-inbox.html">
							<i class="zmdi zmdi-email"></i> <span
							class="label label-pill label-danger label-xs pull-right">New</span>
							<span class="dropdown-text">Inbox</span>
						</a> <a class="dropdown-item animated fadeIn"
							href="pages-profile.html"> <i
							class="zmdi zmdi-settings-square"></i> <span
							class="dropdown-text">Profile</span>
						</a> <a class="dropdown-item animated fadeIn"
							href="pages-lock-screen.html"> <i class="zmdi zmdi-settings"></i>
							<span class="dropdown-text">修改密码</span>
						</a> <a class="dropdown-item animated fadeIn"
							href="${basePath}/api/login/signout"> <i
							class="zmdi zmdi-power"></i> <span class="dropdown-text">登出</span>
						</a>
					</div></li>
			</ul>

		</div>
	</div>
</nav>
<script src="${basePath}/js/plugins/marino/navbar.js"></script>