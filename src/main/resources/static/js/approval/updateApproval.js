function execCmd(command) {
    document.execCommand(command, false, null);
}

function setApprovalInfo(sign, lineId) {
    document.recognize.sign.value = sign;
    document.recognize.lineId.value = lineId;
}


window.addEventListener("DOMContentLoaded", () => {
    approvalEvent();
    // 폼 버튼 눌렀을때 동작 이벤트
    const inputs = document.querySelectorAll(".signs");
    const frm = document.getElementById("recognizeFrm");

    // 반려 관련
    const frm2 = document.getElementById("companionFrm");

    inputs.forEach((input, i) => {
        input.addEventListener('keyup', (e) => {
            const sign = e.target.value;
            const  {lineId, isLast} = e.target.dataset;
            console.log(sign);
            console.log(frm);


            frm.sign.value = sign;
            frm.lineId.value = lineId;
            frm.isLast.value = isLast;

            // 반려 관련
            frm2.lineId.value = lineId;
            frm2.isLast.value = isLast;
        });

    });
})

// 편집 가능여부 컨트롤
const approvalEvent = () => {
    const approvals = document.querySelectorAll(".approver");
    console.log(approvals);
    approvals.forEach((approval, iterStat) => {
        if (iterStat != 0) {
            const sign = approvals[iterStat - 1].querySelector("input[name=sign]");
            console.log(sign.value);
            if(sign.value !== "") {
                const thisSign = approval.querySelector("input[name=sign]");
                console.dir(thisSign);

                thisSign.readOnly = false;
                console.log(thisSign.readOnly);
            }
        }
    });
}


//
// document.getElementById("recognizeBtn").addEventListener('click', (e) => {
//     console.log(e.target);
//     thisFrm.submit;
//     console.dir(thisFrm);
//
// })

