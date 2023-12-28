<%--
  Created by IntelliJ IDEA.
  User: hanjunhee
  Date: 12/27/23
  Time: 12:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <div id="photo-container" class="w-full flex flex-col items-center"></div>
    <div class="flex justify-center">
        <button type="button"
                id="btn-page"
                class="py-2.5 px-5 me-2 mb-2 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg border border-gray-200 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:ring-gray-200">
            더보기 <span id="page"></span>/<span id="totalPage">${totalPage}</span>
        </button>
    </div>
<script src="${pageContext.request.contextPath}/js/photo/photoList.js"></script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>