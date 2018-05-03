
(function() {
		$.when(  
				$.getJSON(basepath+'/api/project/init/')
		   ).then(function(init_json) {
			   init(init_json);
		}).fail(function(script_text,module){
			alert('init_error');
		});
	
	function init(init_json){
	
	var moduleDiv = document.getElementById('module-div');
	moduleDiv.innerHTML = genModule(init_json);
	
	$('.func').click(function(){
		var funccode = this.getAttribute('id');
		pc.boot.module(funccode);
	});
	
	function genModule(init_json){
		function addLi(modules) {
			var str = '';
			for (var i = 0; i < modules.length; i++) {
				var module = modules[i];
				str += '<li class="func" id="' + module['funccode'] + '"><a>'
						+ module['funcname'] + '</a></li>';
			}
			return str;
		}
		var strs = '';
		var modules = init_json['module'];
		for (var i = 0; i < modules.length; i++) {
			var str = '';
			var module = modules[i];
			if (module['funcflag'] == 1) {
				str += '<li class="func" id="' + module['funccode'] + '"><a>'
				+ module['funcname'] + '</a></li>';
			} else {
				str += addLi(module['module']);
			}
			strs += str;
		}
		return strs;
	}
	}
})();

