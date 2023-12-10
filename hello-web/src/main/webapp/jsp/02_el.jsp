<%--
  EL 내장객체
  - context 객체속성 (맵) (생략가능, 생략시 page-request-session-application 순으로 조회)
    - pageScope
    - requestScope
    - sessionScope
    - applicationScope
  - 사용자 입력값 (맵)
    - param
    - paramValues
  - header정보 (맵)
    - header
    - headerValues
  - 쿠키 cookie (맵)
  - pageContext : PageContext 객체 직접 접근 (포인터) : ${pageContext.request.contextPath}
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP | EL</title>
</head>
<body>
    <h1>EL</h1>
    <h2>context 내장객체 속성</h2>
    <ul>
        <li>이름 : ${name} ${requestScope.name} ${sessionScope.name}</li>
        <li>숫자 : ${num}</li>
        <li>취미 : ${hobbies}
            <ul>
                <li>${hobbies[0]}</li>
                <li>${hobbies[1]}</li>
                <li>${hobbies[2]}</li>
                <li>${hobbies[3]}</li>
                <li>${hobbies[4]}</li>
            </ul>
        </li>
        <li>책/가격 : ${bookMap}
            <ul>
                <li>${bookMap.MyJava}</li>
                <li>${bookMap["정신이 나가기전"]}</li>
                <li>${bookMap["Dr.Jang\'s office"]}</li>
            </ul>
        </li>
    </ul>

    <h2>사용자 입력값 처리</h2>
    <ul>
        <li>name : ${param.name}</li>
        <li>option1 : ${paramValues.option[0]}</li>
        <li>option2 : ${paramValues.option[1]}</li>
    </ul>

    <h2>헤더값</h2>
    <ul>
        <li>User Agent : ${header['User-Agent']}</li>
    </ul>
</body>
</html>
