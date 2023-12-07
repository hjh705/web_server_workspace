<%--
    JSP
    - Java + HTML
    - 모든 jsp는 Servlet으로 변환되어서 서비스된다.
    - jsp의 모든 java코드는 서버단에서 먼저 처리된 후 그 결과만 html 형식으로 클라이언트에 전달된다.

    구성요소
    1. Java
        a. 지시어(설정) <%@ page %>, <%@ include %>, <% tablib %>
        b. 스크립팅 원소 <% %>, <%= %%>, <%! %>
    2. EL
    3. JSTL

    내장객체 (선언없이 사용할 수 있는 객체)
    1. context 객체 (서버 운영에 필요한 정보를 가지고, 각기 다른 생명 주기를 가진 객체들)
        - pageContext : PageContext (jsp 처리기간 동안만 사용가능)
        - request : HttpServletRequest (사용자 요청정보를 가진 객체, 요청시작~응답까지 사용가능)
        - session : HttpSession (사용자 첫 접속 ~ 접속해제까지)
        - application : ServletContext (서버시작 ~ 서버종료)
    2. response : HttpServletResponse 응답관련 처리를 위한 객체
    3. out : PrintWriter 응답 출력스트림
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //자바 영역
    //한줄 주석
    /*
        여러줄 주석
     */
    System.out.println("Basic.jsp 🐥");
    int sum = 0;
    for (int i = 1; i <= 100; i++){
        sum += i;
    }
    System.out.println(sum);

    long millis = System.currentTimeMillis();
    System.out.println(millis);

    //context객체에 속성으로 대입 ${}
    pageContext.setAttribute("sum", sum);
    pageContext.setAttribute("millis", millis);
%>
<html>
<head>
    <title>JSP | Basics</title>
</head>
<body>
    <h1>Basics</h1>

    <h2>1~100까지 합</h2>
    <p id="sumFromServer"><%= sum %></p>
    <p id="sumFromServer">${sum}</p>
    <p id="sumFromClient"></p>
    <h2>현재 시각(밀리초) 출력</h2>
    <p id="nowFromServer"><%= millis %></p>
    <p id="nowFromServer">${millis}</p>
    <p id="nowFromClient"></p>

    <%-- jsp주석 : servlet 변환 과정에서 제거 --%>
    <!-- html 주석 : client까지 전달된다  -->
    <script>
        let sum = 0;
        for(let i = 1; i <= 100; i++)
            sum += i;
        document.querySelector("#sumFromClient").innerHTML = sum;
        document.querySelector("#nowFromClient").innerHTML = Date.now();
    </script>
</body>
</html>
