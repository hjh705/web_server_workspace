<%@ page language="java" contentType="text/html; charset=UTF-8"
            pageEncoding="UTF-8" %>
<html>
<head>
	<meta charset="utf-8">
	<title>Hello Wed</title>
</head>
<body>
<h2>Hello World!</h2>
	<p>반갑습니다.</p>
	
	<h2>Servlet</h2>
	
	<ul>
		<li>
			<!-- 서버 절대주소 요청시 /hello-web context-path(앱이름)를 반드시 포함해야 한다 -->
			<a href="${pageContext.request.contextPath}/servlet/01_testPerson.html">GET 취향검사</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/servlet/02_testPerson.html">POST 취향검사</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/servlet/03_testPerson.html">Servlet-JSP 취향검사</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/lifecycle.do">Servlet 생명주기</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/abc.do">Redirect</a>
		</li>
	</ul>
	
	<h2>JSP</h2>
	<ul>
		<li><a href="${pageContext.request.contextPath}/jsp/01_basic.jsp">Basics</a></li>
		<li><a href="${pageContext.request.contextPath}/el.do?name=아이패드&option=red&option=128">EL</a></li>
		<li><a href="${pageContext.request.contextPath}/jstl.do?name=아이패드&option=red&option=128">JSTL</a></li>
	</ul>

</body>
</html>
