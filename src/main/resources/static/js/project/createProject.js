const deleteFile = (trIndex) => {
    const dataTransfer = new DataTransfer();
    const tr = document.getElementById(`file${trIndex}`);
    tr.outerHTML = '';

    const input = document.querySelector("#files");
    let files = input.files;
    let fileList = [...files];
    fileList.splice(trIndex, 1);

    fileList.forEach((file) => {dataTransfer.items.add(file)})

    files = dataTransfer.files;
    console.log(files);
    input.placeholder = `파일 ${files.length}개`;
};
document.querySelector("#files").addEventListener('change', (e) => {
   const input = e.target;
   const tbody = document.querySelector("#fileArea tbody");

   tbody.innerHTML = '';

   console.dir(input);
   const files = [...input.files];

   if(files.length === 0){
       tbody.innerHTML = '<tr class="text-gray-700">\n' +
                            '<td class="px-2 py-2">등록된 파일이 없습니다.</td>\n' +
                        '</tr>';
   }
   else {
       files.forEach((file, i) => {
           tbody.innerHTML += `
       <tr id="file${i}" class="text-gray-700">
           <td class="px-2 py-2">${file.name}</td>
           <td class="px-2 py-2" onclick="deleteFile(${i})">
               <div>
                    <svg xmlns="http://www.w3.org/2000/svg" width="100%" height="100%" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-x cursor-pointer hover:text-red-400 rounded-full w-4 h-4 ml-2">
                        <line x1="18" y1="6" x2="6" y2="18"></line>
                        <line x1="6" y1="6" x2="18" y2="18"></line>
                    </svg>
                </div>
            </td>
       </tr>
       `;
       });
   }
});


const readClickDelete = (empId) => {
    console.log(empId);
    const selected = document.getElementById(`read${empId}`);
    selected.outerHTML = '';
};

/**
 * 목록 중 하나 클릭 시 입력처리
 */
const readClickEvent = () => {
    document.querySelectorAll(".searchResult").forEach((emp) => {
    emp.addEventListener('click', (e) => {

        const input = document.querySelector("#read-search-input");
        input.value = '';
        const searchList = document.querySelector("#read-search-list");
        searchList.innerHTML = '';

        const {empId, empName, empPosition} = e.target.dataset;
        const selectArea = document.querySelector("#readSelectArea");

        selectArea.innerHTML += `
        <div id="read${empId}"
        class="selected flex justify-center items-center m-1 font-medium py-1 px-2 bg-white rounded-full text-blue-700 bg-blue-100 border border-blue-300 ">
        <div class="text-sm font-normal leading-none max-w-full flex-initial">${empName}${empPosition}</div>
            <input type="hidden" readonly value="${empId}" name="readEmp">
            <div class="flex flex-auto flex-row-reverse">
                <div onclick="readClickDelete(${empId})">
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
document.querySelector("#read-search-input").addEventListener('keyup', (e) => {
    const input = e.target;
    console.log(input.value);
    const searchList = document.querySelector("#read-search-list");
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
                <div class="cursor-pointer w-full border-gray-100 rounded-t border-b hover:bg-blue-100 text-sm" onclick="javascript:readClickEvent();">
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
                </div>
               `;
                });
            }
        })
    }
});

/**
 * 입력된 사용자 삭제 
 */
const createClickDelete = (empId) => {
    console.log(empId);
    const selected = document.getElementById(`create${empId}`);
    selected.outerHTML = '';
};

/**
 * 목록 중 하나 클릭 시 입력처리
 */
const createClickEvent = () => {
    document.querySelectorAll(".searchResult").forEach((emp) => {
        emp.addEventListener('click', (e) => {

            const input = document.querySelector("#create-search-input");
            input.value = '';
            const searchList = document.querySelector("#create-search-list");
            searchList.innerHTML = '';

            const {empId, empName, empPosition} = e.target.dataset;
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
                <div class="cursor-pointer w-full border-gray-100 rounded-t border-b hover:bg-blue-100 text-sm" onclick="javascript:createClickEvent();">
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





/**
 * ajax 폼 제출용
 */
// document.projectCreateFrm.addEventListener('submit', (e) => {
//     e.preventDefault();
//
//     const frm = e.target;
//     const frmData = new FormData(frm);
//
//     // 유효성 검사해야됨
//
//     $.ajax({
//         url: `${contextPath}project/createProject.do`,
//         headers: {
//             [csrfHeaderName] : csrfToken
//         },
//         data: frmData,
//         processData: false,
//         contentType: false,
//         method: 'post',
//         success(response) {
//             console.log(frmData);
//             console.log(response);
//             frm.reset();
//         }
//     })
// });