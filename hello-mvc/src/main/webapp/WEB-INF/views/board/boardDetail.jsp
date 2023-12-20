<%--
  Created by IntelliJ IDEA.
  User: hanjunhee
  Date: 12/19/23
  Time: 12:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <div class="xl:container p-8">
        <div class="w-full p-6 bg-white border border-gray-200 rounded-lg shadow">
            <h5 class="mb-2 text-2xl font-semibold tracking-tight text-gray-900 ">${board.title}</h5>
            <p class="mb-3 font-normal text-gray-500">${board.memberId}</p>
            <p class="mb-3 font-normal text-gray-700">${board.content}</p>
            <c:forEach items="${board.attachments}" var="attach">
                <a href="#" class="flex items-center text-blue-600 hover:underline">
                    <img src="../images/file.png" class="w-[16px] mr-1">
                        ${attach.originalFilename}
                </a>
            </c:forEach>
            <div class="text-sm mt-2 font-medium text-gray-400">
                조회수 <span>${board.readCount}</span>
            </div>
            <div class="text-sm mt-2 font-medium text-gray-400">
                작성일
                <span>
                    <fmt:parseDate value="${board.regDate}" pattern="yyyy-MM-dd'T'HH:mm" var="regDate"/>
                    <fmt:formatDate value="${regDate}" pattern="yy/MM/dd HH:mm"/>
                </span>
            </div>
        </div>
    </div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>