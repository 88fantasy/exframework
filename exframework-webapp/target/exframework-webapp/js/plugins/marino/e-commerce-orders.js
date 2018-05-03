/**
 * @author Batch Themes Ltd.
 */
(function() {
    'use strict';
    $(function() {
        var config = $.localStorage.get('config');
        $('body').attr('data-layout', config.layout);
        $('body').attr('data-palette', config.theme);
        $('body').attr('data-direction', config.direction);
        var colors = config.colors;
        var palette = config.palette;
        easyPieChart('.orders-easy-pie-chart-1', 100, colors.warning, palette.oddColor);
        easyPieChart('.orders-easy-pie-chart-2', 100, colors.success, palette.oddColor);
        easyPieChart('.orders-easy-pie-chart-3', 100, colors.danger, palette.oddColor);
        $('#orders-date-picker').datepicker({
            orientation: 'bottom left'
        });
    });
})();
