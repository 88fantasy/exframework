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
        $(document).on('click', '.choose-animation', function() {
            var animation = $(this).data('animation');
            var index = $(this).data('index');
            var element = '#animation-' + index;
            console.log(element);
            $(element).removeClass().addClass('animated ' + animation);
            setTimeout(function() {
                $(element).removeClass();
            }, 5000);
        });
    });
})();
