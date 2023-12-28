<%--
  Created by IntelliJ IDEA.
  User: hanjunhee
  Date: 12/12/23
  Time: 12:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <section>
        <div class="flex flex-col items-center justify-center px-6 py-8 mx-auto lg:py-0">
            <div class="w-full bg-white rounded-lg shadow my-6 sm:max-w-md xl:p-0">
                <div class="p-6 space-y-4 md:space-y-6 sm:p-8">
                    <h1 class="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl">
                        회원정보 입력
                    </h1>
                    <form name="memberRegisterFrm" method="post" class="space-y-4 md:space-y-6">
                        <div class="relative"> <%-- tailwind 기능 사용을 위해 relative--%>
                            <label for="id" class="block mb-2 text-sm font-medium text-gray-900 after:content-['*'] after:ml-0.5 after:text-red-500">아이디</label>
                            <input type="text" name="id" id="id" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" value="sejong" required>
                            <span class="guide ok text-green-700 absolute hidden top-9 end-1">이 아이디는 사용 가능합니다</span>
                            <span class="guide error text-red-700 absolute hidden top-9 end-1">이 아이디는 이미 사용 중 입니다</span>
                            <%-- idValid = 1 은 사용 가능한 아이디, 0은 사용 불가한 아이디 --%>
                            <input type="hidden" id="idValid" value="0">
                        </div>
                        <div>
                            <label for="password" class="block mb-2 text-sm font-medium text-gray-900 after:content-['*'] after:ml-0.5 after:text-red-500">비밀번호</label>
                            <input type="password" name="password" id="password" placeholder="••••••••" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" value="1234a@" required>
                        </div>
                        <div>
                            <label for="confirm-password" class="block mb-2 text-sm font-medium text-gray-900 after:content-['*'] after:ml-0.5 after:text-red-500">비밀번호 확인</label>
                            <input type="password" name="confirm-password" id="confirm-password" placeholder="••••••••" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" value="1234a@" required>
                        </div>
                        <div>
                            <label for="name" class="block mb-2 text-sm font-medium text-gray-900 after:content-['*'] after:ml-0.5 after:text-red-500">이름</label>
                            <input type="text" name="name" id="name" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" value="킴세종" required>
                        </div>
                        <div>
                            <label for="birthday" class="block mb-2 text-sm font-medium text-gray-900">생일</label>
                            <input type="date" name="birthday" id="birthday" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5">
                        </div>
                        <div>
                            <label for="email" class="block mb-2 text-sm font-medium text-gray-900 after:content-['*'] after:ml-0.5 after:text-red-500">이메일</label>
                            <input type="email" name="email" id="email" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" placeholder="name@company.com" value="sejong@gmail.com" required>
                        </div>
                        <div>
                            <label for="phone" class="block mb-2 text-sm font-medium text-gray-900">전화번호</label>
                            <input type="text" name="phone" id="phone" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" placeholder="(-) 없이 입력하세요." value="01012348888" required>
                        </div>
                        <fieldset>
                            <legend class="mb-3">성별</legend>
                            <div class="inline-flex items-center mr-4">
                                <input id="country-option-1" type="radio" name="gender" value="M" class="w-4 h-4 border-gray-300 focus:ring-2 focus:ring-blue-300">
                                <label for="country-option-1" class="block ms-2  text-sm font-medium text-gray-900">남</label>
                            </div>
                            <div class="inline-flex items-center mr-4">
                                <input id="country-option-2" type="radio" name="gender" value="F" class="w-4 h-4 border-gray-300 focus:ring-2 focus:ring-blue-300">
                                <label for="country-option-2" class="block ms-2 text-sm font-medium text-gray-900">여</label>
                            </div>
                        </fieldset>
                        <fieldset>
                            <legend class="mb-3">취미</legend>
                            <div class="inline-flex items-center mr-4">
                                <input id="hobby-1" type="checkbox" name="hobby" value="운동" class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded focus:ring-blue-500 focus:ring-2" >
                                <label for="hobby-1" class="ms-2 text-sm font-medium text-gray-900">운동</label>
                            </div>
                            <div class="inline-flex items-center mr-4">
                                <input id="hobby-2" type="checkbox" name="hobby" value="게임" class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded focus:ring-blue-500 focus:ring-2" >
                                <label for="hobby-2" class="ms-2 text-sm font-medium text-gray-900">게임</label>
                            </div>
                            <div class="inline-flex items-center mr-4">
                                <input id="hobby-3" type="checkbox" name="hobby" value="영화" class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded focus:ring-blue-500 focus:ring-2" >
                                <label for="hobby-3" class="ms-2 text-sm font-medium text-gray-900">영화</label>
                            </div>
                            <div class="inline-flex items-center mr-4">
                                <input id="hobby-4" type="checkbox" name="hobby" value="음악" class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded focus:ring-blue-500 focus:ring-2" >
                                <label for="hobby-4" class="ms-2 text-sm font-medium text-gray-900">음악</label>
                            </div>
                            <div class="inline-flex items-center mr-4" id="hobby-etc-wrapper">
                                <input type="checkbox" value="" class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded focus:ring-blue-500 focus:ring-2" >
                                <label id="hobby-etc" class="ms-2 text-sm font-medium text-gray-500" contenteditable>직접입력</label>
                            </div>
                        </fieldset>

                        <button type="submit" class="text-white w-full bg-primary-600 hover:bg-primary-700 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center">회원가입</button>
                        <p class="text-sm font-light text-gray-500">
                            이미 회원이신가요? <a href="#" class="font-medium text-primary-600 hover:underline">여기서 로그인하세요😀</a>
                        </p>
                    </form>
                </div>
            </div>
        </div>
    </section>
    <script src="${pageContext.request.contextPath}/js/member/memberRegister.js"></script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>