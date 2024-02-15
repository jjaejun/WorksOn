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


window.addEventListener('DOMContentLoaded', () => {
    const btnStartWork = document.getElementById("btn-startwork");
    const btnEndWork = document.getElementById("btn-endwork");
    const workStateElement = document.getElementById("work-state");

    btnStartWork.addEventListener('click', function () {
        if (isAttendRegistered) {
            alert('이미 출근이 등록되어 있습니다.');
            return;
        }
        const {empId} =  btnStartWork.dataset;
        console.log(empId);
        // AJAX를 사용하여 출근 등록 요청
        $.ajax({
            type: 'POST',
            headers: {
                [csrfHeaderName]: csrfToken
            },
            url: `${contextPath}attend/startWork.do`,
            data: {

            },
            success: function (response) {
                alert(response);
                // 출근 등록이 성공하면 상태를 변경
                isAttendRegistered = true;

                console.log('출근 등록 성공');
                workStateElement.textContent = '업무중';
                const currentTime = new Date();
                const formattedTime = currentTime.toLocaleTimeString();
                document.getElementById('startwork-time').textContent = formattedTime;

            },
            error: function (error) {
                // 오류가 발생하면 오류 메시지 출력 또는 다른 처리 수행
                console.error('에러 발생:', error.responseText);
            }

        });
    });

    let isEndWorkRegistered = false;
    btnEndWork.addEventListener('click', function () {
        if (isEndWorkRegistered) {
            alert('이미 퇴근이 등록되어 있습니다.');
            return;
        }
        const {empId} =  btnStartWork.dataset;
        console.log(empId);
        // AJAX를 사용하여 퇴근 등록 요청
        $.ajax({
            type: 'POST',
            headers: {
                [csrfHeaderName]: csrfToken
            },
            url: `${contextPath}attend/endWork.do`,
            data: {

            },
            success: function (response) {
                alert(response);
                // 퇴근 등록이 성공하면 상태를 변경
                isEndWorkRegistered = true;

                console.log('퇴근 등록 성공');
                workStateElement.textContent = '퇴근';
                const currentTime = new Date();
                const formattedTime = currentTime.toLocaleTimeString();
                document.getElementById('endwork-time').textContent = formattedTime;

            },
            error: function (xhr, status, error) {
                const errorMessage = JSON.parse(xhr.responseText).message;
                if (errorMessage === "퇴근시간이 아닙니다.") {
                    alert(errorMessage); // 예외 메시지를 팝업으로 표시
                } else {
                    // 그 외의 오류 처리
                    console.error('서버 오류:', errorMessage);
                }
            }
        });
    });
});

// 근태관리 실시간 시간 보여주기
var Target = document.getElementById("clock");
var yearNow = document.getElementById("year");
function clock() {
    var time = new Date();

    var year = time.getFullYear();
    var month = time.getMonth();
    var date = time.getDate();
    var day = time.getDay();
    var week = ['일', '월', '화', '수', '목', '금', '토'];

    var hours = time.getHours();
    var minutes = time.getMinutes();
    var seconds = time.getSeconds();

    yearNow.innerHTML = `${year}년 ${month + 1}월 ${date}일 (${week[day]})`;
    Target.innerText =
        `${hours < 10 ? `0${hours}` : hours}:${minutes < 10 ? `0${minutes}` : minutes}:${seconds < 10 ? `0${seconds}` : seconds}`;

}
clock();
setInterval(clock, 1000); // 1초마다 실행
// 근태관리 실시간 시간 보여주기 end


document.getElementById("btnRequest").addEventListener('click', function() {
    // 폼 데이터 수집
    const formData = new FormData(document.forms["contentFrm"]);
    let isAttendRequest = true;
    // AJAX 요청
    $.ajax({
        type: "POST",
        headers: {
            [csrfHeaderName]: csrfToken
        },
        url: `${contextPath}attend/request.do`,
        data: formData,
        processData: false,
        contentType: false,
        success: function (data){
            alert("정정 요청이 완료되었습니다.");
        }
    });
});

// DOM 요소 가져오기
const startDateInput = document.getElementById('start-input');
const endDateInput = document.getElementById('end-input');
const submitBtn = document.getElementById('submitBtn');

// 이벤트 리스너 추가
startDateInput.addEventListener('input', handleDateChange);
endDateInput.addEventListener('input', handleDateChange);

// 날짜가 변경되었을 때 실행되는 함수
function handleDateChange() {
    // 선택된 시작 날짜와 종료 날짜 출력
    console.log(`선택된 시작 날짜: ${startDateInput.value}, 종료 날짜: ${endDateInput.value}`);
}

// Submit 버튼 클릭 시 시작 날짜부터 종료 날짜까지 출력
submitBtn.addEventListener('click', function() {
    console.log(`선택한 날짜 범위: ${startDateInput.value} 부터 ${endDateInput.value}`);


    $.ajax({
        type: "GET",
        headers: {
            [csrfHeaderName]: csrfToken
        },
        url: `${contextPath}attend/attendList.do`,
        data: {
            startDate : startDateInput.value,
            endDate : endDateInput.value
        },
        success: function (response){
            console.log(response)
        }
    })
});




