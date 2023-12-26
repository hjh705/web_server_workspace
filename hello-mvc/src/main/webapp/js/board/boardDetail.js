/**
 * 댓글 등록폼
 * - 이벤트 버블링을 이용해서 최상위 document 객체에 submit 핸들러를 연결
 * - 폼이 제출되기 전, submit 이벤트가 발생하고, 상위로 전파되서 (bubbling) 이 핸들러를 호출하게됨.
 */
document.addEventListener('submit', (e)=> {
    // 정적 / 동적으로 생성된 폼 모두 적용
    // js 에서 선택자 비교 -> matches 사용
    if(e.target.matches("[name = boardCommentCreateFrm]")){ // 마지막에 작성한 구문
        const frm = e.target;
        const memberId = frm.memberId;
        const content = frm.content;
        if(!memberId.value){
            alert('로그인 후 댓글을 작성해주세요.js');
            e.preventDefault();
            return;
        }

        if(!/^(.|\n)+$/.test(content.value.trim())){
            alert('댓글 내용을 작성해주세요.js');
            e.preventDefault();
            return;
        }

    }

})

/**
 * 답글버튼 click 핸들러
 * 적절한 위치의 tr을 찾은 후, 클릭 시 다음 td 형제요소로 추가해줘야함
 */
document.querySelectorAll(".btn-reply").forEach((button) =>{
    button.addEventListener('click', (e) => {
        console.log(e.target.value); // 댓글 id
        console.log(e.target.dataset); // 댓글 id
        // dataset을 통해 contextPath, boardId, loginMemberId 값을 js로 가져오기
        const parentCommentId = e.target.value;
        const {contextPath, boardId, loginMemberId} = e.target.dataset;
        <!-- 대댓글 입력폼 tr-->
        const html = `
          <tr>
            <td colspan="4">
              <!-- EL이 아니기에 기존 문법 부분을 주의해서 수정해야함 -->
              <form name="boardCommentCreateFrm" action="${contextPath}/board/boardCommentCreate" method="post">
                <input type="hidden" name="boardId" value="${boardId}">
                <input type="hidden" name="memberId" value="${loginMemberId}">
                <input type="hidden" name="commentLevel" value="2">
                <input type="hidden" name="parentCommentId" value="${parentCommentId}">
                <div class="flex items-center px-3 py-2 bg-white hover:bg-gray-50 border-b">
                    <textarea id="content" name="content" required rows="1" class="resize-none block mx-4 p-2.5 w-full text-sm text-gray-900 bg-white rounded-lg border border-gray-300 focus:ring-blue-500 focus:border-blue-500" placeholder="답글을 작성하세요..."></textarea>
                    <button type="submit" class="inline-flex justify-center p-2 text-blue-600 rounded-full cursor-pointer hover:bg-blue-100">
                      <svg class="w-5 h-5 rotate-90 rtl:-rotate-90" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 18 20">
                          <path d="m17.914 18.594-8-18a1 1 0 0 0-1.828 0l-8 18a1 1 0 0 0 1.157 1.376L8 18.281V9a1 1 0 0 1 2 0v9.281l6.758 1.689a1 1 0 0 0 1.156-1.376Z"/>
                      </svg>
                    </button>
                </div>
              </form>
            </td>
          </tr>
        `;
        const tr = e.target.parentElement.parentElement;
        console.log(tr);
        /**
         * insertAdjacentHTML | insertAdjacentText | insertAdjacentElement
         * beforebegin 이전 형제요소로 추가
         * afterbegin 첫번째 자식요소로 추가
         * beforeend 마지막 자식요소로 추가
         * afterend 다음 형제요소로 추가
         */
        tr.insertAdjacentHTML('afterend', html);
    },{
        once : true
    }); // 한번만 실행되게 옵션 부여
})