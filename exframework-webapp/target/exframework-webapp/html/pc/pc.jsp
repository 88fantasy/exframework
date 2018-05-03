<%@ page contentType="text/html; charset=UTF-8" %>
<% String base = request.getContextPath();%>
<!DOCTYPE html>
<html lang="zh-CN"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache, must-revalidate">
	<meta http-equiv="expires" content="wed, 26 feb 1997 08:21:57 gmt">
	<meta http-equiv="expires" content="0">
    
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="<%=base %>/img/gzmpc.ico">
    <title>打印中心</title>
    <link href="<%=base %>/css/pc/plugins/bootstrap.min.css" rel="stylesheet">
    <link href="<%=base %>/css/pc/plugins/dashboard.css" rel="stylesheet">
    <link href="<%=base %>/css/pc/prototype/pc.css" rel="stylesheet">
    <link href="<%=base %>/css/pc/plugins/bootstrap-datetimepicker.css" rel="stylesheet">
    <link href="<%=base %>/css/pc/plugins/bootstrap-multiselect.css" rel="stylesheet">
     <link href="<%=base %>/css/pc/plugins/jquery.bootgrid.css" rel="stylesheet">
    <script src="<%=base %>/js/pc/plugins/ie-emulation-modes-warning.js"></script>
  	<style>
  	html, body {
  		height:100%;
		font-family: 微软雅黑;
	}
	.copy{
	-moz-user-select: none; /*火狐*/
	-webkit-user-select: none; /*webkit浏览器*/
	-ms-user-select: none; /*IE10*/
	-khtml-user-select: none; /*早期浏览器*/
	user-select: none;
	}
	#navbar li a {
		color:white;
	}

	.menu-btn {
		width: auto;
		margin-right:6px;
		font-family: 微软雅黑;
	/* margin-right:15px; */
}
	.loading{  
    width:auto;  
    height:auto;  
    position: absolute;  
    line-height:56px;  
    color:#fff;  
    font-size:15px;  
    background: #000 url(<%=base %>/img/loading.gif) no-repeat  center center;  
    opacity: 0.7;  
    z-index:9999;  
    filter:progid:DXImageTransform.Microsoft.Alpha(opacity=70);  
}  
.glyphicon{
	top:2px;
}
.nav{
	font-size:15px;

    /* margin-left: 15%; */
  }
  .btn{
  	margin-top:10px;
  }
  .pagination{
  	margin-top:10px;margin-bottom:10px;
  }
  .multiselect{
    padding-top: 0px;
    padding-bottom: 0px;
    margin: 0px;
}
  	</style>
  	
  <script>
  		var basepath = '<%=base %>';
  </script>
  </head>
  <body >
 <nav><div id="loading" class="loading navbar navbar-inverse navbar-fixed-top" style="display:none"></div> </nav>
   <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand"><span class="glyphicon glyphicon-print" aria-hidden="true"></span>  打印中心</a>
           <a class="navbar-brand" style="margin-left:143px;"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> <span  id="username" >登录人</span></a>
<!--            <a class="navbar-brand" style="margin-left:10px;"><span class="glyphicon glyphicon-list" aria-hidden="true"></span>  模块名</a> -->
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li><a href="#" onclick="window.open('../kanban_printer.html')"><span class="glyphicon glyphicon-list" aria-hidden="true"></span>  看板</a></li>
            <li><a href="https://auth.gzmpc.com/sso/accountInfo.html" target="_blank"><span class="glyphicon glyphicon-cog" aria-hidden="true"></span>  修改密码</a></li>
            <li><a href="#" onclick="doLogout()"><span class="glyphicon glyphicon-off" aria-hidden="true"></span>  退出</a></li>
          </ul>
          <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search..." style="text-align:left">
          </form>
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar"style="width:15%;" id="module-div">
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main " style="paddind-bottom:0px;padding-top: 10px;" id="leftFrame">
           <div class="menu sub-header" id="menu-list">
			  
			   <!--  <div class="input-append date form_datetime"style="border: 0px;">
			    <input size="16" type="text" value="" readonly>
			    <span class="add-on"><i class="icon-th"></i></span>
				</div>  -->
		  </div> 
          <div class="table-responsive fix_zh" id="addTable" > 
          </div>
          <div id='Paging'>
          </div>
	       
        </div>
      </div>
    </div>
         <!-- Modal -->  
      <div id="myModal" class="modal fade fixModal" style="display:none" >  
            <div class="modal-header fix-header">  
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>  
                <h5 id="myModalLabel">查询界面</h5>  
            </div>  
            <div class="modal-body fix-body">
               <form> 
                 <div id="queryparam" ></div>
               </form>
            </div> 
            <div class="modal-footer fix-footer"style="padding-top: 5px;">  
                <button type="button" class="btn btn-primary menu-btn"  data-dismiss="modal" aria-hidden="true" handler="queryajax"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 确定</button>
                <button type="button" class="btn btn-primary menu-btn" handler="clear"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span> 清除</button> 
                <button class="btn btn-primary menu-btn" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span> 关闭</button> 
            </div>  
        </div>  

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
  
    <script src="<%=base %>/js/pc/plugins/jquery.min.js"></script>
    <script src="<%=base %>/js/pc/plugins/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="<%=base %>/js/pc/plugins/ie10-viewport-bug-workaround.js"></script>
    <script src="<%=base %>/js/pc/plugins/bootstrap-datetimepicker.js"></script>
    <script src="<%=base %>/js/pc/plugins/jquery.shiftcheckbox.js"></script>
    <script src="<%=base %>/js/pc/plugins/jquery.bootgrid.js"></script>
    <script src="<%=base %>/js/pc/prototype/namespace.js"></script>
    <script src="<%=base %>/js/pc/prototype/util.js"></script>
    <script src="<%=base %>/js/pc/prototype/pc.js"></script>
    <script src="<%=base %>/js/pc/prototype/grid.js"></script>
    <script src="<%=base %>/js/pc/prototype/queryparam.js"></script>
    <script src="<%=base %>/js/pc/plugins/bootstrap-multiselect.js"></script>
    <script src="<%=base %>/js/pc/prototype/boot.js"></script>
    <script src="<%=base %>/js/pc/prototype/storage.js"></script>
    <script src="<%=base %>/js/pc/prototype/table.js"></script>
  
   <script>
   	function doLogout(){
   		if(confirm("确认真的退出吗?")){
	   		window.location.replace("<%=base%>/logout/cas");
   		}
   	}
   </script>
</body></html>