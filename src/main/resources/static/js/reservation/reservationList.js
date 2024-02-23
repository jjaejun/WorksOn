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

document.querySelector('#count').addEventListener('input', (e) => {
    console.log(e);
    console.log(e.target.value);
    const eVal = e.target.value;

    if (eVal <= 0) {
        document.querySelector('#countCheckMsg').classList.remove('hidden');
    } else {
        document.querySelector('#countCheckMsg').classList.add('hidden');
    }
});

document.querySelector('#reservationCheckBtn').addEventListener('click', (e) => {
    e.preventDefault();
    const startAt = document.getElementById('startAt');
    const endAt = document.getElementById('endAt');
    const resourceId = document.getElementById('resourceIdForCheck');
    console.log(startAt.value);
    console.log(endAt.value);
    console.log(resourceId.value)

    $.ajax({
        type: "GET",
        headers: {
            [csrfHeaderName]: csrfToken
        },
        url: `${contextPath}reservation/reservationCheck.do`,
        data: {
            startAt : startAt.value,
            endAt : endAt.value,
            resourceId : resourceId.value
        },
        success(response){
            console.log(response);

            if (response === 0) {
                document.getElementById('atCheckGoodMsg').classList.remove('hidden');
                document.getElementById('atCheckBadMsg').classList.add('hidden');
                document.getElementById('reservationOkBtn').classList.replace('bg-gray-700', 'bg-blue-700');
                document.getElementById('reservationOkBtn').disabled = false;
            } else if (response > 0) {
                document.getElementById('atCheckGoodMsg').classList.add('hidden');
                document.getElementById('atCheckBadMsg').classList.remove('hidden');
                document.getElementById('reservationOkBtn').classList.add('disabled');
                document.getElementById('reservationOkBtn').disabled = true;
            }
        }
    });
});

document.querySelector('#startAt').addEventListener('change', (e) => {
    document.getElementById('atCheckGoodMsg').classList.add('hidden');
    document.getElementById('atCheckBadMsg').classList.remove('hidden');
    document.getElementById('reservationOkBtn').classList.replace('bg-blue-700', 'bg-gray-700');
    document.getElementById('reservationOkBtn').disabled = true;

    console.log(e.target.value);
    const endAt = document.querySelector('#endAt');
    endAt.min = e.target.value;
});
document.querySelector('#endAt').addEventListener('change', () => {
    document.getElementById('atCheckGoodMsg').classList.add('hidden');
    document.getElementById('atCheckBadMsg').classList.remove('hidden');
    document.getElementById('reservationOkBtn').classList.replace('bg-blue-700', 'bg-gray-700');
    document.getElementById('reservationOkBtn').disabled = true;
});

