document.querySelector("#employees-tab").addEventListener('click', (e) => {
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