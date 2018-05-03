<#include "/include/top.ftl" />
<#include "/include/sidebar.ftl" />
<link rel="stylesheet" href="${basePath}/css/prototype/grid.css">

<div id="queryparam" data-control-type="queryparam" data-queryparamcode="saleflow-queryparam" data-queryto="#saleflowGrid" tabindex="-1" role="dialog" aria-labelledby="large-modalLabel" aria-hidden="true" ></div>    
<div class="main-panel">
    <nav data-control-type="toolbar" data-toolbarcode="saleflow-toolbar" data-brand="流向查询" class="navbar navbar-expand-lg navbar-default  navbar-absolute"></nav>
	<div class="content" style="margin-top:20px;">
		<div class="container-fluid" >
		  <div class="row">
		      <div class="card">
                 <div class="card-body">
                    <div id="saleflowGrid" data-control-type="grid" data-gridcode="saleflow-grid" data-page-length="50" data-grid-height="auto" ></div>
                 </div>
             </div>
		  </div>
		</div>
	</div>
</div>

<#include "/include/gridjs.ftl" />
<#include "/include/footerjs.ftl" />
<script src="${basePath}/js/func/saleflow/saleflow.js"></script>
<#include "/include/bottom.ftl" />