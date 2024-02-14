document.querySelectorAll("tr[data-approval-leave-id]").forEach((tr) =>{
   tr.addEventListener('click', (e) => {
     const td = e.target;
     const tr = td.parentElement;
     const {approvalId} = tr.dataset;
     location.href = `${contextPath}approval/approvalDetailLeave.do?id=${approvalId}`;
   });
});

document.querySelectorAll("tr[data-approval-equipment-id]").forEach((tr) =>{
    tr.addEventListener('click', (e) => {
        const td = e.target;
        const tr = td.parentElement;
        const {approvalId} = tr.dataset;
        location.href = `${contextPath}approval/approvalDetailEquipment.do?id=${approvalId}`;
    });
});

document.querySelectorAll("tr[data-approval-cooperation-id]").forEach((tr) =>{
    tr.addEventListener('click', (e) => {
        const td = e.target;
        const tr = td.parentElement;
        const {approvalId} = tr.dataset;
        location.href = `${contextPath}approval/approvalDetailCooperation.do?id=${approvalId}`;
    });
});

window.addEventListener('DOMContentLoaded', () =>{
    const lis = document.querySelectorAll(".approval-tr td");
    lis.forEach((li, i) => {
        li.classList.add("cursor-pointer")
    });
});