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
        var email = $('.login-page #email');
        email.floatingLabels({
            errorBlock: 'Please enter your email',
            isEmailValid: 'Please enter a valid email'
        });
        var password = $('.login-page #password');
        password.floatingLabels({
            errorBlock: 'Please enter a valid password',
            minLength: 6
        });
        $('.login-page .btn-login').click(function(e) {
            e.preventDefault();
            return false;
        });
    });
})();
