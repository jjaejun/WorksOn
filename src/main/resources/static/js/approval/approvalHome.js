// window.addEventListener('DOMContentLoaded', () =>{
//     const focusBar = document.querySelector("#focus-bar");
//     const approvalLi = document.querySelector("#approvalLi");
//     const lis = document.querySelectorAll("#nav-ul li");
//
//     lis.forEach((li, i) => {
//         const h = 58;
//         for(let i = 0; i < lis.length; i++){
//             focusBar.classList.remove(`inset-y-[${h * i}px]`);
//             lis[i].classList.remove("bg-blue-500");
//         }
//     });
//     approvalLi.classList.add("bg-blue-500");
//     focusBar.classList.add(`inset-y-[${approvalLi.offsetTop}px]`);
// });