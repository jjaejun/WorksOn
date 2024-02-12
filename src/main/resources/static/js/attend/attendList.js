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

    btnStartWork.addEventListener('click', function () {
        if (isAttendRegistered) {
            alert('이미 출근이 등록되어 있습니다.');
            return;
        }

        // AJAX를 사용하여 출근 등록 요청
        $.ajax({
            type: 'POST',
            headers: {
                [csrfHeaderName]: csrfToken
            },
            url: `${contextPath}attend/startWork.do`,
            data: {
                id: 952
            },
            success: function (response) {
                alert(response);
                // 출근 등록이 성공하면 상태를 변경
                isAttendRegistered = true;

                console.log('출근 등록 성공');

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
        // AJAX를 사용하여 퇴근 등록 요청
        $.ajax({
            type: 'POST',
            headers: {
                [csrfHeaderName]: csrfToken
            },
            url: `${contextPath}attend/endWork.do`,
            data: {
                id: 952
            },
            success: function (response) {
                alert(response);
                // 퇴근 등록이 성공하면 상태를 변경
                isEndWorkRegistered = true;

                console.log('퇴근 등록 성공');

                const currentTime = new Date();
                const formattedTime = currentTime.toLocaleTimeString();
                document.getElementById('endwork-time').textContent = formattedTime;

            },
            error: function (error) {
                // 오류가 발생하면 오류 메시지 출력 또는 다른 처리 수행
                console.error('에러 발생:', error.responseText);
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

