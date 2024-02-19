// function updateMonthOptions() {
//     const selectedYear = document.getElementById("year").value;
//     console.log("선택된 연도: " + selectedYear);
//
//     $.ajax( {
//         type: "GET",
//         headers: {
//             [csrfToken]: csrfToken
//         },
//         url: `${contextPath}dayoff/dayOffListSearchDate.do`,
//         data: {
//             selectedYear: selectedYear
//         },
//         success(response){
//             console.log(response)
//         }
//     })
// }

document.getElementById("year").addEventListener('change', (e) => {
    console.log(e.target.value);
    const year = e.target.value;
    const tbody = document.querySelector("#searchDate tbody")
    let yearValue;
    switch (year) {
        case "2024" :  yearValue = new Date('2024-01-01'); break;
        case "2023" :  yearValue = new Date('2023-01-01'); break;
        case "2022" :  yearValue = new Date('2022-01-01'); break;
        case "2021" :  yearValue = new Date('2021-01-01'); break;
        case "2020" :  yearValue = new Date('2020-01-01'); break;
        case "2019" :  yearValue = new Date('2019-01-01'); break;
        case "2018" :  yearValue = new Date('2018-01-01'); break;
        default: throw new Error("지정한 년도는 조회 불가능합니다.");
    }
    let timestamp = yearValue.getTime();

    $.ajax({
        url: `${contextPath}dayoff/dayOffListSearchDate.do`,
        data: {
            year: timestamp
        },
        headers: {
            [csrfHeaderName]: csrfToken
        },
        success(response) {
            console.log(response);
            tbody.innerHTML = '';
            response.content.forEach((off) => {
                const {content, count, endAt, startAt, id, type} = off;

                tbody.innerHTML += `
                <tr>
                <td></td>
                <td></td>
                <td>${type}</td>
                <td>
                ${new Date(startAt).toLocaleDateString('en-US', { year: 'numeric', month: '2-digit', day: '2-digit' }).replace(/(\d+)\/(\d+)\/(\d+)/, '$3/$1/$2')}
                ~
                ${new Date(endAt).toLocaleDateString('en-US', { year: 'numeric', month: '2-digit', day: '2-digit' }).replace(/(\d+)\/(\d+)\/(\d+)/, '$3/$1/$2')}
                </td>
                <td>${count}</td>
                <td>${content}</td>
            </tr>
                `
            })

        }
    })
});

function changePage1(pageNumber) {
    let size = 10;
    // 페이지 URL 생성
    let url = '/WorksOn/dayoff/dayoffList.do?continue&page=' + pageNumber + '&size=' + size;

    // 페이지 이동
    window.location.href = url;
}