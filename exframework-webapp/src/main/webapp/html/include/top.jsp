<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String base = request.getContextPath();%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="<%=base %>/img/gzmpc.ico">

    <title>xx系统</title>

    <!-- Bootstrap core CSS -->
    <link href="<%=base %>/css/plugins/bootstrap.min.css" rel="stylesheet">
    <link href="<%=base %>/css/plugins/glyphicon.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <style>
    	body {
		  padding-top: 5rem;
		}
		.starter-template {
		  padding: 3rem 1.5rem;
		  text-align: center;
		}
    </style>
    
     <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="<%=base %>/js/plugins/jquery.slim.min.js" ></script>
    <script src="<%=base %>/js/plugins/popper.min.js"></script>
    <script src="<%=base %>/js/plugins/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="<%=base %>/js/plugins/ie10-viewport-bug-workaround.js"></script>
    
    <script>
  		var basepath = '<%=base %>';
  	</script>
  </head>

  <body>
  
  	<nav class="navbar navbar-fixed-top navbar-light" style="background-color: #e3f2fd;">
      <a class="navbar-brand" href="#"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> 系统管理</a>
      <ul class="nav navbar-nav">
        <li class="nav-item active">
          <a class="nav-link" href="#">Dashboard <span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">About</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Contact</a>
        </li>
        <li class="nav-item dropdown">
	        <a class="nav-link dropdown-toggle" href="http://example.com" id="responsiveNavbarDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Dropdown</a>
	        <div class="dropdown-menu" aria-labelledby="responsiveNavbarDropdown">
	          <a class="dropdown-item" href="#">Action</a>
	          <a class="dropdown-item" href="#">Another action</a>
	          <a class="dropdown-item" href="#">Something else here</a>
	        </div>
	      </li>
      </ul>
      
      <ul class="nav navbar-nav float-xs-right">
        <li class="nav-item">
          <a class="nav-link" href="#"><span class="glyphicon glyphicon-cog" aria-hidden="true"></span>修改密码</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#"><span class="glyphicon glyphicon-off" aria-hidden="true"></span>登出</a>
        </li>
      </ul>
    </nav>