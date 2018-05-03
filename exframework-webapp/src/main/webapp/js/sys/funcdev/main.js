/**
 * @author rwe
 */
(function() {
    'use strict';

    $(function() {

        var config = $.localStorage.get('config');
        $('body').attr('data-layout', config.layout);
        $('body').attr('data-palette', config.theme);
        $('body').attr('data-direction', config.direction);
        
        
//        $('nav[data-control-type="toolbar"]').ToolbarMenu();
//        
//        $('div[data-control-type="queryparam"]').Queryparam();
        
//        $('#queryparam').find('table').on('click', '.selectable', function(event) {
//          var tr = $(this);
//          if($(this).hasClass('table-info')){
//    	    $(this).removeClass('table-info'); 
//    	  } else {
//    	    $(this).addClass('table-info').siblings().removeClass('table-info');
//    	  }
//          //tr.find('input[type="checkbox"]').attr("checked",true);
//    	});
    });

})();

function doQuery() {
	$('#queryparam').modal();
}

function doRefresh() {
	window.location.reload();
}

function doDownload() {
	$('#funcdevGrid').Grid('download');
}

function doReloadConfig() {
	
}

function doLoadConfig() {
	var rows = $('#funcdevGrid').Grid('getSelectedRecords');
	app.util.alert(rows);
}