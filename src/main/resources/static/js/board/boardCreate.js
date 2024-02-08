document.addEventListener('DOMContentLoaded', function() {
    const typeSelect = document.getElementById('type');
    typeSelect.addEventListener('change', function() {
        const selectedType = this.value;
        if (selectedType === 'notification') {
            // 공지사항 선택 시 특정 처리
            console.log('공지사항이 선택되었습니다.');
        } else if (selectedType === 'free') {
            // 자유게시판 선택 시 특정 처리
            console.log('자유게시판이 선택되었습니다.');
        }
    });
});