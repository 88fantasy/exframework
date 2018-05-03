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
        $('.btn-export').click(function() {
            var type = $(this).data('type');
            if (type === 'pdf') {
                $('#table-export-1').tableExport({
                    type: type,
                    pdfFontSize: '7',
                    escape: 'false'
                });
            } else {
                $('#table-export-1').tableExport({
                    type: type,
                    escape: 'false'
                });
            }
        });
    });
})();
