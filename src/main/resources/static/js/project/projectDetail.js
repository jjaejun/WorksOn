const dropDownEvent = (e) => {
    const dropDown = document.querySelector("#dropdown-states");
    dropDown.classList.toggle("hidden");
}
/**
 * 편집완료 버튼 클릭 시
 */
const updateBtnEvent = () => {
    // input태그들 편집불가하도록 하기
    const banner = document.querySelector("#sticky-banner");
    const statesBtn = document.querySelector(".statesBtn");
    const updateBtn = document.querySelector("#updateBtn");
    const editBtn = document.querySelector("#editBtn");

    // 업데이트할 값 가져오기
    const id = document.querySelector("#id");
    const projectTitle = document.querySelector("#projectTitle");
    const status = document.querySelector("#status");
    const startAt = document.querySelector("#startAt");
    const endAt = document.querySelector("#endAt");

    document.querySelector("#updateBtn").addEventListener('click', (e) => {
        const btn = e.target;
        banner.classList.add("hidden");
        btn.classList.add("hidden");

        // 날짜 input 태그 변경
        startAt.classList.add("hidden");
        endAt.classList.add("hidden");

        // content
        projectTitle.classList.add("hidden");

        // btn
        updateBtn.classList.add("hidden");

        console.log(projectTitle.value, status.value, startAt.value, endAt.value);

        $.ajax({
            url: `${contextPath}project/updateProject.do`,
            data: {
                id: id.value,
                title: projectTitle.value,
                status: status.value,
                startAt: startAt.value,
                endAt: endAt.value,
            },
            headers: {
                [csrfHeaderName] : csrfToken
            },
            method: 'post',
            success(response) {
                // html 변경
                document.querySelector("#startAtHtml").innerHTML = `
                ${startAt.value}
                <input type="date" value="${startAt.value}"
                       id="startAt" name="startAt"
                       class="hidden absolute text-sm text-gray-700 focus:bg-gray-50 focus:z-10 top-0 left-0 border-none w-full h-full p-2 m-0" />
                `;
                document.querySelector("#endAtHtml").innerHTML = `
                ${endAt.value}
                <input type="date" value="${endAt.value}"
                       id="endAt" name="endAt"
                       class="hidden absolute text-sm text-gray-700 focus:bg-gray-50 focus:z-10 top-0 left-0 border-none w-full h-full p-2 m-0" />
                `;
                document.querySelector("#titleHtml").innerHTML = `
                ${projectTitle.value}
                <input type="text" name="projectTitle" id="projectTitle" class="hidden absolute top-0 left-0 text-2xl text-gray-700 flex items-center mb-4 border-none p-0" value="${projectTitle.value}" >
                `;
                document.getElementById(`project${id.value}`).firstElementChild.firstElementChild.innerHTML = `${projectTitle.value}`;

                // 이벤트 삭제하기
                // status 편집
                statesBtn.removeEventListener('click', dropDownEvent);
                editBtn.classList.remove("hidden");

                alert(response);
            }
        })
    });
};
/**
 * 수정 버튼 클릭 시
 * data-dropdown-toggle="dropdown-states"
 */
const editBtnEvent = () => {
    // input태그들 편집가능하도록 하기
    const projectTitle = document.querySelector("#projectTitle");
    const startAt = document.querySelector("#startAt");
    const endAt = document.querySelector("#endAt");
    const banner = document.querySelector("#sticky-banner");
    const statesBtn = document.querySelector(".statesBtn");
    const dropDown = document.querySelector("#dropdown-states");
    const updateBtn = document.querySelector("#updateBtn");

    document.querySelector("#editBtn").addEventListener('click', (e) => {
        const btn = e.target;
        banner.classList.remove("hidden");
        btn.classList.add("hidden");

        // 날짜 input 태그 변경
        startAt.classList.remove("hidden");
        endAt.classList.remove("hidden");

        // content
        projectTitle.classList.remove("hidden");

        // btn
        updateBtn.classList.remove("hidden");

        // status 편집
        statesBtn.addEventListener('click', dropDownEvent);

        // 상태 버튼 클릭 시
        const statusInput = document.querySelector("#status");
        const statusValueBtns = document.querySelectorAll(".statusValueBtn");
        statusValueBtns.forEach((btn, i) => {
            btn.addEventListener('click', (e) => {
                statusInput.value = document.getElementById(`statusValue${i + 1}`).dataset.status;
                console.log(statusInput.value);

                statesBtn.innerHTML = statusValueBtns[i].innerHTML;
                dropDown.classList.add("hidden");
            });
        });
    });
};



window.addEventListener('DOMContentLoaded', () => {
    lisColorChange();
    pageEvent();
    editBtnEvent();
    updateBtnEvent();
});

/**
 * page1에 대한 변수
 */
const pageEvent = () => {
    document.querySelectorAll(".ownerPageNumber").forEach((btn) => {
        btn.addEventListener('click', (e) => {
            const pageNumber2 = document.querySelector("#selectBtnEmpPage").value;
            const button = e.target;
            const { pageNumber} = button.dataset;
            let size = 5;
            let url = `/WorksOn/project/projectDetail.do?id=${document.querySelector("#projectId").value}&continue&page1=${pageNumber}&size1=${size}&page2=${pageNumber2}&size2=${size}`;

            console.log(url);

            // Fetch API를 사용하여 비동기로 데이터 가져오기
            fetch(url)
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.text();
                })
                .then(html => {
                    // 서버로부터 받은 HTML을 현재 페이지의 콘텐츠에 적용
                    let parser = new DOMParser();
                    let newDocument = parser.parseFromString(html, 'text/html');

                    const newSidebar = newDocument.querySelector("#sidebarProject");
                    const originSidebar = document.querySelector("#sidebarProject");

                    console.log(newDocument);
                    originSidebar.innerHTML = newSidebar.innerHTML;
                    lisColorChange();
                    pageEvent();
                })
                .catch(error => {
                    console.error('Fetch error:', error);
                });
        });
    });
};

const lisColorChange = () => {
    document.querySelectorAll(".articleLi").forEach((article) => {
        article.addEventListener('click', (e) => {
            const project = e.target;
            const { projectId : id } = article.dataset;
            location.href = `${contextPath}project/projectDetail.do?id=${id}`;
        });

        // console.log(window.location);
        const url = window.location.search;
        if(url === `?id=${article.dataset.projectId}` || url === `?id=${article.dataset.projectId}&continue`){
            article.classList.add("bg-gray-100");
        }
        else {
            article.classList.remove("bg-gray-100");
        }
    });
}



const formattedCreatedAt = (createdAt) => {
    const date = new Date(createdAt);
    return `${date.getFullYear()}-${(date.getMonth() + 1).toString().padStart(2, '0')}-${date.getDate().toString().padStart(2, '0')}`;
}
const findDot = (src) => {
    return src.split(".").pop();
}

const applyFileImg = (ext, src) => {
    const lowerExt = ext.toLowerCase();

    let html = '';
    switch (lowerExt) {
        case "jpeg" : html = `<img class="object-cover w-full" src="${src}" alt="">`; break;
        case "jpg" : html = `<img class="object-cover w-full" src="${src}" alt="">`; break;
        case "png" : html = `<img class="object-cover w-full" src="${src}" alt="">`; break;
        case "svg" : html = `<img class="object-cover w-full" src="${src}" alt="">`; break;
        case "bmp" : html = `<img class="object-cover w-full" src="${src}" alt="">`; break;
        case "txt" : html = `<img class="object-cover w-2/3" src="https://bucket-minjeong2024.s3.ap-northeast-2.amazonaws.com/free-icon-txt-file-format-5719989.png" alt="">`; break;
        case "xlsx" : html = `<img class="object-cover w-2/3" src="https://bucket-minjeong2024.s3.ap-northeast-2.amazonaws.com/free-icon-xlsx-file-format-5720013.png" alt="">`; break;
        case "rar" : html = `<img class="object-cover w-2/3" src="https://bucket-minjeong2024.s3.ap-northeast-2.amazonaws.com/free-icon-rar-file-format-5719948.png" alt="">`; break;
        case "pptx" : html = `<img class="object-cover w-2/3" src="https://bucket-minjeong2024.s3.ap-northeast-2.amazonaws.com/free-icon-pptx-file-format-5719937.png" alt="">`; break;
        case "iso" : html = `<img class="object-cover w-2/3" src="https://bucket-minjeong2024.s3.ap-northeast-2.amazonaws.com/free-icon-iso-file-format-5719803.png" alt="">`; break;
        case "html" : html = `<img class="object-cover w-2/3" src="https://bucket-minjeong2024.s3.ap-northeast-2.amazonaws.com/free-icon-html-file-format-5719779.png" alt="">`; break;
        case "docx" : html = `<img class="object-cover w-2/3" src="https://bucket-minjeong2024.s3.ap-northeast-2.amazonaws.com/free-icon-docx-file-format-5719745.png" alt="">`; break;
        case "zip" : html = `<img class="object-cover w-2/3" src="https://bucket-minjeong2024.s3.ap-northeast-2.amazonaws.com/free-icon-zip-file-format-5721939.png" alt="">`; break;
    }
    return html;
}





document.getElementById("attachUploadFrmBtn").addEventListener('click', (e) => {
    const frm = document.attachUploadFrm;
    const input = frm.querySelector("#upload");
    const ul = document.querySelector("#files ul");
    const lis = ul.querySelectorAll("li");
    const tbody = document.querySelector("#fileArea tbody");

    console.log(input.files);

    console.log(lis.length)

    const frmData = new FormData(frm);

    $.ajax({
        url: `${contextPath}project/uploadAttachment.do`,
        headers: {
            [csrfHeaderName]: csrfToken
        },
        data: frmData,
        processData: false,
        contentType: false,
        method: 'post',
        success(response) {
            console.log(response);

            let html = '';
            const now = new Date();
            response.forEach((attach, i) => {
                const preview = applyFileImg(findDot(attach.originalFilename), attach.url);

                html += `
                <li class="w-fit h-fit bg-gray-100 hover:bg-blue-100 rounded-lg mb-2 mr-2 attaches cursor-pointer"
                    onclick="attachDownload(${lis.length + i});" data-attach-id="${attach.id}" data-attach-url="${attach.url}" data-file-name="${attach.originalFilename}"
                    id="attach${lis.length + i}">
                    <div class="p-2 w-fit overflow-hidden">
                        <h1 class="w-40 h-8 font-bold text-md m-1 mb-2 overflow-hidden">${attach.originalFilename}</h1>
                        <div class="w-40 h-40 m-1 flex items-center justify-center bg-white overflow-hidden">
                            ${preview}
                        </div>
                        <div>
                            <p class="w-40 text-sm m-1">${attach.empName}</p>
                            <p class="w-40 text-sm m-1">${formattedCreatedAt(now)}</p>
                        </div>
                    </div>
                </li>`;
            });

            setTimeout(() => {
                frm.reset();
                tbody.innerHTML = '';
                const close = document.querySelector("#closeBtn");

                ul.insertAdjacentHTML("afterbegin", html);

                alert("파일 업로드가 완료되었습니다.");
                close.click();

            }, 1000);

        }
    });

});

document.querySelector("#upload").addEventListener('change', (e) => {
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
           <td class="px-2 py-2">
               <div>
                    <svg xmlns="http://www.w3.org/2000/svg" width="100%" height="100%" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-x cursor-pointer hover:text-red-400 rounded-full w-4 h-4 ml-2">
                        <line x1="18" y1="6" x2="6" y2="18"></line>
                        <line x1="6" y1="6" x2="18" y2="18"></line>
                    </svg>
                </div>
            </td>
       </tr>`;
        });
    }
});


const attachDownload = (i) => {
    const attachment = document.getElementById(`attach${i}`);
    const {attachId, attachUrl, originalFileName} = attachment.dataset;
    console.dir(attachment);

    // 파일을 다운로드할 URL
    let fileUrl = attachUrl;

    // 동적으로 <a> 태그 생성
    let downloadLink = document.createElement('a');
    downloadLink.href = fileUrl;
    downloadLink.download = originalFileName; // 다운로드되는 파일의 이름

    // downloadLink를 클릭하여 파일 다운로드
    window.open(fileUrl, '_blank');
    // document.body.appendChild(downloadLink);
    // downloadLink.click();
    // document.body.removeChild(downloadLink);

};
document.querySelector("#files-tab").addEventListener('click', (e) => {
   const { projectId } = e.target.dataset;
   const ul = document.querySelector("#files ul");

   $.ajax({
       url: `${contextPath}project/projectAttachmentList.do`,
       data: {
           projectId: projectId
       },
       method: 'get',
       success(response) {
           console.log(response);
           ul.innerHTML = '';

           response.forEach((attach, i) => {
               const { id, originalFileName, employee, createdAt, url } = attach;
              ul.innerHTML += `
                <li class="w-fit h-fit bg-gray-100 rounded-lg hover:bg-blue-100 mb-2 mr-2 attaches cursor-pointer"
                    onclick="attachDownload(${i});" data-attach-id="${id}" data-attach-url="${url}" data-file-name="${originalFileName}"
                    id="attach${i}">
                    <div class="p-2 w-fit overflow-hidden">
                        <h1 class="w-40 h-8 font-bold text-md m-1 mb-2 overflow-hidden">${originalFileName}</h1>
                        <div class="w-40 h-40 m-1 flex items-center justify-center bg-white overflow-hidden">
                            ${applyFileImg(findDot(originalFileName), url)}
                        </div>
                        <div>
                            <p class="w-40 text-sm m-1">${employee.name}</p>
                            <p class="w-40 text-sm m-1">${formattedCreatedAt(createdAt)}</p>
                        </div>
                    </div>
                </li>`;
           });
       }
   })
});

document.querySelector("#employees-tab").addEventListener('click', (e) => {
    console.log(e.target);


    const {projectId} = e.target.dataset;
    const tbody = document.querySelector("#employees tbody");

    $.ajax({
        url: `${contextPath}project/projectEmployeeList.do`,
        data: {
            projectId: projectId
        },
        method: 'get',
        success(response){
            console.log(response);
            tbody.innerHTML = '';

            response.forEach((emp, i) => {
                const {employee, role} = emp;

                tbody.innerHTML += `
                <tr>
                    <td class="px-4 py-3 text-sm">
                        ${i + 1}
                    </td>
                    <td class="px-4 py-3">
                        <div class="flex items-center text-sm">
                            <!-- Avatar with inset shadow -->
                            <div class="relative hidden w-8 h-8 mr-3 rounded-full md:block">
                                <img
                                    class="object-cover w-full h-full rounded-full"
                                    src="${employee.profileUrl != null ? employee.profileUrl : 'https://bucket-minjeong2024.s3.ap-northeast-2.amazonaws.com/profile.png'}"
                                    alt=""
                                    loading="lazy"
                                />
                                <div class="absolute inset-0 rounded-full shadow-inner" aria-hidden="true"></div>
                            </div>
                            <div>
                                <p class="font-semibold">${employee.name}</p>
                            </div>
                        </div>
                    </td>
                    <td class="px-4 py-3 text-sm">
                        ${employee.position.name}
                    </td>
                    <td class="px-4 py-3 text-sm">
                        ${employee.department.name}
                    </td>
                    <td class="px-4 py-3 text-sm">
                        ${employee.email}
                    </td>
                    <td class="px-4 py-3 text-xs">
                        <span
                                class="px-2 py-1 font-semibold leading-tight text-blue-700 bg-blue-100 rounded-full"
                        >
                          ${role === 'CREATE' ? '편집가능' : '조회가능'}
                        </span>
                    </td>
                </tr>
                `;
            });
        }
    })
});