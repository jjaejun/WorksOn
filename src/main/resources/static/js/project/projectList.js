const colorChange = (article) => {
    // 랜덤색상 만들기
    const r = Math.floor(Math.random() * 16 + 240);
    const g = Math.floor(Math.random() * 16 + 240);
    const b = Math.floor(Math.random() * 16 + 240);
    article.style.backgroundColor = `rgb(${r},${g},${b})`;
}

window.addEventListener('DOMContentLoaded', () => {
    const articles = document.querySelectorAll("article");
    articles.forEach((article) => {
        colorChange(article);
    })
});

document.projectCreateFrm.addEventListener('submit', (e) => {
    const frm = e.target;
    const frmData = new FormData(frm);

    // 유효성 검사해야됨



    $.ajax({
        url: `${contextPath}project/createProject.do`,
        header: {
            [csrfHeaderName] : csrfToken
        },
        data: frmData,
        processData: false,
        contentType: false,
        method: 'post',
        success(response) {
            console.log(response);
            frm.reset();
        }

    })


});