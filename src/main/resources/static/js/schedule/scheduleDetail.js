// document.getElementById('update-schedule').addEventListener('click', function() {
//     var form = document.getElementById('schedule-form');
//     form.action = `${contextPath}schedule/updateSchedule.do`;
//     form.method = 'post';
//     form.submit();
// });

document.getElementById('delete-schedule').addEventListener('click', function() {
    var form = document.getElementById('schedule-form');

    form.action = `${contextPath}schedule/deleteSchedule.do`;
    form.method = 'post';
    form.submit();
});
// document.querySelector('[data-modal-toggle="schedule-modal"]').addEventListener('click', () => {
//     const modal = document.getElementById('schedule-modal');
//     modal.classList.add('hidden'); // 모달 숨김
//     modal.classList.remove('flex'); // flex 클래스 제거
// });