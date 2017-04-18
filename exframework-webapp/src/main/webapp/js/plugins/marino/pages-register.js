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
        var username = $('.create-account-page #username');
        username.floatingLabels({
            errorBlock: 'Please enter your username'
        });
        var email = $('.create-account-page #email');
        email.floatingLabels({
            errorBlock: 'Please enter your email',
            isEmailValid: 'Please enter a valid email'
        });
        var password = $('.create-account-page #password');
        password.floatingLabels({
            errorBlock: 'Please enter a valid password',
            minLength: 6
        });
        var confirmPassword = $('.create-account-page #confirm-password');
        confirmPassword.floatingLabels({
            errorBlock: 'Please confirm your password correctly',
            minLength: 6,
            isFieldEqualTo: $('#password')
        });
        $('.create-account-page .btn-lg').click(function(e) {
            e.preventDefault();
            return false;
        });
    });
})();
