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
        Chart.defaults.global.responsive = true;
        Chart.defaults.global.scaleFontColor = palette.textColor;
        //area chart
        var areaChartCtx = document.getElementById("area-chart").getContext("2d");
        var areaChartData = {
            labels: ["January", "February", "March", "April", "May", "June", "July"],
            datasets: [{
                label: "My First dataset",
                fillColor: colors.danger,
                strokeColor: colors.danger,
                pointColor: colors.danger,
                pointStrokeColor: colors.textColor,
                pointHighlightFill: colors.textColor,
                pointHighlightStroke: colors.danger,
                data: [65, 59, 80, 81, 56, 55, 40]
            }, {
                label: "My Second dataset",
                fillColor: colors.warning,
                strokeColor: colors.warning,
                pointColor: colors.warning,
                pointStrokeColor: colors.textColor,
                pointHighlightFill: colors.textColor,
                pointHighlightStroke: colors.warning,
                data: [28, 48, 40, 19, 86, 27, 90]
            }]
        };
        var areaChartOptions = {
            ///Boolean - Whether grid lines are shown across the chart
            scaleShowGridLines: true,
            //String - Colour of the grid lines
            scaleGridLineColor: "rgba(0,0,0,.05)",
            //Number - Width of the grid lines
            scaleGridLineWidth: 1,
            //Boolean - Whether to show horizontal lines (except X axis)
            scaleShowHorizontalLines: true,
            //Boolean - Whether to show vertical lines (except Y axis)
            scaleShowVerticalLines: true,
            //Boolean - Whether the line is curved between points
            bezierCurve: true,
            //Number - Tension of the bezier curve between points
            bezierCurveTension: 0.4,
            //Boolean - Whether to show a dot for each point
            pointDot: true,
            //Number - Radius of each point dot in pixels
            pointDotRadius: 4,
            //Number - Pixel width of point dot stroke
            pointDotStrokeWidth: 1,
            //Number - amount extra to add to the radius to cater for hit detection outside the drawn point
            pointHitDetectionRadius: 20,
            //Boolean - Whether to show a stroke for datasets
            datasetStroke: true,
            //Number - Pixel width of dataset stroke
            datasetStrokeWidth: 2,
            //Boolean - Whether to fill the dataset with a colour
            datasetFill: true,
            //String - A legend template
            legendTemplate: "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].strokeColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>"
        };
        new Chart(areaChartCtx).Line(areaChartData, areaChartOptions);
        //line chart
        var lineChartCtx = document.getElementById("line-chart").getContext("2d");
        var lineChartData = {
            labels: ["January", "February", "March", "April", "May", "June", "July"],
            datasets: [{
                label: "My First dataset",
                fillColor: colors.danger,
                strokeColor: colors.danger,
                pointColor: colors.danger,
                pointStrokeColor: colors.textColor,
                pointHighlightFill: colors.textColor,
                pointHighlightStroke: colors.danger,
                data: [65, 59, 80, 81, 56, 55, 40]
            }, {
                label: "My Second dataset",
                fillColor: colors.warning,
                strokeColor: colors.warning,
                pointColor: colors.warning,
                pointStrokeColor: colors.textColor,
                pointHighlightFill: colors.textColor,
                pointHighlightStroke: colors.warning,
                data: [28, 48, 40, 19, 86, 27, 90]
            }]
        };
        var lineChartOptions = {
            ///Boolean - Whether grid lines are shown across the chart
            scaleShowGridLines: true,
            //String - Colour of the grid lines
            scaleGridLineColor: "rgba(0,0,0,.05)",
            //Number - Width of the grid lines
            scaleGridLineWidth: 1,
            //Boolean - Whether to show horizontal lines (except X axis)
            scaleShowHorizontalLines: true,
            //Boolean - Whether to show vertical lines (except Y axis)
            scaleShowVerticalLines: true,
            //Boolean - Whether the line is curved between points
            bezierCurve: true,
            //Number - Tension of the bezier curve between points
            bezierCurveTension: 0.4,
            //Boolean - Whether to show a dot for each point
            pointDot: true,
            //Number - Radius of each point dot in pixels
            pointDotRadius: 4,
            //Number - Pixel width of point dot stroke
            pointDotStrokeWidth: 1,
            //Number - amount extra to add to the radius to cater for hit detection outside the drawn point
            pointHitDetectionRadius: 20,
            //Boolean - Whether to show a stroke for datasets
            datasetStroke: true,
            //Number - Pixel width of dataset stroke
            datasetStrokeWidth: 2,
            //Boolean - Whether to fill the dataset with a colour
            datasetFill: false,
            //String - A legend template
            legendTemplate: "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].strokeColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>"
        };
        new Chart(lineChartCtx).Line(lineChartData, lineChartOptions);
        //bar-chart
        var barChartCtx = document.getElementById("bar-chart").getContext("2d");
        var barChartData = {
            labels: ["January", "February", "March", "April", "May", "June", "July"],
            datasets: [{
                label: "My First dataset",
                fillColor: colors.success,
                strokeColor: colors.success,
                highlightFill: colors.success,
                highlightStroke: colors.success,
                data: [65, 59, 80, 81, 56, 55, 40]
            }, {
                label: "My Second dataset",
                fillColor: colors.info,
                strokeColor: colors.info,
                highlightFill: colors.info,
                highlightStroke: colors.info,
                data: [28, 48, 40, 19, 86, 27, 90]
            }]
        };
        var barChartOptions = {
            //Boolean - Whether the scale should start at zero, or an order of magnitude down from the lowest value
            scaleBeginAtZero: true,
            //Boolean - Whether grid lines are shown across the chart
            scaleShowGridLines: true,
            //String - Colour of the grid lines
            scaleGridLineColor: "rgba(0,0,0,.05)",
            //Number - Width of the grid lines
            scaleGridLineWidth: 1,
            //Boolean - Whether to show horizontal lines (except X axis)
            scaleShowHorizontalLines: true,
            //Boolean - Whether to show vertical lines (except Y axis)
            scaleShowVerticalLines: true,
            //Boolean - If there is a stroke on each bar
            barShowStroke: true,
            //Number - Pixel width of the bar stroke
            barStrokeWidth: 1,
            //Number - Spacing between each of the X value sets
            barValueSpacing: 0,
            //Number - Spacing between data sets within X values
            barDatasetSpacing: 0,
            //String - A legend template
            legendTemplate: "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].fillColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>"
        };
        new Chart(barChartCtx).Bar(barChartData, barChartOptions);
        /* radar-chart */
        var radarChartData = {
            labels: ["Eating", "Drinking", "Sleeping", "Designing", "Coding", "Cycling", "Running"],
            datasets: [{
                label: "My First dataset",
                fillColor: colors.danger,
                strokeColor: colors.danger,
                pointColor: colors.danger,
                pointStrokeColor: palette.textColor,
                pointHighlightFill: palette.textColor,
                pointHighlightStroke: colors.danger,
                data: [65, 59, 90, 81, 56, 55, 40]
            }, {
                label: "My Second dataset",
                fillColor: colors.primary,
                strokeColor: colors.primary,
                pointColor: colors.primary,
                pointStrokeColor: palette.textColor,
                pointHighlightFill: palette.textColor,
                pointHighlightStroke: colors.primary,
                data: [28, 48, 40, 19, 96, 27, 100]
            }]
        };
        var radarChartOptions = {
            //Boolean - Whether to show lines for each scale point
            scaleShowLine: true,
            //Boolean - Whether we show the angle lines out of the radar
            angleShowLineOut: true,
            //Boolean - Whether to show labels on the scale
            scaleShowLabels: false,
            // Boolean - Whether the scale should begin at zero
            scaleBeginAtZero: true,
            //String - Colour of the angle line
            angleLineColor: "rgba(0,0,0,.1)",
            //Number - Pixel width of the angle line
            angleLineWidth: 1,
            //String - Point label font declaration
            pointLabelFontFamily: "'Lato', 'Helvetica'",
            //String - Point label font weight
            pointLabelFontStyle: "normal",
            //Number - Point label font size in pixels
            pointLabelFontSize: 10,
            //String - Point label font colour
            pointLabelFontColor: palette.textColor,
            //Boolean - Whether to show a dot for each point
            pointDot: true,
            //Number - Radius of each point dot in pixels
            pointDotRadius: 3,
            //Number - Pixel width of point dot stroke
            pointDotStrokeWidth: 1,
            //Number - amount extra to add to the radius to cater for hit detection outside the drawn point
            pointHitDetectionRadius: 20,
            //Boolean - Whether to show a stroke for datasets
            datasetStroke: true,
            //Number - Pixel width of dataset stroke
            datasetStrokeWidth: 2,
            //Boolean - Whether to fill the dataset with a colour
            datasetFill: true,
            //String - A legend template
            legendTemplate: "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].strokeColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>"
        };
        var radarChartCtx = document.getElementById("radar-chart").getContext("2d");
        new Chart(radarChartCtx).Radar(radarChartData, radarChartOptions);
        var polarAreaData = [{
                value: 300,
                color: colors.danger,
                highlight: colors.danger,
                label: "Red"
            }, {
                value: 50,
                color: colors.success,
                highlight: colors.success,
                label: "Green"
            }, {
                value: 100,
                color: colors.warning,
                highlight: colors.warning,
                label: "Yellow"
            }, {
                value: 40,
                color: colors.info,
                highlight: colors.info,
                label: "Grey"
            }
        ];
        var polarAreaOptions = {
            //Boolean - Show a backdrop to the scale label
            scaleShowLabelBackdrop: true,
            //String - The colour of the label backdrop
            scaleBackdropColor: palette.hoverColor,
            // Boolean - Whether the scale should begin at zero
            scaleBeginAtZero: true,
            //Number - The backdrop padding above & below the label in pixels
            scaleBackdropPaddingY: 2,
            //Number - The backdrop padding to the side of the label in pixels
            scaleBackdropPaddingX: 2,
            //Boolean - Show line for each value in the scale
            scaleShowLine: true,
            //Boolean - Stroke a line around each segment in the chart
            segmentShowStroke: false,
            //String - The colour of the stroke on each segement.
            segmentStrokeColor: palette.textColor,
            //Number - The width of the stroke value in pixels
            segmentStrokeWidth: 2,
            //Number - Amount of animation steps
            animationSteps: 100,
            //String - Animation easing effect.
            animationEasing: "easeOutBounce",
            //Boolean - Whether to animate the rotation of the chart
            animateRotate: true,
            //Boolean - Whether to animate scaling the chart from the centre
            animateScale: false,
            //String - A legend template
            legendTemplate: "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<segments.length; i++){%><li><span style=\"background-color:<%=segments[i].fillColor%>\"></span><%if(segments[i].label){%><%=segments[i].label%><%}%></li><%}%></ul>"
        };
        var polarAreaChartCtx = document.getElementById("polar-area-chart").getContext("2d");
        new Chart(polarAreaChartCtx).PolarArea(polarAreaData, polarAreaOptions);
        var doughnutChartCtx = document.getElementById("doughnut-chart").getContext("2d");
        var pieChartCtx = document.getElementById("pie-chart").getContext("2d");
        var pieChartData = [{
            value: 300,
            color: colors.danger,
            highlight: colors.danger,
            label: "Red"
        }, {
            value: 50,
            color: colors.success,
            highlight: colors.success,
            label: "Green"
        }, {
            value: 100,
            color: colors.warning,
            highlight: colors.warning,
            label: "Yellow"
        }];
        var doughnutChartData = [{
            value: 300,
            color: colors.danger,
            highlight: colors.danger,
            label: "Red"
        }, {
            value: 50,
            color: colors.success,
            highlight: colors.success,
            label: "Green"
        }, {
            value: 100,
            color: colors.info,
            highlight: colors.info,
            label: "Yellow"
        }];
        var pieChartOptions = {
            //Boolean - Whether we should show a stroke on each segment
            segmentShowStroke: false,
            //String - The colour of each segment stroke
            segmentStrokeColor: palette.textColor,
            //Number - The width of each segment stroke
            segmentStrokeWidth: 2,
            //Number - The percentage of the chart that we cut out of the middle
            percentageInnerCutout: 0, // This is 0 for Pie charts
            //Number - Amount of animation steps
            animationSteps: 100,
            //String - Animation easing effect
            animationEasing: "easeOutBounce",
            //Boolean - Whether we animate the rotation of the Doughnut
            animateRotate: true,
            //Boolean - Whether we animate scaling the Doughnut from the centre
            animateScale: false,
            //String - A legend template
            legendTemplate: "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<segments.length; i++){%><li><span style=\"background-color:<%=segments[i].fillColor%>\"></span><%if(segments[i].label){%><%=segments[i].label%><%}%></li><%}%></ul>"
        };
        var doughnutChartOptions = {
            //Boolean - Whether we should show a stroke on each segment
            segmentShowStroke: false,
            //String - The colour of each segment stroke
            segmentStrokeColor: palette.textColor,
            //Number - The width of each segment stroke
            segmentStrokeWidth: 0,
            //Number - The percentage of the chart that we cut out of the middle
            percentageInnerCutout: 50, // This is 0 for Pie charts
            //Number - Amount of animation steps
            animationSteps: 100,
            //String - Animation easing effect
            animationEasing: "easeOutBounce",
            //Boolean - Whether we animate the rotation of the Doughnut
            animateRotate: true,
            //Boolean - Whether we animate scaling the Doughnut from the centre
            animateScale: false,
            //String - A legend template
            legendTemplate: "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<segments.length; i++){%><li><span style=\"background-color:<%=segments[i].fillColor%>\"></span><%if(segments[i].label){%><%=segments[i].label%><%}%></li><%}%></ul>"
        };
        new Chart(pieChartCtx).Pie(pieChartData, pieChartOptions);
        new Chart(doughnutChartCtx).Doughnut(doughnutChartData, doughnutChartOptions);
    });
})();
