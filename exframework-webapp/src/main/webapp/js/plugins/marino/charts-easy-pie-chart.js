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
        $('.easy-pie-chart-1').easyPieChart({
            barColor: colors.success,
            size: 200,
            trackColor: palette.oddColor,
            scaleColor: false,
            animate: true,
            lineWidth: 10,
            lineCap: 'square'
        });
        $('.easy-pie-chart-2').easyPieChart({
            barColor: colors.warning,
            size: 200,
            trackColor: palette.oddColor,
            scaleColor: false,
            animate: true,
            lineWidth: 10,
            lineCap: 'round'
        });
        $('.easy-pie-chart-3').easyPieChart({
            barColor: colors.danger,
            size: 200,
            trackColor: palette.oddColor,
            scaleColor: false,
            animate: true,
            lineWidth: 10,
            lineCap: 'square'
        });
        $('.easy-pie-chart-4').easyPieChart({
            barColor: colors.info,
            size: 150,
            trackColor: palette.oddColor,
            scaleColor: false,
            animate: true,
            lineWidth: 10,
            lineCap: 'round'
        });
        $('.easy-pie-chart-5').easyPieChart({
            barColor: colors.primary,
            size: 150,
            trackColor: palette.oddColor,
            scaleColor: false,
            animate: true,
            lineWidth: 10,
            lineCap: 'square'
        });
        $('.easy-pie-chart-6').easyPieChart({
            barColor: colors.success,
            size: 150,
            trackColor: palette.oddColor,
            scaleColor: false,
            animate: true,
            lineWidth: 10,
            lineCap: 'round'
        });
        $('.easy-pie-chart-7').easyPieChart({
            barColor: colors.warning,
            size: 100,
            trackColor: palette.oddColor,
            scaleColor: false,
            animate: true,
            lineWidth: 10,
            lineCap: 'square'
        });
        $('.easy-pie-chart-8').easyPieChart({
            barColor: colors.danger,
            size: 100,
            trackColor: palette.oddColor,
            scaleColor: false,
            animate: true,
            lineWidth: 10,
            lineCap: 'round'
        });
        $('.easy-pie-chart-9').easyPieChart({
            barColor: colors.info,
            size: 100,
            trackColor: palette.oddColor,
            scaleColor: false,
            animate: true,
            lineWidth: 10,
            lineCap: 'square'
        });
    });
})();
