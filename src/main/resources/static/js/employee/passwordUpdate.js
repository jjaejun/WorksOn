document.querySelector("#confirmPassword").onblur = (e) => {
    const password = document.querySelector("#password");
    const passwordConfirmation = e.target;
    if (password.value !== passwordConfirmation.value) {
        alert("패스워드가 일치하지 않습니다.");
        password.select();
    }
};