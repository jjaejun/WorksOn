const regExpPwd = /^(?=.*?[a-zA-Z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
// input tag keyup 이벤트를 반복문을 통해 모두 적용
document.querySelectorAll(".registInputs").forEach((input) => {
    input.addEventListener('keyup', (e) => {
        const defaultMsg = input.nextElementSibling.children[0];
        const okMsg = input.nextElementSibling.children[1];
        const noMsg = input.nextElementSibling.children[2];

        // switch를 통해 input 태그의 아이디에 따라 keyup event 실행내용 변경
        switch (input.id) {
            case "password" :
                console.log(input.id);

                if (input.value === '') {
                    defaultMsg.classList.remove('hidden')
                    noMsg.classList.add("hidden");
                    okMsg.classList.add('hidden');
                } else {
                    if (!regExpPwd.test(input.value)) {
                        defaultMsg.classList.add('hidden');
                        okMsg.classList.add('hidden');
                        noMsg.classList.remove("hidden");
                    } else {
                        defaultMsg.classList.add('hidden');
                        okMsg.classList.remove('hidden');
                        noMsg.classList.add("hidden");
                    }
                };break;
            case "confirmPassword" :
                const password = document.querySelector("#password");

                if (input.value === '') {
                    defaultMsg.classList.remove('hidden')
                    noMsg.classList.add("hidden");
                    okMsg.classList.add('hidden');
                } else {
                    if (password.value !== input.value) {
                        defaultMsg.classList.add('hidden');
                        okMsg.classList.add('hidden');
                        noMsg.classList.remove("hidden");
                    } else {
                        defaultMsg.classList.add('hidden');
                        okMsg.classList.remove('hidden');
                        noMsg.classList.add("hidden");
                    }
                };break;
        }
    });
});
//
// document.querySelector("#confirmPassword").onblur = (e) => {
//     const password = document.querySelector("#password");
//     const passwordConfirmation = e.target;
//     if (password.value !== passwordConfirmation.value) {
//         alert("패스워드가 일치하지 않습니다.");
//         password.select();
//     }
// };

document.updatePasswordFrm.addEventListener('submit', (e) => {
    const frm = e.target;
    e.preventDefault();
    const password = frm.password;
    const confirmPassword = frm.confirmPassword;

    if (!regExpPwd.test(password.value)) {
        e.preventDefault();
        return;
    }
    // 비밀번호 확인
    if (password.value !== confirmPassword.value) {
        e.preventDefault();
        return;
    }
        EmployeeUpdatePasswordDto employee = 
    frm.submit();
});