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
        // eventClick: function(selectionInfo) {
        //     console.log(selectionInfo);
            // let eventObj = selectionInfo;
            let eventObj = info.event;
            // info.jsEvent.preventDefault();

            console.log(eventObj);

            $('#id').val(eventObj.id);
            $('#title').val(eventObj.title);
            $('#startTime').val(eventObj.start.toISOString().slice(0, 16)); // input datetime-local 포맷에 맞게 포맷팅
            $('#endTime').val(eventObj.end ? eventObj.end.toISOString().slice(0, 16) : ''); // null 체크
            $('#category').val(eventObj.extendedProps.categoryId); // 카테고리 ID가 충분하다고 가정, 필요에 따라 조정
            $('#event-content').val(eventObj.extendedProps.content);

            $('#schedule-modal').removeClass('hidden');

            info.jsEvent.preventDefault();

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
                extraParams: {
                    custom_param1 : 'content'
                },
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
                            id: item.id,
                            title: item.title,
                            start: item.startTime,
                            content:item.content,
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
        $('#close-modal').on('click', function() {
            $('#schedule-modal').addClass('hidden'); // 모달 숨기기
        });
    });
});



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

// function addEventToCalendar(event){
//     calendar.addEvent(event);
// }
//
// function removeEventFromCalendar(id){
//     var calendarEvent = calendar.getEventById(id);
//     calendarEvent.remove();
//
// }


// document.addEventListener('DOMContentLoaded', () =>{
//     function openModalWithData(event){
//         const name = event.target.getAttribute('data-name');
//         const color = event.target.getAttribute('data-color');
//         const id = event.target.getAttribute('data-id');
//
//         //
//         document.getElementById('name').value = name;
//         document.getElementById('color').value = color;
//         document.getElementById('id').value = id;
//
//         console.log("name = ", name);
//
//         document.getElementById('crud-modal').style.display = 'block';
//     }
//
//     // 모든 수정 버튼에 이벤트 리스너 추가
//     document.querySelectorAll('.modal-btn').forEach(function(button) {
//         button.addEventListener('click', openModalWithData);
//     });
//
// });

// document.addEventListener('DOMContentLoaded', () => {
//
//     document.querySelectorAll('.modal-Btn').forEach(button => {
//
//         button.addEventListener('click', event => {
//             const target = event.currentTarget;
//             const name = target.getAttribute('data-name');
//             const color = target.getAttribute('data-color');
//             const id = target.getAttribute('data-id');
//
//             // 모달의 입력 필드에 설정
//             document.getElementById('name').value = name;
//             document.getElementById('category').value = color;
//             document.getElementById('id').value = id;
//
//             console.log("name =", name, ", color =", color, "id = ", id);
//
//             // 모달 표시
//             const modal = document.getElementById('crud-modal');
//             modal.style.display = 'block';
//
//         });
//     });
// });

document.addEventListener('DOMContentLoaded', () => {
    // 모달 열기 버튼에 대한 이벤트 리스너 설정
    document.querySelectorAll('.modal-Btn').forEach(button => {
        button.addEventListener('click', event => {
            const target = event.currentTarget;
            const name = target.getAttribute('data-name');
            const color = target.getAttribute('data-color');
            const id = target.getAttribute('data-id');

            // 모달의 입력 필드에 데이터 설정
            document.getElementById('name').value = name;
            document.getElementById('color').value = color.toUpperCase();
            document.getElementById('id').value = id;
            document.getElementById('delete-category').value=id;

            console.log("name =", name, ", color =", color, ", id =", id);

            // 모달 표시
            const modal = document.getElementById('crud-modal');
            modal.classList.remove('hidden'); // hidden 클래스 제거
            modal.classList.add('flex'); // flex 클래스 추가로 모달 표시
        });
    });

    // 모달 닫기 버튼에 대한 이벤트 리스너 설정
    document.querySelector('[data-modal-toggle="crud-modal"]').addEventListener('click', () => {
        const modal = document.getElementById('crud-modal');
        modal.classList.add('hidden'); // 모달 숨김
        modal.classList.remove('flex'); // flex 클래스 제거
    });

    document.getElementById("color").addEventListener('change', function () {
        var selectedColor = document.getElementById("color");
        selectedColor.value = selectedColor.options[selectedColor.selectedIndex].value;
    })
});


document.getElementById('delete-category').addEventListener('click', function() {
    const scheduleCategoryId = this.value;
    // console.log('rowId = ', rowId)
    $.ajax({
        type: "POST",
        headers: {
            [csrfHeaderName]:csrfToken
        },
        url: `${contextPath}schedule/deleteCategory.do`,
        data:{
            scheduleCategoryId: scheduleCategoryId
        },
        success(resp){
            document.getElementById('close-modal').click();
            document.location.reload(); // 추후 비동기로 html 갱신
        }
    })


})


// document.querySelector('#delete-category').addEventListener('click', function (){
//     const scheduleCategoryId = this.value;
//     console.log(scheduleCategoryId);
//     function sendData(data, url) {
//         const form = document.createElement('form');
//         form.setAttribute('method', 'post');
//         form.setAttribute('action', url);
//
//         const hiddenInput = document.createElement('input');
//         hiddenInput.setAttribute('type', 'hidden');
//         hiddenInput.setAttribute('name', 'id');
//         hiddenInput.setAttribute('value', data);
//
//         const csrfInput = document.createElement('input');
//         csrfInput.setAttribute('type', 'hidden');
//         csrfInput.setAttribute('name', csrfHeaderName);
//         csrfInput.setAttribute('value', csrfToken);
//
//         form.appendChild(hiddenInput);
//         form.appendChild(csrfInput);
//         document.body.appendChild(form);
//         form.submit();
//     }
//
//     // 여기서 sendData 함수를 호출합니다. contextPath 변수가 정의되어 있어야 합니다.
//     sendData(scheduleCategoryId, `${contextPath}schedule/deleteCategory.do`);
//
//
// })