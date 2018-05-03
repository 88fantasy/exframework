/**
 * JQuery shiftcheckbox plugin
 *
 * shiftcheckbox provides a simpler and faster way to select/unselect multiple checkboxes within a given range with just two clicks.
 * Inspired from GMail checkbox functionality
 *
 * Just call $('.<class-name>').shiftcheckbox() in $(document).ready
 *
 * @name shiftcheckbox
 * @type jquery
 * @cat Plugin/Form
 * @return JQuery
 *
 * @URL http://www.sanisoft.com/blog/2009/07/02/jquery-shiftcheckbox-plugin
 *
 * Copyright (c) 2009 Aditya Mooley <adityamooley@sanisoft.com>
 * Dual licensed under the MIT (MIT-LICENSE.txt) and GPL (GPL-LICENSE.txt) licenses
 */

(function ($) {
	 var prevChecked = null;

     var selectorStr;
    $.fn.shiftcheckbox = function()
    {
    	selectorStr = this;
    	return handleClick;
      
    };
   
    var handleClick=function(ele,event)
    {
    	
    	if(pc.boot.instance.edit_status==true)return;
    	
    	var node = ele.getElementsByTagName('input')[0];
        var val = node.value;
        var checkStatus = node.checked;
        //get the checkbox number which the user has checked

        //check whether user has pressed shift
        if (event.shiftKey) {
        	var ctrlKey = event.ctrlKey;
        	ctrlKey=true;
        	console.log(ctrlKey);
        	$(document.getElementsByTagName('body')[0]).addClass('copy');
            if (prevChecked != 'null') {
            	
                //get the current checkbox number
                var ind = 0, found = 0, currentChecked;
                currentChecked = getSelected(val);

                ind = 0;
                if (currentChecked < prevChecked) {
                    $(selectorStr).each(function(i) {
                        if (ind >= currentChecked && ind <= prevChecked) {
                        
                        	pc.boot.instance.ctrlKey(this,this.parentNode.parentNode,ctrlKey,checkStatus);
                        }
                        ind++;
                    });
                } else {
                    $(selectorStr).each(function(i) {
                        if (ind >= prevChecked && ind <= currentChecked) {
                        	
                        	pc.boot.instance.ctrlKey(this,this.parentNode.parentNode,ctrlKey,checkStatus);
                        }
                        ind++;
                    });
                }

                prevChecked = currentChecked;
            }
           
        } else {
            if (checkStatus){
                prevChecked = getSelected(val);
            }
        }
    /*   setTimeout( function(){$(document.getElementsByTagName('body')[0]).removeClass('copy')},2000);*/
    };

    function getSelected(val)
    {
        var ind = 0, found = 0, checkedIndex;

        $(selectorStr).each(function(i) {
            if (val == this.value && found != 1) {
                checkedIndex = ind;
                found = 1;
            }
            ind++;
        });
        return checkedIndex;
    };
})(jQuery);