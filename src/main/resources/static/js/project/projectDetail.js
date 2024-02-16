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