/**
 * 참여사원 목록 요청
 */
document.querySelector("#search-input").addEventListener('keyup', (e) => {
    const input = e.target;
    console.log(input.value);

    $.ajax({
        url: `${contextPath}employee/searchEmployee.do`,
        headers: {
            [csrfHeaderName] : csrfToken
        },
        data: {
            name: input.value
        },
        success(response) {
            console.log(response);
        }
    })


});


/**
 * 폼 제출용
 */
document.projectCreateFrm.addEventListener('submit', (e) => {
    e.preventDefault();

    const frm = e.target;
    const frmData = new FormData(frm);

    // 유효성 검사해야됨

    $.ajax({
        url: `${contextPath}project/createProject.do`,
        headers: {
            [csrfHeaderName] : csrfToken
        },
        data: frmData,
        processData: false,
        contentType: false,
        method: 'post',
        success(response) {
            console.log(frmData);
            console.log(response);
            frm.reset();
        }

    })


});