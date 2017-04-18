<nav class="navbar navbar-fixed-top navbar-light" style="background-color: #e3f2fd;">
  <a class="navbar-brand" href="${basePath}/dashboard.view"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> 您好,${account.accountName}</a>
  <#list navs>
	  <ul class="nav navbar-nav">
	  	<#items as nav>
	    <li class="nav-item dropdown">
	     <#list nav.list>
		     <a class="nav-link dropdown-toggle" href="#" id="Navbar-${nav.code}" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${nav.name}</a>
		     <div class="dropdown-menu" aria-labelledby="Navbar-${nav.code}">
		       <#items as func>
		       <a class="dropdown-item" href="${basePath}${func.path}">${func.name}</a>
		       </#items>
		     </div>
		  </#list>
	    </li>
	    </#items>
	  </ul>
  </#list>
  <ul class="nav navbar-nav float-xs-right">
  	<li class="nav-item">
  		 <input type="text" class="form-control" placeholder="搜索功能...">
  	</li>
    <li class="nav-item">
      <a class="nav-link" href="#"><span class="glyphicon glyphicon-cog" aria-hidden="true"></span>修改密码</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="${basePath}/api/login/signout"><span class="glyphicon glyphicon-off" aria-hidden="true"></span>登出</a>
    </li>
  </ul>
</nav>