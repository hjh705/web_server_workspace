<%--
  Created by IntelliJ IDEA.
  User: user1
  Date: 12/22/2023
  Time: 3:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ajax | xml</title>
    <style>
        table{
            border : 1px solid #000;
            border-collapse: collapse;
            margin : 10px 0
        }
        th ,td {
            border : 1px solid #000;
            padding: 5px;
        }
        table img {
            width: 200px;
        }
    </style>
</head>
<body>
    <h1>xml</h1>
    <button id="btn1">확인</button>
    <table id="books">
        <thead>
            <th>주제</th>
            <th>제목</th>
            <th>저자</th>
        </thead>
        <tbody>

        </tbody>
    </table>

    <button id="btn-celeb">Celeb 확인</button>
    <%--table#celebs>thead>tr>th*4--%>
    <table id="celebs">
        <thead>
            <tr>
                <th>Id</th>
                <th>Profile</th>
                <th>Name</th>
                <th>Type</th>
            </tr>
        </thead>
        <tbody>

        </tbody>
    </table>
<script
        src="https://code.jquery.com/jquery-3.7.1.js"
        integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
        crossorigin="anonymous"></script>
<script>
    const contextPath = '${pageContext.request.contextPath}';
</script>
<script src="${pageContext.request.contextPath}/js/xml.js"></script>
</body>
</html>
