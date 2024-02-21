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