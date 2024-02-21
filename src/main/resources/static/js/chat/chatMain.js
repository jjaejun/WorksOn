const chatRoomIdIpt = document.querySelector('#chatRoomIdIpt');
const ws = new SockJS(`http://${location.host}${contextPath}stomp`);
const stompClient = Stomp.over(ws);

stompClient.connect({}, () => {
    console.log('stomp connected...');
});

document.addEventListener('DOMContentLoaded', () => {
    const chatLogDiv = document.querySelector('#chatLogDiv');

    const radioButtons = document.querySelectorAll('input[type="radio"][name="chatRoom-radio"]');
    radioButtons.forEach((radioButton) => {
        radioButton.addEventListener('change', (e) => {
            console.log(e.target.value);
            const chatRoomId = e.target.value;
            chatRoomIdIpt.value = chatRoomId;

            chatLogDiv.scrollTop = chatLogDiv.scrollHeight;
            chatLogDiv.innerHTML = '';

            $.ajax({
                type: "GET",
                headers: {
                    [csrfHeaderName]: csrfToken
                },
                url: `${contextPath}chat/chatRoom.do`,
                data: {
                    chatRoomId: chatRoomIdIpt.value
                },
                success(chatLogs) {
                    chatLogs.forEach((chatlog) => {
                        const {empId, empName, content, createdAt} = chatlog;
                        const newCreatedAt = new Date(createdAt).toLocaleTimeString('ko-KR', {
                            hour: '2-digit',
                            minute: '2-digit'
                        });

                        if (employeeId != empId) {
                            chatLogDiv.innerHTML += `
                            <div class="mr-auto ml-8 min-w-[320px]">
                                <div class="flex items-center space-x-2 rtl:space-x-reverse">
                                    <span class="text-sm font-semibold text-gray-900 dark:text-white">${empName}</span>
                                    <span class="text-sm font-normal text-gray-500 dark:text-gray-400">${newCreatedAt}</span>
                                </div>
                                <div class="flex flex-col leading-1.5 p-4 border-gray-200 bg-gray-100 rounded-r-xl rounded-bl-xl dark:bg-gray-700">
                                    <p class="text-sm font-normal text-gray-900 dark:text-white">${content}</p>
                                </div>
                            </div>
                            `;
                        } else if (employeeId == empId) {
                            chatLogDiv.innerHTML += `
                            <div class="ml-auto mr-8 min-w-[320px]">
                                <div class="flex items-center space-x-2 rtl:space-x-reverse">
                                    <span class="text-sm font-semibold text-gray-900 dark:text-white">${empName}</span>
                                    <span class="text-sm font-normal text-gray-500 dark:text-gray-400">${newCreatedAt}</span>
                                </div>
                                <div class="flex flex-col leading-1.5 p-4 border-gray-200 bg-blue-600 rounded-l-xl rounded-br-xl dark:bg-gray-700">
                                    <p class="text-sm font-normal text-white dark:text-white">${content}</p>
                                </div>
                            </div>
                            `;
                        }
                    })
                }
            });

            console.log(`stomp subscribe - /sub/chatMain/${chatRoomId}`);
            stompClient.subscribe(`/sub/chatMain/${chatRoomId}`, (message) => {
                const {empId, empName, content} = JSON.parse(message.body);
                console.log(empId, empName, content);
                const createdAt = Date.now();
                const newCreatedAt = new Date(createdAt).toLocaleTimeString('ko-KR', {
                    hour: '2-digit',
                    minute: '2-digit'
                });

                if (employeeId != empId) {
                    chatLogDiv.innerHTML += `
                            <div class="mr-auto ml-8 min-w-[320px]">
                                <div class="flex items-center space-x-2 rtl:space-x-reverse">
                                    <span class="text-sm font-semibold text-gray-900 dark:text-white">${empName}</span>
                                    <span class="text-sm font-normal text-gray-500 dark:text-gray-400">${newCreatedAt}</span>
                                </div>
                                <div class="flex flex-col leading-1.5 p-4 border-gray-200 bg-gray-100 rounded-r-xl rounded-bl-xl dark:bg-gray-700">
                                    <p class="text-sm font-normal text-gray-900 dark:text-white">${content}</p>
                                </div>
                            </div>
                            `;
                } else if (employeeId == empId) {
                    chatLogDiv.innerHTML += `
                            <div class="ml-auto mr-8 min-w-[320px]">
                                <div class="flex items-center space-x-2 rtl:space-x-reverse">
                                    <span class="text-sm font-semibold text-gray-900 dark:text-white">${empName}</span>
                                    <span class="text-sm font-normal text-gray-500 dark:text-gray-400">${newCreatedAt}</span>
                                </div>
                                <div class="flex flex-col leading-1.5 p-4 border-gray-200 bg-blue-600 rounded-l-xl rounded-br-xl dark:bg-gray-700">
                                    <p class="text-sm font-normal text-white dark:text-white">${content}</p>
                                </div>
                            </div>
                            `;

                    chatLogDiv.scrollTop = chatLogDiv.scrollHeight;
                }
            });
        });
    });
});

document.querySelector('#chatInput').addEventListener('keypress', (e) => {
    if (e.key === 'Enter') {
        e.preventDefault();
        // console.log(e);

        const msg = {
            content: e.target.value,
            employeeId: `${employeeId}`,
            chatRoomId: `${chatRoomIdIpt.value}`,
        };
        console.log(msg);
        e.target.value = '';

        stompClient.send(`/pub/chatMain/${chatRoomIdIpt.value}`, {}, JSON.stringify(msg));
    }
});

document.querySelector('#chatBtn').addEventListener('click', (e) => {
    e.preventDefault();
    console.log(e);
    const content = document.querySelector('#chatInput').value;

    const msg = {
        content: content,
        employeeId: `${employeeId}`,
        chatRoomId: `${chatRoomIdIpt.value}`,
    };
    console.log(msg);

    document.querySelector('#chatInput').value = '';

    stompClient.send(`/pub/chatMain/${chatRoomIdIpt.value}`, {}, JSON.stringify(msg));
});