/**
 * 체크박스 1개만 선택하게 하기
 */
function checkOnlyOne(element){
    const checkboxes = document.getElementsByName("dept");
    checkboxes.forEach((cb) => {
        cb.checked = false;
    })
    element.checked = true;
}
function checkOnlyOne1(element){
    const checkboxes = document.getElementsByName("position");
    checkboxes.forEach((cb) => {
        cb.checked = false;
    })
    element.checked = true;
}

// /**
//  *  비밀번호
//  *
//  */
// document.querySelector("#confirmPassword").onblur = (e) => {
//     const password = document.querySelector("#password");
//     const passwordConfirmation = e.target;
//     if (password.value !== passwordConfirmation.value) {
//         alert("패스워드가 일치하지 않습니다.");
//         password.select();
//     }
// };



document.employeeCreateFrm.onsubmit = (e) =>{
    const frm = e.target;
    const email = frm.email;
    const emailDuplicateCheck = frm.emailDuplicateCheck
    // const password = frm.password;
    const passwordConfirmation = frm.confirmPassword;
    const name = frm.name;

    if(emailDuplicateCheck.value == 0){
        alert("사용가능한 이메일 입력해주세요.");
        email.select();
        return false;
    }

    // 비밀번호 일치 검사
    // if (password.value !== passwordConfirmation.value) {
    //     alert("패스워드가 일치하지 않습니다.");
    //     password.select();
    //     return false;
    // }

    // 이름 형식 검사
    if (!/^[\w가-힣]{2,}$/.test(name.value)) {
        alert("이름을 2글자 이상 입력하세요.");
        name.select();
        console.log(!/^[\w가-힣]{2,}$/.test(name.value))
        return false;
    }


    // 이메일 형식 검사
    if (!/^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/.test(email.value)) {
        alert("유효한 이메일을 작성해주세요.");
        email.select();
        return false;
    }

};


/**
 *  이메일중복검사
 */

document.querySelector("#email").onkeyup = (e) => {
    const email = e.target;
    const guideOk = document.querySelector(".guide.ok");
    const guideError = document.querySelector(".guide.error");
    const emailDuplicateCheck = document.querySelector("#emailDuplicateCheck");
    console.log(email)
    console.log(guideOk)
    console.log(guideError)
    console.log(emailDuplicateCheck)



    // 이메일 형식 유효성 검사 정규 표현식 수정
    if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email.value.trim())){
        guideError.style.display = "none";
        guideOk.style.display = "none";
        emailDuplicateCheck.value = 0;
        return;

    }


    // AJAX 요청을 이벤트 핸들러 안으로 이동
    $.ajax({
        url : `${contextPath}employee/checkEmailDuplicate.do`,
        method : 'post',
        headers:{
            [csrfHeaderName] : csrfToken
        },
        data : {
            email : email.value.trim()
        },
        success(response) {
            console.log(response); // {"available" : true}
            const {available} = response;
            if(available){
                guideError.style.display = "none";
                guideOk.style.display = "inline";
                emailDuplicateCheck.value = 1;
            }
            else{
                guideError.style.display = "inline";
                guideOk.style.display = "none";
                emailDuplicateCheck.value = 0;
            }
        }
    });
};

// 저장 버튼 클릭 시 메일 발송
document.getElementById('submitButton').addEventListener('click', function () {
    sendMail();
});

function sendMail() {
    const to = document.getElementById('to').value;
    const cc = document.getElementById('cc').value;
    const subject = document.getElementById('subject').value;
    const body = document.getElementById('body').value;
    const fileInput = document.getElementById('file');

    const formData = new FormData();
    formData.append('to', to);
    formData.append('cc', cc);
    formData.append('subject', subject);
    formData.append('body', body);

    if (fileInput.files.length > 0) {
        for (let i = 0; i < fileInput.files.length; i++) {
            formData.append('file', fileInput.files[i]);
        }
    }

    const xhr = new XMLHttpRequest();
    xhr.open('POST', '/mail/send', true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                alert('메일이 발송되었습니다.');
            } else {
                alert('메일 발송에 실패했습니다.');
            }
        }
    };
    xhr.send(formData);
}
