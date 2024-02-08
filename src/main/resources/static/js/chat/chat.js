const ws = new SockJS(`http://${location.host}${contextPath}stomp`);
const stompClient = Stomp.over(ws);

// 최초 연결요청
stompClient.connect({}, (frame) => {
    console.log("Open : ", frame);

    // 구독신청
    stompClient.subscribe(`/sub/member/${username}`, (message) => {
        console.log(`/sub/member/${username} : `, message);
        const msg = JSON.parse(message.body);
        console.log('메세지가 도착했어요 ~ : ', msg);
    });
});

// 메세지 저장(enter키)
document.querySelector("#message").addEventListener("keypress", (e) => {
    if (e.keyCode === 13) {
        e.preventDefault();
        e.value = '';

    }
});