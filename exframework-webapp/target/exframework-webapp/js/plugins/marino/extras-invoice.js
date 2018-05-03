/**
 * @author Batch Themes Ltd.
 */
(function() {
    'use strict';
    $(function() {
        var config = $.localStorage.get('config');
        $('body').attr('data-layout', 'empty-view');
        $('body').attr('data-palette', config.theme);
        $('body').attr('data-direction', config.direction);
    });
})();
