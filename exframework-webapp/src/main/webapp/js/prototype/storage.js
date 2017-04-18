var pc = function(pc){
	var _pc=pc;
	function Storage (){
		this.project={};
	}
	Storage.prototype.getInstance =function(funccode,fn){
		/*if(this.project[funccode]==undefined){
			this.setInstance(funccode,fn);
		}else{
			fn(this.project[funccode]);
		}*/
		this.setInstance(funccode,fn);
	};
	Storage.prototype.setInstance = function(funccode,fn){
		var callback = function(module){
			this.project[funccode]=new pc.func[funccode][funccode](funccode);
			this.project[funccode].module=module;
			fn(this.project[funccode]);
		}
		this.util.addFile(funccode,callback.bind(this));
	};
	
	Storage.prototype.util=function(){
		function Util() {
		}
		Util.prototype.addFile = function(funccode, callback) {
			this.getCssFile(funccode, callback);
		};
		Util.prototype.getCssFile = function(funccode,callback){
			var head = document.getElementsByTagName('HEAD').item(0);
			var style = document.createElement('link');
			style.href =  basepath+"/css/func/" + funccode + "/" + funccode + ".css";
			style.rel = 'stylesheet';
			style.type = 'text/css';
			head.appendChild(style);
			var getJsFile=this.getJsFile;
			$(style).load(function(){
				getJsFile(funccode,callback);
			});
			$(style).error(function(){
				getJsFile(funccode,callback);
				alert("加载/func/" + funccode + "/" + funccode + ".css 出错");
			});
		};
		Util.prototype.getJsFile = function(funccode, callback) {
			$.when(  
				  $.getScript( basepath+"/js/func/" + funccode + "/" + funccode + ".js"),   
				  $.getJSON(basepath+'/api/func/init/'+funccode)  
			   ).then(function(script_text, module) {
				   callback(module[0]);
			}).fail(function(script_text,module){
				alert("加载"+"/js/func/" + funccode + "/" + funccode + ".js"
						+"  和      "+"/api/func/init/"+funccode+"时出错");
				pc.util.loading.close();
			});
	
		};
		return new Util();
	}();
	
	_pc.storage=new Storage();
	return _pc;
}(pc||{});
