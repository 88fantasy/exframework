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
        //first example
        function updatePreview1(c) {
            if (parseInt(c.w) > 0) {
                var rx = xsize / c.w;
                var ry = ysize / c.h;
                $preview1.css({
                    width: Math.round(c.w) + 'px',
                    height: Math.round(c.h) + 'px',
                });
                $pimg1.css({
                    width: Math.round(rx * boundx) + 'px',
                    height: Math.round(ry * boundy) + 'px',
                    marginLeft: '-' + Math.round(rx * c.x) + 'px',
                    marginTop: '-' + Math.round(ry * c.y) + 'px'
                });
            }
        }
        var jcrop_api1,
            boundx1,
            boundy1,
            // Grab some information about the preview pane
            $preview1 = $('#preview-pane-1'),
            $pcnt1 = $('#preview-pane-1 .preview-container'),
            $pimg1 = $('#preview-pane-1 .preview-container img'),
            xsize1 = $pcnt1.width(),
            ysize1 = $pcnt1.height();
        $('#target1').Jcrop({
            onChange: updatePreview1,
            onSelect: updatePreview1,
            //aspectRatio: xsize1 / ysize1
        }, function() {
            // Use the API to get the real image size
            var bounds = this.getBounds();
            var boundx = bounds[0];
            var boundy = bounds[1];
            // Store the API in the jcrop_api variable
            jcrop_api1 = this;
            // Move the preview into the jcrop container for css positioning
            $preview1.appendTo(jcrop_api1.ui.holder);
        });
        //second example
        function updatePreview(c) {
            if (parseInt(c.w) > 0) {
                var rx = xsize / c.w;
                var ry = ysize / c.h;
                $pimg.css({
                    width: Math.round(rx * boundx) + 'px',
                    height: Math.round(ry * boundy) + 'px',
                    marginLeft: '-' + Math.round(rx * c.x) + 'px',
                    marginTop: '-' + Math.round(ry * c.y) + 'px'
                });
            }
        }
        var jcrop_api,
            boundx,
            boundy,
            // Grab some information about the preview pane
            $preview = $('#preview-pane-2'),
            $pcnt = $('#preview-pane-2 .preview-container'),
            $pimg = $('#preview-pane-2 .preview-container img'),
            xsize = $pcnt.width(),
            ysize = $pcnt.height();
        $('#target2').Jcrop({
            onChange: updatePreview,
            onSelect: updatePreview,
            aspectRatio: xsize / ysize
        }, function() {
            // Use the API to get the real image size
            var bounds = this.getBounds();
            boundx = bounds[0];
            boundy = bounds[1];
            // Store the API in the jcrop_api variable
            jcrop_api = this;
            // Move the preview into the jcrop container for css positioning
            $preview.appendTo(jcrop_api.ui.holder);
        });
    });
})();
