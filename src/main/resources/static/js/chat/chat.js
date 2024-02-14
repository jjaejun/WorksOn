// 1. 페이지 로드 시
const ws = new SockJS(`http://${location.host}${contextPath}stomp`);
// console.log(`${location.host}`, `${contextPath}`, `${chatRoomId}`);
const stompClient = Stomp.over(ws);

// 2. 최초 연결요청
stompClient.connect({}, () => {
    // console.log(`${chatRoomId}`);

    // 3. 구독신청
    stompClient.subscribe(`/sub/chatRoom/${chatRoomId}`, (message) => {
        const {content, empId, chatRoomId} = JSON.parse(message.body);


    });
});

// 메세지 입력(enter키)
// document.querySelector('#chatInput').addEventListener('keypress', (e) => {
//     if (e.key === 'Enter') {
//         e.preventDefault();
//         console.log(e);
//
//         const msg = {
//             content : e.target.value,
//             empId : `${empId}`,
//             chatRoomId : `${chatRoomId}`
//         };
//         console.log(msg);
//
//         stompClient.send(`/pub/chatRoom/${chatRoomId}`, {}, JSON.stringify(msg));
//     }
// });
//
// // 메세지 입력(전송 버튼)
// document.querySelector('#chatBtn').addEventListener('click', (e) => {
//     e.preventDefault();
//     console.log(e);
//     const content = document.querySelector('#chatInput').value;
//
//     const msg = {
//         content : content,
//         empId : `${empId}`,
//         chatRoomId : `${chatRoomId}`
//     };
//     console.log(msg);
//
//     stompClient.send(`/pub/chatRoom/${chatRoomId}`, {}, JSON.stringify(msg));
// });