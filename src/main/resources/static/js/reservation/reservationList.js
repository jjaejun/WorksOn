const startDateInput = document.getElementById('start-input');
const endDateInput = document.getElementById('end-input');

document.querySelector("#submitBtn").addEventListener('click', (e) => {

    console.log(`선택한 날짜 범위: ${startDateInput.value} 부터 ${endDateInput.value}`);
    // const startDateValue = startDateInput.value;
    // const endDateValue = endDateInput.value;

    const resourceId = document.getElementById('resourceId');
    const startDate = startDateInput.datepicker.dates[0];
    const endDate = endDateInput.datepicker.dates[0];
    const tbody = document.querySelector("#reservationTable tbody");
    console.log(startDate," ~ " ,endDate);

    $.ajax({
        type: "GET",
        headers: {
            [csrfHeaderName]: csrfToken
        },
        url: `${contextPath}reservation/reservationListSearchDate.do`,
        data: {
            resourceId : resourceId.value,
            startDate : startDate, // millis
            endDate : endDate
        },
        success(response){
            console.log(response);
            tbody.innerHTML = '';
            response.content.forEach((reservation) => {
                const {startAt, endAt, content, count, empName, resourceId} = reservation;

                tbody.innerHTML += `
                    <tr>
                        <td class="border px-4 py-2">${empName}</td>
                        <td class="border px-4 py-2"">${count}</td>
                        <td class="border px-4 py-2"">${startAt}</td>
                        <td class="border px-4 py-2"">${endAt}</td>
                        <td class="border px-4 py-2"">${content}</td>
                    </tr>`;
            });
        },
        error(e){
            console.log(e);
        }
    })
});