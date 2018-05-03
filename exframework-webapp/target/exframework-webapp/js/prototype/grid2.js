function Grid(){         
	this.table_id = '';
	this.tbody_id ='tbody_id';
	this.table_className ='table table-striped table-bordered fix table-hover';
	this.table_head_className = 'test';
	this.table_body_className ='check ';
	this.previous='null';
	this.edit_status = false;
	this.rows_old_value=[];
	this.edit_trs=[];
	this.lastSelecteds=[];
	this.conditions=[];
}

Grid.prototype._genQueryParamJson=function(){
	var queryparam ={
			operations:["equal","greater","less","between","greater_equal","less_equal","in","matching","not_equal","is_null"],
			zh_operations : { 'equal':'等于', 'greater':'大于', 'less':'小于', 'between':'介于', 'greater_equal':'大于等于'
				, 'less_equal':'小于等于', 'in':'in','matching':'匹配', 'not_equal':'不等于', 'is_null':'is Null' },
			keys : [ 'column', 'operation', 'value1', 'value2', 'type' ],
			zh_keys : {
				'column' : '列名',
				'operation' : '操作',
				'value1' : '值1',
				'value2' : '值2',
				'type' : '类型'
			}		
	}
	queryparam.valuesList = this.module.queryparam.valuesList;
	this.module.queryparamjson=queryparam;
}
Grid.prototype._genTableJson=function(json){
	
	var data={};
	data.keys=json.keys;
	data.zh_keys=json.zh_keys;
	var valuesList = [];
	for(var i=0,len=json.valuesList.length;i<len;i++){
		var obj={};
		for(var j=0,jlen=data.keys.length;j<jlen;j++){
			var key = data.keys[j];
			obj[key] = json.valuesList[i][j];
		}
		valuesList.push(obj);
		
	}
	data.valuesList=valuesList;
	data.totalpage=json.totalpage;
	data.currentpage=json.currentpage;
	return data;
}
Grid.prototype._addPaging = function() {
	if(this.data==undefined)return '';
	if(this.data.totalpage==undefined)return '';
	
	var str ='<nav><ul class="pagination"id="page-ul">'
	if(this.data.currentpage==1)str+='<li page="previous" class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>';
	else str+='<li page="previous" handler="loadPage" class="paging-btn"><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>';
	for(var i=1;i<=this.data.totalpage;i++){
		if(this.data.currentpage==i)str+= '<li class="active" page="'+i+'" handler="loadPage"><a href="#">'+i+'</a></li>';
		else str+='<li class="paging-btn" page="'+i+'" handler="loadPage"><a href="#">'+i+'</a></li>';
	}
	if(this.data.currentpage==this.data.totalpage)str+='<li page="next" class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">&raquo;</span></a></li>';
	else str+='<li page="next" handler="loadPage" class="paging-btn"><a href="#" aria-label="Previous"><span aria-hidden="true">&raquo;</span></a></li>';
	str+='</ul></nav>';
	return str;

};

Grid.prototype.loadPage = function(ele){
	
	var page = ele.getAttribute('page');
	if(page=='next'){
		this.doQuery(this.conditions,this.currentpage+1);
	}else if(page=='previous'){
		this.doQuery(this.conditions,this.currentpage-1);
	}else{
		this.doQuery(this.conditions,page);
	}
	
}
Grid.prototype._adapterToTable = function() {
	
	if(this.data==undefined)return '';
	
	var json = this.data;
	var head = {};
	var body = {};
	head.keys = json.keys;
	head.zh_keys = json.zh_keys;
	head.className = this.table_head_className;
	body.keys = json.keys;
	body.valuesList = json.valuesList;
	body.className = this.table_body_className;
	body.id=this.tbody_id;
	this.querydata={head:head, body:body, table_id:'grid-'+this.funccode,
			table_className:this.table_className};
	var str = pc.table.addTable(this.querydata);
	return str;

};
Grid.prototype._adapterToQueryTable = function() {
	this._genQueryParamJson();
	var json = this.module.queryparamjson;
	var head = {};
	var body = {};
	head.keys = json.keys;
	head.values = json.zh_keys;
	head.className = this.table_head_className;
	body.keys = json.keys;
	body.valuesList = json.valuesList;
	body.className = this.table_body_className;
	var str = pc.queryparam.addTable(head, body, this.table_id,
			this.table_className, {
				'operations' : json.operations,
				'zh_operations' : json.zh_operations
			});
	return str;

};
Grid.prototype.saveItem=function(key,value,defalut){
	
	localStorage.setItem(key,JSON.stringify(value||defalut));
};
Grid.prototype.getItem=function(key,defalut){
	var obj;
	obj=localStorage.getItem(key);
	if(obj==undefined)return defalut;
	else return JSON.parse(obj);
};
Grid.prototype.setQueryParam=function(){
	this.conditions = this.getItem('pc.func.'+this.funccode+'.'+this.funccode+'.conditions',this.conditions,[]);
	
}
Grid.prototype.ajax = function(obj) {
	
	$.ajax(obj);
};
Grid.prototype.doQuery = function(conditions,currentpage){
	
	if(!currentpage)currentpage=1;
	var obj={};
	obj.async=true;
	obj.context = this;
	obj.url = basepath+'/api/func/query/'+this.funccode;
//	if(conditions!=undefined)obj.url = basepath+'/api/func/query/'+this.funccode+'?conditions='+encodeURI(JSON.stringify(conditions))+'&currentpage='+currentpage;
//	else obj.url = basepath+'/api/func/query/'+this.funccode+'?conditions='+encodeURI(JSON.stringify(this.conditions))+'&currentpage='+currentpage;
	
	var param = {
			token:pc.util.getToken(),
			conditions:JSON.stringify(this.conditions),
			currentpage:currentpage
	};
	
	obj.success=function(data){
		this.data = this._genTableJson(data);
		this.initTable();
		this.edit_status=false;
	}
	obj.error=function(data){
		pc.util.loading.close();
		var json = data.responseText;
		var obj = JSON.parse(json);
		alert(obj.message);
	}
	obj.type='post';
	obj.contentType="application/json",
	obj.data=JSON.stringify(param);
	/*obj.data={};
	obj.data.conditions=JSON.stringify(this.conditions);*/
	/*console.log(obj.data.conditions);*/
	obj.beforeSend=pc.util.loading.open;
	obj.complete=pc.util.loading.close;
	obj.timeout=function(){
		alert('请求超时');
		pc.util.loading.close();
	}
	this.ajax(obj);
};
Grid.prototype.queryByCondition=function(conditions){
	this.conditions = conditions;
	this.doQuery(conditions,1);
}
Grid.prototype.queryajax = function(ele) {
	var con = this.getSelectedConditions();
	if(typeof con == 'object'){
		ele.setAttribute('data-dismiss','modal');
		this.saveItem('pc.func.'+this.funccode+'.'+this.funccode+'.conditions',this.conditions_storage,[]);
		this.doQuery();
	}else{
		alert(str);
		ele.setAttribute('data-dismiss','');
	}
	
};
Grid.prototype.refresh = function() {
	if(this.conditions == undefined || this.conditions.length < 1){
		alert('没有输入查询条件');
		return;
//		this.conditions=[];
	}
	
	this.doQuery();
};

Grid.prototype.queryparam = function() {
	var str = this._adapterToQueryTable();
	document.getElementById("queryparam").innerHTML = str;
	$('#queryparam tr').click(function() {
		this.getElementsByTagName('input')[0].checked = true;
	});
	pc.util.tag.init();
	$('.fix-operation').change(function(event) {
		pc.util.tag.listener(event.target);
	});
	$('.fix-checkbox-event').click(function(event){
		this.getElementsByTagName('input')[0].checked = !(this.getElementsByTagName('input')[0].checked );
		event.stopPropagation();
	});
	this._loadConditions();
};
Grid.prototype.__setCondition=function(tr,condition){
	var type = condition['type'];
	var operation = condition['operation'];
	var value = condition['value'];
	tr.firstChild.firstChild.checked=true;
	pc.util.tag.listener(tr.childNodes[2].getElementsByTagName('select')[0]);
	if(operation=='between'){
		var inputs = tr.getElementsByTagName('input');
		 inputs[1].value = value[0];
		 inputs[2].value = value[1];
	}else if(operation=='is_null'){
		
	}else if(operation=='in'){
		var value1 = tr.childNodes[3];
		var button=value1.getElementsByTagName('button')[0];
		var span=value1.getElementsByTagName('span')[0];
		var buttonTitle='';
		var spanText='';
		for(var i=0;i<value.length;i++){
			buttonTitle+=value[i]+'##';
			spanText+=value[i]+', ';
		}
		button.setAttribute('title',buttonTitle.substr(0, buttonTitle.length - 2));
		span.innerText=spanText.substr(0, spanText.length - 2);
	}else{
		if(tr.getAttribute('querylist')=='undefined'){
			var inputs = tr.getElementsByTagName('input');
			inputs[1].value = value[0];
		}else{
			var select = childs[3].getElementsByTagName('select')[0];
			select.setAttribute('value',value[0]);
		}
	}
};
Grid.prototype._loadConditions=function(){
	var conditions = this.getItem('pc.func.'+this.funccode+'.'+this.funccode+'.conditions');
	if(conditions==undefined)return ;
	var table = document.getElementById('queryparam').getElementsByTagName('table')[0];
	var trs = table.getElementsByTagName("tr");
	this.conditions=[];
	for (var i = 1; i < trs.length; i++) {
		var tr = trs[i];
		var childs = tr.childNodes;
		var column = tr.childNodes[1].getAttribute('name');
		if(conditions[column]!=undefined){
			this.__setCondition(tr,conditions[column]);
			this.conditions.push(conditions[column]);
		}
		
	}
}
Grid.prototype._print=function(param,url,ele){
	var grid = this;
	var obj={};
	obj.async=true;
	obj.context = this;
	if(url==undefined)obj.url = basepath+'/api/'+this.funccode+'/print';
	else obj.url = url;
	//增加时间戳 防止重复提交
	param.timestamp = new Date().getTime();
	param.token =pc.util.getToken();
	obj.success=function(data){
		var o = {};
		if(data != null){
			if(typeof(data) == 'object'){
				o = data;
			}
			else if(typeof(data) == 'string'){
				o = JSON.parse(data);
			}
			else {
				alert('返回类型为'+typeof(data));
			}
		}
		if(o.status!=200){
			alert(o.errorMessage);
			return;
		}else{
			alert('即将开始进行打印');
			setTimeout(grid.refresh.bind(grid),0);
			if(ele) {
				ele.disabled = false;
			}
		}
	}
	obj.error=function(data){
		pc.util.loading.close();
		if(ele) {
			ele.disabled = false;
		}
		var o = {};
		var json = data.responseText;
		var obj = JSON.parse(json);
		alert(obj.message);
	}
	obj.type='post';
	obj.contentType="application/json",
	obj.data=JSON.stringify(param);
	obj.beforeSend=pc.util.loading.open;
	obj.complete=pc.util.loading.close;
	obj.timeout=function(){
		alert('请求超时');
		pc.util.loading.close();
		if(ele) {
			ele.disabled = false;
		}
	}
	console.log("提交打印"+obj);
	if(ele) {
		ele.disabled = true;
	}
	this.ajax(obj);
};
Grid.prototype.print = function() {
	alert('没有实现子类的print方法');
};
Grid.prototype.chainprint = function() {
	alert('没有实现子类的chainprint方法');
	
};
Grid.prototype.preview = function() {
	
};
Grid.prototype._getRows = function(flag) {
	var objs = [];
	var table = document.getElementsByTagName('table')[0];
	var trs = table.getElementsByTagName("tr");
	for (var i = 1; i < trs.length; i++) {
		var tr = trs[i];
		var childs = tr.childNodes;
		if (childs[0].firstChild.checked == true||flag) {
			var len = childs.length;
			var obj = {};
			for (var j = 1; j < len; j++) {
				var key = childs[j].getAttribute('name');
				var value = this._getTdValue(childs[j]);
				obj[key] = value;
			}
			objs.push(obj);
		}
	}
	return objs;

};
Grid.prototype.getSelectedRows = function() {
	return this._getRows(false);
};
Grid.prototype.getAllRows = function() {
	return this._getRows(true);
};
Grid.prototype.toggleTrClass=function(check,tr,flag){

	check.checked=!check.checked;
	if(flag!=undefined)check.checked=flag;
	if(check.checked==true){
		$(tr).addClass('selectTr');
	}else{
		$(tr).removeClass('selectTr');
	}
};
Grid.prototype.selectAll = function() {
	var table = document.getElementsByTagName('table')[0];
	var trs = table.getElementsByTagName("tr");
	for (var i = 1; i < trs.length; i++) {
		var tr = trs[i];
		var childs = tr.childNodes;
		this.ctrlKey(childs[0].firstChild,tr,true,true);
	}
};
Grid.prototype.inverseAll = function() {
	var table = document.getElementsByTagName('table')[0];
	var trs = table.getElementsByTagName("tr");
	for (var i = 1; i < trs.length; i++) {
		var tr = trs[i];
		var childs = tr.childNodes;
		this.ctrlKey(childs[0].firstChild,tr,true);
	}
};
Grid.prototype.clear = function(){
	var table = document.getElementById('queryparam').getElementsByTagName('table')[0];
	var trs = table.getElementsByTagName("tr");
	for (var i = 1; i < trs.length; i++) {
		var tr = trs[i];
		var childs = tr.childNodes;
		childs[0].firstChild.checked = false;
		/*this.ctrlKey(childs[0].firstChild,tr,false);*/
	}
};
Grid.prototype.getSelectedConditions = function(){
	var checkValue = function(value,type){
		if(value==''||value==undefined||value=='undefined'){
			return '没有定义,请重新输入';
		}
		if(type=='number'){
			if(isNaN(parseFloat(value)))return '输入值不正确,请重新输入';
		}
		return  'success';
	}
	var queryparam = {};
	var objs = [];
	var objs_storage={};
	var table = document.getElementById('queryparam').getElementsByTagName('table')[0];
	var trs = table.getElementsByTagName("tr");
	
	for (var i = 1; i < trs.length; i++) {
		var tr = trs[i];
		var childs = tr.childNodes;
		if (childs[0].firstChild.checked == true) {
			var obj = {};
			
			var type = tr.getAttribute('type');
			obj.type=type;
			var column = childs[1];
			var columnName =  childs[1].innerText;
			obj.column = column.getAttribute('name');
			
			var operation = childs[2].childNodes[0];
			obj.operation = operation[operation.selectedIndex].getAttribute('dd_id');
			
			var value=[];
			if(obj.operation=='between'){
				var inputs = tr.getElementsByTagName('input');
				var ColumnValue = inputs[1].value;
				var str = checkValue(ColumnValue,type);
				if(str=='success'){
					value.push(ColumnValue);
				}else {
					continue;
					return columnName+': 值1'+str;
				}
				var ColumnValue = inputs[2].value;
				var str = checkValue(ColumnValue,type);
				if(str=='success'){
					value.push(ColumnValue);
				}else {
					return columnName+': 值2'+str;
				}
			}else if(obj.operation=='is_null'){
				value.push('is_null');
			}else if(obj.operation=='in'){
				var value1 = childs[3];
				var strs = value1.getElementsByTagName('button')[0].getAttribute('title');
				if(strs.length>=1)value = strs.split('##');
				else continue;
				
			}else{
				if(tr.getAttribute('querylist')=='undefined'){
					var inputs = tr.getElementsByTagName('input');
					var ColumnValue = inputs[1].value;
					var str = checkValue(ColumnValue,type);
					if(str=='success')value.push(ColumnValue);
					else {
						continue;
						/* return columnName+': 值1'+str;*/
					}
				}else{
					var select = childs[3].getElementsByTagName('select')[0];
					var ColumnValue = select[select.selectedIndex].getAttribute('dd_id');
					var str = checkValue(ColumnValue,type);
					if(str=='success')value.push(ColumnValue);
					else {
						continue;
						/*return columnName+': 值1'+str;*/
					}
				}
			}
			obj.value=value;
			
			objs.push(obj);
			objs_storage[obj['column']]=obj;
		}

	}
	this.conditions=objs;
	this.conditions_storage=objs_storage;
	return this.conditions;
};
Grid.prototype.getSelectedNeededs=function(needs){
	
	var obj={};
	var rows =this.getSelectedRows();
	for(var j=0,jlen=needs.length;j<jlen;j++){
		var str='';
		for(var i=0,len=rows.length;i<len;i++){
			var row = rows[i];
			str+=row[needs[j]]+',';
		}
		obj[needs[j]]=str.substring('0', str.length - 1);
	}
	
	return obj;
};
Grid.prototype.getSelectedNeededObjs=function(needs){
	
	var objs=[];
	var rows =this.getSelectedRows();
	for(var i=0,len=rows.length;i<len;i++){
		var obj={};
		var row = rows[i];
		for(var j=0,jlen=needs.length;j<jlen;j++){
			var need =needs[j];
			obj[need]=row[need];
		}
		objs.push(obj);
	}
	return objs;
};
Grid.prototype._preview=function(url,param,name){
	if(!url){
		url = basepath+'/api/'+this.funccode+'/pdf?';
	}
	if(param==undefined){
		alert('请传入正确的url');
		return;
	}
	console.log(param);
	var urlparam = this.objToUrl(param);
	if(name==undefined)name="预览";
	window.open( url+urlparam,name);
};

Grid.prototype.tableSort=function(ele){
	var name = ele.getAttribute('name');
	if(this.data==undefined)return;
	if(this.data.valuesList==undefined)return;
	document.getElementById('tbody_id').innerHTML='<div style="font-size:26px;">Loading ...</div>';
	/*pc.util.loading.open();*/
	/*alert(name+': 排序开始');*/
	var span = ele.getElementsByTagName('span')[0];
	var order=span.getAttribute('order');
	this.data.valuesList=this.data.valuesList.sort(pc.util.compare(name,order));
	this.initTable(true);
	
	if(this.previous!='null'){
		this.previous.getElementsByTagName('span')[0].setAttribute('order','up');
		this.previous.getElementsByTagName('span')[0].setAttribute('class','');
	}
	this.previous = ele;
	
	if(order=='up'){
		span.setAttribute('order','down');
		span.setAttribute('class','glyphicon glyphicon-triangle-top');
	}else{
		span.setAttribute('order','up');
		span.setAttribute('class','glyphicon glyphicon-triangle-bottom');
	}
	
	/*alert(name+': 排序结束');*/
	/*pc.util.loading.close();*/
};
Grid.prototype.ctrlKey=function(check,tr,ctrlKeyValue,flag){
	if(ctrlKeyValue!=true){
		for(var i=0,len=this.lastSelecteds.length;i<len;i++){
			this.toggleTrClass(this.lastSelecteds[i]['check'],this.lastSelecteds[i]['tr'],false);
		}
		this.lastSelecteds=[];
	}
	this.lastSelecteds.push({check:check,tr:tr});
	this.toggleTrClass(check,tr,flag);
}
Grid.prototype.initTable = function(flag){
	if(flag==true){
		this.data.className = this.table_body_className;
		var str =pc.table.addTbodyTrs(this.data);
		document.getElementById("tbody_id").innerHTML = str;
		
	}else{
		var str =this._adapterToTable();
		document.getElementById("addTable").innerHTML = str;
		$('.th-sort').dblclick(function(){
			var handler = 'tableSort';
			pc.boot.handler(handler,this);
		});	
		
		/* $('#grid-'+this.funccode).bootgrid();*/
		
	}
	document.getElementById("Paging").innerHTML = this._addPaging();;
	$('.paging-btn').click(function() {
		var handler = this.getAttribute('handler');
		pc.boot.handler(handler,this);
	});
	var that = this;
	var handleClick = $('.shiftCheckbox').shiftcheckbox();
	$('#addTable tbody tr').click(function(event){
		
		var check = this.getElementsByTagName('input')[0];
		if(that.edit_status==true){
			$(this).removeClass('selectTr');
			return;
		}else{
			that.ctrlKey(check,this,event.shiftKey||event.ctrlKey);
			handleClick(this,event);
		}
	});
	
	
};
Grid.prototype._getTrs = function(flag){
	var objs = [];
	var table = document.getElementsByTagName('table')[0];
	var trs = table.getElementsByTagName("tr");
	for (var i = 1; i < trs.length; i++) {
		 tr = trs[i];
		var childs = tr.childNodes;
		if (childs[0].firstChild.checked == true||flag) {
			objs.push(tr);
		}

	}
	return objs;
};
Grid.prototype.getSelectedTrs = function(){
	return this._getTrs(false);
};
Grid.prototype.getAllTrs = function(){
	return this._getTrs(true);
};
Grid.prototype._edit1 = function(limit){
	if(limit==undefined){
		alert('没有定义limit');
		return;
	}
	var trs = this.getSelectedTrs();
	for(var i=0,len=trs.length;i<len;i++){
		var tr = trs[i];
		var str = '';
		str+='<td ><input type="checkbox" checked></td>';
		for(var j=1;j<limit;j++){
			var td = tr.childNodes[j];
			str+='<td name="'+td.getAttribute('name')+'">'+$(td).html()+'</td>';
		}
		for(var j=limit,jlen=tr.childNodes.length;j<jlen;j++){
			var td = tr.childNodes[j];
			var value;
			if(td.childNodes[0]&&td.childNodes[0].value!=undefined){
				value = td.childNodes[0].value;
			}else{
				value = td.innerText;
			}
			str+='<td name="'+td.getAttribute('name')+'"><input type="text" style="text-align:center;border:0px" value="'+value+'"> <span class="glyphicon glyphicon-edit" aria-hidden="true"></span></td>';
		}
		tr.innerHTML=str; 
	}
};
Grid.prototype._getTdValue = function(td){
	var value;
	var input = td.getAttribute('_input');
	if(input=='text'){
		value = td.getElementsByTagName('input')[0].value;
	}else if(input=='date'){
		value = td.getElementsByTagName('input')[0].value;
	}else if(input=='select'){
		var select = td.getElementsByTagName('select')[0];
		this.__now_select_value=select[select.selectedIndex].innerText;
		value = select[select.selectedIndex].getAttribute('dd_id');
	}else if(input=='checkbox'){
		value = td.getElementsByTagName('input')[0].checked;
		if(value==true)value='1';
		else value='0';
	}else{
		value = td.innerText;
		this.__now_select_value=value;
	}
	return value;
};
Grid.prototype._genEditTd = function(td,item){
	var input = item['input'];
	var value = this._getTdValue(td);
	var str='';
	if(input=='text'){
		str+='<td name="'+td.getAttribute('name')+'" _input="text"><input type="text" style="text-align:center;border:0px" value="'+value+'"> <span class="glyphicon glyphicon-edit" aria-hidden="true"></span></td>';
	}else if(input=='date'){
		str += '<td name="'+td.getAttribute('name')+'" _input="date"><div class="input-append date form_datetime"style="border: 0px;"><input class="fix-time-picker" size="16" type="text" value="'+ value+'" readonly> <span class="glyphicon glyphicon-calendar" aria-hidden="true"></span><span class="add-on"><i class="icon-th "></i></span></div></td>'
	}else if(input=='select'){
		var querylist = item['querylist'];
		str+='<td name="'+td.getAttribute('name')+'" _input="select"><select class="fix-time-picker" value="'+this.__now_select_value+'">'
		+ pc.util.tag.addOptions(querylist,true)
		+ '</select> <span class="glyphicon glyphicon-th-list" aria-hidden="true"></span></td>';
	}else if(input=='checkbox'){
		if(value!='0')str+='<td name="'+td.getAttribute('name')+'" _input="checkbox" ><input type="checkbox" checked> <span class="glyphicon glyphicon-check" aria-hidden="true"></span></td>';
		else str+='<td name="'+td.getAttribute('name')+'" _input="checkbox" ><input type="checkbox" > <span class="glyphicon glyphicon-check" aria-hidden="true"></span></td>';
	}else{
		str+='<td name="'+td.getAttribute('name')+'" >'+value+'</td>';
	}
	return str;
};
Grid.prototype._getEditedRows = function(trs){
	var objs = [];
	for (var i = 0; i < trs.length; i++) {
		var tr = trs[i];
		var childs = tr.childNodes;
		var len = childs.length;
		var obj = {};
		for (var j = 1; j < len; j++) {
			var key = childs[j].getAttribute('name');
			var value = this._getTdValue(childs[j]);
			obj[key] = value;
		}
		objs.push(obj);	
	}
	return objs;
};
Grid.prototype._edit = function(trs){
	if(this.edit_status==true){
		alert('当前有未保存行,请保存先');
		return false;
	}
	var dataitemcfg = this.dataitemcfg;
	if(trs==undefined)trs=this.getSelectedTrs();
	if(trs.length==0){
		return false;
	}
	this.edit_status=true;
	for(var i=0,len=trs.length;i<len;i++){
		var tr = trs[i];
		var str = '';
		str+='<td ><input type="checkbox" checked></td>';
		for(var j=1,jlen=tr.childNodes.length;j<jlen;j++){
			var td = tr.childNodes[j];
			var item = dataitemcfg[j-1];
			str+=this._genEditTd(td,item);
		}
		tr.innerHTML=str;
		var selects = tr.getElementsByTagName('select');
		for(var k=0,klen=selects.length;k < klen;k++){
			var select = selects[k];
			select.value=select.getAttribute('value');
		}
	}
	this.rows_old_value=this._getEditedRows(trs);
	this.edit_trs=trs;
	$(".form_datetime").datetimepicker({
		format : 'yyyy-mm-dd hh:ii:ss',
		autoclose : true,
		todayBtn : true,
		startDate : date,
		minuteStep : 10
	});
};
Grid.prototype.addNewRow = function(){
	
	this.lastSelecteds=[];
	var tr = document.createElement('tr');
	var td = document.createElement('td');
	td.innerHTML='<input type="checkbox" class="shiftCheckbox" checked >';
	tr.appendChild(td);
	for(var i=0,len = this.data.keys.length;i<len;i++){
		var td = document.createElement('td');
		td.setAttribute('name',this.data.keys[i]);
		tr.appendChild(td);
	}
	if(this._edit([tr])==false)return;
	var tbody = document.getElementById('tbody_id');
	if(!tbody){
		var tbody = document.createElement('tbody');
		document.getElementsByTagName('table')[0].appendChild(tbody);
		tbody.appendChild(tr);
	}else{
		if(tbody.firstChild)tbody.insertBefore(tr,tbody.firstChild) ;
		else tbody.appendChild(tr);
	}
	$(tr).click(function(){
		if(pc.boot.instance.edit_status!=true)
		this.getElementsByTagName('input')[0].checked = !(this.getElementsByTagName('input')[0].checked);
	});
	$('.form_datetime').datetimepicker({
		format : 'yyyy-mm-dd hh:ii:ss',
		autoclose : true,
		todayBtn : true,
		startDate : date,
		minuteStep : 10
	});
	
	this.ctrlKey(tr.getElementsByTagName('input')[0],tr,false,true);
};
Grid.prototype.deleteRow = function(){
	if(this.edit_status==true){
		alert('当前有未保存行,请保存先');
		return false;
	}
	var rows = this.getSelectedRows();
	if(rows.length==0){
		alert('请选择一行');
	}
	/*document.getElementById('tboyd_id').appengChild() ;*/
};
Grid.prototype.getDirtyRows=function(oldRows,newRows){
	console.log(oldRows);
	console.log(newRows);
	if(oldRows.length!=newRows.length)alert('严重，程序内部错误，请找本部服务组');
	var len = this.data.keys.length;
	var rows=[];
	for(var i=0;i<oldRows.length;i++){
		var oldRow=oldRows[i];
		var newRow=newRows[i];
		var k=0;
		for(var j in oldRow){
			if(oldRow[j]!=newRow[j])continue;
			k++;
		}
		if(k!=len)rows.push(newRow);
	}
	return rows;
}
Grid.prototype.save = function(){
	if(this.rows_old_value.length==0){
		alert('请先编辑再保存');
	}
	var rows = this._getEditedRows(this.edit_trs);

	var dirtyRows = this.getDirtyRows(this.rows_old_value,rows);
	console.log(dirtyRows);
	this.refresh();
	this.edit_status=false;
	this.rows_old_value=[];
	this.edit_trs=[];
};

Grid.prototype.objToUrl = function(obj){
	if(typeof obj == 'string')return obj;
	var str = '';
	for( var key in obj) {
		var row = obj[key];
		if(typeof(row) == 'object'){
			str += key+"="+JSON.stringify(row);
		}
		else {
			str += key+"="+row;
		}
		str +="&";
	}
	if(str.length>0){
		str = str.substring(0,str.length-1);
	}
	return str;
	
};

Grid.prototype._appletPrint = function(url){
	 var divObj=document.createElement("div");
//	 divObj.style.display = 'none';
	 divObj.style.height = '0px';
	 divObj.style.width = '0px';
	 alert("即将进行打印,请稍等");
   	var str="<object classid = 'clsid:8AD9C840-044E-11D1-B3E9-00805F499D93' codebase = 'http://java.sun.com/update/1.8.0/jinstall-1_8-windows-i586.cab#Version=8,0,0,16'"+
   	       " WIDTH = 0 HEIGHT = 0 NAME = 'myprint' id='objectid'>"+
   	       " <PARAM NAME = CODE VALUE = 'com.gzmpc.applet.PrintApplet.class' >"+
   	      "  <PARAM NAME = CODEBASE VALUE = '/pc/applet/'>"+
   	       " <PARAM NAME = ARCHIVE VALUE = '/pc/applet/printapplet.jar,/pc/applet/commons-logging-1.2.jar,/pc/applet/jasperreports-6.1.0.jar,/pc/applet/commons-collections-3.2.1.jar,/pc/applet/commons-digester-1.8.jar' >"+
   	      "  <PARAM NAME = NAME VALUE = 'myapp' >"+
   	       " <param name = 'type' value = 'application/x-java-applet;version=1.8'>"+
   	        "<param name = 'scriptable' value = 'true'>"+
   	     " <param name='addressurl' value='"+url+
 	       "'>"+
   	       "<comment>"+
   	    	"<embed"+
   	               " type = 'application/x-java-applet;version=1.8' "+
   	              "  CODE = 'com.gzmpc.applet.PrintApplet.class' "+
   	               " ARCHIVE = '/pc/applet/printapplet.jar,/pc/applet/commons-logging-1.2.jar,/pc/applet/jasperreports-6.1.0.jar,/pc/applet/commons-collections-3.2.1.jar,/pc/applet/commons-digester-1.8jar'"+
   	               " NAME = 'myapp'"+
   	               " WIDTH = 200 "+
   	                "HEIGHT = 100"+
   	    	   " scriptable = true"+
   	    	   " pluginspage = 'http://java.sun.com/products/plugin/index.html#download'>"+
   	    	   " <noembed>"+
   	                "</noembed>"+
   	    	"</embed>"+
   	       " </comment>"+
   	   "</object>";
   	
   	   
   	 var first=document.body.firstChild;//得到页面的第一个元素
   	divObj.innerHTML=str;
   	document.body.insertBefore(divObj,first);
   	alert("准备完成");
};