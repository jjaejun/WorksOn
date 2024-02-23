

document.addEventListener('DOMContentLoaded', function () {
    const radioButtons = document.querySelectorAll('input[type="radio"][name="approval-form"]');
    const approvalButtons = document.querySelectorAll('input[type="checkbox"][name="helper-radio"]')

    approvalButtons.forEach(function (button) {
        button.addEventListener('click', function () {
            if (button.checked) {
                handleApprovalSelection();
            }
        });
    });

    const today = new Date(); // 현재 날짜로 초기화

    // 로그인한 사용자 정보
    const empIdElement = document.getElementById('empId');
    const empId = empIdElement.getAttribute('data-emp-id');

    const empNameElement = document.getElementById('empName');
    const empName = empNameElement.getAttribute('data-emp-name');

    const deptNameElement = document.getElementById('deptName');
    const deptName = deptNameElement.getAttribute('data-dept-name');

    const restElement = document.getElementById('restCount');
    const restCount = parseInt(restElement.getAttribute('data-rest-count'));

    radioButtons.forEach(function (radioButton) {
        radioButton.addEventListener('change', function () {
            // 라디오 버튼이 변경될 때 다른 그룹의 내용을 초기화
            resetOtherForms(radioButton);

            const formType = radioButton.id;

            switch (formType) {
                case 'create-form-leave-input':
                    handleLeaveForm(empName, deptName, restCount);
                    break;
                case 'create-form-equipment-input':
                    handleEquipmentForm(empName, deptName);
                    break;
                case 'create-form-cooperation-input':
                    handleCooperationForm(empName, deptName);
                    break;
                // 추가적인 케이스가 필요한 경우 계속해서 추가할 수 있습니다.
            }
        });
    });

    // 다른 그룹의 내용을 초기화하는 함수
    function resetOtherForms(selectedRadioButton) {
        radioButtons.forEach(function (radioButton) {
            if (radioButton !== selectedRadioButton) {
                const formId = radioButton.id;
                const form = document.getElementById(`${formId}`);
                form.innerHTML = ''; // 다른 그룹의 내용 초기화
            }
        });
    }

    // 아래는 각 폼 타입에 따른 처리 함수입니다.
    function handleLeaveForm(empName, deptName, restCount) {
        const createLeaveForm = document.getElementById('create-form');

        createLeaveForm.innerHTML = ''; // 초기화

        // 연차 신청서
        createLeaveForm.innerHTML = '<div class="container mx-auto p-8 bg-white border mt-10">\n' +
            '    <h1 class="text-3xl text-center mb-4">연차 신청서</h1>\n' +
            '    <div class="flex">\n' +
            '        <table class="w-20% mb-6">\n' +
            '            <tr class="border">\n' +
            '                <th class="border p-2">기안자</th>\n' +
            '                <td><input type="text" class="w-20% p-2 border " value="' + empName + '" readonly></td>\n' +
            '            </tr>\n' +
            '            <tr class="border">\n' +
            '                <th class="border p-2">소속</th>\n' +
            '                <td><input type="text" class="w-20% p-2 border " value="' + deptName + '" readonly></td>\n' +
            '            </tr>\n' +
            '            <tr class="border">\n' +
            '                <th class="border p-2">기안일</th>\n' +
            '                <td><input type="date" class="w-20% p-2 border" id="draft_date"></td>\n' +
            '            </tr>\n' +
            '        </table>\n' +
            '    <div id="approval-section-container1" class="ml-auto">\n' +
            '     </div>\n' +
            '    </div>\n' +
            '\n' +
            '    <table class="w-full mb-6">\n' +
            '        <tr class="border">\n' +
            '            <th class="border p-2 w-32">휴가 종류</th>\n' +
            '            <td>\n' +
            '                <input type="text" class="w-20% p-2 border ">\n' +
            '            </td>\n' +
            '        </tr>\n' +
            '        <tr class="border">\n' +
            '            <th class="border p-2 w-32">기간 및 일시</th>\n' +
            '            <td><input type="date" class="w-15% p-2 border " id="start_date" name="start_date">&nbsp ~ &nbsp<input type="date" class="w-20% p-2 border" id="end_date" name="end_date"></td>\n' +
            '        </tr>\n' +
            '        <tr class="border">\n' +
            '            <th class="border p-2 w-32">반차 여부</th>\n' +
            '            <td><input type="checkbox" class="w-12 p-2 border" id="annul-check"><label for="annul-check" class="ml-2">반차여부(체크시 기간 및 일시가 같은 날일때 0.5로 바뀜)</label></td>\n' +
            '        </tr>\n' +
            '        <tr class="border">\n' +
            '            <th class="border p-2 w-32">연차 일수</th>\n' +
            '            <td>\n' +
            '                잔여연차 : <input type="text" class="w-24 p-2 border text-right" value="' + restCount + '" readonly>\n' +
            '                신청연차 : <input type="text" class="w-24 p-2 border text-right" id="duration" name="duration">\n' +
            '            </td>\n' +
            '        </tr>\n' +
            '        <tr class="border">\n' +
            '            <th class="border p-2 w-32">휴가 사유</th>\n' +
            '            <td><input type="text" class="w-full p-2 border"></td>\n' +
            '        </tr>\n' +
            '    </table>\n' +
            '\n' +
            '    <h2 class="p-2 w-32">파일 첨부</h2>\n' +
            '    <div class="border-dotted">\n' +
            '        <input type="file" class="custom-file-input mb-3" name="upFile" id="upFile" multiple placeholder="이곳에 파일을 드래그 하세요. 또는 파일선택">\n' +
            '    </div>\n' +
            '\n' +
            '    <button type="submit" class="bg-blue-500 text-white p-2 rounded">결재요청</button>\n' +
            '</div>';

        // 시작일과 종료일 입력 요소 가져오기
        const startDateInput = document.getElementById('start_date');
        const endDateInput = document.getElementById('end_date');
        const annulCheck = document.getElementById('annul-check');
        const draftDateInput = document.getElementById('draft_date');

        console.log(startDateInput);
        console.log(endDateInput);

        draftDateInput.addEventListener('change', function () {
            // 기안일이 변경될 때도 현재 날짜와 비교하여 처리
            if (new Date(draftDateInput.value) < today) {
                // 현재 날짜보다 이전일 경우 경고 메시지 출력 등의 처리
                alert('기안일은 현재 날짜 이전으로 선택할 수 없습니다.');
                draftDateInput.value = getCurrentDate();
            }
        });

        if (startDateInput && endDateInput && annulCheck) {
            startDateInput.addEventListener('change', function () {
                updateDuration();
            });

            endDateInput.addEventListener('change', function () {
                updateDuration();
            });

            annulCheck.addEventListener('change', function () {
                updateDuration();
            });
        }

        function updateDuration() {
            const startDate = new Date(startDateInput.value);
            const endDate = new Date(endDateInput.value);
            let duration = (endDate - startDate) / (1000 * 60 * 60 * 24); // 일수 계산

            if (annulCheck.checked) {
                duration += 0.5; // 반차 여부 체크 시 0.5 추가
            }

            // 기간 출력 업데이트
            const durationInput = document.querySelector('input[name="duration"]');
            durationInput.value = duration.toFixed(1);

            // // 신청연차 일수 업데이트
            // const requestedLeaveInput = document.querySelector('input[name="requested_leave"]');
            // requestedLeaveInput.value = duration.toFixed(1);

            if (startDate < today || endDate < startDate) {
                // 현재 날짜보다 이전일 경우 경고 메시지 출력 등의 처리
                alert('현재 날짜 이전의 날짜는 선택할 수 없습니다.');
                startDateInput.value = '';
                endDateInput.value = '';
            } else {
                // 현재 날짜 이후일 경우 정상적으로 업데이트
                const durationInput = document.querySelector('input[name="duration"]');
                durationInput.value = duration.toFixed(1);
            }

        }
    }


    function handleEquipmentForm(empName, deptName) {
        const createEquipmentForm = document.getElementById('create-form');
        createEquipmentForm.innerHTML = ''; // 초기화

        // 비품 신청서
        createEquipmentForm.innerHTML = '<div class="container mx-auto p-8 bg-white border mt-10">\n' +
            '    <h1 class="text-3xl text-center mb-4">비품 신청서</h1>\n' +
            '    <div class="flex">\n' +
            '        <table class="w-20% mb-6">\n' +
            '            <tr class="border">\n' +
            '                <th class="border p-2">기안자</th>\n' +
            '                <td><input type="text" class="w-20% p-2 border " value="' + empName + '" readonly></td>\n' +
            '            </tr>\n' +
            '            <tr class="border">\n' +
            '                <th class="border p-2">소속</th>\n' +
            '                <td><input type="text" class="w-20% p-2 border " value="' + deptName + '" readonly></td>\n' +
            '            </tr>\n' +
            '            <tr class="border">\n' +
            '                <th class="border p-2">기안일</th>\n' +
            '                <td><input type="date" class="w-20% p-2 border" id="draft_date"></td>\n' +
            '            </tr>\n' +
            '        </table>\n' +
            '    <div id="approval-section-container2" class="ml-auto">\n' +
            '     </div>\n' +
            '    </div>\n' +
            '\n' +
            '    <table class="w-full mb-6 flex">\n' +
            '        <tr class="border">\n' +
            '            <th class="border p-2 w-32">제목</th>\n' +
            '            <td colspan="6"><input type="text" class="w-full p-2 border " placeholder="제목"></td>\n' +
            '        </tr>\n' +
            '        <tr class="border">\n' +
            '            <th class="border p-2 w-32">내용</th>\n' +
            '            <td colspan="6"><input type="text" class="w-full p-2 border " placeholder="내용"></td>\n' +
            '        </tr>\n' +
            '        <tr>\n' +
            '            <tr>\n' +
            '                <th class="border px-4 py-2">품명</th>\n' +
            '                <th class="border px-4 py-2">수량</th>\n' +
            '                <th class="border px-4 py-2">단가</th>\n' +
            '                <th class="border px-4 py-2">용도</th>\n' +
            '            </tr>\n' +
            '        </tr>\n' +
            '        <tr>\n' +
            '            <td><input type="text" class="border px-4 py-2 w-full"></td>\n' +
            '            <td><input type="text" class="border px-4 py-2 w-full"></td>\n' +
            '            <td><input type="text" class="border px-4 py-2 w-full"></td>\n' +
            '            <td><input type="text" class="border px-4 py-2 w-full"></td>\n' +
            '        </tr>\n' +
            '        <tr class="border">\n' +
            '            <th class="border p-2 w-32">합계</th>\n' +
            '            <td colspan="6"><input type="text" class="w-full p-2 border ml-auto" placeholder="합계금액"></td>\n' +
            '        </tr>\n' +
            '    </table>\n' +
            '\n' +
            '    <h2 class="p-2 w-32">파일 첨부</h2>\n' +
            '    <div class="border-dotted">\n' +
            '        <input type="file" class="custom-file-input mb-3" name="upFile" id="upFile" multiple placeholder="이곳에 파일을 드래그 하세요. 또는 파일선택">\n' +
            '    </div>\n' +
            '\n' +
            '    <button type="submit" class="bg-blue-500 text-white p-2 rounded">결재 재출</button>\n' +
            '</div>';

        const draftDateInput = document.getElementById('draft_date');

        draftDateInput.addEventListener('change', function () {
            // 기안일이 변경될 때도 현재 날짜와 비교하여 처리
            if (new Date(draftDateInput.value) < today) {
                // 현재 날짜보다 이전일 경우 경고 메시지 출력 등의 처리
                alert('기안일은 현재 날짜 이전으로 선택할 수 없습니다.');
                draftDateInput.value = getCurrentDate();
            }
        });
    }

    function handleCooperationForm(empName, deptName) {
        const createCooperationForm = document.getElementById('create-form');
        createCooperationForm.innerHTML = ''; // 초기화

        // 업무협조 신청서
        createCooperationForm.innerHTML = '<form name="createCooperationFrm" method="post" >' +
            `<input type="hidden" name="_csrf" value="${csrfToken}">\n` +
            '<div class="container mx-auto p-8 bg-white border mt-10">\n' +
            '    <h1 class="text-3xl text-center mb-4">업 무 협 조</h1>\n' +
            '    <div class="flex">\n' +
            '        <table class="w-20% mb-6">\n' +
            '            <tr class="border">\n' +
            '                <th class="border p-2">기안자</th>' + `<input type="hidden" name="empId" value=${empId} />` +
            '                <td><input type="text" class="w-20% p-2 border" value="' + empName + '" readonly></td>\n' +
            '            </tr>\n' +
            '            <tr class="border">\n' +
            '                <th class="border p-2">소속</th>\n' +
            '                <td><input type="text" class="w-20% p-2 border" value="' + deptName + '" readonly></td>\n' +
            '            </tr>\n' +
            '            <tr class="border">\n' +
            '                <th class="border p-2">기안일</th>\n' +
            '                <td><input type="date" name="approvalEndDate" class="w-20% p-2 border" id="draft_date"></td>\n' +
            '            </tr>\n' +
            '        </table>\n' +
            '    <div id="approval-section-container" class="ml-auto">\n' +
            '     </div>\n' +
            '    </div>\n' +
            '\n' +
            '    <table class="w-full mb-6">\n' +
            '        <tr class="border">\n' +
            '            <th class="border p-2 w-32">수신처</th>\n' +
            '            <td><input type="text" name="cooperationDept" class="w-full p-2 border " placeholder="수신처"></td>\n' +
            '        </tr>\n' +
            '        <tr class="border">\n' +
            '            <th class="border p-2 w-32">제목</th>\n' +
            '            <td><input type="text" name="title" class="w-full p-2 border " placeholder="제목"></td>\n' +
            '        </tr>\n' +
            '        <tr class="border">\n' +
            '            <th class="border p-2 w-32">내용</th>\n' +
            '            <td><input type="text" name="content" class="w-full p-2 border " placeholder="내용"></td>\n' +
            '        </tr>\n' +
            '        <tr class="border">\n' +
            '           <th class="border p-2 w-32">기간 및 일시</th>\n' +
            '           <td><input type="date" name="startDate" class="w-15% p-2 border" id="start_date">&nbsp ~ &nbsp<input type="date" name="endDate" class="w-20% p-2 border" id="end_date"></td>\n' +
            '        </tr>\n' +
            '    </table>\n' +
            '\n' +
            '    <h2 class="p-2 w-32">파일 첨부</h2>\n' +
            '    <div class="border-dotted">\n' +
            '        <input type="file" class="custom-file-input mb-3" name="upFile" id="upFile" multiple placeholder="이곳에 파일을 드래그 하세요. 또는 파일선택">\n' +
            '    </div>\n' +
            '\n' +
            '    <button type="submit" class="bg-blue-500 text-white p-2 rounded">결재요청</button>\n' +
            '</div>' +
            '</form>';

        const draftDateInput = document.getElementById('draft_date');
        const startDateInput = document.getElementById('start_date');
        const endDateInput = document.getElementById('end_date');


        draftDateInput.addEventListener('change', function () {
            // 기안일이 변경될 때도 현재 날짜와 비교하여 처리
            if (new Date(draftDateInput.value) < today) {
                // 현재 날짜보다 이전일 경우 경고 메시지 출력 등의 처리
                alert('기안일은 현재 날짜 이전으로 선택할 수 없습니다.');
                draftDateInput.value = getCurrentDate();
            }
        });

        if (startDateInput && endDateInput) {
            startDateInput.addEventListener('change', function () {
                updateDuration();
            });

            endDateInput.addEventListener('change', function () {
                updateDuration();
            });

        }

        findCheckboxEmpId();
        frmSubmit();

        function updateDuration() {
            const startDate = new Date(startDateInput.value);
            const endDate = new Date(endDateInput.value);


            if (startDate < today || endDate < startDate) {
                // 현재 날짜보다 이전일 경우 경고 메시지 출력 등의 처리
                alert('현재 날짜 이전의 날짜는 선택할 수 없습니다.');
                startDateInput.value = '';
                endDateInput.value = '';
            }
        }
    }

    function getCurrentDate() {
        const now = new Date();
        const year = now.getFullYear();
        const month = String(now.getMonth() + 1).padStart(2, '0');
        const day = String(now.getDate()).padStart(2, '0');
        return `${year}-${month}-${day}`;
    }

    function handleApprovalSelection() {
        // const selectApprovals = getSelectedApprovals();
        //
        // const createLeaveForm = document.getElementById('create-form');
        // const approvalSection = createApprovalSection(selectApprovals);
        //
        // createLeaveForm.innerHTML += approvalSection;

        const createForm = document.getElementById('create-form');

        // 이전에 생성된 승인 섹션 제거
        const existingApprovalSection = createForm.querySelector('.approval-section');
        if (existingApprovalSection) {
            existingApprovalSection.remove();
        }

        const selectApprovals = getSelectedApprovals();

        // 새로운 승인 섹션 생성 및 추가
        const approvalSection = createApprovalSection(selectApprovals);
        createForm.innerHTML += approvalSection;
    }

    function getSelectedApprovals() {
        const selectedApprovals = [];

        // 체크된 체크박스들을 선택
        const checkboxes = document.querySelectorAll('input[name="helper-radio"]:checked');

        checkboxes.forEach(function (checkbox) {
            const empId = checkbox.value;
            const empName = checkbox.parentElement.querySelector('.check-approval').innerText; // 선택된 체크박스의 부모에서 이름 가져오기 (예시 기준)

            // console.log(empName);

            // 선택된 승인자 정보를 배열에 추가
            selectedApprovals.push({
                id: empId,
                name: empName
            });
        });

        return selectedApprovals;
    }


// 동적으로 HTML을 생성하는 함수 추가 (예시)
    function createApprovalSection(approvals) {
        let employeesNameElements = document.querySelectorAll('.checkInfo'); // 클래스 선택

        employeesNameElements.forEach(function (employeesNameElement) {
            let employeesName = employeesNameElement.getAttribute('data-employees-name');
            console.log(employeesName);

            let employeesPositionName = employeesNameElement.getAttribute('data-position-name');
            console.log(employeesPositionName);

            let html = '<div class="text-xl p-2 py-4 font-bold hover:text-[#000000] approval-section">';

            for (const approval of approvals) {
                // 여기서 approval 객체의 프로퍼티에 접근하여 HTML을 생성
                html += '<table class="mb-6 ml-auto mr-1">';
                html += '<tr class="border">';
                html += '<th class="border p-2 w-10" rowspan="4">승인</th>';
                html += '</tr>';
                html += '<tr class="border">';
                html += '<td><input type="text" class="w-24 p-2" value="' + approval.name + '" readonly></td>';
                html += '</tr>';
                html += '<tr class="border">';
                html += '<td><input type="text" class="w-24 p-2" value="' + approval.position + '" readonly></td>';
                html += '</tr>';
                html += '<tr class="border">';
                html += '<td><input type="text" class="w-24 p-2" value="사인" readonly></td>';
                html += '</tr>';
                html += '</table>';
            }

            html += '</div>';
            // 여기서 생성된 HTML을 해당 요소에 추가하거나 처리할 수 있습니다.
            return html;
        });
    }
});

// 새 결재 진행시 제출 코드(업무협조)
const frmSubmit = () => {
    document.createCooperationFrm.addEventListener('submit', (e) => {
        e.preventDefault();

        const frm = e.target;
        console.log(frm);

        console.log(frm.querySelectorAll('input'));

    });
};

// 새 결재 진행에서 넣어주는 함수(업무협조)
const findCheckboxEmpId = () => {
    const checkboxes = document.querySelectorAll('.check-approval').forEach((checkbox, i) => {
        checkbox.addEventListener('change', (e) => {
            const name = e.target;
            const area = document.querySelector('#approval-section-container');
            const {employeesName, positionName} = name.dataset;
            if (name.checked) {
                area.innerHTML += `<table class="mb-6 ml-auto mr-1">
                                    <tr class="border">
                                        <th class="border p-2 w-10" rowspan="4">승인</th>
                                    </tr>
                                    <tr class="border">
                                        <td><input type="hidden" class="w-24 p-2" readonly >${positionName}</td>
                                    </tr>
                                    <tr class="border">
                                        <td><input type="hidden" class="w-24 p-2" value="${name.value}" name="approverId" readonly >${employeesName}</td>
                                    </tr>
                                </table>`;
            }

            console.log(e.target)
        });
    });
};



// window.addEventListener('DOMContentLoaded', (e) => {
//
// });