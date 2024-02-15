document.employeeCreateFrm.onsubmit = (e) =>{
    const frm = e.target;
    const email = frm.email;
    const password = frm.password;
    const passwordConfirmation = frm.confirmPassword;
    const name = frm.name;


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
        return false;
    }

    // 이메일 형식 검사
    if (!/^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/.test(email.value)) {
        alert("유효한 이메일을 작성해주세요.");
        email.select();
        return false;
    }

};