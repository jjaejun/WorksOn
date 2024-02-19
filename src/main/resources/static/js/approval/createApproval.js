

document.addEventListener('DOMContentLoaded', function () {
    const radioButtons = document.querySelectorAll('input[type="radio"][name="approval-form"]');
    const today = new Date(); // 현재 날짜로 초기화

    // 로그인한 사용자 정보
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
            '                <td><input type="text" class="w-20% p-2 border " value="'+ deptName +'" readonly></td>\n' +
            '            </tr>\n' +
            '            <tr class="border">\n' +
            '                <th class="border p-2">기안일</th>\n' +
            '                <td><input type="date" class="w-20% p-2 border" id="draft_date"></td>\n' +
            '            </tr>\n' +
            '        </table>\n' +
            '        <table class="mb-6 ml-auto mr-1">\n' +
            '            <tr class="border">\n' +
            '                <th class="border p-2 w-10" rowspan="4">승인</th>\n' +
            '                <tr class="border">\n' +
            '                    <td><input type="text" class="w-24 p-2" value="팀장" readonly></td>\n' +
            '                </tr>\n' +
            '                <tr class="border">\n' +
            '                    <td><input type="text" class="w-24 p-2" value="이민정" readonly></td>\n' +
            '                </tr>\n' +
            '                <tr class="border">\n' +
            '                    <td><input type="text" class="w-24 p-2" value="사인" readonly></td>\n' +
            '                </tr>\n' +
            '            </tr>\n' +
            '        </table>\n' +
            '        <table class="mb-6 mr-1">\n' +
            '            <tr class="border">\n' +
            '                <th class="border p-2 w-10" rowspan="4">승인</th>\n' +
            '                <tr class="border">\n' +
            '                    <td><input type="text" class="w-24 p-2" value="팀원" readonly></td>\n' +
            '                </tr>\n' +
            '                <tr class="border">\n' +
            '                    <td><input type="text" class="w-24 p-2" value="천무진" readonly></td>\n' +
            '                </tr>\n' +
            '                <tr class="border">\n' +
            '                    <td><input type="text" class="w-24 p-2" value="사인" readonly></td>\n' +
            '                </tr>\n' +
            '            </tr>\n' +
            '        </table>\n' +
            '        <table class="mb-6">\n' +
            '            <tr class="border">\n' +
            '                <th class="border p-2 w-10" rowspan="4">승인</th>\n' +
            '                <tr class="border">\n' +
            '                    <td><input type="text" class="w-24 p-2" value="팀원" readonly></td>\n' +
            '                </tr>\n' +
            '                <tr class="border">\n' +
            '                    <td><input type="text" class="w-24 p-2" value="한준희" readonly></td>\n' +
            '                </tr>\n' +
            '                <tr class="border">\n' +
            '                    <td><input type="text" class="w-24 p-2" value="사인" readonly></td>\n' +
            '                </tr>\n' +
            '            </tr>\n' +
            '        </table>\n' +
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
            '        <table class="mb-6 ml-auto mr-1">\n' +
            '            <tr class="border">\n' +
            '                <th class="border p-2 w-10" rowspan="4">승인</th>\n' +
            '                <tr class="border">\n' +
            '                    <td><input type="text" class="w-24 p-2" value="총무과" readonly></td>\n' +
            '                </tr>\n' +
            '                <tr class="border">\n' +
            '                    <td><input type="text" class="w-24 p-2" value="성민준" readonly></td>\n' +
            '                </tr>\n' +
            '                <tr class="border">\n' +
            '                    <td><input type="text" class="w-24 p-2" value="사인" readonly></td>\n' +
            '                </tr>\n' +
            '            </tr>\n' +
            '        </table>\n' +
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
        createCooperationForm.innerHTML = '<div class="container mx-auto p-8 bg-white border mt-10">\n' +
            '    <h1 class="text-3xl text-center mb-4">업 무 협 조</h1>\n' +
            '    <div class="flex">\n' +
            '        <table class="w-20% mb-6">\n' +
            '            <tr class="border">\n' +
            '                <th class="border p-2">기안자</th>\n' +
            '                <td><input type="text" class="w-20% p-2 border" value="'+ empName +'" readonly></td>\n' +
            '            </tr>\n' +
            '            <tr class="border">\n' +
            '                <th class="border p-2">소속</th>\n' +
            '                <td><input type="text" class="w-20% p-2 border" value="' + deptName + '" readonly></td>\n' +
            '            </tr>\n' +
            '            <tr class="border">\n' +
            '                <th class="border p-2">기안일</th>\n' +
            '                <td><input type="date" class="w-20% p-2 border" id="draft_date"></td>\n' +
            '            </tr>\n' +
            '        </table>\n' +
            '        <table class="mb-6 ml-auto mr-1">\n' +
            '            <tr class="border">\n' +
            '                <th class="border p-2 w-10" rowspan="4">승인</th>\n' +
            '                <tr class="border">\n' +
            '                    <td><input type="text" class="w-24 p-2" value="팀장" readonly></td>\n' +
            '                </tr>\n' +
            '                <tr class="border">\n' +
            '                    <td><input type="text" class="w-24 p-2" value="이민정" readonly></td>\n' +
            '                </tr>\n' +
            '                <tr class="border">\n' +
            '                    <td><input type="text" class="w-24 p-2" value="사인" readonly></td>\n' +
            '                </tr>\n' +
            '            </tr>\n' +
            '        </table>\n' +
            '        <table class="mb-6 mr-1">\n' +
            '            <tr class="border">\n' +
            '                <th class="border p-2 w-10" rowspan="4">승인</th>\n' +
            '                <tr class="border">\n' +
            '                    <td><input type="text" class="w-24 p-2" value="팀원" readonly></td>\n' +
            '                </tr>\n' +
            '                <tr class="border">\n' +
            '                    <td><input type="text" class="w-24 p-2" value="천무진" readonly></td>\n' +
            '                </tr>\n' +
            '                <tr class="border">\n' +
            '                    <td><input type="text" class="w-24 p-2" value="사인" readonly></td>\n' +
            '                </tr>\n' +
            '            </tr>\n' +
            '        </table>\n' +
            '        <table class="mb-6">\n' +
            '            <tr class="border">\n' +
            '                <th class="border p-2 w-10" rowspan="4">승인</th>\n' +
            '                <tr class="border">\n' +
            '                    <td><input type="text" class="w-24 p-2" value="팀원" readonly></td>\n' +
            '                </tr>\n' +
            '                <tr class="border">\n' +
            '                    <td><input type="text" class="w-24 p-2" value="한준희" readonly></td>\n' +
            '                </tr>\n' +
            '                <tr class="border">\n' +
            '                    <td><input type="text" class="w-24 p-2" value="사인" readonly></td>\n' +
            '                </tr>\n' +
            '            </tr>\n' +
            '        </table>\n' +
            '    </div>\n' +
            '\n' +
            '    <table class="w-full mb-6">\n' +
            '        <tr class="border">\n' +
            '            <th class="border p-2 w-32">수신처</th>\n' +
            '            <td><input type="text" class="w-full p-2 border " placeholder="수신처"></td>\n' +
            '        </tr>\n' +
            '        <tr class="border">\n' +
            '            <th class="border p-2 w-32">제목</th>\n' +
            '            <td><input type="text" class="w-full p-2 border " placeholder="제목"></td>\n' +
            '        </tr>\n' +
            '        <tr class="border">\n' +
            '            <th class="border p-2 w-32">내용</th>\n' +
            '            <td><input type="text" class="w-full p-2 border " placeholder="내용"></td>\n' +
            '        </tr>\n' +
            '        <tr class="border">\n' +
            '           <th class="border p-2 w-32">기간 및 일시</th>\n' +
            '           <td><input type="date" class="w-15% p-2 border" id="start_date">&nbsp ~ &nbsp<input type="date" class="w-20% p-2 border" id="end_date"></td>\n' +
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


});


// document.addEventListener('DOMContentLoaded', function () {
//     const approvalFormRadioButtons = document.querySelectorAll('input[name="approval-form"]');
//     const selectApproversButton = document.querySelector('#create-form');
//
//     selectApproversButton.addEventListener('click', function () {
//         let isApprovalFormSelected = false;
//
//         approvalFormRadioButtons.forEach(function (radioButton) {
//             if (radioButton.checked) {
//                 isApprovalFormSelected = true;
//             }
//         });
//
//         if (isApprovalFormSelected) {
//             // 여기에 결재자 선택을 위한 로직 추가
//             alert('결재자를 선택할 수 있습니다.');
//         } else {
//             alert('먼저 결재 양식을 선택하세요.');
//         }
//     });
// });