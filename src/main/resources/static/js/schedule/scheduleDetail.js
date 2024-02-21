document.getElementById('update-schedule').addEventListener('click', function() {
    var form = document.getElementById('schedule-form');
    form.action = '/schedule/updateSchedule.do';
    form.method = 'post';
    form.submit();
});

document.getElementById('delete-schedule').addEventListener('click', function() {
    var form = document.getElementById('schedule-form');

    form.action = `${contextPath}schedule/deleteSchedule.do`;
    form.method = 'post';
    form.submit();
});
