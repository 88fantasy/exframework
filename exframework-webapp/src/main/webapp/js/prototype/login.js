function doLogin(){
	var form = $('form');
	var data = form.serialize();
	$.ajax({
		url : basePath+"/api/login/async",
		type : "POST",
		data:data,
		success : function(o) {
			//localStorage.setItem("usertoken",obj.data);
			if(o.status == 200) {
				window.location.replace(basePath+"/dashboard.view");
			}
			else {
				app.util.alert(o.errorMessage);
			}
		},
		error:function(obj) {
			var json = obj.responseText;
			var o = JSON.parse(json);
			app.util.alert(o.message);
		}
	});
	return false;
}