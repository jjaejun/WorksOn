const radios = document.querySelectorAll(".radio-wrap");

radios.forEach((input) => {
    input.addEventListener('click', (e) => {
        const box = input.parentElement;

        radios.forEach((r) => {
            r.parentElement.classList.remove("bg-blue-100");
            r.parentElement.classList.add("bg-gray-50");
        });
        box.classList.add("bg-blue-100");
   });
});

const chatRoomIdIpt = document.querySelector('#chatRoomIdIpt');
const ws = new SockJS(`https://${location.host}${contextPath}stomp`);
const stompClient = Stomp.over(ws);

stompClient.connect({}, () => {
    // console.log('stomp connected...');
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
                    // console.log(chatLogs);
                    chatLogs.forEach((chatlog) => {
                        const {empId, empName, content, createdAt, profileUrl} = chatlog;

                        // console.log(chatlog);

                        const newCreatedAt = new Date(createdAt).toLocaleTimeString('ko-KR', {
                            hour: '2-digit',
                            minute: '2-digit'
                        });

                        if (employeeId != empId) {
                            chatLogDiv.innerHTML += `
                            <div class="flex flex-col w-full mx-auto mt-3 mr-16">
                                <div class="flex mr-auto items-center space-x-2 rtl:space-x-reverse mb-1">
                                    <img src="${profileUrl}" class="object-cover w-8 h-8 rounded-full" alt="" aria-hidden="true"/>                                
                                    <span class="text-sm font-semibold text-gray-900 dark:text-white">${empName}</span>
                                    <span class="text-sm font-normal text-gray-500 dark:text-gray-400">${newCreatedAt}</span>
                                </div>
                                <div class="flex mr-auto leading-1.5 p-4 border-gray-200 bg-gray-100 rounded-r-xl rounded-bl-xl dark:bg-gray-700 w-fit h-fit">
                                    <p class="text-sm font-normal text-gray-900 dark:text-white">${content}</p>
                                </div>
                            </div>
                            `;
                        } else if (employeeId == empId) {
                            chatLogDiv.innerHTML += `
                            <div class="flex flex-col w-full mx-auto mt-3 ml-16">
                                <div class="flex ml-auto items-center space-x-2 rtl:space-x-reverse mb-1">
                                    <img src="${profileUrl}" class="object-cover w-8 h-8 rounded-full" alt="" aria-hidden="true"/>
                                    <span class="text-sm font-semibold text-gray-900 dark:text-white">${empName}</span>
                                    <span class="text-sm font-normal text-gray-500 dark:text-gray-400">${newCreatedAt}</span>
                                </div>
                                <div class="flex ml-auto leading-1.5 p-4 border-gray-200 bg-gradient-to-r from-blue-500 to-cyan-500 rounded-l-xl rounded-br-xl dark:bg-gray-700 w-fit h-fit">
                                    <p class="text-sm font-normal text-white dark:text-white">${content}</p>
                                </div>
                            </div>
                            `;
                        }

                        chatLogDiv.scrollTop = chatLogDiv.scrollHeight;
                    })
                }
            });

            // console.log(`stomp subscribe - /sub/chatMain/${chatRoomId}`);
            stompClient.subscribe(`/sub/chatMain/${chatRoomId}`, (message) => {
                const {empId, empName, content, profileUrl} = JSON.parse(message.body);
                // console.log(empId, empName, content, profileUrl);
                const createdAt = Date.now();
                const newCreatedAt = new Date(createdAt).toLocaleTimeString('ko-KR', {
                    hour: '2-digit',
                    minute: '2-digit'
                });

                if (employeeId != empId) {
                    chatLogDiv.innerHTML += `
                            <div class="flex flex-col w-full mx-auto mt-3 mr-16">
                                <div class="flex mr-auto items-center space-x-2 rtl:space-x-reverse mb-1">
                                    <img src="${profileUrl}" class="object-cover w-8 h-8 rounded-full" alt="" aria-hidden="true"/>
                                    <span class="text-sm font-semibold text-gray-900 dark:text-white">${empName}</span>
                                    <span class="text-sm font-normal text-gray-500 dark:text-gray-400">${newCreatedAt}</span>
                                </div>
                                <div class="flex mr-auto leading-1.5 p-4 border-gray-200 bg-gray-100 rounded-r-xl rounded-bl-xl dark:bg-gray-700 w-fit h-fit">
                                    <p class="text-sm font-normal text-gray-900 dark:text-white">${content}</p>
                                </div>
                            </div>
                            `;
                } else if (employeeId == empId) {
                    chatLogDiv.innerHTML += `
                            <div class="flex flex-col w-full mx-auto mt-3 ml-16">
                                <div class="flex ml-auto items-center space-x-2 rtl:space-x-reverse mb-1">
                                    <img src="${profileUrl}" class="object-cover w-8 h-8 rounded-full" alt="" aria-hidden="true"/>
                                    <span class="text-sm font-semibold text-gray-900 dark:text-white">${empName}</span>
                                    <span class="text-sm font-normal text-gray-500 dark:text-gray-400">${newCreatedAt}</span>
                                </div>
                                <div class="flex ml-auto leading-1.5 p-4 border-gray-200 bg-gradient-to-r from-blue-500 to-cyan-500 rounded-l-xl rounded-br-xl dark:bg-gray-700 w-fit h-fit">
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
        // console.log(msg);
        e.target.value = '';

        stompClient.send(`/pub/chatMain/${chatRoomIdIpt.value}`, {}, JSON.stringify(msg));
    }
});

document.querySelector('#chatBtn').addEventListener('click', (e) => {
    e.preventDefault();
    // console.log(e);
    const content = document.querySelector('#chatInput').value;

    const msg = {
        content: content,
        employeeId: `${employeeId}`,
        chatRoomId: `${chatRoomIdIpt.value}`,
    };
    // console.log(msg);

    document.querySelector('#chatInput').value = '';

    stompClient.send(`/pub/chatMain/${chatRoomIdIpt.value}`, {}, JSON.stringify(msg));
});
