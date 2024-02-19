document.addEventListener('DOMContentLoaded', function () {
    const radioButtons = document.querySelectorAll('input[type="radio"][name="approval-form"]');

    radioButtons.forEach(function (radioButton) {
        radioButton.addEventListener('change', function () {
            // 라디오 버튼이 변경될 때 다른 그룹의 내용을 초기화
            resetOtherForms(radioButton);

            const formType = radioButton.id;

            switch (formType) {
                case 'create-form-leave-input':
                    handleLeaveForm();
                    break;
                case 'create-form-equipment-input':
                    handleEquipmentForm();
                    break;
                case 'create-form-cooperation-input':
                    handleCooperationForm();
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
    function handleLeaveForm() {
        const createLeaveForm = document.getElementById('create-form');
        createLeaveForm.innerHTML = ''; // 초기화

        // 동적으로 내용을 추가하거나 AJAX 호출을 수행할 수 있습니다.
        createLeaveForm.innerHTML = '<div class="container mx-auto p-8 bg-white border mt-10">\n' +
            '    <h1 class="text-3xl text-center mb-4">연차 신청서</h1>\n' +
            '    <div class="flex">\n' +
            '        <table class="w-20% mb-6">\n' +
            '            <tr class="border">\n' +
            '                <th class="border p-2">기안자</th>\n' +
            '                <td><input type="text" class="w-20% p-2 border " value="오우진" readonly></td>\n' +
            '            </tr>\n' +
            '            <tr class="border">\n' +
            '                <th class="border p-2">소속</th>\n' +
            '                <td><input type="text" class="w-20% p-2 border " value="esc" readonly></td>\n' +
            '            </tr>\n' +
            '            <tr class="border">\n' +
            '                <th class="border p-2">기안일</th>\n' +
            '                <td><input type="text" class="w-20% p-2 border " value="2024-02-26(화)" readonly></td>\n' +
            '            </tr>\n' +
            '            <tr class="border">\n' +
            '                <th class="border p-2">문서번호</th>\n' +
            '                <td><input type="text" class="w-20% p-2 border " value="1" readonly></td>\n' +
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
            '                <!-- Dropdown 버튼 -->\n' +
            '                <div class="relative inline-block text-left">\n' +
            '                    <button id="dropdownButton" type="button" class="border-2 border-blue-700 px-4 py-2 rounded-md focus:outline-none focus:ring focus:border-blue-300">\n' +
            '                        연차\n' +
            '                    </button>\n' +
            '\n' +
            '                    <!-- Dropdown 메뉴 -->\n' +
            '                    <div class="absolute mt-1 w-16 bg-white border rounded-md shadow-lg hidden">\n' +
            '                        <div class="py-1">\n' +
            '                            <a href="#" class="block px-4 py-2 text-gray-800 hover:bg-gray-300 text-xs">연차</a>\n' +
            '                            <a href="#" class="block px-4 py-2 text-gray-800 hover:bg-gray-300 text-xs">반차</a>\n' +
            '                        </div>\n' +
            '                    </div>\n' +
            '                </div>\n' +
            '            </td>\n' +
            '        </tr>\n' +
            '        <tr class="border">\n' +
            '            <th class="border p-2 w-32">기간 및 일시</th>\n' +
            '            <td>달력2024-02-06 ~ 달력2024-02-06 사용일수: 1</td>\n' +
            '        </tr>\n' +
            '        <tr class="border">\n' +
            '            <th class="border p-2 w-32">반차 여부</th>\n' +
            '            <td><input type="checkbox" class="w-12 p-2 border">반차여부(체크시 기간 및 일시가 같은 날일때 0.5로 바뀜)</td>\n' +
            '        </tr>\n' +
            '        <tr class="border">\n' +
            '            <th class="border p-2 w-32">연차 일수</th>\n' +
            '            <td>\n' +
            '                잔여연차 : <input type="text" class="w-24 p-2 border text-right" value="15" readonly>\n' +
            '                신청연차 : <input type="text" class="w-24 p-2 border text-right" value="1" readonly>\n' +
            '            </td>\n' +
            '        </tr>\n' +
            '        <tr class="border">\n' +
            '            <th class="border p-2 w-32">휴가 사유</th>\n' +
            '            <td><input type="text" class="w-full p-2 border" value="여기는 에디터 안씁니다."></td>\n' +
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
    }

    function handleEquipmentForm() {
        const createEquipmentForm = document.getElementById('create-form');
        createEquipmentForm.innerHTML = ''; // 초기화

        // 동적으로 내용을 추가하거나 AJAX 호출을 수행할 수 있습니다.
        createEquipmentForm.innerHTML = '<div class="container mx-auto p-8 bg-white border mt-10">\n' +
            '    <h1 class="text-3xl text-center mb-4">비품 신청서</h1>\n' +
            '    <div class="flex">\n' +
            '        <table class="w-20% mb-6">\n' +
            '            <tr class="border">\n' +
            '                <th class="border p-2">기안자</th>\n' +
            '                <td><input type="text" class="w-20% p-2 border " value="오우진" readonly></td>\n' +
            '            </tr>\n' +
            '            <tr class="border">\n' +
            '                <th class="border p-2">소속</th>\n' +
            '                <td><input type="text" class="w-20% p-2 border " value="esc" readonly></td>\n' +
            '            </tr>\n' +
            '            <tr class="border">\n' +
            '                <th class="border p-2">기안일</th>\n' +
            '                <td><input type="text" class="w-20% p-2 border " value="2024-02-26(화)" readonly></td>\n' +
            '            </tr>\n' +
            '            <tr class="border">\n' +
            '                <th class="border p-2">문서번호</th>\n' +
            '                <td><input type="text" class="w-20% p-2 border " value="1" readonly></td>\n' +
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
            '                <th class="border px-4 py-2">금액</th>\n' +
            '                <th class="border px-4 py-2">용도</th>\n' +
            '            </tr>\n' +
            '        </tr>\n' +
            '        <tr>\n' +
            '            <td><input type="text" class="border px-4 py-2 w-full" value="품명1"></td>\n' +
            '            <td><input type="text" class="border px-4 py-2 w-full" value="5"></td>\n' +
            '            <td><input type="text" class="border px-4 py-2 w-full" value="10,000원"></td>\n' +
            '            <td><input type="text" class="border px-4 py-2 w-full" value="50,000원"></td>\n' +
            '            <td><input type="text" class="border px-4 py-2 w-full" value="용도1"></td>\n' +
            '        </tr>\n' +
            '        <tr>\n' +
            '            <td><input type="text" class="border px-4 py-2 w-full" value="품명2"></td>\n' +
            '            <td><input type="text" class="border px-4 py-2 w-full" value="4"></td>\n' +
            '            <td><input type="text" class="border px-4 py-2 w-full" value="10,000원"></td>\n' +
            '            <td><input type="text" class="border px-4 py-2 w-full" value="40,000원"></td>\n' +
            '            <td><input type="text" class="border px-4 py-2 w-full" value="용도2"></td>\n' +
            '        </tr>\n' +
            '        <tr>\n' +
            '            <td><input type="text" class="border px-4 py-2 w-full" value="품명3"></td>\n' +
            '            <td><input type="text" class="border px-4 py-2 w-full" value="3"></td>\n' +
            '            <td><input type="text" class="border px-4 py-2 w-full" value="10,000원"></td>\n' +
            '            <td><input type="text" class="border px-4 py-2 w-full" value="30,000원"></td>\n' +
            '            <td><input type="text" class="border px-4 py-2 w-full" value="용도3"></td>\n' +
            '        </tr>\n' +
            '        <tr>\n' +
            '            <td><input type="text" class="border px-4 py-2 w-full" value="품명4"></td>\n' +
            '            <td><input type="text" class="border px-4 py-2 w-full" value="2"></td>\n' +
            '            <td><input type="text" class="border px-4 py-2 w-full" value="10,000원"></td>\n' +
            '            <td><input type="text" class="border px-4 py-2 w-full" value="20,000원"></td>\n' +
            '            <td><input type="text" class="border px-4 py-2 w-full" value="용도4"></td>\n' +
            '        </tr>\n' +
            '        <tr>\n' +
            '            <td><input type="text" class="border px-4 py-2 w-full" value="품명5"></td>\n' +
            '            <td><input type="text" class="border px-4 py-2 w-full" value="1"></td>\n' +
            '            <td><input type="text" class="border px-4 py-2 w-full" value="10,000원"></td>\n' +
            '            <td><input type="text" class="border px-4 py-2 w-full" value="10,000원"></td>\n' +
            '            <td><input type="text" class="border px-4 py-2 w-full" value="용도5"></td>\n' +
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
    }

    function handleCooperationForm() {
        const createCooperationForm = document.getElementById('create-form');
        createCooperationForm.innerHTML = ''; // 초기화

        // 동적으로 내용을 추가하거나 AJAX 호출을 수행할 수 있습니다.
        createCooperationForm.innerHTML = '<div class="container mx-auto p-8 bg-white border mt-10">\n' +
            '    <h1 class="text-3xl text-center mb-4">업 무 협 조</h1>\n' +
            '    <div class="flex">\n' +
            '        <table class="w-20% mb-6">\n' +
            '            <tr class="border">\n' +
            '                <th class="border p-2">기안자</th>\n' +
            '                <td><input type="text" class="w-20% p-2 border " value="오우진" readonly></td>\n' +
            '            </tr>\n' +
            '            <tr class="border">\n' +
            '                <th class="border p-2">소속</th>\n' +
            '                <td><input type="text" class="w-20% p-2 border " value="esc" readonly></td>\n' +
            '            </tr>\n' +
            '            <tr class="border">\n' +
            '                <th class="border p-2">기안일</th>\n' +
            '                <td><input type="text" class="w-20% p-2 border " value="2024-02-26(화)" readonly></td>\n' +
            '            </tr>\n' +
            '            <tr class="border">\n' +
            '                <th class="border p-2">문서번호</th>\n' +
            '                <td><input type="text" class="w-20% p-2 border " value="1" readonly></td>\n' +
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
            '    </table>\n' +
            '\n' +
            '    <h2 class="p-2 w-32">파일 첨부</h2>\n' +
            '    <div class="border-dotted">\n' +
            '        <input type="file" class="custom-file-input mb-3" name="upFile" id="upFile" multiple placeholder="이곳에 파일을 드래그 하세요. 또는 파일선택">\n' +
            '    </div>\n' +
            '\n' +
            '    <button type="submit" class="bg-blue-500 text-white p-2 rounded">결재요청</button>\n' +
            '</div>';
    }
});