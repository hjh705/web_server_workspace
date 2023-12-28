const getPhotos = (page) => {
    // 비동기 요청으로 photo 5건씩 조회
    $.ajax({
        url : `${contextPath}/photo/page`,
        data : {
            page // 속성명 속성값이 같으면 한번만 적어도 가능함
        },
        success(photos) {
            console.log(photos);
            const container = document.querySelector("#photo-container");

            container.innerHTML += photos.reduce((html, photo) => {
                const{id, memberId, content, renamedFilename, readCount, regDate} = photo;
                return `${html}
                <div class="my-5 max-w-sm bg-white border border-gray-200 rounded-lg shadow hover:shadow-lg">
                    <img class="rounded-t-lg"
                         src="${contextPath}/upload/photo/${renamedFilename}"
                         alt="" />
                    <div class="p-5">
                        <h5 class="mb-2 inline text-sm font-bold tracking-tight text-gray-900">${memberId}</h5>
                        <h6 class="inline text-sm text-gray-500">${regDate}</h6>
                        <p class="mb-3 font-sans text-gray-700">${content}</p>
                        <h6 class="inline text-sm text-gray-500">조회수</h6>
                        <span class="text-sm text-gray-500">${readCount}</span>
                        <h6 class="like inline text-sm cursor-pointer">🤍</h6>
                    </div>
                </div>
                `;
            }, '');
        },
        complete(){
            // 더보기 버튼의 page 처리
            document.querySelector("#page").innerHTML = page;
        }
    })
}
document.querySelector("#btn-page").addEventListener('click', (e) =>{
    const page = Number(document.querySelector("#page").innerHTML) + 1;
    const totalPage = Number(document.querySelector("#totalPage").innerHTML)
    page <= totalPage && getPhotos(page);
})
// 페이지 로드 시 실행
window.addEventListener('DOMContentLoaded', () => {
    getPhotos(1);
});
