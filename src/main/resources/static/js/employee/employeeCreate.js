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

/**
 *  비밀번호
 *
 */
document.querySelector("#confirmPassword").onblur = (e) => {
    const password = document.querySelector("#password");
    const passwordConfirmation = e.target;
    if (password.value !== passwordConfirmation.value) {
        alert("패스워드가 일치하지 않습니다.");
        password.select();
    }
};



document.employeeCreateFrm.onsubmit = (e) =>{
    const frm = e.target;
    const email = frm.email;
    const emailDuplicateCheck = frm.emailDuplicateCheck
    const password = frm.password;
    const passwordConfirmation = frm.confirmPassword;
    const name = frm.name;

    if(emailDuplicateCheck.value == 0){
        alert("사용가능한 이메일 입력해주세요.");
        email.select();
        return false;
    }

    // 비밀번호 일치 검사
    if (password.value !== passwordConfirmation.value) {
        alert("패스워드가 일치하지 않습니다.");
        password.select();
        return false;
    }

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
