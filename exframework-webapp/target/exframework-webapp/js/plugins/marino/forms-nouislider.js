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
        var colors = ["primary", "default", "success", "info", "warning", "danger"];
        var shades = [100, 300, 500, 700, 900];
        setTimeout(function() {
            for (var i in colors) {
                for (var j in shades) {
                    var slider = 'slider-' + colors[i] + '-' + shades[j];
                    noUiSlider.create(document.getElementById(slider), {
                        start: [20, 80],
                        connect: true,
                        range: {
                            'min': 0,
                            'max': 100
                        }
                    });
                }
            }
        }, 500);
    });
})();
