const ws = new SockJS(`http://${location.host}${contextPath}stomp`);
// console.log(`${location.host}`, `${contextPath}`, `${chatRoomId}`);
const stompClient = Stomp.over(ws);

// 최초 연결요청
stompClient.connect({}, (frame) => {
    console.log(`${chatRoomId}`);
    console.log("Open : ", frame);

    // 구독신청
    stompClient.subscribe(`/sub/chatRoom/${chatRoomId}`, (message) => {
        console.log(`/sub/chatRoom/${chatRoomId} : `, message);
        const msg = JSON.parse(message.body);
        console.log('메세지가 도착했어요 ~ : ', msg);
    });
});

// 메세지 입력(enter키)
// document.querySelector("#message").addEventListener("keypress", (e) => {
//     if (e.keyCode === 13) {
//         e.preventDefault();
//         e.value = '';
//     }
// });