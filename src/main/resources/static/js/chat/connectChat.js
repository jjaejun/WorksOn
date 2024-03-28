// 1. 페이지 로드 시
const ws = new SockJS(`https://${location.host}${contextPath}stomp`);
// console.log(`${location.host}`, `${contextPath}`, `${chatRoomId}`);
const stompClient = Stomp.over(ws);

// 2. 최초 연결요청
stompClient.connect({}, () => {
    // console.log(`${chatRoomId}`);

    // 3. 구독신청
    stompClient.subscribe(`/sub/chatRoom/${chatRoomId}`, (message) => {
        const {content, empId, name} = JSON.parse(message.body);
        // console.log(empId, employeeId);
        const _createdAt = Date.now();
        const createdAt = new Date(_createdAt).toLocaleTimeString('ko-KR', { hour: '2-digit', minute: '2-digit' });
        // console.log(createdAt);

        const box = document.querySelector("#parent");
        // console.log(box.lastElementChild);
        if (employeeId != empId) {
            box.lastElementChild.innerHTML += `
                <div class="mr-auto ml-8 min-w-[320px]">
                    <div class="flex items-center space-x-2 rtl:space-x-reverse">
                    <span class="text-sm font-semibold text-gray-900 dark:text-white">${name}</span>
                    <span class="text-sm font-normal text-gray-500 dark:text-gray-400">${createdAt}</span>
                </div>
                <div class="flex flex-col leading-1.5 p-4 border-gray-200 bg-gray-100 rounded-r-xl rounded-bl-xl dark:bg-gray-700">
                    <p class="text-sm font-normal text-gray-900 dark:text-white">${content}</p>
                </div>
                </div>
                `;
        } else if (employeeId == empId) {
            box.lastElementChild.innerHTML += `
                <div class="ml-auto mr-8 min-w-[320px]">
                    <div class="flex items-center space-x-2 rtl:space-x-reverse">
                        <span class="text-sm font-semibold text-gray-900 dark:text-white">${name}</span>
                        <span class="text-sm font-normal text-gray-500 dark:text-gray-400">${createdAt}</span>
                    </div>
                    <div class="flex flex-col leading-1.5 p-4 border-gray-200 bg-blue-600 rounded-l-xl rounded-br-xl dark:bg-gray-700">
                        <p class="text-sm font-normal text-white dark:text-white">${content}</p>
                    </div>
                </div>`;

            const room = document.querySelector("#parent");
            console.dir(room);
            room.scrollTop = room.scrollHeight;
        }
    });
});
