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
        
        
    });

})();

function doQuery() {
	$('#queryparam').modal();
}

function doDownload() {
	
}