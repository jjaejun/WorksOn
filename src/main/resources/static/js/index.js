window.addEventListener('DOMContentLoaded', () =>{
    const focusBar = document.querySelector("#focus-bar");
    
    // li에 id 달아서 수정하시면 됩니당
    const dashboardLi = document.querySelector("#dashboardLi");
    const lis = document.querySelectorAll("#nav-ul li");

    // console.log(projectLi);

    lis.forEach((li, i) => {
        const h = 58;
        for(let i = 0; i < lis.length; i++){
            focusBar.classList.remove(`inset-y-[${h * i}px]`);
            lis[i].classList.remove("bg-blue-500");
        }
    });
    dashboardLi.classList.add("bg-blue-500");
    focusBar.classList.add(`inset-y-[${dashboardLi.offsetTop}px]`);
});