var pc = function(pc) {
	pc.util.strToArray = function(str) {
		if (str == undefined)
			return;
		var opts = [];
		var strs = str.split('-');
		for (var i = 0, len = strs.length; i < len; i = i + 2) {
			var opt = {};
			opt['dd_id'] = strs[i];
			opt['dd_value'] = strs[i + 1];
			opts.push(opt);
		}
		return opts;
	};
	pc.util.arrayToStr = function(opts) {
		if (opts == undefined)
			return;
		var str = '';
		for (var i = 0; i < opts.length; i++) {
			var opt = opts[i];
			str += opt["dd_id"] + '-' + opt["dd_value"] + '-';
		}
		str = str.substring('0', str.length - 1);
		return str;
	};
	var tag = function() {
		 date = (new Date()).GetDateStr(-10);
		var _tag = {};
		var tag_str;
		var operations = [ 'equal', 'greater', 'less', 'between',
				'greater_equal', 'less_equal', 'in', 'matching', 'not_equal',
				'is_null' ];
		var type = [ 'number', 'date', 'querylist', 'string', 'upper', 'lower' ];
		_tag.listener = function(ele,dd_id_value) {
			var dd_id = ele[ele.selectedIndex].getAttribute('dd_id');
			if(dd_id_value!=undefined)dd_id=dd_id_value;
			var typename = ele.parentNode.parentNode.getAttribute('type');
			var querylist = ele.parentNode.parentNode.getAttribute('querylist');
			var value2 = ele.parentNode.parentNode.lastChild;
			var value1 = value2.previousSibling;
			if (dd_id == 'between') {
				value1.innerHTML = _tag.open(typename, 'value1', querylist,dd_id);
				value2.innerHTML = _tag.open(typename, 'value2', querylist,dd_id,true);
				/*_tag.initOne(value1,typename);
				_tag.initOne(value2,typename);*/
			} else if (dd_id == 'is_null') {
				value1.innerHTML = _tag.close();
				value2.innerHTML = _tag.close();

			} else {
				value1.innerHTML = _tag.open(typename, 'value1', querylist,dd_id);
				value2.innerHTML = _tag.close();
				_tag.initOne(value1,typename,dd_id);
			}
			
			$(".form_datetime").datetimepicker({
				format : 'yyyy-mm-dd hh:ii:ss',
				autoclose : true,
				todayBtn : true,
			/*	startDate : date,*/
				minuteStep : 10
			});
			
		}
		_tag.initOne=function(ele,type,dd_id){
			 var node = ele.firstChild;
			 if(type=='querylist'&&dd_id=='in'){
				$(node).multiselect({
					 buttonTitle: function(options, select) {
			                if (options.length === 0) {
			                    return this.nonSelectedText;
			                }
			                else {
			                    var selected = '';
			                    options.each(function () {
			                        selected += this.getAttribute('dd_id') + '##';
			                    });
			                    return selected.substr(0, selected.length - 2);
			                }
			            }
				});
			}
		}
		_tag.init = function() {
			$(".form_datetime").datetimepicker({
				format : 'yyyy-mm-dd hh:ii:ss',
				autoclose : true,
				todayBtn : true,
				/*startDate : date,*/
				minuteStep : 10
			});
			$('.multiselect').multiselect({
				 buttonTitle: function(options, select) {
		                if (options.length === 0) {
		                    return this.nonSelectedText;
		                }
		                else {
		                    var selected = '';
		                    options.each(function () {
		                        selected += this.getAttribute('dd_id') + '##';
		                    });
		                    return selected.substr(0, selected.length - 2);
		                }
		            }
			});
		}
		_tag.open = function(type, key, querylist,dd_id){
		
			var str = '';
			if (type == 'date') {
				var date = (new Date()).GetDateStr(0);
				if(key=='value2')date=(new Date()).GetDateStr(1);
				str += ' <div class="input-append date form_datetime"style="border: 0px;"><input class="fix-time-picker" size="16" type="text" value="'+ date+'" readonly> <span class="glyphicon glyphicon-calendar" aria-hidden="true"></span><span class="add-on"><i class="icon-th "></i></span></div> '

			} else if (type == 'querylist') {
				if(dd_id=='in')
				str += '<select class="fix-time-picker  multiselect" multiple="multiple">'
						+ _tag.addOptions(pc.util.strToArray(querylist),false)
						+ ' <span class="glyphicon glyphicon-th-list" aria-hidden="true"></span><span class="add-on"><i class="icon-th "></i></span></select>';
				
				else str += '<select class="fix-time-picker">'
					+ _tag.addOptions(pc.util.strToArray(querylist),true)
					+ ' <span class="glyphicon glyphicon-th-list" aria-hidden="true"></span><span class="add-on"><i class="icon-th "></i></span></select>';

			} else {
				str += '<input  type="text" class="fix-time-picker"/> <span class="glyphicon glyphicon-edit" aria-hidden="true"></span><span class="add-on"><i class="icon-th "></i></span>';
			}

			return str;
		};
		_tag.close = function() {
			return '';
		};
		_tag.addOptions = function(opts,flag) {
			var str = '';
		
			if(flag==true)str+='<option></option>';
			for (var i = 0; i < opts.length; i++) {
				var opt = opts[i];
				
				str += '<option dd_id="' + opt["dd_id"] + '">'
						+ opt["dd_value"] + '</option>';
				
			}
			return str;
		}

		return _tag;

	}();

	var queryparam = function() {
		var _table = {};
		var operations = {};
		_table.addThead = function(keys, values, className) {
			var _class = '';
			if (className != null && className != undefined)
				_class += className;
			var str = '<thead><tr class="' + _class + '">';
			str += '<th></th>';
			for (var i = 0; i < keys.length - 1; i++) {
				var key = keys[i];
				var value = values[key];
				if (value == undefined)
					value = key;
				str += '<th>' + value + '</th>';
			}
			str += '</tr></thead>';
			return str;
		};
		_table.addTfoot = function(keys, values, className) {
			var _class = '';
			if (className != null && className != undefined)
				_class += className;
			var str = '<tfoot><tr class="' + _class + '">';
			str += '<td></td>';
			for (var i = 0; i < keys.length - 1; i++) {
				var key = keys[i];
				var value = values[key];
				if (value == undefined)
					value = key;
				str += '<td>' + value + '</td>';
			}
			str += '</tr></tfoot>';
			return str;
		};

		_table.addTr = function(keys, values, className, operations) {
			var _class = '';
			var type = values[keys[keys.length - 1]];
			if (className != null && className != undefined)
				_class += className;
			var str = '<tr class="' + _class + '" type="' + type + '" querylist="'
					+ pc.util.arrayToStr(values['querylist']) + '">';
			str += '<td  class="fix-checkbox-event"><input type="checkbox"></td>';
			// 列--column
			var key = keys[0];
			str += '<td name=' + values['datacol'] + '>' + values[key] + '</td>';

			// operation
			var key = keys[1];
			str += '<td name=' + key + '>' + '<select class="fix-operation">'
					+ addOperations({operations:values['operations'],zh_operations:operations.zh_operations}, values['operation'])
					+ '</select></td>';

			if (values['operation'] == 'between') {
				str += addTd(type, pc.util
						.arrayToStr(values['querylist']), true, true,values['operation']);
				
			} else if (values['operation'] == 'is_null') {
				str += addTd(type, pc.util
						.arrayToStr(values['querylist']), false, false,values['operation']);
			} else {
				str += addTd(type,  pc.util
						.arrayToStr(values['querylist']), true, false,values['operation']);
			}

			str += '</tr>'
			return str;
		};
		function addTd(type, querylist,flag1,flag2,dd_id) {
			var str = '';
			var str1='';
			var str2='';
			if(flag1) str1=pc.util.tag.open(type, 'value1', querylist,dd_id);
			if(flag2) str2=pc.util.tag.open(type, 'value2', querylist,dd_id);
			str += '<td name="value1">'
					+ str1 + '</td>';
			str+='<td name="value2" class="fix-time-picker ">'
				+ str2 + '</td>';
			return str;
		}
		function addOperations(opts,selected) {
			var operations = opts["operations"];
			var zh_operations = opts['zh_operations'];
			var str = '';
			for (var i = 0; i < operations.length; i++) {
				var opt = operations[i];
				var zh_opt = zh_operations[opt];
				if (opt == selected)
					str += '<option selected dd_id="' + opt + '">' + zh_opt
							+ '</option>';
				else
					str += '<option dd_id="' + opt + '">' + zh_opt
							+ '</option>';
			}
			return str;
		}

		_table.addTbody = function(keys, valuesList, id, className, operation) {

			var _id = '';
			if (id != null && id != undefined)
				_id += id;
			var str = '<tbody id="' + _id + '">';
			for (var i = 0; i < valuesList.length; i++) {
				var values = valuesList[i];
				str += _table.addTr(keys, values, className, operation);
			}
			str += '</tbody>';
			return str;
		};

		_table.addTable = function(head, body, id, className, operation) {
			var str = '';
			str += '<table class="' + className + '" id="' + id + '">';
			str += _table.addThead(head.keys, head.values, head.className);
			/*str += _table.addTfoot(head.keys, head.values, head.className);*/
			str += _table.addTbody(body.keys, body.valuesList, body.id,
					body.className, operation);
			str += "</table>";
			return str;
		};
		_table.clear=function(ele){
			var table = ele;
			var trs = table.getElementsByTagName("tr");
			for (var i = 1; i < trs.length; i++) {
				var tr = trs[i];
				var childs = tr.childNodes;
				childs[0].firstChild.checked = false;
			}
		}
		return _table;
	}();
	pc.util.tag = tag;
	pc.queryparam = queryparam;
	return pc;
}(pc || {});
