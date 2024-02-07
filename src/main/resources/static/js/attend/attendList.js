window.addEventListener('DOMContentLoaded', () =>{
    const focusBar = document.querySelector("#focus-bar");
    const attendBtn = document.querySelector("#attendBtn");
    const lis = document.querySelectorAll("#nav-ul li");

    // console.log(projectLi);

    lis.forEach((li, i) => {
        const h = 58;
        for(let i = 0; i < lis.length; i++){
            focusBar.classList.remove(`inset-y-[${h * i}px]`);
            lis[i].classList.remove("bg-blue-500");
        }
    });
    attendBtn.classList.add("bg-blue-500");
    focusBar.classList.add(`inset-y-[${attendBtn.offsetTop}px]`);
});