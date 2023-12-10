<%--
    JSTL
    - JSP standard Tag Library
    - JSP에서 사용가능한 액션태그의 한 종류
        - 표준액션태그 <jsp:xxx>  <사용빈도 적음
        - 커스텀액션태그 <c:xxx>, <fmt:xxx>, ${fn:xxx}
            - jstl.jar 의존 추가 후 taglib 등록 후 사용
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>JSP | JSTL</title>
</head>
<body>
    <h1>JSTL</h1>
    <h2>core</h2>
    <%-- 분기처리 if --%>
    <c:if test="${num % 2 == 0}">
        <p title="${num}">짝수입니다</p>
    </c:if>
    <c:if test="${num % 2 != 0}">
        <p title="${num}">홀수입니다</p>
    </c:if>
    <%-- else가 없어서 추가하려면 계속 if문을 추가해줘야한다   --%>

    <%-- 분기처리 choose --%>
    <c:choose>
        <c:when test="${num >= 1 and num < 50}">
            <p>🍎ㅊㅋㅊㅋ</p>
        </c:when>
        <c:when test="${num >= 50 and num < 75}">
            <p>🍔ㅊㅋㅊㅋㅊㅋ</p>
        </c:when>
        <c:otherwise>
            <p>💩ㅋㅋㅎㅈ</p>
        </c:otherwise>
    </c:choose>

    <%-- 반복처리 forEach --%>
    <%-- 단순반복 증감변수 n의 범위 : begin ~ end (step) --%>
    <c:forEach begin="1" end="6" var="n">
        <h${n}>HelloWorld${n}</h${n}>
    </c:forEach>
    <%--
        list, 배열 등 반복처리
    --%>
    <ul>
        <c:forEach items="${hobbies}" var="hobby">
            <li>${hobby}</li>
        </c:forEach>
    </ul>

    <table>
        <thead>
            <tr>
                <th>No</th>
                <th>취미</th>
            </tr>
        </thead>
        <tbody>
            <%--
                varStatus 반복상태를 관리하는 객체 이름 지정
                vs.index : 0-based index
                vs.count : 1-based index
                vs.first : 첫번째 요소 여부
                vs.last : 마지막 요소 여부
            --%>
            <c:forEach items="${hobbies}" var="h" varStatus="vs">
                <tr>
                    <td>${vs.index}</td>
                    <td>
                        ${vs.first ? '👍' : ''}
                        ${vs.last ? '👎' : ''}
                        ${h}
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div>
        <%-- 독서, 넷플, 게임, 맛집 --%>
        <c:forEach items="${hobbies}" var="hb" varStatus="vs">
            <tr>
                <td>
                    ${hb}${not vs.last ? ',' : ''}
                </td>
            </tr>
        </c:forEach>
    </div>
    <h2>fmt</h2>
    <%-- https://docs.oracle.com/javase/8/docs/api/java/text/DecimalFormat.html --%>
    <%-- https://docs.oracle.com/javase/8/docs/api/java/text/SimpleDateFormat.html --%>
    <h2>functions</h2>

    <ul>
        <li>${name}</li>
        <li>${fn:toUpperCase(name)}</li>
        <li>${fn:startsWith(name, 'Hong')}</li>
        <li>${fn:indexOf(name, 'ng')} ${fn:indexOf(name, 'xxxx')}</li>
        <li>${fn:substring(name, 6, 8)}</li>
        <li>${fn:replace(name, 'gil', '길')}</li>
    </ul>
    <%-- https://docs.oracle.com/javaee/5/jstl/1.1/docs/tlddocs/fn/tld-summary.html --%>
    <ul>
        <li>
            <fmt:formatNumber value="${no1}" pattern="#,###"/>
        </li>
        <li>
            <%-- # 해당 자리수가 없으면 공란처리 --%>
            <%-- 0 해당 자리수가 없으면 0 처리 --%>
            <fmt:formatNumber value="${no2}" pattern="#.#"/> <%-- 특정 자리수까지 반올림처리 --%>
            <fmt:formatNumber value="${no2}" pattern="#.000"/>
        </li>
        <li>
            <fmt:formatDate value="${today}" pattern="yy/MM/dd(E) HH:mm:ss"/>
        </li>
    </ul>
</body>
</html>
