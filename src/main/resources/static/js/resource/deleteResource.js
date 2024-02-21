document.querySelector('#resourceList').addEventListener('change', (e) => {
    console.log(e);
    console.log(e.target.value);
    const reservationList = document.querySelector('#reservationListBody');

    $.ajax({
        type: "GET",
        headers: {
            [csrfHeaderName]: csrfToken
        },
        url: `${contextPath}resource/reservationListForResource.do`,
        data: {
            id: e.target.value
        },
        success(reservations) {
            console.log(reservations);
            reservations.forEach((reservation) => {
                const {id, empName, count, startAt, endAt, content, resourceId} = reservation;
                reservationList.innerHTML += `<tr>
            <td class="border px-4 py-2">${empName}</td>
            <td class="border px-4 py-2">${count}</td>
            <td class="border px-4 py-2">${startAt}</td>
            <td class="border px-4 py-2">${endAt}</td>
            <td class="border px-4 py-2">${content}</td>
        </tr>`
            })
        }
    });
});