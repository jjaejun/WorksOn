// document.addEventListener('DOMContentLoaded', function() {
// //     let replyButtons = document.querySelectorAll('.btn-reply');
// //     replyButtons.forEach(function(button) {
// //         button.addEventListener('onclick', function() {
// //             console.log('답글 버튼이 클릭되었습니다.');
// //             // 여기에 댓글에 대한 답글 작성 등의 동작을 추가할 수 있습니다.
// //         });
// //     });
// // });

document.addEventListener('DOMContentLoaded', function () {
    document.querySelectorAll(".btn-reply").forEach((button) => {
        button.addEventListener('click', (e) => {
            const {parentId, boardId } = e.target.dataset;
            console.log(parentId)
            console.dir(e.target.dataset);

            // 대댓글 폼 생성
            const html = `
                <tr>
                    <td colspan="4">
                        <form> 
                            <input type="hidden" name="boardId" value="${boardId}">
                            <input type="hidden" name="commentLevel" value="2">
                            <input type="hidden" name="parentCommentId" value="${parentId}">
                            <div class="flex items-center px-3 py-2 bg-white hover:bg-gray-50 border-b">
                                <textarea id="content" name="content" rows="1" required class="resize-none block mx-4 p-2.5 w-full text-sm text-gray-900 bg-white rounded-lg border border-gray-300 focus:ring-blue-500 focus:border-blue-500" placeholder="답글을 작성하세요..."></textarea>
                                <button type="submit" class="inline-flex justify-center p-2 text-blue-600 rounded-full cursor-pointer hover:bg-blue-100">
                                    <svg class="w-5 h-5 rotate-90 rtl:-rotate-90" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 18 20">
                                        <path d="m17.914 18.594-8-18a1 1 0 0 0-1.828 0l-8 18a1 1 0 0 0 1.157 1.376L8 18.281V9a1 1 0 0 1 2 0v9.281l6.758 1.689a1 1 0 0 0 1.156-1.376Z"/>
                                    </svg>
                                </button>
                            </div>
                        </form>
                    </td>
                </tr>`;
            const tr = e.target.parentElement.parentElement;
            tr.insertAdjacentHTML('afterend', html);
        });
    });
});