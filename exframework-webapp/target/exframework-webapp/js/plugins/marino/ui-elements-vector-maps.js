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
        var colors = config.colors;
        var palette = config.palette;
        var world = new Datamap({
            element: document.getElementById("world"),
            responsive: true,
            projection: 'mercator',
            fills: {
                defaultFill: palette.oddColor,
                altFill: palette.evenColor,
                dangerFill: colors.danger,
                warningFill: colors.warning,
                infoFill: colors.info,
                successFill: colors.success
            },
            geographyConfig: {
                borderWidth: 1,
                borderOpacity: 1,
                borderColor: palette.borderColor,
                highlightOnHover: true,
                highlightFillColor: palette.hoverColor,
                highlightBorderColor: palette.borderColor,
                highlightBorderWidth: 1,
                highlightBorderOpacity: 1
            },
            labels: {
                labelColor: 'blue',
                fontSize: 12
            },
            data: {
                AUS: {
                    fillKey: "infoFill"
                },
                JPN: {
                    fillKey: "dangerFill"
                },
                ITA: {
                    fillKey: "altFill"
                },
                BRA: {
                    fillKey: "successFill"
                },
                DEU: {
                    fillKey: "warningFill"
                },
            }
        });
        window.addEventListener('resize', function() {
            world.resize();
        });
        var USdata = {
            'AK': 'Alaska',
            'AL': '123',
            'AR': '7576',
            'AZ': '345',
            'CA': '453',
            'CO': '453',
            'CT': '765',
            'DC': '234',
            'DE': '35434',
            'FL': '234',
            'GA': '234',
            'HI': '234',
            'IA': '234',
            'ID': 'Idaho',
            'IL': '234',
            'IN': '234',
            'KS': '234',
            'KY': '234',
            'LA': '234',
            'MA': '234',
            'MD': '7566',
            'ME': '567',
            'MI': '567',
            'MN': '46',
            'MO': '456',
            'MS': '567',
            'MT': '345',
            'NC': '456',
            'ND': '345',
            'NE': '65',
            'NH': '356',
            'NJ': '54',
            'NM': '4356',
            'NV': '5463',
            'NY': '3456',
            'OH': '2345',
            'OK': '2345',
            'OR': '564',
            'PA': '456',
            'RI': '234',
            'SC': '234',
            'SD': '5467',
            'TN': '5467',
            'TX': '2345',
            'UT': '345',
            'VA': '432',
            'VT': '654',
            'WA': '456',
            'WI': '543',
            'WV': '345',
            'WY': '1234'
        };
        var USmap = new Datamap({
            element: document.getElementById("usa"),
            scope: 'usa', //currently supports 'usa' and 'world', however with custom map data you can specify your own
            projection: 'equirect',
            responsive: true,
            fills: {
                defaultFill: palette.oddColor
            },
            geographyConfig: {
                borderWidth: 1,
                borderOpacity: 1,
                borderColor: palette.borderColor,
                highlightOnHover: true,
                highlightFillColor: palette.hoverColor,
                highlightBorderColor: palette.borderColor,
                highlightBorderWidth: 1,
                highlightBorderOpacity: 1,
                popupTemplate: function(geography, data) {
                    return '<div class="hoverinfo">' + geography.properties.name + '</div>';
                }
            }
        });
        USmap.labels({
            'customLabelText': USdata,
            labelColor: palette.textColor,
            fontSize: 12
        });
        window.addEventListener('resize', function() {
            USmap.resize();
        });
        var bubbleMap = new Datamap({
            element: document.getElementById("bubble_map"),
            scope: 'world',
            projection: 'mercator',
            responsive: true,
            fills: {
                defaultFill: palette.oddColor
            },
            geographyConfig: {
                popupOnHover: false,
                highlightOnHover: false,
                borderWidth: 1,
                borderOpacity: 1,
                borderColor: palette.borderColor,
                highlightOnHover: true,
                highlightFillColor: palette.hoverColor,
                highlightBorderColor: palette.borderColor,
                highlightBorderWidth: 1,
                highlightBorderOpacity: 1,
                popupTemplate: function(geography, data) {
                    return '<div class="hoverinfo">' + geography.properties.name + '</div>';
                }
            }
        });
        var bubbles = [{
            name: 'Bubble 1',
            radius: 25,
            latitude: 0,
            longitude: 0
        }, {
            name: 'Bubble 2',
            radius: 25,
            latitude: 50,
            longitude: 0
        }, {
            name: 'Bubble 3',
            radius: 25,
            latitude: -33,
            longitude: -70
        }, {
            name: 'Bubble 4',
            radius: 45,
            latitude: 50,
            longitude: -78
        }, {
            name: 'Bubble 5',
            radius: 45,
            latitude: 50,
            longitude: 120
        }, ];
        bubbleMap.bubbles(bubbles, {
            borderWidth: 1,
            borderOpacity: 1,
            borderColor: colors.warning,
            highlightFillColor: colors.warning,
            highlightBorderColor: colors.warning,
            popupTemplate: function(geo, data) {
                return ['<div class="hoverinfo">' + data.name,
                    '</div>'
                ].join('');
            }
        });
        window.addEventListener('resize', function() {
            bubbleMap.resize();
        });
    });
})();
