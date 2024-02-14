// function draw(data) {
//     [
//         {
//             title: 'All Day Event',
//             start: '2023-11-01'
//         },
//         {
//             title: 'Long Event',
//             start: '2023-11-07',
//             end: '2023-11-10'
//         },
//         {
//             groupId: '999',
//             title: 'Repeating Event',
//             start: '2023-11-09T16:00:00'
//         },
//         {
//             groupId: '999',
//             title: 'Repeating Event',
//             start: '2023-11-16T16:00:00'
//         },
//         {
//             title: 'Conference',
//             start: '2023-11-11',
//             end: '2023-11-13'
//         },
//         {
//             title: 'Meeting',
//             start: '2023-11-12T10:30:00',
//             end: '2023-11-12T12:30:00'
//         },
//         {
//             title: 'Lunch',
//             start: '2023-11-12T12:00:00'
//         },
//         {
//             title: 'Meeting',
//             start: '2023-11-12T14:30:00'
//         },
//         {
//             title: 'Birthday Party',
//             start: '2023-11-13T07:00:00'
//         },
//         {
//             title: 'Click for Google',
//             url: 'https://google.com/',
//             start: '2023-11-28'
//         }
//     ]
//
// }
var calendar;

// fullcalendar
document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        selectable: true,
        dayMaxEventRows: true, // 또는 숫자를 지정하여 최대 이벤트 수를 설정할 수도 있습니다.
        views:{
            timeGrid : {
                dayMaxEventRows:5,
            }
        },
        events: [
            {id : "2024-02-07", start : "2024-02-02T12:20",end : '2024-02-05T15:20', color : "red" },
            {id : "2024-02-07", start : "2024-02-02T12:20",end : '2024-02-05T15:20', color : "red" },
            {id : "2024-02-07", start : "2024-02-02T12:20",end : '2024-02-05T15:20', color : "red" },
            {id : "2024-02-08", start : "2024-02-08T14:20", end : '2024-02-08T15:20',  color: "#3788d8"},
        ],

        // dateClick: function (info){
        //     console.log("clicked event occurs : date = " + info.dateStr)
        //     // addEventToCalender({title : "memo", start: info.dateStr, color : "red"})
        //     // removeEventFromCalender("2024-02-07");
        // },
        dateClick:function (selectionInfo){
            console.log(selectionInfo);
        },
        headerToolbar: {
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth,timeGridWeek,timeGridDay'
        },
        // https://fullcalendar.io/docs/date-formatting
        eventTimeFormat: {
            hour: '2-digit',
            minute: '2-digit',
            meridiem: false,
            hour12 : false
        }
    });
    calendar.render();
});

function addEventToCalender(event){
    calendar.addEvent(event);
}

function removeEventFromCalender(id){
    var calenderEvent = calendar.getEventById(id);
    calenderEvent.remove();

}

