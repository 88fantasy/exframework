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
        var rtl = config.direction === 'rtl' ? true : false;
        $('.carousel-1').owlCarousel({
            loop: true,
            margin: 10,
            responsiveClass: true,
            rtl: rtl,
            responsive: {
                0: {
                    items: 1,
                    nav: true
                },
                600: {
                    items: 3,
                    nav: false
                },
                1000: {
                    items: 5,
                    nav: true,
                    loop: false
                }
            }
        });
        $('.custom-1').owlCarousel({
            animateOut: 'slideOutDown',
            animateIn: 'slideInUp',
            items: 1,
            nav: true,
            margin: 10,
            stagePadding: 10,
            smartSpeed: 450,
            rtl: rtl
        });
        $('.custom-2').owlCarousel({
            animateOut: 'fadeOut',
            animateIn: 'bounceIn',
            items: 1,
            nav: true,
            margin: 10,
            stagePadding: 10,
            smartSpeed: 450,
            rtl: rtl
        });
    });
})();
