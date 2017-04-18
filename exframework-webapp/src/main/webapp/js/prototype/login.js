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
				window.location.replace(basePath+"/sys/funcdev/main.view");
			}
			else {
				alert(o.errorMessage);
			}
		},
		error:function(obj) {
			var json = obj.responseText;
			var o = JSON.parse(json);
			alert(o.message);
		}
	});
	return false;
}