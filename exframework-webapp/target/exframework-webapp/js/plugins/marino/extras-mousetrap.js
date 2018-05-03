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
        Mousetrap.bind('4', function() {
            $('.mousetrap-1').toggleClass('highlighted');
        });
        Mousetrap.bind('h', function() {
            $('.mousetrap-2').toggleClass('hidden');
        });
        Mousetrap.bind(['alt+w', 'command+shift+k'], function() {
            $(document).fullScreen(true);
            return false;
        });
        Mousetrap.bind('q a', function() {
            toastr.options = {
                positionClass: 'toast-top-right'
            };
            toastr.error('Danger!');
        });
        Mousetrap.bind('w s', function() {
            toastr.options = {
                positionClass: 'toast-top-right'
            };
            toastr.success('Great idea!');
        });
        // konami code!
        Mousetrap.bind('up up down down left left right right', function() {
            console.log('konami code');
        });
    });
})();
