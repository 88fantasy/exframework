<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="${basePath}/img/gzmpc.ico">

    <title>${appname}</title>
    
    <!--[if IE]>
	<script src="${basePath}/js/plugins/html5shiv/html5shiv.min.js"></script>
	<![endif]-->

    <!-- 全局css -->
	<link href="${basePath}/css/plugins/bootstrap/bootstrap.min.css" rel="stylesheet">
	<link href="${basePath}/css/plugins/bootstrap-datepicker/bootstrap-datepicker3.min.css" rel="stylesheet">
	<link href="${basePath}/css/plugins/bootstrap-multiselect/bootstrap-multiselect.css" rel="stylesheet">
	<link rel="stylesheet" href="${basePath}/css/plugins/loaders.css/loaders.min.css">
	<#if marino!false == true>  
	<link href="${basePath}/css/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet">
	<link href="${basePath}/css/plugins/material-design-iconic-font/css/material-design-iconic-font.min.css" rel="stylesheet">
	<link rel="stylesheet" href="${basePath}/css/plugins/noto/css/notosans.css">
	<link rel="stylesheet" href="${basePath}/css/plugins/marino/main.css">
	</#if>
	
	<link rel="stylesheet" href="${basePath}/css/prototype/exframe-bootstrap.css">
	
	<!-- global scripts -->
	<script src="${basePath}/js/plugins/bootstrap/jquery.min.js"></script>
	<script src="${basePath}/js/plugins/bootstrap/tether.min.js"></script>
	<script src="${basePath}/js/plugins/bootstrap/bootstrap.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="${basePath}/js/plugins/bootstrap/ie10-viewport-bug-workaround.js"></script>
	<script src="${basePath}/js/prototype/namespace.js"></script>
	<script src="${basePath}/js/prototype/util.js"></script>
	<script src="${basePath}/js/plugins/mustache/mustache.min.js"></script>
	<script src="${basePath}/js/plugins/bootstrap-datepicker/bootstrap-datepicker.min.js"></script>
	<script src="${basePath}/js/plugins/bootstrap-datepicker/bootstrap-datepicker.zh-CN.min.js"></script>
	<script src="${basePath}/js/plugins/bootstrap-multiselect/bootstrap-multiselect.js"></script>
	
	<#if marino!false == true >  
	<script src="${basePath}/js/plugins/pace/pace.js"></script>
	<script src="${basePath}/js/plugins/jquery-storage-api/jquery.storageapi.min.js"></script>
	<script src="${basePath}/js/plugins/marino/functions.js"></script>
	<script src="${basePath}/js/plugins/marino/colors.js"></script>
	</#if>
	
	<script>
  		var basePath = '${basePath}';
  		var apiPrefix = '/api';
  	</script>
    
    
  </head>

  <body data-layout="empty-layout" data-palette="palette-0" data-direction="none">
  
  	