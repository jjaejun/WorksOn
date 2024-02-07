window.addEventListener('DOMContentLoaded', () =>{
    const focusBar = document.querySelector("#focus-bar");
    const attendBtn = document.querySelector("#attendBtn");
    const lis = document.querySelectorAll("#nav-ul li");

    // console.log(projectLi);

    lis.forEach((li, i) => {
        const h = 58;
        for(let i = 0; i < lis.length; i++){
            focusBar.classList.remove(`inset-y-[${h * i}px]`);
            lis[i].classList.remove("bg-blue-500");
        }
    });
    attendBtn.classList.add("bg-blue-500");
    focusBar.classList.add(`inset-y-[${attendBtn.offsetTop}px]`);
});

// 출근하기 버튼 클릭 이벤트
$(document).on('click', '#btn-startwork', function () {
    // AJAX를 사용하여 출근 등록 요청
    $.ajax({
        type: 'POST',
        url: `${contextPath}attend/startWork.do`, // 해당 URL은 실제로 사용하는 URL로 변경해야 합니다.
        data: {
            id: 952 // 출근 등록을 위한 사용자 ID 또는 다른 필요한 데이터
        },
        success: function (data) {
            // 출근 등록이 성공하면 원하는 동작 수행
            console.log('출근 등록 성공');
            // 여기에서 추가적인 동작을 수행하거나 생략할 수 있습니다.
        },
        error: function (error) {
            // 오류가 발생하면 오류 메시지 출력 또는 다른 처리 수행
            console.error('에러 발생:', error.responseText);
        }
    });
});
