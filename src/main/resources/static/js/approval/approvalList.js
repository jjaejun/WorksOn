document.querySelectorAll("tr[data-approval-leave-id]").forEach((tr) =>{
   tr.addEventListener('click', (e) => {
     const td = e.target;
     const tr = td.parentElement;
     const {approvalLeaveId} = tr.dataset;
     location.href = `${contextPath}approval/approvalDetailLeave.do?id=${approvalLeaveId}`;
   });
});

document.querySelectorAll("tr[data-approval-equipment-id]").forEach((tr) =>{
    tr.addEventListener('click', (e) => {
        const td = e.target;
        const tr = td.parentElement;
        const {approvalEquipmentId} = tr.dataset;
        location.href = `${contextPath}approval/approvalDetailEquipment.do?id=${approvalEquipmentId}`;
    });
});

document.querySelectorAll("tr[data-approval-cooperation-id]").forEach((tr) =>{
    tr.addEventListener('click', (e) => {
        const td = e.target;
        const tr = td.parentElement;
        const {approvalCooperationId} = tr.dataset;
        location.href = `${contextPath}approval/approvalDetailCooperation.do?id=${approvalCooperationId}`;
    });
});

window.addEventListener('DOMContentLoaded', () =>{
    const lis = document.querySelectorAll(".approval-tr td");
    lis.forEach((li, i) => {
        li.classList.add("cursor-pointer")
    });
});