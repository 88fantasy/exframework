<#include "/include/top.ftl" />
<#include "/include/navbar.ftl" />
<#include "/include/sidebar.ftl" />
<link rel="stylesheet" href="${basePath}/css/prototype/grid.css">

<div id="queryparam" data-control-type="queryparam" data-queryparamcode="saleflow-queryparam" data-queryto="#saleflowGrid" tabindex="-1" role="dialog" aria-labelledby="large-modalLabel" aria-hidden="true" ></div>
<div class="main" id="main">
	<div class="container-fluid col-xs-12" >
		<h3>功能开发</h3>
		<nav  data-control-type="toolbar" data-toolbarcode="saleflow-toolbar"></nav>
		
		<div id="saleflowGrid" data-control-type="grid" data-gridcode="saleflow-grid" data-page-length="50" data-grid-height="auto" ></div>
	</div>
</div>

<script src="${basePath}/js/prototype/grid.js"></script>
<#include "/include/footerjs.ftl" />
<script src="${basePath}/js/func/saleflow/saleflow.js"></script>
<#include "/include/bottom.ftl" />