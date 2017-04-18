<#include "/include/top.ftl" />
<#include "/include/navbar.ftl" />
<#include "/include/sidebar.ftl" />
<div class="main" id="main">
	<div class="container-fluid col-xs-12" >
		<h3>系统信息</h3>
		
		<div class="row">
	    	<div class="col-md-5">
				<ul class="list-group">
				  <li class="list-group-item">Cras justo odio</li>
				  <li class="list-group-item">Dapibus ac facilisis in</li>
				  <li class="list-group-item">Morbi leo risus</li>
				  <li class="list-group-item">Porta ac consectetur ac</li>
				  <li class="list-group-item">Vestibulum at eros</li>
				</ul>
			</div>
			<div class="col-md-2 btn-group-vertical">
			     <button type="button" class="btn btn-success">设置<i class="zmdi zmdi-long-arrow-right"></i></button>
			     
			     <button type="button" class="btn btn-danger"><i class="zmdi zmdi-long-arrow-left"></i>移除</button>
            </div>
            <div class="col-md-5 col-md-offset-7">
               <ul class="list-group">
                  <li class="list-group-item">Cras justo odio</li>
                  <li class="list-group-item">Dapibus ac facilisis in</li>
                </ul>
            </div>
	    </div>
	</div>
</div>


<#include "/include/footerjs.ftl" />
<script src="${basePath}/js/sys/sysinfo/main.js"></script>
<#include "/include/bottom.ftl" />