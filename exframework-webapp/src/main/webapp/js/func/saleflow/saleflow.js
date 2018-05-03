/**
 * @author rwe
 */
(function() {
    'use strict';

    $(function() {

    		$('body').bootstrapMaterialDesign();
    		
    });

})();

function doQuery() {
	$('#queryparam').modal();
}

function doDownload() {
	$('#saleflowGrid').Grid('download');
}