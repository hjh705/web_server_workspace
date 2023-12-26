<%--
  header, footer에 오류가 있으면 무한반복 되기 때문에 따로 페이지를 만들어주는 것이 좋다
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
    isErrorPage="true" %>
<%--
    page지시어의 isErrorPage = true 인 경우,
    현재 페이지에서 던져진 예외객체 exception 사용이 가능하다 (스크립틀릿 사용)
    에러 코드로 넘어온 경우는 exception 객체가 null 이다
--%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Hello MVC | Not Found</title>
    <%--테일윈드 사용 시 src 빼먹지 말 것 --%>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="${pageContext.request.contextPath}/js/tailwind.config.js"></script>
</head>
    <body>
        <div class="flex min-h-full flex-col items-center px-6 py-12">
            <h1 class="text-[300px]">🫥</h1>
            <p class="text-red-700">해당 페이지를 찾을 수 없습니다</p>
            <p><a href="${pageContext.request.contextPath}" class="hover:underline text-blue-700">메인페이지로 돌아가기</a></p>
        </div>
    </body>
</html>

