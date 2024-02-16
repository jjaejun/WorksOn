window.addEventListener('DOMContentLoaded', () => {
    lisColorChange();
    pageEvent();
});

/**
 * page1에 대한 변수
 */
const pageEvent = () => {
    document.querySelectorAll(".ownerPageNumber").forEach((btn) => {
        btn.addEventListener('click', (e) => {
            const pageNumber2 = document.querySelector("#selectBtnEmpPage").value;
            const button = e.target;
            const { pageNumber} = button.dataset;
            let size = 5;
            let url = `/WorksOn/project/taskDetail.do?id=${document.querySelector("#projectId").value}&projectId=${document.querySelector("#projectId").value}&continue&page1=${pageNumber}&size1=${size}&page2=${pageNumber2}&size2=${size}`;

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

                    const newSidebar = newDocument.querySelector("#sidebarProject");
                    const originSidebar = document.querySelector("#sidebarProject");

                    // console.log(newDocument);
                    originSidebar.innerHTML = newSidebar.innerHTML;
                    lisColorChange();
                    pageEvent();
                })
                .catch(error => {
                    console.error('Fetch error:', error);
                });
        });
    });
};

const lisColorChange = () => {
    document.querySelectorAll(".articleLi").forEach((article) => {
        article.addEventListener('click', (e) => {
            const project = e.target;
            const { projectId : id } = article.dataset;
            location.href = `${contextPath}project/projectDetail.do?id=${id}`;
        });

        console.log(window.location);
        const url = window.location.search;
        if(url.includes(`projectId=${article.dataset.projectId}`)){
            article.classList.add("bg-gray-100");
        }
        else {
            article.classList.remove("bg-gray-100");
        }
    });
}

