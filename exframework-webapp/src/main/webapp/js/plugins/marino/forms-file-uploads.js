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
        var url = 'http://localhost:8003/jquery-file-upload/server/php/index.php';
        //uploaded files can be found in the file-uploads/jquery-file-upload/server/php/files/ folder
        // Initialize the jQuery File Upload widget:
        $('#fileupload').fileupload({
            // Uncomment the following to send cross-domain cookies:
            //xhrFields: {withCredentials: true},
            url: url
        });
        // Enable iframe cross-domain access via redirect option:
        $('#fileupload').fileupload(
            'option',
            'redirect',
            window.location.href.replace(
                /\/[^\/]*$/,
                'http://localhost:8003/jquery-file-upload/cors/result.html?%s'
            )
        );
        if (window.location.hostname === 'localhost:9003') {
            // Demo settings:
            $('#fileupload').fileupload('option', {
                url: url,
                // Enable image resizing, except for Android and Opera,
                // which actually support image resizing, but fail to
                // send Blob objects via XHR requests:
                disableImageResize: /Android(?!.*Chrome)|Opera/
                    .test(window.navigator.userAgent),
                maxFileSize: 999000,
                acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i
            });
            // Upload server status check for browsers with CORS support:
            if ($.support.cors) {
                $.ajax({
                    url: url,
                    type: 'HEAD'
                }).fail(function() {
                    $('<div class="alert alert-danger"/>')
                        .text('Upload server currently unavailable - ' +
                            new Date())
                        .appendTo('#fileupload');
                });
            }
        } else {
            // Load existing files:
            $('#fileupload').addClass('fileupload-processing');
            $.ajax({
                // Uncomment the following to send cross-domain cookies:
                //xhrFields: {withCredentials: true},
                url: $('#fileupload').fileupload('option', 'url'),
                dataType: 'json',
                context: $('#fileupload')[0]
            }).always(function() {
                $(this).removeClass('fileupload-processing');
            }).done(function(result) {
                $(this).fileupload('option', 'done')
                    .call(this, $.Event('done'), {
                        result: result
                    });
            });
        }
    });
})();
