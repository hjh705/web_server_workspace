<?xml version="1.0" encoding="uft-8"?>
<%--제일 첫째줄에 주석, 공백도 들어가면 안된다 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<Celebs>
    <c:forEach items="${celebs}" var="celeb">
        <Celeb>
            <Id>${celeb.id}</Id>
            <Profile>${celeb.profile}</Profile>
            <Name>${celeb.name}</Name>
            <Type>${celeb.type}</Type>
        </Celeb>
    </c:forEach>
</Celebs>