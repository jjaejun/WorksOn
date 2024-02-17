window.addEventListener('DOMContentLoaded', () => {
    lisColorChange();
    pageEvent();
    updateBtnEvent();
});

/**
 * 수정 버튼 클릭 시
 */
const updateBtnEvent = () => {
    const inputs = document.querySelectorAll("input");
    console.log(inputs);

    // input태그들 편집가능하도록 하기
    const taskEmp = document.querySelector("#taskEmp");
    const stars = document.querySelectorAll(".star");
    const priority = document.querySelector("#priority");
    const startAt = document.querySelector("#startAt");
    const endAt = document.querySelector("#endAt");
    const taskContent = document.querySelector("#taskContent");
    const btns = document.querySelectorAll("#taskFrmBtns button");
    const banner = document.querySelector("#sticky-banner");

    document.querySelector("#editBtn").addEventListener('click', (e) => {
       const btn = e.target;
       banner.classList.remove("hidden");

       btns.forEach((b) => {
           if(b.classList.contains("hidden")){
               b.classList.remove("hidden");
           }
           else {
               b.classList.add("hidden");
           }
       })

       taskEmp.classList.remove("hidden");
       startAt.classList.remove("hidden");
       endAt.classList.remove("hidden");
       taskContent.classList.remove("hidden");

       // star 편집
        stars.forEach((btn, i) => {
            btn.addEventListener('click', (e) => {
                // 전체 요소 회색 처리
                stars.forEach((s) => {
                    s.classList.remove("text-yellow-300");
                    s.classList.add("text-gray-300");
                })
                // 클릭한 요소까지 노랑색 처리
                for(let j = 0; j <= i; j++){
                    stars[j].classList.remove("text-gray-300");
                    stars[j].classList.add("text-yellow-300");
                }
                // 클릭한 요소의 인덱스 + 1로 input태그 value 설정하기
                priority.value = i + 1;
                console.log(priority.value);
            });
        });
    });
};





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

        // console.log(window.location);
        const url = window.location.search;
        if(url.includes(`projectId=${article.dataset.projectId}`)){
            article.classList.add("bg-gray-100");
        }
        else {
            article.classList.remove("bg-gray-100");
        }
    });
}

