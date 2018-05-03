/*
 * author: rwe 
 */
(function(factory) {
	'use strict';

	if (typeof define === 'function' && define.amd) {
		// AMD
		define([ 'jquery' ], factory);
	} else if (typeof exports === 'object') {
		// CommonJS
		factory(require('jquery'));
	} else {
		// Browser globals
		factory(jQuery);
	}
}(function($) {
	
	
	
	/**
     * Constructor to create a new Grid using the given select.
     *
     * @param {jQuery} div
     * @param {Object} options
     * @returns {Grid}
     */
    function Grid(div, options) {

        this.$div = $(div);
        this.options = this.mergeOptions($.extend({}, options, this.$div.data()));

        // 初始化.
        // 必须复制原属性.
        this.originalOptions = this.$div.clone()[0].options;

        this.options.onInitError = $.proxy(this.options.onInitError, this);
        this.options.onInited = $.proxy(this.options.onInited, this);

        // 生成

        this.init(div);

        this.options.onInited(this.$div);
    }
	
	Grid.prototype = {

		$ : $,
			
		defaults: {
			defaultDivClass : 'grid-ul',
			
			selectedCss : 'bg-default',
			
			MaxLength : 99999999,
			
			firstQuery : true,
			
			smallTable : false,

			onInitError: function(div, o) {

	        },
			
			onInited: function(div) {

	        }
	        
		},
		
		constructor: Grid,
		
		inited : 0,	//0 未初始化 1 初始化中 2 初始化完成
		
	
		buildparam : {},  //init后台返回的参数
		
		page : 1,
		
		pageLength : 50,
		
		lastSelectIndex : -1,
		
		currentConditions : [],
		
		currentRows : [],
		
		apiurl : {
			init : "/grid/init/",
			query : "/grid/query/",
			download : "/grid/download/",
			setting : "/grid/setting/"
		},
		
		template : '<div class="table-responsive grid-table-div" {{#gridheight}}style="height:{{gridheight}}px;"{{/gridheight}} >'
				+'	<table class="table {{#small}}table-sm{{/small}} {{^selectable}}table-striped{{/selectable}}  table-hover  grid-table">'
				+'		<thead>'
				+'			<tr>'
				+'				{{#headers}}'
				+'				<th data-column="{{field}}">{{header}}</th>'
				+'				{{/headers}}'
				+'			</tr>'
				+'		</thead>'
				+'		<tbody>'
				+'		</tbody>'
				+'	</table>'
				+'</div>'
				+'<div class="grid-footer-bar" aria-label="Grid Footer">'
				+'	<nav>'
				+'		<ul class="grid-footer-bar-ul">'
				+'			<li class="grid-footer-item grid-footer-pagination">'
				+'				<ul class="pagination">'
				+'					<li class="page-item"><span class="page-link">请先查询数据</span></li>'
				+'				</ul>'
				+'			</li>'
				+'			<li class="grid-footer-item grid-footer-pagesize-selector">'
				+'				<label> 每页显示</label>'
				+'				<select name="pagesize-select" class="form-control ">'
				+' 						{{#pagesizes}}'
				+'						<option value="{{value}}">{{name}}</option>'
				+'						{{/pagesizes}}'
				+'				</select>'
				+'			</li>'
				+'			<li class="grid-footer-item grid-footer-col-selector">'
				+'				<button type="button" data-action="setting" class="btn btn-outline btn-sm">调整字段</button>'
				+'				<button type="button" data-action="refresh" class="btn btn-outline btn-sm">刷新数据</button>'
				+'			</li>'
				+'			<li class="grid-footer-item grid-footer-timehint">'
				+'				<label></label>'
				+'			</li>'
				+'		</ul>'
				+'	</nav>'
				+'</div>'
				+'<div class="modal fade grid-setting-dialog">'
				+'	<div class="modal-dialog modal-lg" role="document">'
				+'    <div class="modal-content">'
				+'	    <div class="modal-header">'
				+'	        <h4 class="modal-title" id="large-modalLabel">表格设置</h4>'
				+'	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">'
				+'	            <span aria-hidden="true">&times;</span>'
				+'	        </button>'
				+'	    </div>'
				+'	    <div class="modal-body grid-setting-body-fix">'
				+'	        <div class="container-fluid" >'
				+'				<div class="row">'
				+'					<div class="col-md-5">'
				+'						<ul class="list-group grid-setting-columns">'
				+'							{{#cols}}'
				+'							<li class="list-group-item" data-column="{{field}}">{{header}}</li>'
				+'							{{/cols}}'
				+'						</ul>'
				+'					</div>'
				+'					<div class="col-md-2 btn-group-vertical">'
				+'					     <button type="button" data-action="addHeader" class="btn btn-success">设置<i class="zmdi zmdi-long-arrow-right"></i></button>'   
				+'					     <button type="button" data-action="removeHeader" class="btn btn-warning"><i class="zmdi zmdi-long-arrow-left"></i>移除</button>'
				+'					     <button type="button" data-action="removeAllHeader" class="btn btn-danger"><i class="zmdi zmdi-long-arrow-left"></i>全部移除</button>'
				+'		          </div>'
				+'		          <div class="col-md-5 col-md-offset-7">'
				+'		             <ul class="list-group grid-setting-headers">'
				+'		               	 	{{#headers}}'
				+'							<li class="list-group-item" data-column="{{field}}">{{header}}</li>'
				+'							{{/headers}}'
				+'		              </ul>'
				+'		          </div>'
				+'				</div>'
				+'			</div>'
				+'	    </div>'
				+'	    <div class="modal-footer">'
				+'	        <button type="button" class="btn btn-success confirm" data-action="saveSetting"><i class="btn-icon fa fa-check"></i>确定</button>'
				+'	        <button type="button" class="btn btn-warning reset" data-action="resetSetting"><i class="btn-icon fa fa-undo"></i>初始化</button>'
				+'	        <button type="button" class="btn btn-danger" data-dismiss="modal"><i class="btn-icon fa fa-window-close-o"></i>取消</button>'
				+'	    </div>'
				+'	  </div>'
				+'	</div>'
				+'</div>'
				,
		
		pageTemplate : '{{#previous}}<li class="page-item"><a class="page-link" action="previous" '
			+'					aria-label="Previous"> <i class="fa fa-caret-left" aria-hidden="true"></i><span aria-hidden="true">&nbsp;上一页</span>'
			+'						<span class="sr-only">Previous</span>'
			+'				</a></li>{{/previous}}'
			+'			{{#pages}}'
			+'			<li class="page-item"><a class="page-link {{current}}" action="{{page}}">{{prefix}}{{page}}{{lastfix}}</a></li>'
			+'			{{/pages}}'
			+'			{{#next}}<li class="page-item"><a class="page-link " action="next" '
			+'					aria-label="Next"> <span aria-hidden="true">下一页&nbsp;</span>'
			+'						<span class="sr-only">Next</span><i class="fa fa-caret-right" aria-hidden="true"></i>'
			+'				</a></li>{{/next}}',
		
		
		
		build : function(param) {
			var grid = this;
			grid.buildparam = param;
			var result = {};
			var pageLength = grid.pageLength;
			var pagesize = param.pagesize.split(",");
			var pagesizes = [];
			pagesize.forEach(function(item,index,arr){
				if(index==1){
					if(item != 'infinity') {
						pageLength = item * 1;
					}
				}
				if(item == 'infinity') {
					pagesizes.push({
						value: item ,
						name: '∞'
					});
				}
				else {
					pagesizes.push({
						value: item * 1,
						name: item
					});
				}
			});
			result.pagesizes = pagesizes;
			
			grid.pageLength = grid.options.pageLength?grid.options.pageLength:pageLength;
			
			result.selectable = param.selectmodel != 3;
			
			result.small   = grid.options.smallTable;
			
			var headers = [], cols = [];
			var columntemplate = '{{#dataList}}<tr rowindex="{{_const_rowindex}}">';
			param.fields.forEach(function(item,index,arr){
				if(!item._const_hidden) {
					headers.push({field:item._const_field,header:item._const_header});
					if(item._const_field == 'rownum_'){
						columntemplate += '<th scope="row">{{'+item._const_field+'._const_render}}</th>';
					}
					else {
						columntemplate += '<td>{{'+item._const_field+'._const_render}}</td>';
					}
				}
				cols.push({field:item._const_field,header:item._const_header});
			});
			result.headers = headers;
			result.cols = cols;
			columntemplate += '</tr>{{/dataList}}';
			grid.columntemplate = columntemplate;
			if(grid.options.gridHeight){
				if(grid.options.gridHeight == 'auto') {
					var dom = grid.$div.get(0);
					result.gridheight = $(window).height() - app.util.getTop(dom) - 40; //40是footer高度
				}
				else {
					result.gridheight = grid.options.gridHeight;
				}
				
			}
			
			var rendered = Mustache.render(grid.template, result);
			var content = grid.$div.append(rendered);
			content.find(":button").each(function(){
				var $button = $(this), action = $button.data('action');
				if(action){
					if(typeof(grid[action]) == 'function'){
						$button.on("click",function(){grid[action]();});
					}
					else {
						console.error("按钮["+$button.prop('textContent')+"]缺少操作函数");
					}
				}
			});
			content.find(".grid-footer-pagesize-selector select").on("change",function(){
				grid.switchLength(this.value);
			});
			
//			var $thead = content.find(".grid-table-div .grid-table > thead");
//			var oriPosition  = $thead.css("position");
//			var treadOffset = $thead.height();
//			
//			content.find(".grid-table-div").scroll(function() {
//			    var offset = $(this).scrollTop();
////			    console.log("offset "+offset+", visibility "+$thead.css("visibility"));
//			    if (offset >= treadOffset ) {
//			    	$thead.css("position","fixed");
//			    }
//			    else {
//			    	$thead.css("position",oriPosition);
//			    }
//			});
			
//			var $table = content.find(".grid-table-div .grid-table");
//			$table.fixedHeaderTable({ footer: false, cloneHeadToFoot: false, fixedColumn: false });
			
			content.find(".grid-setting-columns li").click(function() {
				app.util.setSelected(this,grid.options.selectedCss);
			});
			
			content.find(".grid-setting-headers li").click(function() {
				app.util.setSelected(this,grid.options.selectedCss);
			});
		},
		
		setting : function() {
			this.$div.find(".grid-setting-dialog").modal("show");
		},
		
		saveSetting : function () {
			var grid = this;
			var cols = "";
			grid.$div.find(".grid-setting-headers li").each(function(){
				cols += $(this).data("column") + ",";
			});
			if(cols.length>0) {
				app.util.ajax(basePath + apiPrefix + grid.apiurl.setting+grid.options.gridcode,{
					type : "POST",
					beforeSend: app.util.loading.open,
					complete: app.util.loading.close,
					data:{
						action : "update",
						params : cols.substring(0,cols.length-1)
						
					},
					success : function(o) {
						grid.$div.find(".grid-setting-dialog").modal("hide");
						if(confirm("保存成功,需要更新页面才能生效,需要现在更新吗?")){
							window.location.reload();
						}
					}
				});
			}
			else {
				app.util.alert("没有选择任何字段");
			}
		},
		
		resetSetting : function () {
			var grid = this;
			if(confirm("您确定初始化表格设置吗?")){ 
				app.util.ajax(basePath + apiPrefix + grid.apiurl.setting+grid.options.gridcode,{
					type : "POST",
					beforeSend: app.util.loading.open,
					complete: app.util.loading.close,
					data:{
						action : "reset"
						
					},
					success : function(o) {
						grid.$div.find(".grid-setting-dialog").modal("hide");
						if(confirm("初始化成功,需要更新页面才能生效,需要现在更新吗?")){
							window.location.reload();
						}
					}
				});
			}
		},
		
		addHeader : function() {
			var grid = this;
			var selected = this.$div.find(".grid-setting-columns li."+this.options.selectedCss);
			var colname = selected.data("column");
			if(this.$div.find(".grid-setting-headers li[data-column="+colname+"]").length == 0) {
				selected.clone().removeClass(this.options.selectedCss).appendTo('.grid-setting-headers').click(function(){
					app.util.setSelected(this,grid.options.selectedCss);
				});
			}
		},
		
		removeHeader : function() {
			this.$div.find(".grid-setting-headers li."+this.options.selectedCss).remove();
		},
		
		removeAllHeader : function() {
			this.$div.find(".grid-setting-headers li").remove();
		},
		
		switchLength : function(size) {
			if(size != 'infinity'){
				this.pageLength = size * 1;
			}
			else {
				this.pageLength = this.options.MaxLength;
				this.page = -3; //-3是特殊页码,代表不分页
			}
			this.refresh();
		},
		
		refresh : function() {
			if(this.options.firstQuery){
				app.util.alert("必须进行首次查询后才能刷新数据");
				return;
			}
			this.queryData(this.page,this.pageLength,this.currentConditions);
		},
		
		queryparam : function(e,param) {	//param是传递过来的集合,里面conditions是选择的筛选条件
			var $this = $(this);
			var grid = $this.data('grid');
			grid.queryData(1,grid.pageLength,param.conditions);
		},
		
		queryData : function(index,pagesize,conditions) {
			var paramdata = {
					conditions : conditions,
					startIndex : index,
					pagesize : pagesize
				};
				app.util.ajax(basePath + apiPrefix + this.apiurl.query+this.options.gridcode,{
					context: this,
					type : "POST",
					contentType:"application/json",
					beforeSend: app.util.loading.open,
					complete: app.util.loading.close,
					data:JSON.stringify(paramdata),
					success : function(o) {
						if(o.status == 200) {
							this.currentConditions = conditions;
							this.refreshData(o.data);
						}
					}
				});
		},
		
		refreshData : function(data) {
			var grid = this;
			grid.options.firstQuery = false;
			grid.page = data.currentpage;
			var databody = grid.$div.find('.grid-table tbody');
			databody.empty();
			data.rows.forEach(function(item,index,arr){
				item._const_rowindex = index;
			});
			grid.currentRows = data.rows;
			var render = Mustache.render(grid.columntemplate, {dataList:data.rows});
			var content = databody.append(render);
			if(grid.buildparam.selectmodel == 1 || grid.buildparam.selectmodel == 2) {
				content.find("tr").click(function(e){
					var index = $(this).attr('rowindex') * 1;
					grid.select(index,app.util.eventcompatible(e));
					grid.lastSelectIndex = index;
				});
			}
			
			var timehint = grid.$div.find('.grid-footer-timehint label');
			timehint.replaceWith( "<label>耗时"+data.time+"</label>");
			var pagination = grid.$div.find('.pagination');
			pagination.empty();
			var pages = [];
			if(!data.totalpage){
				pages.push({page:grid.page,current:grid.options.selectedCss});
			}
			else {
				if(data.totalpage <= 6) {
					for(var i=1;i<=data.totalpage;i++){
						pages.push({page:i,current:i==grid.page?grid.options.selectedCss:""});
					}
				}
				else if(grid.page<5) {
					for(var i=1;i<6;i++){
						pages.push({page:i,current:i==grid.page?grid.options.selectedCss:""});
					}
					pages.push({page:data.totalpage,prefix:"..."});
				}
				else if(data.totalpage-grid.page<5) {
					for(var i=data.totalpage;i>data.totalpage-6;i--){
						pages.push({page:i,current:i==grid.page?grid.options.selectedCss:""});
					}
					pages.push({page:1,lastfix:"..."});
					pages.reverse();
				}
				else {
					pages.push({page:1,lastfix:"..."});
					for(var i=grid.page-2;i<=grid.page+2;i++){
						pages.push({page:i,current:i==grid.page?grid.options.selectedCss:""});
					}
					pages.push({page:data.totalpage,prefix:"..."});
				}
			}
			var pagecontent = Mustache.render(grid.pageTemplate, {
				pages: pages,
				previous: grid.page!=1,
				next: !data.isquerylastpage
			});
			pagination.append(pagecontent);
			pagination.find('.page-link').each(function() {
				var a = this, $this = $(this), action = $this.attr('action');
				$this.click(action,function(){
					var page = grid.page;
					if(action == 'previous') {
						page--;
					}
					else if(action == 'next') {
						page++;
					}
					else {
						page = action * 1;
					}
					grid.queryData(page,grid.pageLength,grid.currentConditions);
				});
			});
			
			
//			var $th = grid.$div.find('.grid-table > thead > th');
//			
//			var $headerfix = grid.$div.find('.grid-header-fixed');
//			headerfix.empty();
			
		},
		
		toggleSelect : function(index,checked) {
			var $tr = this.$div.find('.grid-table-div .grid-table tbody tr[rowindex="'+index+'"]');
			var selected = typeof checked === 'undefined' ? !$tr.attr('checked') : checked;
			$tr.attr("checked", selected );
			var cssed = $tr.hasClass(this.options.selectedCss);
			if(selected && ! cssed){
				$tr.addClass(this.options.selectedCss);
			}
			else if( !selected && cssed ){
				$tr.removeClass(this.options.selectedCss);
			}
		},
		
		select : function(index,e) {
			var grid = this;
			if(grid.buildparam.selectmodel != 3) { //非选
				var $tr = this.$div.find('.grid-table-div .grid-table tbody tr[rowindex="'+index+'"]');
				this.toggleSelect(index,true);
				var selectclear = true;
				if(grid.buildparam.selectmodel == 2) { //多选
					if(e){
						if(!e.ctrlKey && !e.shiftKey) {
							selectclear = true;
						}
						else if(e.shiftKey) {
							var indexes = [grid.lastSelectIndex,index];
							indexes.sort(function(a, b) {
						        return a - b;
						    });
						    for (var i = indexes[0]; i <= indexes[1]; i++) {
						    	grid.toggleSelect(i,true);
						    	selectclear = false;
						    }
						}
						else {
							selectclear = false;
						}
					}
				}
				if(selectclear) { //单选
					$tr.siblings().each(function(){
						grid.toggleSelect($(this).attr("rowindex")*1,false);
					});
				}
			}
			
		},
		
		
		recordToObject : function($tr) {
			var item = this.currentRows[$tr.attr("rowindex")];
			return this.rowToObject(item);
		},
		
		rowToObject : function(item) {
			var result = {};
			for( p in item) {
				result[p] = item[p]._const_value;
			}
			delete result._const_rowindex;
			return result;
		},
		
		/**
         * 这里放提供给外部调用的api
         * 和内部调用的隔离
         */
		api : {
			download : function() {
				if(this.options.firstQuery){
					app.util.alert("必须进行首次查询后才能下载数据");
					return;
				}
				var form = document.createElement("form");
				form.action = basePath + apiPrefix + this.apiurl.download+this.options.gridcode;
				form.target = "_blank";
				form.method = "post";
				var params = document.createElement("input");
				params.type = "hidden";
				params.name = "params";
				params.value = JSON.stringify({conditions:this.currentConditions});
				form.appendChild(params);
				document.body.appendChild(form).submit();
			},
			
			select : function(index,multi) {
				index = index * 1;
				multi = multi || false;
				if(!isNaN(index)){
					this.select(index,multi?{ctrlKey:true}:null);
				}
			},
			
			unselect : function(index) {
				index = index * 1;
				if(!isNaN(index)){
					this.toggleSelect(index,false);
				}
			},
			
			selectAll : function() {
				var grid = this;
				if(grid.buildparam.selectmodel == 2) { //多选
					var $trs = this.$div.find('.grid-table-div .grid-table tbody tr');
					$trs.each(function(){
						var $tr = $(this);
						if(!$tr.attr('checked')) {
							$tr.attr("checked", true );
						}
						if(!$tr.hasClass(grid.options.selectedCss)){
							$tr.addClass(grid.options.selectedCss);
						}
					});
				}
			},
			
			getSelectedRecords : function() {
				return  this.$div.find('.grid-table-div .grid-table tbody tr[checked="checked"]');
			},
			
			getRecordBySeletor : function(selector) {
				return  this.$div.find('.grid-table-div .grid-table tbody tr'+(selector||''));
			},
			
			getSelectedObj : function() {
				var $trs = this.$div.find('.grid-table-div .grid-table tbody tr[checked="checked"]');
				var obj = null;
				if($trs.length>0) {
					obj = this.recordToObject($trs.first());
				}
				return obj;
			},
			
			getSelectedObjs : function() {
				var grid = this;
				var $trs = grid.$div.find('.grid-table-div .grid-table tbody tr[checked="checked"]');
				var objs = [];
				$trs.each(function(){
					objs.push(grid.recordToObject($(this)));
				});
				return objs;
			},
			
			getAllObjs : function() {
				var grid = this, result = [];
				grid.currentRows.forEach(function(item,index,arr){
					result.push(grid.rowToObject(item));
				});
				return result;
			},
			
			getCount : function() {
				return this.currentRows.length;
			}
		},
		
		
		/**
         * Set the options.
         *
         * @param {Array} options
         */
        setOptions: function(options) {
            this.options = this.mergeOptions(options);
        },

        /**
         * Merges the given options with the default options.
         *
         * @param {Array} options
         * @returns {Array}
         */
        mergeOptions: function(options) {
            return $.extend(true, {}, this.defaults, this.options, options);
        },
		
		init : function(div) {
			this.inited = 1;
			if(this.options.gridcode) {
				app.util.ajax(basePath + apiPrefix + this.apiurl.init+this.options.gridcode, {
					context: this,
					type : "GET",
					dataType : 'json',
					data: {
						
					},
					success : function(o) {
						if(o.status == 200) {
							var param = o.data, grid = this;
							this.$div.addClass(this.options.defaultDivClass);
							
							this.build(param);
							

							this.inited = 2;
						}
						else {
							this.options.onInitError(this.$div,{resulterror:o});
						}
					},
					error : function(o) {
						this.options.onInitError(this.$div,{ajaxerror:o});
					}
				});
			}
		},
	};

	// jQuery access
	$.fn.Grid = function(option) {
		var options = typeof option === 'object' ? option : {};
		var method = typeof option === 'string' ? option : "init";
    	    if(method === "init") {
			return this.each(function() {
	        	var $this = $(this), data = $this.data('grid');

	            // 初始化 查询框.
	            if (!data) {
	                var data = new Grid($this, options);
	                $this.on('queryparam.confirm',data.queryparam);
	                $this.data('grid', data);
	            }
			});
		}
		else if (method === 'destroy') {
			return this.each(function() {
	        	var $this = $(this), data = $this.data('grid');

	            // 初始化 查询框.
	            if (data && data.inited == 2) {
	            	$this.un('queryparam.confirm');
	            	$this.removeData('grid');
	            }
			});
        }
        else {
	        	var result = {};
	        	var args = [].slice.call(arguments);
	        	//剔除第1个方法名参数
	        	args.shift();
	        	this.each(function() {
		        	var $this = $(this), data = $this.data('grid');
		            // 初始化 查询框.
		            if (data && data.inited == 2 && data.api[method]) {
			            	var res = data.api[method].apply(data,args);
			            	if(res) {
			            		result[data.options.gridcode] = res;
			            	}
		            }
				});
	        	if(Object.keys(result).length == 1) {
	        		for(x in result){
	        			return result[x];
	        		}
	        	}
	        	else {
	            	return result;
	        	}
        }
    };
	
	$.fn.Grid.Constructor = Grid;

	$(function() {
		 $('div[data-control-type="grid"]').Grid();
    });

	return $.fn.Grid;
}));