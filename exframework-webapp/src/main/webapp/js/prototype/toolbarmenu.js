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
     * @param {jQuery} nav
     * @param {Object} options
     * @returns {Queryparam}
     */
    function ToolbarMenu(nav, options) {

        this.$nav = $(nav);
        this.options = this.mergeOptions($.extend({}, options, this.$nav.data()));

        // 初始化.
        // 必须复制原属性.
        this.originalOptions = this.$nav.clone()[0].options;

        // 生成
        this.init(nav);

//        this.$nav.wrap('<span class="multiselect-native-select" />').after(this.$container);
        this.options.onInited(this.$nav);
    }
    
    ToolbarMenu.prototype = {
		// Provide access to the host jQuery object (circular reference)
		$ : $,
	
		defaults: {
			
			overflow : false,
			
			defaultClass : 'navbar navbar-toggleable navbar-light',
			
			btnDefaultClass : 'btn-info btn-outline',

			onInitError: function(nav, error) {

	        },
			
			onInited: function(nav) {

	        }
		},
		
		constructor: ToolbarMenu,
		
		inited : 0,	//0 未初始化 1 初始化中 2 初始化完成
		
		template : '<button id="tbn-{{buttonid}}" class="btn {{iconCls}} toolbar-btn {{#tooltip}}need-tooltip{{/tooltip}}" type="button" {{#tooltip}}data-placement="bottom" title="{{tooltip}}" data-animation="true" {{/tooltip}}  >{{{text}}}</button>',
		
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
		
		init : function(nav) {
			this.inited = 1;
			var $this = this.$nav;
			var toolbarcode = $this.data('toolbarcode');
			if(toolbarcode) {
				$.ajax({
					context: this,
					url : basePath + apiPrefix + "/toolbar/get",
					type : "GET",
					dataType : 'json',
					data: {
						menu:toolbarcode
					},
					success : function(o) {
						if(o.status == 200) {
							var tbs = o.data;
							if(!$this.hasClass('navbar') && !this.options.overflow) {
								$this.addClass(this.options.defaultClass);
							}
							var bar = this,buttons = [],methods = {};
							tbs.forEach(function(item,index){
								item.iconCls = item.iconCls || bar.options.btnDefaultClass;
								var rendered = Mustache.render(bar.template, item);
								buttons.push(rendered);
								methods['tbn-'+item.buttonid] = item.toggleHandler;
							});
							var nav = $this.append(buttons);
							nav.find('button').each(function(){
								var handler = methods[this.id];
								if(typeof window[handler] === 'function' ) {
									$(this).on('click',window[handler]);
								}
								else {
									console.warn(handler+"方法未定义");
								}
							});
							$this.find(".need-tooltip" ).tooltip();
							this.inited = 2;
						}
						else {
							this.options.onInitError($this,{resulterror:o});
						}
					},
					error : function(o) {
						this.options.onInitError($this,{ajaxerror:o});
					}
				});
			}
		}
    };
	
	// jQuery access
	$.fn.ToolbarMenu = function(option, parameter, extraOptions) {
        return this.each(function() {
        	var $this = $(this), data = $this.data('toolbar');
            var options = typeof option === 'object' && option;

            // 初始化 工具栏.
            if (!data) {
                var data = new ToolbarMenu($this, options);
                $this.data('toolbar', data);
            }
            
            if(data.inited == 2) { //初始化完成
            	
            	// 调用方法.
                if (typeof option === 'string') {
                    if (option === 'destroy') {
                    	$this.removeData('toolbar');
                    }
                }
            }

        });
    };

	$.fn.ToolbarMenu.Constructor = ToolbarMenu;

	// Provide access to the host jQuery object (circular reference)
	ToolbarMenu.$ = $;
	
	$(function() {
		$('nav[data-control-type="toolbar"]').ToolbarMenu();
    });

	return $.fn.ToolbarMenu;
}));