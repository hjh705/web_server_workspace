<%--
  header, footer에 오류가 있으면 무한반복 되기 때문에 따로 페이지를 만들어주는 것이 좋다
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
    isErrorPage="true" %>
<%--
    page지시어의 isErrorPage = true 인 경우,
    현재 페이지에서 던져진 예외객체 exception 사용이 가능하다 (스크립틀릿 사용)
--%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Hello MVC | 에러</title>
    <%--테일윈드 사용 시 src 빼먹지 말 것 --%>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="${pageContext.request.contextPath}/js/tailwind.config.js"></script>
</head>
    <body>
        <div class="flex min-h-full flex-col items-center px-6 py-12">
            <h1 class="text-[300px]">😭</h1>
            <p>이용에 불편을 드려 죄송합니다</p>
            <p class="text-red-700"><%= exception.getMessage()%></p>
            <p><a href="${pageContext.request.contextPath}" class="hover:underline text-blue-700">메인페이지로 돌아가기</a></p>
        </div>
    </body>
</html>

