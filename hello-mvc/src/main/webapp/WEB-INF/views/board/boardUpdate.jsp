<%--
  Created by IntelliJ IDEA.
  User: hanjunhee
  Date: 12/20/23
  Time: 12:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <div class="py-8 px-4 mx-auto max-w-2xl lg:py-16">
        <h2 class="mb-4 text-xl font-bold text-gray-900">게시글 수정</h2>
        <form name="boardUpdateFrm" method="post" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${board.id}">
            <div class="grid gap-4 sm:grid-cols-2 sm:gap-6">
                <div class="sm:col-span-2">
                    <label for="title" class="block mb-2 text-sm font-medium text-gray-900">제목</label>
                    <input type="text" name="title" id="title" value='${board.title}' class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" placeholder="제목을 작성하세요." required>
                </div>
                <div class="sm:col-span-2">
                    <label for="memberId" class="block mb-2 text-sm font-medium text-gray-900">작성자</label>
                    <input type="text" name="memberId" id="memberId" value="${board.memberId}" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5"  required readonly>
                </div>
                <div class="sm:col-span-2">
                    <label class="block mb-2 text-sm font-medium text-gray-900" for="upFile">첨부파일</label>
                    <input type="file" id="upFile" name="upFile" multiple class="block p-2.5 w-full text-sm text-gray-900 border border-gray-300 rounded-lg cursor-pointer bg-gray-50 focus:outline-none">
                </div>
                <div class="sm:col-span-2">
                    <c:forEach items="${board.attachments}" var="attach" varStatus="vs">
                        <label for="delFile${vs.count}">${attach.originalFilename} 삭제</label>
                        <input type="checkbox" name="delFile" id="delFile${vs.count}" value="${attach.id}">
                        <br>
                    </c:forEach>
                </div>
                <div class="sm:col-span-2">
                    <label for="content" class="block mb-2 text-sm font-medium text-gray-900">내용</label>
                    <textarea name="content" id="content" rows="8" class="block p-2.5 w-full text-sm text-gray-900 bg-gray-50 rounded-lg border border-gray-300 focus:ring-primary-500 focus:border-primary-500" placeholder="내용을 작성하세요." required>${board.content}</textarea>
                </div>
            </div>
            <button type="submit" class="inline-flex items-center px-5 py-2.5 mt-4 sm:mt-6 text-sm font-medium text-center text-white bg-primary-700 rounded-lg focus:ring-4 focus:ring-primary-200 sm:col-span-2">
                저장
            </button>
        </form>
    </div>
<script src="${pageContext.request.contextPath}/js/board/boardUpdate.js"></script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>