var pc=function(pc){
	var _pc = pc ;
	namespace.reg('pc.boot');
	
	function Boot(){
		this.module_loading_flag = false;
		this.clear_reset='';
		this.previous=null;
	}
	Boot.prototype.module = function(funccode,ele){
		if(this.previous!=null){
			$(this.previous).removeClass('module-select');
		}
		$(ele).addClass('module-select');
		this.previous=ele;
		if(this.module_loading_flag == true)return;
		this.module_loading_flag = true;
		this.clear_reset = setTimeout(this.reset_module_loading_flag.bind(this),1000);
		pc.util.loading.open();
		pc.storage.getInstance(funccode,this.setInstance.bind(this));
	};
	Boot.prototype.setInstance = function(instance){
		this.instance = instance;
		this.init();
		clearTimeout(this.clear_reset);
		this.module_loading_flag = false;
		pc.util.loading.close();
	};
	Boot.prototype.handler = function(handler,ele){
		if((typeof (this.instance[handler]))!='function')console.log(handler);
		this.instance[handler](ele);
	}
	Boot.prototype.init = function(){
		initTool.init(this.instance);
	};
	Boot.prototype.reset_module_loading_flag=function(){
		this.module_loading_flag=false;
	}
	var initTool = function(){
		var same = '<button type="button" class="btn btn-primary " data-toggle="modal" data-target="#myModal" ><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 查询</button>' 
			 +' <button type="button" class="btn btn-primary menu-btn distance" handler="selectAll"><span class="glyphicon glyphicon-th-list" aria-hidden="true"></span> 全选 </button>'
			  +'<button type="button" class="btn btn-primary menu-btn" handler="inverseAll"><span class="glyphicon glyphicon-pushpinglyphicon glyphicon-pushpin" aria-hidden="true"></span> 反选</button>'
		+'<button type="button" class="btn btn-primary menu-btn" handler="refresh"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 刷新</button>'
		+'<button type="button" class="btn" style="width: 1px; padding: 0px;height: 35px;margin-right: 7px; border-right: 1.5px solid #337AB7;"></button>';
		var init=function(instance){
			init_button(instance.module.buttonList);
			init_queryparam(instance);
			init_data(instance);
			pc.util.reset();
		};
		var init_button =function(buttonList){
			var str=same;
			var menus = document.getElementById('menu-list');
			for(var i=0,len =buttonList.length;i<len;i++){
				var button = buttonList[i];
				str+='<button type="button" class="btn btn-primary menu-btn " handler="'+button.handler+'">'
				+'<span class="'+button.iconcls+'" aria-hidden="true"></span> '
				+button.name+'</button>';
			}
			menus.innerHTML=str;
			$('.menu-btn').click(function(){
				var handler = this.getAttribute('handler');
				pc.boot.handler(handler,this);
			});
		}
		var init_queryparam = function(instance){
			instance.queryparam();
		}
		var init_data = function(instance){
			if(instance.data==undefined){
				instance.data=instance.module.grid;	
			}
			instance.initTable();
			
			
		};
		return {
			init:init
		}
	}();
	_pc.boot = new Boot();
	return _pc;
}(pc||{});