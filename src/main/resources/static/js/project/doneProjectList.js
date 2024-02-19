const trs = document.querySelectorAll("#projectList tr");
trs.forEach((tr, i) => {
    tr.addEventListener('click', (e) => {
        const { id } = trs[i].dataset;
        location.href = `${contextPath}project/projectDetail.do?id=${id}`;
    });
});