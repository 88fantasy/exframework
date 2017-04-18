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
     * Constructor to create a new Queryparam using the given select.
     *
     * @param {jQuery} div
     * @param {Object} options
     * @returns {Queryparam}
     */
    function Queryparam(div, options) {

        this.$div = $(div);
        this.options = this.mergeOptions($.extend({}, options, this.$div.data()));

        // 初始化.
        // 必须复制原属性.
        this.originalOptions = this.$div.clone()[0].options;

        this.options.onInitError = $.proxy(this.options.onInitError, this);
        this.options.onInited = $.proxy(this.options.onInited, this);
        this.options.onConditionBuilded = $.proxy(this.options.onConditionBuilded, this);

        // 生成

        this.init(div);

        this.options.onInited(this.$div);
    }
	
	Queryparam.prototype = {

		// Provide access to the host jQuery object (circular reference)
		$ : $,
			
		defaults: {
			defaultDivClass : 'modal fade',
			
			selectedCss : 'bg-default',

			onInitError: function(div, o) {

	        },
			
			onInited: function(div) {

	        },
			
	        onConditionBuilded : function( conditions ) {
	        	if(this.options.queryto) {
	        		var eventname = this.events.confirm;
	        		$(this.options.queryto).each(function(){
	        			var $this = $(this);
	        			$this.trigger(eventname,{conditions:conditions});
	        		});
	        	}
	        }
	        
		},
		
		constructor: Queryparam,
		
		inited : 0,	//0 未初始化 1 初始化中 2 初始化完成
		
		qpis : [],  //在调用init后赋值
		
		events : {
			confirm : "queryparam.confirm"	//确定条件时触发关联对象
		},
	
		operations : [ 'equal', 'greater', 'less', 'between',
				'greater_equal', 'less_equal', 'in', 'matching', 'not_equal',
				'is_null' ],
		datatype : [ 'number', 'date', 'ddl', 'string', 'upper', 'lower' ],
		
		template : '<div class="modal-dialog modal-lg queryparam" role="document">'
					  +'      <div class="modal-content">'
					  +'	    <div class="modal-header">'
					  +'	        <h4 class="modal-title" id="large-modalLabel">查询条件</h4>'
					  +'	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">'
					  +'	            <span aria-hidden="true">&times;</span>'
					  +'	        </button>'
					  +'	    </div>'
					  +'	    <div class="modal-body fixbody table-responsive">'
					  +'	       <table class="table table-hover ">'
					  +'				<thead class="thead-default">'
					  +'					<tr>'
					  +'						<th style="width:2%;"> '
					  +'	    				</th>'
					  +'						<th style="width:22%;">列名</th>'
					  +'						<th style="width:20%;">操作</th>'
					  +'						<th style="width:28%;">值1</th>'
					  +'						<th style="width:28%;">值2</th>'
					  +'					</tr>'
					  +'				</thead>'
					  +'				<tbody>'
					  +'					{{#rows}}'
					  +'					<tr class="selectable" paramindex="{{index}}">'
					  +'						<td name="checkbox">'
					  +'							<label class="c-input c-checkbox" style="margin-top:0.3rem;">'
					  +'					                <input type="checkbox" class="rowcheck" ><span class="c-indicator c-indicator-success"></span>'
					  +'				            </label>'
					  +'						</td>'
					  +'						<td name="colname">{{{getColname}}}</td>'
					  +'						<td name="operation">{{{getOperation}}}</td>'
					  +'						<td name="value1">{{{getValue1}}}</td>'
					  +'						<td name="value2">{{{getValue2}}}</td>'
					  +'					</tr>'
					  +'					{{/rows}}'
					  +'				</tbody>'
					  +'			</table>'
					  +'	    </div>'
					  +'	    <div class="modal-footer">'
					  +'	        <button type="button" class="btn btn-success confirm"><i class="btn-icon fa fa-search"></i>查询</button>'
					  +'	        <button type="button" class="btn btn-warning clear"><i class="btn-icon fa fa-refresh"></i>清除</button>'
					  +'	        <button type="button" class="btn btn-danger" data-dismiss="modal"><i class="btn-icon fa fa-window-close-o"></i>取消</button>'
					  +'	    </div>'
					  +'	</div>'
					  +'</div>',
					  
		rowRender : {
			getColname : function() {
				return '<label class="custom-label">'+this.colname+'</label>';
			},
			getOperation : function() {
				var operstr = '<div class="input-group date"><select class="custom-select"  style="width:100%;" >';
				for(var i=0;i<this.operations.length;i++){
					operstr += '<option '+(this.oper==this.operations[i].oper?"selected":"")+' value="'+this.operations[i].oper+'">'+this.operations[i].zh_cn+'</option>';
				}
				operstr += '</select></div>';
				return operstr;
			},
			getValue1 : function() {
				if(this.qp.editorBuilder[this.datatype]) {
					return this.qp.editorBuilder[this.datatype].getEditor1(this);
				}
				else {
					return this.qp.editorBuilder['default'].getEditor1(this);
				}
			},
			getValue2 : function() {
				if(this.qp.editorBuilder[this.datatype]) {
					return this.qp.editorBuilder[this.datatype].getEditor2(this);
				}
				else {
					return this.qp.editorBuilder['default'].getEditor2(this);
				}
			}
		},
		
		editorBuilder : {
			date : {
				getEditor1 : function (qpi) {
					var today = new Date().GetDateStr(0);
					return '<div class="input-group date "><input type="text" class="custom-date editable" data-control-type="date" value='+today+' ></input><span class="input-group-addon"><i class="fa fa-calendar fa-lg"></i></span></div>';
				},
				getEditor2 : function (qpi) {
					var tomorrow = new Date().GetDateStr(1);
					return '<div class="input-group date "><input type="text" class="custom-date editable" data-control-type="date" value='+tomorrow+' ></input><span class="input-group-addon"><i class="fa fa-calendar fa-lg"></i></span></div>';
				}
			},
			ddl : {
				getEditor1 : function (qpi) {
					var select = '<div class="input-group select" data-bg="white"><select data-control-type="multiselect" multiple="multiple" class="custom-select editable" >';
					for(var key in qpi.operdata) {
						select += '<option value="'+key+'">'+qpi.operdata[key]+'</option>';
					}
					select += '</select></div>';
					return select;
				},
				getEditor2 : function (qpi) {
					return '';
				}
			},
			default : {
				getEditor1 : function (qpi) {
					return '<div class="input-group text"><input type="text" class="custom-input editable" ></input><span class="input-group-addon"><i class="fa fa-pencil-square-o fa-lg"></i></span></div>';
				},
				getEditor2 : function (qpi) {
					return '';
				}
			}
		},
		
		conditionBuilder : {
			date : function (qpi,$tr,oper) {
				var value1 = $tr.find('td[name="value1"] input').prop('value');
				var value2 = '';
				if(oper == 'between') {
					value2 = $tr.find('td[name="value2"] input').prop('value');
				}
				if(value1 != '') {
					if(Date.isDate(value1) && (oper != 'between' || (oper == 'between' &&Date.isDate(value2)))) {
						return {
							fieldname : qpi.colfield,
							oper :  oper,
							value1 : value1,
							value2 : value2,
							qp : qpi
						};
					} else {
						throw '字段['+qpi.colname+']不是日期['+value1+' , '+value2+']';
					}
				}
				else {
					return null;
				}
			},
			ddl : function (qpi,$tr,oper) {
				var ms = $tr.find('td[name="value1"] select').data('multiselect');
				var options = ms.getSelected();
				if(options.length>0) {
					var str = '';
					options.each(function(index,item){
						str += item.value+',';
					});
					str = str.substring(0,str.length-1);
					return {
						fieldname : qpi.colfield,
						oper :  oper,
						value1 : str,
						value2 : '',
						qp : qpi
					};
				}
				else {
					return null;
				}
				
			},
			number : function (qpi,$tr,oper) {
				var value = $tr.find('td[name="value1"] input').prop('value');
				if( value != '') {
					if(isNaN(value)){
						throw '字段['+qpi.colname+']不是数字['+value+']';
					}
					return {
						fieldname : qpi.colfield,
						oper :  oper,
						value1 : value,
						value2 : '',
						qp : qpi
					};
				}
				else {
					return null;
				}
			},
			string : function (qpi,$tr,oper) {
				var value = $tr.find('td[name="value1"] input').prop('value');
				if( value != '') {
					return {
						fieldname : qpi.colfield,
						oper :  oper,
						value1 : value,
						value2 : '',
						qp : qpi
					};
				}
				else {
					return null;
				}
			},
			upper : function (qpi,$tr,oper) {
				var value = $tr.find('td[name="value1"] input').prop('value');
				if( value != '') {
					return {
						fieldname : qpi.colfield,
						oper :  oper,
						value1 : value.toUpperCase(),
						value2 : '',
						qp : qpi
					};
				}
				else {
					return null;
				}
			},
			lower : function (qpi,$tr,oper) {
				var value = $tr.find('td[name="value1"] input').prop('value');
				if( value != '') {
					return {
						fieldname : qpi.colfield,
						oper :  oper,
						value1 : value.toLowerCase(),
						value2 : '',
						qp : qpi
					};
				}
				else {
					return null;
				}
			},
			default : function (qpi,$tr,oper) {
				return null;
			}
		},
		
		onConfirm : function(e) {
			var qp = e.data.qp;
			var checks = qp.$div.find('table tbody tr.'+qp.options.selectedCss);
			if(checks.length==0 && !confirm('当前没有选择任何条件,查询可能非常缓慢,你确定查询码?')) {
				return;
			}
			var conditions = [],errors = [];
			checks.each(function(index,row) {
				try{
					var $tr = $(this);
					var qpi = $.extend({}, qp.getQpi($tr));
					delete qpi['qp'];		//删除qp对象
					var oper = $tr.find('td[name="operation"] select').prop('value');
					if(qp.conditionBuilder[qpi.datatype]) {
						var condition = qp.conditionBuilder[qpi.datatype](qpi,$tr,oper);
						if(condition != null) {
							conditions.push(condition);
						}
					}
					else {
						var condition = qp.conditionBuilder['default'](qpi,$tr,oper);
						if(condition != null) {
							conditions.push(condition);
						}
					}
				} catch ( err) {
					errors.push(err);
				}
			});
			if(errors.length > 0) {
				var e = '校验条件出现以下错误:';
				errors.forEach(function(item,index){
					e += '\n'+item;
				});
				alert(e);
			}
			else {
				qp.$div.modal('hide');
				qp.options.onConditionBuilded(conditions);
			}
		},
		
		onClear : function(e) {
			var qp = e.data.qp;
			var checks = qp.$div.find('table tbody tr input.rowcheck:checked');
			checks.each(function() {
				this.checked = false;
			});
		},
		
		onRowChange : function(e) {
			var $tr = $(this).parents(".selectable");
			var index = $tr.attr('paramindex');
			e.data.qp.select(parseInt(index));
		},
		
		onCheckerChange : function(e) {
			var $tr = $(this).parents(".selectable");
			var index = $tr.attr('paramindex');
			if(this.checked){
				e.data.qp.select(parseInt(index));
			}
			else {
				e.data.qp.unselect(parseInt(index));
			}
		},
		
		onOperChange : function(e) {
			var $tr = $(this).parents(".selectable");
			if(this.value == 'between') {
				$tr.find('.editable').prop('disabled',false);
			}
			else {
				$tr.find('td[name="value2"] .editable').prop('disabled',true);
			}
		},
		
		select : function(index) {
			var $tr = this.$div.find('table tbody tr[paramindex="'+index+'"]');
			var checker = $tr.find('input.rowcheck');
			if(!checker.prop('checked')) {
				checker.prop("checked", true );
			}
			if(!$tr.hasClass(this.options.selectedCss)){
				$tr.addClass(this.options.selectedCss);
			}
		},
		
		unselect : function(index) {
			var $tr = this.$div.find('table tbody tr[paramindex="'+index+'"]');
			var checker = $tr.find('input.rowcheck');
			if(checker.prop('checked')) {
				checker.prop("checked", false );
			}
			if($tr.hasClass(this.options.selectedCss)){
				$tr.removeClass(this.options.selectedCss);
			}
		},
		
		getQpi : function($tr) {
			var index = $tr.attr('paramindex') * 1 - 1;
			return this.qpis[index];
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
			var qpcode = this.$div.data('queryparamcode');
			if(qpcode) {
				$.ajax({
					context: this,
					url : basePath + apiPrefix + "/queryparam/get",
					type : "GET",
					dataType : 'json',
					data: {
						queryparamcode:qpcode
					},
					success : function(o) {
						if(o.status == 200) {
							var params = o.data, qp = this;
							this.$div.addClass(this.options.defaultDivClass);
							var i=1;
							params.forEach(function(item,index){
								item.qp = qp;
								item.index = i;
								i++;
							});
							qp.qpis = params;
							var rendered = Mustache.render(this.template, {
								rows: params,
								getColname : this.rowRender.getColname,
								getOperation : this.rowRender.getOperation,
								getValue1 : this.rowRender.getValue1,
								getValue2 : this.rowRender.getValue2
							});
							var content = this.$div.append(rendered);
							content.find('input[data-control-type="date"]').datepicker({
								format: "yyyy-mm-dd",
								autoclose: true,
								language: "zh-CN",
								todayHighlight: true
							});
							content.find('select[data-control-type="multiselect"]').multiselect({
								includeSelectAllOption: true
							});
							content.find('button.confirm').on('click',{qp:this},this.onConfirm);
							content.find('button.clear').on('click',{qp:this},this.onClear);
							content.find('.editable').on('change',{qp:this},this.onRowChange);
							content.find('input.rowcheck').on('change',{qp:this},this.onCheckerChange);
							content.find('td[name="operation"] select').on('change',{qp:this},this.onOperChange);
//									params.forEach(function(item,index){
//										item.iconCls = item.iconCls ||btnDefaultClass;
//										var rendered = Mustache.render(template, item);
//										$(bar).append(rendered).find(".need-tooltip" ).tooltip();
//
//									});
							

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
	$.fn.Queryparam = function(option, parameter, extraOptions) {
        return this.each(function() {
        	var $this = $(this), data = $this.data('queryparam');
            var options = typeof option === 'object' && option;

            // 初始化 查询框.
            if (!data) {
                var data = new Queryparam($this, options);
                $this.data('queryparam', data);
            }
            
            if(data.inited == 2) { //初始化完成
            	
            	// 调用方法.
                if (typeof option === 'string') {
                    if (option === 'destroy') {
                    	$this.removeData('queryparam');
                    }
                }
            }

        });
    };
	
	$.fn.Queryparam.Constructor = Queryparam;

	$(function() {
		 $('div[data-control-type="queryparam"]').Queryparam();
    });

	return $.fn.Queryparam;
}));