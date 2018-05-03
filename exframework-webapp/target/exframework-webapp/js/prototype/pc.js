(function() {
	$.when($.getJSON(basepath+'/api/project/init')).then(function(init_json) {
		init(init_json);
	}).fail(function(script_text, module) {
		var json = script_text.responseText;
		var obj = JSON.parse(json);
		alert("出现错误:"+obj.message+",请联系管理员");
	});
	function redirect(url){
		location.href=url;
	}
	function init(init_json) {
		var moduleDiv = document.getElementById('module-div');
		moduleDiv.innerHTML = genModule(init_json);
		var user = $("#username")[0];
		var username = init_json.username;
		if(user.textContent){
			user.textContent = username;
		}
		else {
			user.innerHTML = username;
		}
		pc.boot.username = username;
		pc.boot.token = init_json.token;
		
		$('.func').click(function() {
			var funccode = this.getAttribute('id');
			pc.boot.module(funccode,this);
		});

		function genModule(init_json) {
			function addLi(modules) {
				var str = '';
				for (var i = 0; i < modules.length; i++) {
					var module = modules[i];
					str += '<li class="func" id="' + module['funccode']
							+ '"><a>' + module['funcname'] + '</a></li>';
				}

				return str;
			}
			var strs = '';
			var modules = init_json['module'];
			for (var i = 0; i < modules.length; i++) {
				var str = '';
				var module = modules[i];
				if (module['funcflag'] == 1) {
					str += '<ul class="nav nav-sidebar"><li class="titleColor func fix-module-func" id="'
							+ module['funccode']
							+ '">'
							+ module['funcname']
							+ '</li></ul>';
				} else {
					str += '<ul class="nav nav-sidebar"><li class="titleColor module" id="'
							+ module['funccode']
							+ '">'
							+ module['funcname']
							+ '</li>' + addLi(module['module']) + '</ul>';
				}

				strs += str;
			}

			return strs;
		}
	}
})();
