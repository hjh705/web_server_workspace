document.boardCreateFrm.addEventListener('submit', (e)=>{
    const frm = e.target;
    const title = frm.title;
    const content = frm.content;

    // 제목 유효성 검사
    if(!/^.+$/.test(title.value.trim())){
        alert('제목을 작성해주세요');
        e.preventDefault();
        return;
    }
    // 내용 유효성 검사
    // 정규표현식의 . 에는 \n 개행문자가 포함되지 않는다
    // /^(.|\n)+$/ . 아무글자 + 개행문자 까지 포함
    if(!/^(.|\n)+$/.test(content.value.trim())){
        alert('내용을 작성해주세요');
        e.preventDefault();
        return;
    }
});