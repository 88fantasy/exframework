<#include "/include/top.ftl" />
<#include "/include/navbar.ftl" />
<#include "/include/sidebar.ftl" />
<link rel="stylesheet" href="${basePath}/css/prototype/grid.css">

<div class="main" id="main">
	<div class="container-fluid col-xs-12" >
		<h3>功能开发</h3>
		<div id="queryparam" data-control-type="queryparam" data-queryparamcode="funcdev-queryparam" data-queryto="#funcdevGrid" tabindex="-1" role="dialog" aria-labelledby="large-modalLabel" aria-hidden="true" ></div>
		<nav data-control-type="toolbar" data-toolbarcode="funcdev-toolbar" ></nav>
		
		<div id="funcdevGrid" data-control-type="grid" data-gridcode="funcdev-grid" data-page-length="50" data-grid-height="auto" data-small-table="true" ></div>
	</div>
</div>


<script src="${basePath}/js/prototype/grid.js"></script>
<#include "/include/footerjs.ftl" />
<script src="${basePath}/js/sys/funcdev/main.js"></script>
<#include "/include/bottom.ftl" />