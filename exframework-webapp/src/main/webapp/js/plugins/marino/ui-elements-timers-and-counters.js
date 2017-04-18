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
        $('#clock').countdown('2020/10/10').on('update.countdown', function(event) {
            $(this).html(event.strftime('' + '<span>%-w</span> week%!w ' + '<span>%-d</span> day%!d ' + '<span>%H</span> hr ' + '<span>%M</span> min ' + '<span>%S</span> sec'));
        });
        $('#clock-wrapper-1').countdown('2020/10/10', function(event) {
            $('#clock-a').html(event.strftime('%w weeks and %d days'));
        });
        $('#clock-wrapper-2').countdown('2020/10/10', function(event) {
            $('#clock-b').html(event.strftime('%m months and %n days'));
        });
        $('#clock-wrapper-3').countdown('2020/10/10', function(event) {
            $('#clock-c').html(event.strftime('%D days'));
        });
        $('[data-countdown]').each(function() {
            var $this = $(this),
                finalDate = $(this).data('countdown');
            $this.countdown(finalDate, function(event) {
                $this.html(event.strftime('%D days %H:%M:%S'));
            });
        });
    });
})();
