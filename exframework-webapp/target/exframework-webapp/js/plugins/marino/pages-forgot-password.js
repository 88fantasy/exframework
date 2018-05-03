/**
 * @author Batch Themes Ltd.
 */
(function() {
    'use strict';
    $(function() {
        var config = $.localStorage.get('config');
        $('body').attr('data-layout', 'fullsize-background-image');
        $('body').attr('data-palette', config.theme);
        $('body').attr('data-direction', config.direction);
        var email = $('.forgot-password-page #email');
        email.floatingLabels({
            errorBlock: 'Please enter your email',
            isEmailValid: 'Please enter a valid email'
        });
        $('.forgot-password-page .btn-register').click(function(e) {
            e.preventDefault();
            return false;
        });
    });
})();
