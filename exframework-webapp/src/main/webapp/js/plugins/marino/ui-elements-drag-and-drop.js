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
        dragula([document.getElementById('board-1'), document.getElementById('board-2'), document.getElementById('board-3'), document.getElementById('board-4')]);
        dragula([document.getElementById('left-list'), document.getElementById('right-list')])
            .on('drag', function(el) {
                el.className = el.className.replace('ex-moved', '');
            })
            .on('drop', function(el) {
                el.className += 'ex-moved';
            })
            .on('over', function(el, container) {
                container.className += 'ex-over';
            })
            .on('out', function(el, container) {
                container.className = container.className.replace('ex-over', '');
            });
    });
})();
