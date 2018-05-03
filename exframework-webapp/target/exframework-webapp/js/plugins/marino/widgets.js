function chartistAreaChart1(element) {
    var series = [];
    var labels = [];
    //random	
    for (var x = 0; x < 30; x++) {
        labels.push('Label ' + x);
        series.push(random(20, 80));
    }
    var data = {
        labels: labels,
        series: [
            series
        ]
    };
    var options = {
        showPoint: false,
        showLine: false,
        showArea: true,
        fullWidth: true,
        showLabel: false,
        axisX: {
            showGrid: false,
            showLabel: false,
            offset: 0
        },
        axisY: {
            showGrid: false,
            showLabel: false,
            offset: 0
        },
        chartPadding: 0,
        low: 0,
        high: 100
    };
    new Chartist.Line(element, data, options);
};
function chartistBarChart1(element) {
    var series = [];
    var labels = [];
    for (var x = 0; x < 40; x++) {
        labels.push('Label ' + x);
        series.push(random(20, 50));
    }
    var data = {
        labels: labels,
        series: [
            series
        ]
    };
    var options = {
        fullWidth: true,
        showLabel: false,
        axisX: {
            showGrid: false,
            showLabel: false,
            offset: 0
        },
        axisY: {
            showGrid: false,
            showLabel: false,
            offset: 0
        },
        chartPadding: 0,
        low: 0,
        high: 100
    };
    new Chartist.Bar(element, data, options);
};
function chartistLineChart1(element) {
    var series = [];
    var labels = [];
    for (var x = 0; x < 50; x++) {
        if (x % 2 === 0) {
            continue;
        }
        labels.push('Label ' + x);
        series.push(random(x, x + 10));
    }
    var data = {
        labels: labels,
        series: [
            series
        ]
    };
    var options = {
        showPoint: false,
        showLine: true,
        showArea: false,
        fullWidth: true,
        showLabel: false,
        axisX: {
            showGrid: false,
            showLabel: false,
            offset: 0
        },
        axisY: {
            showGrid: false,
            showLabel: false,
            offset: 0
        },
        chartPadding: 0,
        low: 0,
        high: 100
    };
    new Chartist.Line(element, data, options);
};
