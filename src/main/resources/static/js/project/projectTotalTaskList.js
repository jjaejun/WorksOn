const lisColorChange = () => {
    document.querySelectorAll(".articleLi").forEach((article) => {
        article.addEventListener('click', (e) => {
            const project = e.target;
            const { projectId : id } = article.dataset;
            location.href = `${contextPath}project/projectDetail.do?id=${id}`;
        });

        // console.log(window.location);
        const url = window.location.search;
        if(url === `?id=${article.dataset.projectId}` || url === `?id=${article.dataset.projectId}&continue`){
            article.classList.add("bg-gray-100");
        }
        else {
            article.classList.remove("bg-gray-100");
        }
    });
}

const trEvent = () => {
    const trs = document.querySelectorAll("#taskList tr");
    trs.forEach((tr, i) => {
        tr.addEventListener('click', (e) => {
            const { id, projectId } = trs[i].dataset;
            location.href = `${contextPath}project/taskDetail.do?id=${id}&projectId=${projectId}`;
        });
    });
}
window.addEventListener('DOMContentLoaded', () => {
    pageEvent();
    trEvent();
    lisColorChange();
});

const pageEvent = () => {
    document.querySelectorAll(".pageNumber").forEach((btn) => {
        btn.addEventListener('click', (e) => {
            const button = e.target;
            const { pageNumber} = button.dataset;
            let size = 10;
            let url = `/WorksOn/project/totalTaskList.do?page=${pageNumber}&size=${size}`;

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

                    const newProjectList = newDocument.querySelector("#taskList");
                    const originProjectList = document.querySelector("#taskList");
                    const newPagebar = newDocument.querySelector("#page-bar-container");
                    const originPagebar = document.querySelector("#page-bar-container");

                    originProjectList.innerHTML = newProjectList.innerHTML;
                    originPagebar.innerHTML = newPagebar.innerHTML;

                    pageEvent();
                    trEvent();
                })
                .catch(error => {
                    console.error('Fetch error:', error);
                });
        });
    });
};