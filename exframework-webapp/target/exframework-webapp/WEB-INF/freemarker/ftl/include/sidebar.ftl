<div class="sidebar" data-color="purple" data-background-color="white">
	<!--
        Tip 1: You can change the color of the sidebar using: data-color="purple | azure | green | orange | danger"

        Tip 2: you can also add an image using data-image tag
    -->
	<div class="logo">
		<span class="simple-text" style="margin-left:70px;">
			${appname} </span>
	</div>
	<div class="sidebar-wrapper">
		<ul class="nav">
			<li class="nav-item "><a class="nav-link"
				href="${basePath}/dashboard.view"> <i class="material-icons">dashboard</i>
					<p>Dashboard</p>
			</a></li>
			<li class="nav-item ">
			    <a class="nav-link" data-toggle="collapse" href="#person-collapse">
                    <i class="material-icons">person</i>
                    <p> ${account.accountName} 
                        <b class="caret"></b>
                    </p>
                </a>
                <div class="collapse" id="person-collapse">
                    <ul class="nav">
                        <li class="nav-item">
                            <a class="nav-link" href="#">
                              <span class="sidebar-mini"> <i class="material-icons">lock</i> </span>
                              <span class="sidebar-normal"> 修改密码 </span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${basePath}/api/login/signout">
                                <span class="sidebar-mini"> <i class="material-icons">power_settings_new</i> </span>
                                <span class="sidebar-normal"> 登出 </span>
	                        </a>
                        </li>
                    </ul>
                </div>
			</li>
			<#list navs>
            <#items as nav>
            <li class="nav-item ">
                <a class="nav-link" data-toggle="collapse" href="#sidebar-${nav.code}">
                    <i class="material-icons">${nav.icon}</i>
                    <p> ${nav.name} 
                        <b class="caret"></b>
                    </p>
                </a>
                <#list nav.list>
                <div class="collapse" id="sidebar-${nav.code}">
                    <ul class="nav">
                        <#items as func>
                        <li class="nav-item ">
                            <a class="nav-link" href="${basePath}${func.path}">
                              <span class="sidebar-normal"> ${func.name} </span>
                            </a>
                        </li>
                        </#items>
	                 </ul>
	             </div>
	             </#list>
	         </li>
            </#items>
            </#list>
		</ul>
	</div>
</div>