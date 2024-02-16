document.querySelector('#chatInput').addEventListener('keypress', (e) => {
    if (e.key === 'Enter') {
        e.preventDefault();
        // console.log(e);

        const msg = {
            content : e.target.value,
            employeeId : `${employeeId}`,
            chatRoomId : `${chatRoomId}`,
        };
        console.log(msg);
        e.target.value = '';
        document.querySelector("#parent").focus();
        document.querySelector("#parent").lastElementChild.focus();
        stompClient.send(`/pub/chatRoom/${chatRoomId}`, {}, JSON.stringify(msg));
    }
});

document.querySelector('#chatBtn').addEventListener('click', (e) => {
    e.preventDefault();
    console.log(e);
    const content = document.querySelector('#chatInput').value;

    const msg = {
        content : content,
        employeeId : `${employeeId}`,
        chatRoomId : `${chatRoomId}`,
    };
    console.log(msg);

    document.querySelector('#chatInput').value = '';

    document.querySelector("#parent").focus();
    document.querySelector("#parent").lastElementChild.focus();

    stompClient.send(`/pub/chatRoom/${chatRoomId}`, {}, JSON.stringify(msg));
});

