var calendar;
/**
 * 체크박스 스케쥴 출력 설정
 */
document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    calendar = new FullCalendar.Calendar(calendarEl, {
        // plugins:[interactionPlugin],
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
        eventClick: function(info) {
            // info.jsEvent.preventDefault(); // don't let the browser navigate

            if (info.event.url) {
                window.open(info.event.url);
            }
        },
        eventTimeFormat: {
            hour: '2-digit',
            minute: '2-digit',
            meridiem: false,
            hour12: false
        },
    });
    calendar.render();
    var date = calendar.getDate();
    $('input[type="checkbox"][name="myScheduleCategoryId"]').on('change',function() {
        //change = 값이 변할 때만 확인
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
                            categoryId: categoryId,
                            color: item.color
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

/**
 * 모달창
 */


// '모든 일정 보기' 체크박스를 토글할 때 모든 카테고리 체크박스를 체크/해제
// function checkAllMyCategories(source) {
//     document.querySelectorAll('input[name="myScheduleCategoryId"]').forEach(checkbox => {
//         checkbox.checked = source.checked;
//     });
// }
//
// // 개별 'myScheduleCategoryId' 체크박스의 상태가 변경될 때 실행
// document.addEventListener('DOMContentLoaded', function() {
//     document.querySelectorAll('input[name="myScheduleCategoryId"]').forEach(function(checkbox) {
//         checkbox.addEventListener('change', function() {
//             // 'mainCheckbox'의 상태를 업데이트
//             var allChecked = true;
//             document.querySelectorAll('input[name="myScheduleCategoryId"]').forEach(function(cb) {
//                 if (!cb.checked) {
//                     allChecked = false;
//                 }
//             });
//
//             document.querySelector('input[name="mainCheckbox"]').checked = allChecked;
//         });
//     });
// });

// document.addEventListener('DOMContentLoaded', function() {
//     var calendarEl = document.getElementById('calendar');
//     calendar = new FullCalendar.Calendar(calendarEl, {
//         // 달력 설정...
//         initialView: 'dayGridMonth',
//         selectable: true,
//         // 기타 달력 옵션...
//     });
//     calendar.render();
//
//     // 체크박스 상태 확인 및 처리 함수
//     function handleCheckboxChange(checkbox) {
//         let categoryId = checkbox.value;
//         let isChecked = checkbox.checked;
//
//         if (isChecked) {
//             // 체크될 때 AJAX 요청으로 이벤트 데이터 가져오기
//             fetchEvents(categoryId, isChecked);
//         } else {
//             // 해제될 때 달력에서 이벤트 제거
//             removeEvents(categoryId);
//         }
//     }
//
//     // 이벤트 가져오기
//     function fetchEvents(categoryId, isChecked) {
//         $.ajax({
//             url: `${contextPath}/schedule/mySchedule.do`,
//             type: 'GET',
//             data: {
//                 'categoryId': categoryId,
//                 'isChecked': isChecked
//             },
//             success: function(response) {
//                 let events = response.map(item => ({
//                     id: item.id,
//                     title: item.title,
//                     start: item.startTime,
//                     end: item.endTime,
//                     categoryId: categoryId
//                 }));
//                 calendar.addEventSource(events);
//             },
//             error: function(xhr, status, error) {
//                 console.error("Request failed: ", error);
//             }
//         });
//     }
//
//     // 이벤트 제거
//     function removeEvents(categoryId) {
//         var events = calendar.getEvents();
//         events.forEach(function(event) {
//             if (event.extendedProps.categoryId === categoryId) {
//                 event.remove();
//             }
//         });
//     }
//
//     // 체크박스에 대한 초기 처리 및 이벤트 리스너 설정
//     document.querySelectorAll('input[type="checkbox"][name="myScheduleCategoryId"]').forEach(function(checkbox) {
//         // 초기 상태에 대한 처리
//         handleCheckboxChange(checkbox);
//
//         // 변경 시 처리를 위한 이벤트 리스너
//         checkbox.addEventListener('change', function() {
//             handleCheckboxChange(this);
//         });
//     });
// });

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
