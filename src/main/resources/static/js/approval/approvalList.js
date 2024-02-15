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

// $(document).ready(function () {
//     $('.pagination a.page-link').on('click', function (e) {
//         e.preventDefault();
//
//         var page = $(this).text();
//         var url = `${contextPath}approval/approvalCooperation.do?page=` + page;
//
//         $.ajax({
//             type: 'GET',
//             url: url,
//             success: function (data) {
//                 // 페이지 내용 업데이트
//                 $('#content').html(data);
//
//                 // 선택된 페이지 표시 업데이트 (이 부분은 적절히 수정해야 할 수 있습니다)
//                 $('.pagination li').removeClass('active');
//                 $(e.target).parent().addClass('active');
//             },
//             error: function (error) {
//                 console.error('Error fetching page: ' + page, error);
//             }
//         });
//     });
// });