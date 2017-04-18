<div class="sidebar-outer-wrapper">
    <div class="sidebar-inner-wrapper">
        <div class="sidebar-1">
            <div class="profile" style="height: initial;">
                <button data-click="toggle-sidebar" type="button" class="btn btn-danger btn-outline no-border close-sidebar"> <i class="fa fa-close"></i> 
                </button>
                <div class="profile-image">
                    <img class="rounded-circle img-fluid" src="${basePath}/img/avatar/avatar-default.png">
                </div>
                <div class="profile-title">${account.accountId}</div>
                <div class="profile-subtitle">${account.accountName}</div>
            </div>
            <div class="sidebar-nav">
            	<div class="sidebar-section">
            		<div class="section-content" >
            			<a class="sideline" href="${basePath}/dashboard.view"> <i class="zmdi zmdi-view-dashboard md-icon pull-left"></i> <span class="title">DashBoard</span> 
                                    </a>
            		</div>
            	</div>
                <div class="sidebar-section">
                    <div class="section-title">业务功能</div>
					<#list navs>
					<ul class="l1 list-unstyled section-content">
						<#items as nav>
						<li class="nav-item dropdown">
							<a class="sideline" data-id="sidebar-${nav.code}" data-click="toggle-section"> <i class="pull-right fa fa-caret-down icon-sidebar-${nav.code}"></i>  <span class="pull-right tag tag-success tag-rounded">${nav.list?size}</span>  <i class="zmdi ${nav.icon} md-icon pull-left"></i>  <span class="title">${nav.name}</span> 
                            </a>
							<#list nav.list> 
							 <ul class="list-unstyled section-sidebar-${nav.code} l2">
								<#items as func>
								<li>
                                    <a class="sideline" href="${basePath}${func.path}"> <span class="title">${func.name}</span> 
                                    </a>
                                </li>
								</#items>
							</ul>
							</#list>
						</li>
						</#items>
					</ul>
					</#list>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${basePath}/js/plugins/marino/left-sidebar.js"></script>