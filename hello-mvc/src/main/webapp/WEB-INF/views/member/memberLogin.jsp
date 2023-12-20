<%--
  Created by IntelliJ IDEA.
  User: hanjunhee
  Date: 12/12/23
  Time: 10:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <div class="flex min-h-full flex-col justify-center px-6 py-12 lg:px-8">
        <div class="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
            <h1 class="mb-6 text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl">
                로그인
            </h1>
            <form class="space-y-6" method="POST">
                <div>
                    <label for="id" class="block text-sm font-medium leading-6 text-gray-900">아이디</label>
                    <div class="mt-2">
                        <input id="id" name="id" type="text" value="admin" autocomplete="id" required class="px-4 block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">
                    </div>
                </div>

                <div>
                    <div class="flex items-center justify-between">
                        <label for="password" class="block text-sm font-medium leading-6 text-gray-900">비밀번호</label>
                        <%--            <div class="text-sm">--%>
                        <%--              <a href="#" class="font-semibold text-indigo-600 hover:text-indigo-500">Forgot password?</a>--%>
                        <%--            </div>--%>
                    </div>
                    <div class="mt-2">
                        <input id="password" name="password" type="password" value="1234a@" autocomplete="password" required class="px-4 block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">
                    </div>
                </div>
                <div class="flex items-center">
                    <input id="saveId" name="saveId" type="checkbox" class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded focus:ring-blue-500">
                    <label for="saveId" class="ms-2 text-sm font-medium text-gray-700">아이디 저장</label>
                </div>

                <div>
                    <button
                            type="submit" class="flex w-full justify-center rounded-md bg-indigo-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600">로그인</button>
                </div>
            </form>
        </div>
    </div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>