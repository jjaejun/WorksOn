/**
 * 목록 중 하나 클릭 시 입력처리
 */
const searchEvent = () => {
    document.querySelectorAll(".searchResult").forEach((emp) => {
    emp.addEventListener('click', (e) => {
        const {empId, empName} = e.target.dataset;
        const selectArea = document.querySelector("#selectArea");

        selectArea.innerHTML += `
        <div class="flex justify-center items-center m-1 font-medium py-1 px-2 bg-white rounded-full text-teal-700 bg-teal-100 border border-teal-300 ">
        <div class="text-xs font-normal leading-none max-w-full flex-initial">${empName}</div>
            <input type="hidden" readonly value="${empId}" name="createEmp">
            <div class="flex flex-auto flex-row-reverse">
                <div>
                    <svg xmlns="http://www.w3.org/2000/svg" width="100%" height="100%" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-x cursor-pointer hover:text-teal-400 rounded-full w-4 h-4 ml-2">
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
document.querySelector("#search-input").addEventListener('keyup', (e) => {
    const input = e.target;
    console.log(input.value);
    const searchList = document.querySelector("#search-list");
    searchList.innerHTML = '';

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
                const {id, name, department: {name : deptName}, position: {name: positionName}} = e;

                searchList.innerHTML += `
                <div class="cursor-pointer w-full border-gray-100 rounded-t border-b hover:bg-blue-100">
                    <div class="flex w-full items-center p-2 pl-2 border-transparent border-l-2 relative hover:border-blue-100">
                        <div data-emp-id="${id}" data-emp-name="${name}" class="searchResult w-full items-center flex">
                            <div class="mx-2">${name}</div>
                            <div class="mx-2">${positionName}</div>
                            <div class="mx-2">${deptName}</div>
                        </div>
                    </div>
                </div>
               `;
            });
        }
    })
});


/**
 * 폼 제출용
 */
document.projectCreateFrm.addEventListener('submit', (e) => {
    e.preventDefault();

    const frm = e.target;
    const frmData = new FormData(frm);

    // 유효성 검사해야됨

    $.ajax({
        url: `${contextPath}project/createProject.do`,
        headers: {
            [csrfHeaderName] : csrfToken
        },
        data: frmData,
        processData: false,
        contentType: false,
        method: 'post',
        success(response) {
            console.log(frmData);
            console.log(response);
            frm.reset();
        }

    })


});