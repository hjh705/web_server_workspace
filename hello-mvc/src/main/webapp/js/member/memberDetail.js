/**
 * 회원정보 수정 유효성검사
 */
document.memberUpdateFrm.addEventListener('submit', (e) => {
    const frm = e.target;
    const name = frm.name;
    const email = frm.email;

    // 이름 한글2글자 이상
    if(!/^[가-힣]{2,}$/.test(name.value)) {
        alert('이름은 한글 2글자 이상 작성하세요.');
        e.preventDefault();
        return;
    }
    // 이메일 형식
    if(!/^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/.test(email.value)) {
        alert('유효한 이메일을 작성하세요.');
        e.preventDefault();
        return;
    }
});