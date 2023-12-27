document.querySelector("#btn1").onclick = () =>{
    console.log(123);
    $.ajax({
        url : `${contextPath}/xml/sample.xml`,
        method : "get",
        dataType : "xml",
        success(xmlDoc){
            const tbody = document.querySelector("#books tbody");
            tbody.innerHTML = ''; //초기화

            // xml 문서를 응답받아 parsing, javascript 객체(document)로 반환
            console.log(xmlDoc); // xml 문서를 파싱해서 document 객체를 반환

            const root = xmlDoc.querySelector(":root");
            console.dir(root);

            // const books = root.children;
            const books = xmlDoc.querySelectorAll("book");
            books.forEach((book) =>{
                console.log(book);
                const [subject, title, author] = book.children;
                console.log(subject.textContent, title.textContent, author.textContent);
                tbody.innerHTML += `
                <tr>
                    <td>${subject.textContent}</td>
                    <td>${title.textContent}</td>
                    <td>${author.textContent}</td>
                </tr>
                `;
            });
        }
    })
}

document.querySelector("#btn-celeb").addEventListener('click', (e) => {
    $.ajax({
        url : `${contextPath}/xml/celeb/findAll`,
        method : "get",
        dataType: "xml",
        success(xmlDoc) {
            console.log(xmlDoc)
            const tbody = document.querySelector("#celebs tbody");

            const celebs = xmlDoc.querySelectorAll("Celeb");
            celebs.forEach((celeb) => {
                const [id, profile, name, type] = celeb.children;
                console.log(id,profile,name,type);
                tbody.innerHTML += `
                <tr>
                    <td>${id.textContent}</td>
                    <td>
                        <img src="${contextPath}/images/celeb/${profile.textContent}" alt="">
                    </td>
                    <td>${name.textContent}</td>
                    <td>${type.textContent}</td>
                </tr>`;

            })
        }

    });
});