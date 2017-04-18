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
        $('#calendar').fullCalendar({
            dayClick: function(date, jsEvent, view) {
                console.log('Clicked on: ' + date.format());
                console.log('Event: ', jsEvent);
                console.log('Coordinates: ' + jsEvent.pageX + ',' + jsEvent.pageY);
                console.log('Current view: ' + view.name);
                //$(this).css('background-color', 'red');
            },
            header: {
                left: 'prev,next today',
                center: 'title',
                right: 'month,agendaWeek,agendaDay'
            },
            defaultDate: '2016-03-12',
            defaultView: 'month',
            editable: true,
            events: [{
                title: 'All Day Event',
                start: '2016-03-01'
            }, {
                title: 'Long Event',
                start: '2016-03-07',
                end: '2016-03-10'
            }, {
                id: 999,
                title: 'Repeating Event',
                start: '2016-03-09T16:00:00'
            }, {
                id: 999,
                title: 'Repeating Event',
                start: '2016-03-16T16:00:00'
            }, {
                title: 'Meeting',
                start: '2016-03-12T10:30:00',
                end: '2016-03-12T12:30:00'
            }, {
                title: 'Lunch',
                start: '2016-03-12T12:00:00'
            }, {
                title: 'Birthday Party',
                start: '2016-03-13T07:00:00'
            }, {
                title: 'Click for Google',
                url: 'http://google.com/',
                start: '2016-03-28'
            }]
        });
    });
})();
