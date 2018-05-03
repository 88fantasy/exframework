/**
 * @author Batch Themes Ltd.
 */
(function() {
    'use strict';
    $(function() {
        $('.horizontal-navigation-2 [data-click]').on('click', function(e) {
            var action = $(this).data('click');
            var id = $(this).data('id');
            if (action === 'set-layout') {
                e.preventDefault();
                setLayout(id);
                return false;
            }
        });
        var wow = new WOW({
            boxClass: 'wow', // animated element css class (default is wow)
            animateClass: 'animated', // animation css class (default is animated)
            offset: 100, // distance to the element when triggering the animation (default is 0)
            mobile: true, // trigger animations on mobile devices (default is true)
            live: true, // act on asynchronously loaded content (default is true)
            callback: function(box) {
                // the callback is fired every time an animation is started
                // the argument that is passed in is the DOM node being animated
            },
            scrollContainer: null // optional scroll container selector, otherwise use window
        });
        wow.init();
    });
})();
