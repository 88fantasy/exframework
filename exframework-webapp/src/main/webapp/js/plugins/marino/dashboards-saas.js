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
        chartistBarChart1('#saas-bar-chart-1');
        chartistBarChart1('#saas-bar-chart-2');
        chartistLineChart1('#saas-line-chart-1');
        chartistLineChart1('#saas-line-chart-2');
        chartistLineChart1('#saas-line-chart-3');
        chartistAreaChart1('#saas-area-chart-1');
        chartistAreaChart1('#saas-area-chart-2');
        chartistAreaChart1('#saas-area-chart-3');
    });
})();
