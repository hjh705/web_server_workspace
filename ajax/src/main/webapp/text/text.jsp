<%--
  Created by IntelliJ IDEA.
  User: hanjunhee
  Date: 12/22/23
  Time: 12:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ajax | Text</title>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
    <script
            src="https://code.jquery.com/jquery-3.7.1.js"
            integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
            crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
</head>
<body>
    <h1>text</h1>
    <button id="btn1">확인</button>
    <br><br>
    <div class="ui-widget">
        <label for="studentSearch">학생검색: </label>
        <input id="studentSearch">
    </div>

<script>
    const contextPath = '${pageContext.request.contextPath}';
</script>

<script src="${pageContext.request.contextPath}/js/text.js"></script>
</body>
</html>
