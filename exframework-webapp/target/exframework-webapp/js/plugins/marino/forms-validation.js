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
        var firstName = $('#firstname');
        firstName.floatingLabels({
            errorBlock: 'Please enter your first name'
        });
        var lastName = $('#lastname');
        lastName.floatingLabels({
            errorBlock: 'Please enter your last name'
        });
        var email = $('#email');
        email.floatingLabels({
            errorBlock: 'Please enter your email',
            isEmailValid: 'Please enter a valid email'
        });
        var password = $('#password');
        password.floatingLabels({
            errorBlock: 'Please enter a valid password',
            minLength: 6
        });
        var confirmPassword = $('#confirm-password');
        confirmPassword.floatingLabels({
            errorBlock: 'Please confirm your password correctly',
            minLength: 6,
            isFieldEqualTo: $('#password')
        });
        $('#bootstrap-validator-form').validator({
            focus: false,
            feedback: {
                success: 'fa fa-ok',
                error: 'fa fa-remove'
            }
        })
        $('#bootstrap-validator-form').validator().on('submit', function(e) {
            if (e.isDefaultPrevented()) {
                console.log('error');
                $('#bootstrap-validator-form-success').addClass('hidden')
            } else {
                console.log('success');
                $('#bootstrap-validator-form-success').removeClass('hidden');
            }
        })
    });
})();
