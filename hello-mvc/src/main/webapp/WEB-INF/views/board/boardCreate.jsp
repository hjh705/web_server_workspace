<%--
  Created by IntelliJ IDEA.
  User: hanjunhee
  Date: 12/19/23
  Time: 11:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="py-8 px-4 mx-auto max-w-2xl lg:py-16">
    <h2 class="mb-4 text-xl font-bold text-gray-900">새 게시글</h2>
    <%-- enctype="multipart/form-data" 가 있어야 파일이 전송된다 --%>
    <form name="boardCreateFrm" method="post" enctype="multipart/form-data">
        <div class="grid gap-4 sm:grid-cols-2 sm:gap-6">
            <div class="sm:col-span-2">
                <label for="title" class="block mb-2 text-sm font-medium text-gray-900">제목</label>
                <input type="text" name="title" id="title" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" placeholder="제목을 작성하세요." required>
            </div>
            <div class="sm:col-span-2">
                <label for="memberId" class="block mb-2 text-sm font-medium text-gray-900">작성자</label>
                <input type="text" name="memberId" id="memberId" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" placeholder="" value = "${loginMember.id}"required readonly>
            </div>
            <div class="sm:col-span-2">
                <label class="block mb-2 text-sm font-medium text-gray-900" for="upFile">첨부파일</label>
                <%--multiple 구문 추가해주면 한번에 여러 파일 업로드가 가능해짐--%>
                <input id="upFile" name="upFile" type="file" multiple class="block p-2.5 w-full text-sm text-gray-900 border border-gray-300 rounded-lg cursor-pointer bg-gray-50 focus:outline-none" >
            </div>
            <div class="sm:col-span-2">
                <label for="content" class="block mb-2 text-sm font-medium text-gray-900">내용</label>
                <textarea id="content" name="content" rows="8" class="block p-2.5 w-full text-sm text-gray-900 bg-gray-50 rounded-lg border border-gray-300 focus:ring-primary-500 focus:border-primary-500" placeholder="내용을 작성하세요." required></textarea>
            </div>
        </div>
        <button type="submit" class="inline-flex items-center px-5 py-2.5 mt-4 sm:mt-6 text-sm font-medium text-center text-white bg-primary-700 rounded-lg focus:ring-4 focus:ring-primary-200class="sm:col-span-2">
        저장
        </button>
    </form>
</div>
<script src="${pageContext.request.contextPath}/js/board/boardCreate.js"></script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>