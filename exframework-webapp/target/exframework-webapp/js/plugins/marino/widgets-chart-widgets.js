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
        chartistBarChart1('#chart-widgets-bar-chart-1');
        chartistBarChart1('#chart-widgets-bar-chart-2');
        chartistBarChart1('#chart-widgets-bar-chart-3');
        chartistLineChart1('#chart-widgets-line-chart-1');
        chartistLineChart1('#chart-widgets-line-chart-2');
        chartistLineChart1('#chart-widgets-line-chart-3');
        chartistAreaChart1('#chart-widgets-area-chart-1');
        chartistAreaChart1('#chart-widgets-area-chart-2');
        chartistAreaChart1('#chart-widgets-area-chart-3');
        chartistPieChart1('#chart-widgets-pie-chart-1');
        chartistPieChart2('#chart-widgets-pie-chart-2');
        chartistPieChart3('#chart-widgets-pie-chart-3');
        chartistPieChart4('#chart-widgets-pie-chart-4');
        chartistHalfPieChart('#chart-widgets-half-pie-chart-1');
        easyPieChart('.chart-widgets-easy-pie-chart-1', 100, colors.warning, palette.oddColor);
        easyPieChart('.chart-widgets-easy-pie-chart-2', 100, colors.success, palette.oddColor);
        easyPieChart('.chart-widgets-easy-pie-chart-3', 100, colors.danger, palette.oddColor);
        easyPieChart('.chart-widgets-easy-pie-chart-4', 100, colors.info, palette.oddColor);
    });
})();
