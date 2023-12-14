package com.sh.mvc.member.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 서버측 session 객체의 무효화처리 invalidate
 * - 속성으로 저장된 데이터도 모두 폐기됨
 */
@WebServlet("/member/memberLogout")
public class MemberLogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // create 매개변수
        // - true 기본값. 세션이 있으면 세션 반환, 없으면 새로 생성해서 반환
        // - false 세션이 있으면 세션 반환, 없으면 null 반환
        HttpSession session = req.getSession(false); // 새로 생성 여부
        if(session != null){
            session.invalidate();
        }
        // 인덱스 페이지로 이동 (url 변경)
        resp.sendRedirect(req.getContextPath() + "/");

    }
}