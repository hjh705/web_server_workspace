<%@ page contentType="text/html; charset=utf-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Ajax | Home</title>
</head>
<body>
    <h1>Hello Ajax</h1>

    <h2>js - XMLHttpRequest 직접 제어</h2>
    <ul>
        <li><a href="${pageContext.request.contextPath}/javascript/js_ajax.jsp">js ajax</a></li>
    </ul>

    <h2>JQuery.ajax</h2>
    <ul>
        <li><a href="${pageContext.request.contextPath}/text/text.jsp">text</a></li>
        <li><a href="${pageContext.request.contextPath}/xml/xml.jsp">xml</a></li>
        <li><a href="${pageContext.request.contextPath}/json/json.jsp">json</a></li>
    </ul>
</body>
</html>