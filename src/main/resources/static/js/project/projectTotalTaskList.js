const sidebarPageEvent = () => {
    document.querySelectorAll(".ownerPageNumber").forEach((btn) => {
        btn.addEventListener('click', (e) => {
            const pageNumber2 = document.querySelector("#selectBtnEmpPage").value;
            const button = e.target;
            const { pageNumber} = button.dataset;
            let size = 5;
            let url = `/WorksOn/project/projectDetail.do?id=${document.querySelector("#thisProjectId").value}&continue&page1=${pageNumber}&size1=${size}&page2=${pageNumber2}&size2=${size}`;

            // console.log(url);

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
                    sidebarPageEvent();
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
    sidebarPageEvent();
});

const pageEvent = () => {
    document.querySelectorAll(".taskPageNumber").forEach((btn) => {
        btn.addEventListener('click', (e) => {
            const button = e.target;
            const { pageNumber} = button.dataset;
            const projectId = document.querySelector("#thisProjectId").value;
            let size = 10;
            let url = `/WorksOn/project/projectTotalTaskList.do?id=${projectId}&page3=${pageNumber}&size3=${size}`;

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
                    console.log("들어왔니..");


                    // 서버로부터 받은 HTML을 현재 페이지의 콘텐츠에 적용
                    let parser = new DOMParser();
                    let newDocument = parser.parseFromString(html, 'text/html');

                    const newProjectList = newDocument.querySelector("#taskList");
                    const originProjectList = document.querySelector("#taskList");
                    const newPagebar = newDocument.querySelector("#task-page-bar-container");
                    const originPagebar = document.querySelector("#task-page-bar-container");


                    console.log(originPagebar);
                    console.log(newPagebar);
                    console.log(originProjectList);
                    console.log(newProjectList);

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