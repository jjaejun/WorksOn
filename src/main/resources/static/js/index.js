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
document.getElementById("workBtn").addEventListener("click", function() {
    document.getElementById("formContainer").style.display = "block";
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