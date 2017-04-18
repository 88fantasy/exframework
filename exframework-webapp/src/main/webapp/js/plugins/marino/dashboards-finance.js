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
        worldMap('finance-vector-map', colors, palette);
        chartistBarChart1('#chart-widgets-bar-chart-1');
        chartistLineChart1('#chart-widgets-line-chart-1');
        chartistAreaChart1('#chart-widgets-area-chart-3');
        setTimeout(function() {
            notify('You have 5 unread messages', 'info');
        }, 2000);
        var data = bitcoinData();
        data = [data[0]];
        nv.addGraph(function() {
            //var chart = nv.models.stackedAreaChart()
            var chart = nv.models.lineChart()
                .margin({
                    top: 40,
                    left: 40,
                    right: 40,
                    bottom: 40
                })
                .focusEnable(true)
                .x(function(d) {
                    return d[0]
                }) //We can modify the data accessor functions...
                .y(function(d) {
                    return d[1]
                }) //...in case your data is formatted differently.
                .forceY([100, 600])
                //.color([palette.textColor])
                .color([palette.textColor])
                .showLegend(false)
                //.color([colors.danger, colors.warning, colors.success])
            //Format x-axis labels with custom function.
            chart.xAxis
                .tickFormat(function(d) {
                    return d3.time.format('%x')(new Date(d))
                });
            chart.x2Axis
                .tickFormat(function(d) {
                    return d3.time.format('%x')(new Date(d))
                });
            chart.yAxis
                .tickFormat(d3.format(',.0f'));
            d3.select('#finance-chart svg')
                .datum(data)
                .transition().duration(2000)
                .call(chart);
            nv.utils.windowResize(chart.update);
            return chart;
        });
    });
})();
