const projects = document.querySelectorAll(".project");
projects.forEach((p, i) => {
    p.addEventListener('click', (e) => {
       const {projectId} = projects[i].dataset;
       window.location.href = `${contextPath}project/projectDetail.do?id=${projectId}`;
    });
});
const waitApprovals = document.querySelectorAll(".waitApproval");
waitApprovals.forEach((p, i) => {
    p.addEventListener('click', (e) => {
        window.location.href = `${contextPath}approval/approvalWait.do`;
    });
});
const reApprovals = document.querySelectorAll(".reApproval");
reApprovals.forEach((p, i) => {
    p.addEventListener('click', (e) => {
        window.location.href = `${contextPath}approval/approvalReceived.do`;
    });
});
// 근태관리 실시간 시간 보여주기
const Target = document.getElementById("clock");
const today = document.getElementById("today");
function clock() {
    let time = new Date();

    let year = time.getFullYear();
    let month = time.getMonth();
    let date = time.getDate();
    let day = time.getDay();
    let week = ['일', '월', '화', '수', '목', '금', '토'];

    let hours = time.getHours();
    let minutes = time.getMinutes();
    let seconds = time.getSeconds();

    today.innerHTML = `${year}년 ${month + 1}월 ${date}일 (${week[day]})`;
    Target.innerText =
        `${hours < 10 ? `0${hours}` : hours}:${minutes < 10 ? `0${minutes}` : minutes}:${seconds < 10 ? `0${seconds}` : seconds}`;

}
clock();
setInterval(clock, 1000); // 1초마다 실행

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


// 근태관리 실시간 시간 보여주기 end

// window.addEventListener('DOMContentLoaded', () =>{
//     const focusBar = document.querySelector("#focus-bar");
//
//     // li에 id 달아서 수정하시면 됩니당
//     const dashboardLi = document.querySelector("#dashboardLi");
//     const lis = document.querySelectorAll("#nav-ul li");
//
//     // console.log(projectLi);
//
//     lis.forEach((li, i) => {
//         const h = 58;
//         for(let i = 0; i < lis.length; i++){
//             focusBar.classList.remove(`inset-y-[${h * i}px]`);
//             lis[i].classList.remove("bg-blue-500");
//         }
//     });
//     dashboardLi.classList.add("bg-blue-500");
//     focusBar.classList.add(`inset-y-[${dashboardLi.offsetTop}px]`);
// });

// 버튼 클릭 시 폼 보이기
document.getElementById("workBtn").addEventListener("click", function(e) {
    const frm = document.getElementById("formContainer");
    const list = document.querySelector("#daliyWorkList");
    const btn = e.target;

    console.log(e.target);
    console.log(frm);
    if(frm.classList.contains("hidden")){
        frm.classList.remove("hidden");
        list.classList.add("hidden");
        btn.innerHTML = "업무일지 내역보기";


        btn.classList.add("bg-gray-500")
        btn.classList.remove("bg-blue-500")
    }
    else {
        frm.classList.add("hidden");
        list.classList.remove("hidden");
        btn.innerHTML = "업무일지 작성하기";


        btn.classList.add("bg-blue-500");
        btn.classList.remove("bg-gray-500");
    }

});

const createClickDelete = (empId) => {
    console.log(empId);
    const selected = document.getElementById(`create${empId}`);
    selected.outerHTML = '';
};

/**
 * 목록 중 하나 클릭 시 입력처리
 */
const createClickEvent = () => {
    const searchResult = document.querySelectorAll(".searchResult");
    searchResult.forEach((emp, i) => {
        emp.addEventListener('click', (e) => {

            const validEmpId = e.target.dataset.empId;

            // undefined일 때 입력되지 않도록 함
            if(validEmpId === undefined){
                return;
            }
            // 한번만 입력하도록 함
            if(document.getElementById(`create${validEmpId}`) !== null){
                alert("동일한 사원을 추가할 수 없습니다.")
                return;
            }

            const input = document.querySelector("#create-search-input");
            input.value = '';
            const searchList = document.querySelector("#create-search-list");
            searchList.innerHTML = '';

            const {empId, empName, empPosition} = searchResult[i].dataset;
            const selectArea = document.querySelector("#createSelectArea");

            selectArea.innerHTML += `
        <div id="create${empId}"
        class="selected flex justify-center items-center m-1 font-medium py-1 px-2 bg-white rounded-full text-blue-700 bg-blue-100 border border-blue-300 ">
        <div class="text-sm font-normal leading-none max-w-full flex-initial">${empName}${empPosition}</div>
            <input type="hidden" readonly value="${empId}" name="createEmp">
            <div class="flex flex-auto flex-row-reverse">
                <div onclick="createClickDelete(${empId})">
                    <svg xmlns="http://www.w3.org/2000/svg" width="100%" height="100%" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-x cursor-pointer hover:text-red-400 rounded-full w-4 h-4 ml-2">
                        <line x1="18" y1="6" x2="6" y2="18"></line>
                        <line x1="6" y1="6" x2="18" y2="18"></line>
                    </svg>
                </div>
            </div>
        </div>
        `;
        });
    })}

/**
 * 참여사원 목록 요청
 */
document.querySelector("#create-search-input").addEventListener('keyup', (e) => {
    const input = e.target;
    console.log(input.value);
    const searchList = document.querySelector("#create-search-list");
    searchList.innerHTML = '';

    if(input.value !== ''){
        $.ajax({
            url: `${contextPath}employee/searchEmployee.do`,
            headers: {
                [csrfHeaderName] : csrfToken
            },
            data: {
                name: input.value
            },
            success(response) {
                console.log(response);
                searchList.innerHTML = '';

                response.forEach((e) => {
                    const {id, name, department: {name : deptName}, position: {name: positionName}, profileUrl} = e;

                    searchList.innerHTML += `
                <div class="cursor-pointer w-full border-gray-100 rounded-t border-b hover:bg-blue-100 text-sm" 
                        onclick="javascript:createClickEvent();">
                    <div class="flex w-full items-center p-2 pl-2 border-transparent border-l-2 relative hover:border-blue-100">
                        <div data-emp-id="${id}" data-emp-name="${name}" data-emp-position="${positionName}" 
                            class="searchResult w-full items-center flex">
                            <div style="width: 40px; height: 40px; overflow: hidden" class="rounded-full">
                                <img width="50px" alt="사원 프로필 사진" class="block rounded-full" 
                                src="${profileUrl != null ? profileUrl : 'https://bucket-minjeong2024.s3.ap-northeast-2.amazonaws.com/profile.png'}">
                            </div>
                            <div>
                                <p class="ml-2 text-sm text-blue-700 font-bold">
                                     ${deptName}
                                </p>
                                <p class="ml-2 text-sm">
                                    ${name} ${positionName}
                                </p>
                            </div>
                        </div>
                    </div>
                </div>`;
                });
            }
        })
    }
});

document.getElementById("")