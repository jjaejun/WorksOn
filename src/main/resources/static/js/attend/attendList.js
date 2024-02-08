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
                // 여기에서 추가적인 동작을 수행하거나 생략할 수 있습니다.
                document.getElementById('work-state').textContent = '업무중';

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
});

