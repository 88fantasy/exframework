var pc = function(pc){
	var _pc=pc;
	var table = function() {
		var _table = {};
		_table.addThead = function(data) {
			var keys=data.keys, 
				zh_keys=data.zh_keys;
				className=data.className;
			var _class = '';
			if (className != null && className != undefined)
				_class += className;
			var str = '<thead><tr class="' + _class + '">';
			str += '<th></th>';
			
			for (var i = 0; i < keys.length; i++) {
				var zh_key = zh_keys[i];
				var key =keys[i];
				var value = zh_key;
				if (value == undefined)
					value = key;
				str += '<th name="'+key+'" class="th-sort " data-column-id="'+key+'">' +value+ ' <span order="up"  aria-hidden="true"></span>' + '</th>';
			}
			str += '</tr></thead>';
			return str;
		};
		_table.addTfoot = function(data) {
			var keys=data.keys, 
				zh_keys=data.zh_keys;
				className=data.className;
			var _class = '';
			if (className != null && className != undefined)
				_class += className;
			var str = '<tfoot><tr class="' + _class + '" id="fix-thead">';
			str += '<td></td>';

			for (var i = 0; i < keys.length; i++) {
				var zh_key = zh_keys[i];
				var key =keys[i];
				var value = zh_key;
				if (value == undefined)
					value = key;
				str += '<td>' + value + '</td>';
			}
			str += '</tr></tfoot>';
			return str;
		};

		_table.addTr = function(data) {
			var keys=data.keys, 
				values=data.values, 
				className=data.className
				index= data.index;
			
			var _class = '';
			if (className != null && className != undefined)
				_class += className;
			var str = '<tr class="' + _class + '" >';
			str += '<td><input type="checkbox" value="'+index+'" class="shiftCheckbox"></td>';

			for (var i = 0; i < keys.length; i++) {
				var key = keys[i];
				str += '<td name=' + key + '>' + values[key] + '</td>';
			}
			str += '</tr>'
			return str;
		};

		_table.addTbody = function(data) {
			if(data.valuesList==undefined) return'';
			var keys = data.keys, 
				valuesList=data.valuesList, 
				id=data.id, 
				className=data.className;
			
			var _id = '';
			if (id != null && id != undefined)
				_id += id;
			var str = '<tbody id="' + _id + '">';
			for (var i = 0; i < valuesList.length; i++) {
				var values = valuesList[i];
				var c=className;
				if(values['jobstatus']==='失败')c=className+' dangerous';
				str += _table.addTr({keys:keys, values:values, className:c,index:i});
			}
			str += '</tbody>';
			return str;
		};
		_table.addTbodyTrs = function(data) {
			if(data.valuesList==undefined) return'';
			var keys = data.keys, 
				valuesList=data.valuesList, 
				className=data.className;	
			var str = '';

			for (var i = 0; i < valuesList.length; i++) {
				var values = valuesList[i];
				var c=className;
				if(values['jobstatus']==='失败')c=className+' dangerous';
				str += _table.addTr({keys:keys, values:values, className:c,index:i});
			}
			return str;
		};

		_table.addTable = function(data) {
			var str = '';
			str += '<table class="' + data.table_className + '" id="' + data.table_id + '">';
			str += _table.addThead(data.head);
			/*str += _table.addTfoot(data.head);*/
			str += _table.addTbody(data.body);
			str += "</table>";
			return str;
		};

		return _table;
	}();
	_pc.table=table;
	return _pc;
}(pc||{});