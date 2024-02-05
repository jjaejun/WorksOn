window.addEventListener('DOMContentLoaded', () =>{
    const lis = document.querySelectorAll("#nav-ul li");
    lis.forEach((li, i) => {
        li.classList.add("cursor-pointer")
    });
});