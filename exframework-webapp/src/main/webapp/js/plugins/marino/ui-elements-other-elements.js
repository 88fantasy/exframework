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
        $('[data-toggle="tooltip"]').tooltip();
        $('[data-toggle="popover"]').popover();
        $('.toolbar-icons a').on('click', function(event) {
            event.preventDefault();
        });
        $('div[data-toolbar="user-options"]').toolbar({
            content: '#user-options',
            position: 'top',
        });
        $('div[data-toolbar="transport-options"]').toolbar({
            content: '#transport-options',
            position: 'top',
        });
        $('div[data-toolbar="transport-options-o"]').toolbar({
            content: '#transport-options-o',
            position: 'right',
            event: 'click',
            hideOnClick: true,
        });
        $('div[data-toolbar="content-option"]').toolbar({
            content: '#transport-options',
        });
        $('div[data-toolbar="position-option"]').toolbar({
            content: '#transport-options',
            position: 'right',
        });
        $('div[data-toolbar="style-option"]').toolbar({
            content: '#transport-options',
            position: 'right',
            style: 'primary',
        });
        $('div[data-toolbar="animation-option"]').toolbar({
            content: '#transport-options',
            position: 'right',
            style: 'primary',
            animation: 'flyin'
        });
        $('div[data-toolbar="event-option"]').toolbar({
            content: '#transport-options',
            position: 'right',
            style: 'primary',
            event: 'click',
        });
        $('div[data-toolbar="hide-option"]').toolbar({
            content: '#transport-options',
            position: 'right',
            style: 'primary',
            event: 'click',
            hideOnClick: true
        });
        $('#link-toolbar').toolbar({
            content: '#user-options',
            position: 'top',
            event: 'click',
            adjustment: 35
        });
        $('div[data-toolbar="set-01"]').toolbar({
            content: '#set-01-options',
            position: 'top',
        });
        $('div[data-toolbar="set-02"]').toolbar({
            content: '#set-02-options',
            position: 'left',
        });
        $('div[data-toolbar="set-03"]').toolbar({
            content: '#set-03-options',
            position: 'right',
        });
        $('div[data-toolbar="set-04"]').toolbar({
            content: '#set-04-options',
            position: 'right',
        });
        $("#transport-options-2").find('a').on('click', function() {
            var $this = $(this);
            var $button = $('div[data-toolbar="transport-options-2"]');
            var $newClass = $this.find('i').attr('class').substring(3);
            var $oldClass = $button.find('i').attr('class').substring(3);
            if ($newClass !== $oldClass) {
                $button.find('i').animate({
                    top: "+=50",
                    opacity: 0
                }, 200, function() {
                    $(this).removeClass($oldClass).addClass($newClass).css({
                        top: "-=100",
                        opacity: 1
                    }).animate({
                        top: "+=50"
                    });
                });
            }
        });
        $('div[data-toolbar="transport-options-2"]').toolbar({
            content: '#transport-options-2',
            position: 'top',
        });
        /*
        $('.modal').each(function(index) {
            $(this).on('show.bs.modal', function(e) {
                var open = $(this).attr('data-easein');
                if (open == 'shake') {
                    $('.modal-dialog').velocity('callout.' + open);
                } else if (open == 'pulse') {
                    $('.modal-dialog').velocity('callout.' + open);
                } else if (open == 'tada') {
                    $('.modal-dialog').velocity('callout.' + open);
                } else if (open == 'flash') {
                    $('.modal-dialog').velocity('callout.' + open);
                } else if (open == 'bounce') {
                    $('.modal-dialog').velocity('callout.' + open);
                } else if (open == 'swing') {
                    $('.modal-dialog').velocity('callout.' + open);
                } else {
                    $('.modal-dialog').velocity('transition.' + open);
                }
            });
        });
		*/
    });
})();
