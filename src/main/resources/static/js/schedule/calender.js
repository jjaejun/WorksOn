var calendar;
/**
 * 체크박스 스케쥴 출력 설정
 */
document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var date = calender.getDate();
    calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        selectable: true,
        dayMaxEventRows: true, // 또는 숫자를 지정
        views: {
            timeGrid: {
                dayMaxEventRows: 5,
            }
        },
        headerToolbar: {
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth,timeGridWeek,timeGridDay'
        },
        events: [],
        dateClick: function(selectionInfo) {
            console.log(selectionInfo);
        },
        eventTimeFormat: {
            hour: '2-digit',
            minute: '2-digit',
            meridiem: false,
            hour12: false
        },
    });
    calendar.render();


    $('input[type="checkbox"][name="myScheduleCategoryId"]').change(function() {
        let categoryId = $(this).val(); // 체크박스의 value 값 (category.id)을 가져옴
        let isChecked = $(this).is(':checked'); // 체크박스의 체크 상태

        if (isChecked) {
            // 체크박스가 체크될 때 - AJAX 요청으로 이벤트 데이터를 가져오고 달력에 추가
            $.ajax({
                url: `${contextPath}schedule/mySchedule.do`,
                type: 'get',
                // dataType:'json',
                headers: {
                    [csrfHeaderName]: csrfToken
                },
                data: {
                    'categoryId': categoryId,
                    'isChecked': isChecked
                },
                success: function(response) {
                    let events = response.map(function(item) {
                        return {
                            id: item.id, // 이벤트의 고유 ID
                            title: item.title,
                            start: item.startTime,
                            end: item.endTime,
                            categoryId: categoryId
                        };
                    });
                    // 달력에 이벤트 추가
                    calendar.addEventSource(events);

                    // calendar.removeAllEvents(); // 기존 이벤트를 제거
                    // calendar.addEventSource(events); // 새 이벤트 추가
                    // calendar.refetchEvents(); // 이벤트 갱신

                },
                error: function(xhr, status, error) {
                    console.error("Request failed: " + status, error);
                }
            });
        } else {
            // 체크박스가 해제될 때 - 달력에서 해당 카테고리의 이벤트를 제거
            var events = calendar.getEvents();
            events.forEach(function(event) {
                if (event.extendedProps.categoryId === categoryId) {
                    event.remove();
                }
            });
        }
    });
});

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
// }

// function addEventToCalender(event){
//     calendar.addEvent(event);
// }
//
// function removeEventFromCalender(id){
//     var calenderEvent = calendar.getEventById(id);
//     calenderEvent.remove();
//
// }
