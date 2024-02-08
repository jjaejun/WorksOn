document.getElementById("previousPage").addEventListener("click", function(event) {
    event.preventDefault(); // 기본 동작 방지

    window.location.href= ""
    console.log("이전 페이지로 이동합니다.");
});

// 다음 페이지로 이동하는 이벤트 처리
document.getElementById("nextPage").addEventListener("click", function(event) {
    event.preventDefault(); // 기본 동작 방지
    window.location.href=""
    console.log("다음 페이지로 이동합니다.");
});