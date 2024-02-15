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
// document.addEventListener('DOMContentLoaded', function() {
//     var calendarEl = document.getElementById('calendar');
//
//     calendar = new FullCalendar.Calendar(calendarEl, {
//         initialView: 'dayGridMonth',
//         selectable: true,
//         dayMaxEventRows: true, // 또는 숫자를 지정하여 최대 이벤트 수를 설정할 수도 있습니다.
//         views:{
//             timeGrid : {
//                 dayMaxEventRows:5,
//             }
//         },
//         events: [
//             {id : "2024-02-07", start : "2024-02-02T12:20",end : '2024-02-05T15:20', color : "red" },
//             {id : "2024-02-07", start : "2024-02-02T12:20",end : '2024-02-05T15:20', color : "red" },
//             {id : "2024-02-07", start : "2024-02-02T12:20",end : '2024-02-05T15:20', color : "red" },
//             {id : "2024-02-08", start : "2024-02-08T14:20", end : '2024-02-08T15:20',  color: "#3788d8"},
//         ],
//
//         // dateClick: function (info){
//         //     console.log("clicked event occurs : date = " + info.dateStr)
//         //     // addEventToCalender({title : "memo", start: info.dateStr, color : "red"})
//         //     // removeEventFromCalender("2024-02-07");
//         // },
//         dateClick:function (selectionInfo){
//             console.log(selectionInfo);
//         },
//         headerToolbar: {
//             left: 'prev,next today',
//             center: 'title',
//             right: 'dayGridMonth,timeGridWeek,timeGridDay'
//         },
//         // https://fullcalendar.io/docs/date-formatting
//         eventTimeFormat: {
//             hour: '2-digit',
//             minute: '2-digit',
//             meridiem: false,
//             hour12 : false
//         }
//     });
//     calendar.render();
// });

// function addEventToCalender(event){
//     calendar.addEvent(event);
// }
//
// function removeEventFromCalender(id){
//     var calenderEvent = calendar.getEventById(id);
//     calenderEvent.remove();
//
// }

document.addEventListener('DOMContentLoaded', function(){
    var request =
    $.ajax({
        url: `${contextPath}schedule/calender.do`,
        method: "GET",
        // dataType: "json"
    });
    // request.done(function( data ) {
    request.done(function(  ) {
        // console.log(data);
        var calendarEl = document.getElementById('calendar');
        var calendar = new FullCalendar.Calendar(calendarEl, {
            initialView: 'dayGridMonth',
            selectable: true,
            dayMaxEventRows: true, // 또는 숫자를 지정하여 최대 이벤트 수를 설정할 수도 있습니다.
            views:{
                timeGrid : {
                    dayMaxEventRows:5,
                }
            },
            headerToolbar: {
                left: 'prev,next today',
                center: 'title',
                right: 'dayGridMonth,timeGridWeek,timeGridDay'
            },
            events: [
            {id : "2024-02-07", start : "2024-02-02T12:20",end : '2024-02-05T15:20', color : "red" },
            {id : "2024-02-07", start : "2024-02-02T12:20",end : '2024-02-05T15:20', color : "red" },
            {id : "2024-02-07", start : "2024-02-02T12:20",end : '2024-02-05T15:20', color : "red" },
            {id : "2024-02-08", start : "2024-02-08T14:20", end : '2024-02-08T15:20',  color: "#3788d8"},
            ],
            // ,events: data
            dateClick:function (selectionInfo){
                console.log(selectionInfo);
            },eventTimeFormat: {
                hour: '2-digit',
                minute: '2-digit',
                meridiem: false,
                hour12 : false
            },
        });
        calendar.render();
    });
    request.fail(function( jqXHR, textStatus ) {
        alert( "Request failed: " + textStatus );
        console.log("Request failed: " + textStatus, errorThrown);
    });
});

/**
 * gpt 코드
 */
// document.addEventListener('DOMContentLoaded', function() {
//     var calendarEl = document.getElementById('calendar');
//
//     // FullCalendar 초기화
//     var calendar = new FullCalendar.Calendar(calendarEl, {
//         initialView: 'dayGridMonth',
//         selectable: true,
//         dayMaxEventRows: true,
//         views: {
//             timeGrid: {
//                 dayMaxEventRows: 5,
//             }
//         },
//         headerToolbar: {
//             left: 'prev,next today',
//             center: 'title',
//             right: 'dayGridMonth,timeGridWeek,timeGridDay'
//         },
//         dateClick: function (selectionInfo) {
//             console.log(selectionInfo);
//         },
//         eventTimeFormat: {
//             hour: '2-digit',
//             minute: '2-digit',
//             meridiem: false,
//             hour12: false
//         }
//     });
//
//     // Ajax로 데이터 가져오기
//     $.ajax({
//         url: '/your/data/endpoint',  // 데이터를 가져올 엔드포인트 URL
//         method: 'GET',
//         dataType: 'json',
//         success: function(data) {
//             // 가져온 데이터를 FullCalendar의 events에 할당
//             calendar.setOption('events', data);
//
//             // FullCalendar 렌더링
//             calendar.render();
//         },
//         error: function(jqXHR, textStatus, errorThrown) {
//             console.error('Ajax request failed:', textStatus, errorThrown);
//         }
//     });
// });


/**
 * 체크박스 스케쥴 출력 설정
 */

$(document).ready(function(){
    // 체크박스 클릭 이벤트 리스너
    $('input[type="checkbox"][name="myScheduleCategoryId"]').change(function() {
        let categoryId = $(this).val(); // 체크박스의 value 값 (category.id)을 가져옴
        let isChecked = $(this).is(':checked'); // 체크박스의 체크 상태
        const tbody = document.querySelector("#category-test tbody");

        // AJAX 요청
        $.ajax({
            url: `${contextPath}schedule/mySchedule.do`, // 컨트롤러의 URL을 입력
            type: 'get', // HTTP 요청 방식 (GET, POST 등)
            headers:{
                [csrfHeaderName]: csrfToken
            },
            data: {
                'categoryId': categoryId, // 서버로 보낼 데이터
                'isChecked': isChecked // 체크 상태도 함께 전송할 수 있음
            },
            success(response){
                // 성공 시 로직 처리
                console.log(response);
                console.log("작동확인하기")
                tbody.innerHTML = '';



            },
            error: function(xhr, status, error) {
                // 오류 처리
                console.error('AJAX error:', status, error);
            }
        });
    });
});