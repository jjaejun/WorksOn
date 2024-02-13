const startTime = document.getElementById('startTime');
const endTime = document.getElementById('endTime');
function endTimeChecker(){
    if(startTime > endTime){
        alert("종료 시간은 시작 시간보다 늦어야합니다.")
        return;
    }
}

const currentTime = new Date();
const formattedTime = currentTime.toISOString().slice(0, 16);
startTime.value = formattedTime;
endTime.value = formattedTime;