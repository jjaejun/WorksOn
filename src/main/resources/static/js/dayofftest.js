
/**
 * 민정 test
 */
document.querySelector("#year").addEventListener('change', (e) => {
    console.log(e.target.value);
    const year = e.target.value;
    let yearValue;
    switch (year) {
        case "2024" :  yearValue = new Date('2024-01-01'); break;
        case "2023" :  yearValue = new Date('2023-01-01'); break;
        case "2022" :  yearValue = new Date('2022-01-01'); break;
        default: throw new Error("지정한 년도는 조회 불가능합니다.");
    }
    let timestamp = yearValue.getTime();


    $.ajax({
        url: `${contextPath}attend/searchDayoff.do`,
        data: {
            year : timestamp
        },
        headers: {
            [csrfHeaderName] : csrfToken
        },
        success(response){
            console.log(response);
        }
    })
});
