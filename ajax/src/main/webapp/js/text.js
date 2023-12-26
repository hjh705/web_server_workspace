document.querySelector("#btn1").addEventListener("click", (e) => {
    $.ajax({
        url : `${contextPath}/text`,
        method : "get",
        data : {
            name : "홍길동",
            num : 123
        },
        dataType : "text",
        beforeSend() {
            console.log('beforeSend');
        },
        success(response){
            console.log('success');
            console.log(response);
        },
        error(jqXHR, textStatus, errorThrown){
            console.log('error');
            console.log(jqXHR, textStatus, errorThrown);
        },
        complete() {
            console.log('complete');
        }
    });
});


$( "#studentSearch" ).autocomplete({ // 검색 시 자동완성 예시 나오게 하는 설정
    source(request, callback) {
        // console.log(request);
        // console.log(callback);
        $.ajax({
            url : `${contextPath}/text/studentSearch`,
            method: 'get',
            data : request,
            dataType: 'text',
            success(response) {
                console.log(response);
                /**
                 * n, aaa
                 * n, bbb
                 * n, ccc
                 * 으로 받아온 값을
                 * {
                 *     label : 'aaa' // 노출값
                 *     value : 'aaa' // 내부값
                 * }
                 * 로 바꿔줘야함
                 */

                if(response) {
                    const temp = response.split("\r\n");
                    const students = temp.map((student) => {
                        const[id,name] = student.split(',');
                        return{
                            label : `${name}(${id})`,
                            value : id
                        }
                    })
                    console.log(students);
                    callback(students); // jqueryui#autocomplete에 전달. 화면 자동으로 랜더
                }
            }
        })
    },
    select(e, selected) {
        console.log(e,selected);
        const{item : {value}} = selected;
        location.href = `${contextPath}/student/studentDetail?id=${value}`
    }
});