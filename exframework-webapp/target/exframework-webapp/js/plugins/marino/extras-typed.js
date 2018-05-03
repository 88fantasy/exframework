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
        $(".typed-element-1").typed({
            strings: ["First sentence.", "Second sentence.",
                "Lorem ipsum dolor sit amet",
                "Consectetur adipiscing elit", "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
            ],
            typeSpeed: 20,
            loop: true,
            loopCount: 50
        });
        $(".typed-element-2").typed({
            strings: ["First sentence.", "Second sentence.",
                "Lorem ipsum dolor sit amet",
                "Consectetur adipiscing elit", "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
            ],
            typeSpeed: 20,
            loop: true,
            loopCount: 50
        });
        $(".typed-element-3").typed({
            strings: ["First sentence.", "Second sentence.",
                "Lorem ipsum dolor sit amet",
                "Consectetur adipiscing elit", "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
            ],
            typeSpeed: 20,
            loop: true,
            loopCount: 50
        });
        $(".typed-element-4").typed({
            strings: ["First sentence.", "Second sentence.",
                "Lorem ipsum dolor sit amet",
                "Consectetur adipiscing elit", "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
            ],
            typeSpeed: 20,
            loop: true,
            loopCount: 50
        });
    });
})();
