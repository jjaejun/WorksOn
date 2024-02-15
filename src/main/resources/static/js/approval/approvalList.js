document.querySelectorAll("tr[data-approval-leave-id]").forEach((tr) =>{
   tr.addEventListener('click', (e) => {
     const td = e.target;
     const tr = td.parentElement;
     const {approvalLeaveId} = tr.dataset;
     location.href = `${contextPath}approval/approvalDetailLeave.do?id=${approvalLeaveId}`;
   });
});

document.querySelectorAll("tr[data-approval-equipment-id]").forEach((tr) =>{
    tr.addEventListener('click', (e) => {
        const td = e.target;
        const tr = td.parentElement;
        const {approvalEquipmentId} = tr.dataset;
        location.href = `${contextPath}approval/approvalDetailEquipment.do?id=${approvalEquipmentId}`;
    });
});

document.querySelectorAll("tr[data-approval-cooperation-id]").forEach((tr) =>{
    tr.addEventListener('click', (e) => {
        const td = e.target;
        const tr = td.parentElement;
        const {approvalCooperationId} = tr.dataset;
        location.href = `${contextPath}approval/approvalDetailCooperation.do?id=${approvalCooperationId}`;
    });
});

window.addEventListener('DOMContentLoaded', () =>{
    const lis = document.querySelectorAll(".approval-tr td");
    lis.forEach((li, i) => {
        li.classList.add("cursor-pointer")
    });
});

// 연차 문서함 - 비동기 처리
// function changePage1(pageNumber) {
//     let size = 1;
//     // 페이지 URL 생성
//     let url = '/WorksOn/approval/approvalLeave.do?continue&page=' + pageNumber + '&size=' + size;
//
//     // Fetch API를 사용하여 비동기로 데이터 가져오기
//     fetch(url)
//         .then(response => {
//             if (!response.ok) {
//                 throw new Error('Network response was not ok');
//             }
//             return response.text();
//         })
//         .then(html => {
//             // 서버로부터 받은 HTML을 현재 페이지의 콘텐츠에 적용
//             var parser = new DOMParser();
//             var newDocument = parser.parseFromString(html, 'text/html');
//             document.documentElement.innerHTML = newDocument.documentElement.innerHTML;
//         })
//         .catch(error => {
//             console.error('Fetch error:', error);
//         });
// }
function changePage1(pageNumber) {
    let pageSize = 0;
    let size = pageSize;
    // 페이지 URL 생성
    let url = '/WorksOn/approval/approvalLeave.do?continue&page=' + pageNumber + '&size=' + size;

    // 페이지 이동
    window.location.href = url;
}

// 비품 문서함
function changePage2(pageNumber) {
    let pageSize = 0;
    let size = pageSize;
    // 페이지 URL 생성
    let url = '/WorksOn/approval/approvalEquipment.do?continue&page=' + pageNumber + '&size=' + size;

    // 페이지 이동
    window.location.href = url;
}

// 협조 문서함
function changePage3(pageNumber) {
    let pageSize = 0;
    let size = pageSize;
    // 페이지 URL 생성
    let url = '/WorksOn/approval/approvalCooperation.do?continue&page=' + pageNumber + '&size=' + size;

    // 페이지 이동
    window.location.href = url;
}

// 임시 저장함
function changePage4(pageNumber) {
    let pageSize = 0;
    let size = pageSize;
    // 페이지 URL 생성
    let url = '/WorksOn/approval/approvalTemporary.do?continue&page=' + pageNumber + '&size=' + size;

    // 페이지 이동
    window.location.href = url;
}


