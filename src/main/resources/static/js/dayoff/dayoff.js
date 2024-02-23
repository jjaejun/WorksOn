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

window.addEventListener('DOMContentLoaded', () => {
    pageEvent();
})


const pageEvent = () => {
    document.querySelectorAll(".pageNumber").forEach((btn) => {
        btn.addEventListener('click', (e) => {
            const button = e.target;
            const year = document.querySelector("#year");
            const yearValue = new Date(`${year.value}-01-01`);

            const { pageNumber} = button.dataset;
            let size = 10;
            let url = `/WorksOn/dayoff/dayoffList.do?year=${yearValue.getTime()}&page=${pageNumber}&size=${size}`;

            console.log(url);

            // Fetch API를 사용하여 비동기로 데이터 가져오기
            fetch(url)
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.text();
                })
                .then(html => {
                    // 서버로부터 받은 HTML을 현재 페이지의 콘텐츠에 적용
                    let parser = new DOMParser();
                    let newDocument = parser.parseFromString(html, 'text/html');

                    const newDayoffList = newDocument.querySelector(".leave-history");
                    const originDayoffList = document.querySelector(".leave-history");


                    originDayoffList.innerHTML = newDayoffList.innerHTML;

                    pageEvent();
                })
                .catch(error => {
                    console.error('Fetch error:', error);
                });
        });
    });
};



document.getElementById("year").addEventListener('change', (e) => {
    // console.log(e.target.value);
    const year = e.target.value;
    const tbody = document.querySelector("#searchDate tbody")
    const yearValue = new Date(`${year}-01-01`);

    let size = 10;
    let url = `/WorksOn/dayoff/dayoffList.do?year=${yearValue.getTime()}&page=0&size=${size}`;

    // console.log(url);

    let timestamp = yearValue.getTime();
    // Fetch API를 사용하여 비동기로 데이터 가져오기
    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text();
        })
        .then(html => {
            // 서버로부터 받은 HTML을 현재 페이지의 콘텐츠에 적용
            let parser = new DOMParser();
            let newDocument = parser.parseFromString(html, 'text/html');

            const newDayoffList = newDocument.querySelector(".leave-history");
            const originDayoffList = document.querySelector(".leave-history");
            const newRemainDayOffCount = newDocument.querySelector("#remainDayOffCount");
            const originRemainDayOffCount = document.querySelector("#remainDayOffCount");
            const newUseDayOffCount = newDocument.querySelector("#useDayOffCount");
            const originUseDayOffCount = document.querySelector("#useDayOffCount");

            originDayoffList.innerHTML = newDayoffList.innerHTML;
            originRemainDayOffCount.innerHTML = newRemainDayOffCount.innerHTML;
            originUseDayOffCount.innerHTML = newUseDayOffCount.innerHTML;
            pageEvent();
        })
        .catch(error => {
            console.error('Fetch error:', error);
        });



    // $.ajax({
    //     url: `${contextPath}dayoff/dayOffListSearchDate.do`,
    //     data: {
    //         year: timestamp
    //     },
    //     headers: {
    //         [csrfHeaderName]: csrfToken
    //     },
    //     success(response) {
    //         console.log(response);
    //         tbody.innerHTML = '';
    //         response.content.forEach((off) => {
    //             const {employee, content, count, endAt, startAt, id, type} = off;
    //
    //             tbody.innerHTML += `
    //             <tr>
    //             <td>${employee.name}</td>
    //             <td>${employee.position.name}</td>
    //             <td>${type}</td>
    //             <td>
    //             ${new Date(startAt).toLocaleDateString('en-US', { year: 'numeric', month: '2-digit', day: '2-digit' }).replace(/(\d+)\/(\d+)\/(\d+)/, '$3/$1/$2')}
    //             ~
    //             ${new Date(endAt).toLocaleDateString('en-US', { year: 'numeric', month: '2-digit', day: '2-digit' }).replace(/(\d+)\/(\d+)\/(\d+)/, '$3/$1/$2')}
    //             </td>
    //             <td>${count}</td>
    //             <td>${content}</td>
    //         </tr>
    //             `
    //         })
    //
    //     }
    // })
});

function changePage1(pageNumber) {
    let size = 10;
    // 페이지 URL 생성
    let url = '/WorksOn/dayoff/dayoffList.do?continue&page=' + pageNumber + '&size=' + size;

    // 페이지 이동
    window.location.href = url;
}