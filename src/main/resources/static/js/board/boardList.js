
document.querySelectorAll("tr[data-board-id]").forEach((tr) =>{
    tr.style.cursor = "pointer";
    tr.addEventListener('click',(e) =>{
        const td =e.target;
        const tr = td.parentElement;
        const {boardId} = tr.dataset;
        location.href = `${contextPath}board/boardDetail.do?id=${boardId}`;
    });
});


// document.getElementById("previousPage").addEventListener("click", function(event) {
//     event.preventDefault(); // 기본 동작 방지
//
//     window.location.href= ""
//     console.log("이전 페이지로 이동합니다.");
// });
//
// // 다음 페이지로 이동하는 이벤트 처리
// document.getElementById("nextPage").addEventListener("click", function(event) {
//     event.preventDefault(); // 기본 동작 방지
//     window.location.href=""
//     console.log("다음 페이지로 이동합니다.");
// });