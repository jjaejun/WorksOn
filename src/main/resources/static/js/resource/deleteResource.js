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
                const fmtStartAt = startAt.replace('T', ' ').slice(0, -3);
                const fmtEndAt = endAt.replace('T', ' ').slice(0, -3);

                reservationList.innerHTML += `<tr>
            <td class="border px-4 py-2">${empName}</td>
            <td class="border px-4 py-2">${count}</td>
            <td class="border px-4 py-2">${fmtStartAt}</td>
            <td class="border px-4 py-2">${fmtEndAt}</td>
            <td class="border px-4 py-2">${content}</td>
        </tr>`
            })
        }
    });
});