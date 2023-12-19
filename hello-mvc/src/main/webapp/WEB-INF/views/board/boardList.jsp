<%--
  Created by IntelliJ IDEA.
  User: hanjunhee
  Date: 12/15/23
  Time: 3:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <div class="container mx-auto my-6">
        <div class="flex justify-start">
            <h1 class="m-4 text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl">
                게시판
            </h1>
        </div>
        <div class="flex justify-end">
            <button type="button" class="text-white bg-gradient-to-r from-blue-500 via-blue-600 to-blue-700 hover:bg-gradient-to-br focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center me-2 mb-2">글쓰기</button>
        </div>
        <div class="relative overflow-x-auto shadow-md sm:rounded-lg">
            <table class="w-full text-sm text-left rtl:text-right text-gray-500">
                <thead class="text-xs text-gray-700 uppercase bg-gray-50">
                <tr>
                    <th scope="col" class="px-6 py-3">번호</th>
                    <th scope="col" class="px-6 py-3">제목</th>
                    <th scope="col" class="px-6 py-3">작성자</th>
                    <th scope="col" class="px-6 py-3">작성일</th>
                    <th scope="col" class="px-6 py-3">첨부파일</th>
                    <th scope="col" class="px-6 py-3">조회수</th>
                </tr>
                </thead>
                <tbody>
                <tr class="odd:bg-white even:bg-gray-50 border-b ">
                    <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap">1</th>
                    <td class="px-6 py-4">Winter is coming...</td>
                    <td class="px-6 py-4">honggd</td>
                    <td class="px-6 py-4">1999/09/09</td>
                    <td class="px-6 py-4">
                        <img class="w-[16px]" src="../images/file.png" alt="">
                    </td>
                    <td class="px-6 py-4">10</td>
                </tr>
                <tr class="odd:bg-white even:bg-gray-50 border-b ">
                    <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap">2</th>
                    <td class="px-6 py-4">Winter was gone...</td>
                    <td class="px-6 py-4">honggd</td>
                    <td class="px-6 py-4">1999/09/09</td>
                    <td class="px-6 py-4">
                        <img class="w-[16px]" src="../images/file.png" alt="">
                    </td>
                    <td class="px-6 py-4">10</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>

    <div class="flex justify-center mt-6">
        <nav aria-label="Page navigation example">
            <ul class="flex items-center -space-x-px h-8 text-sm">
                <%-- 생성한 pagebar --%>
            </ul>
        </nav>
    </div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>