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
        var firstName = $('.forms-basic #firstname');
        firstName.floatingLabels();
        var lastName = $('.forms-basic #lastname');
        lastName.floatingLabels({
            errorBlock: 'Please enter your last name'
        });
        var email = $('.forms-basic #email');
        email.floatingLabels({
            errorBlock: 'Please enter your email',
            isEmailValid: 'Please enter a valid email'
        });
        var password = $('.forms-basic #password');
        password.floatingLabels({
            errorBlock: 'Please enter a valid password',
            minLength: 6
        });
        var confirmPassword = $('.forms-basic #confirm-password');
        confirmPassword.floatingLabels({
            errorBlock: 'Please confirm your password correctly',
            minLength: 6,
            isFieldEqualTo: $('#password')
        });
    });
})();
