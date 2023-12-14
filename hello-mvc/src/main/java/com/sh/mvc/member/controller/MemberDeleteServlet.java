package com.sh.mvc.member.controller;

import com.sh.mvc.member.model.entity.Gender;
import com.sh.mvc.member.model.entity.Member;
import com.sh.mvc.member.model.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

@WebServlet("/member/memberDelete")
public class MemberDeleteServlet extends HttpServlet {
    MemberService memberService = new MemberService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 인코딩 처리
        // 2. 사용자 입력값 가져오기
        HttpSession session = req.getSession();
        Member loginMember = (Member) session.getAttribute("loginMember");
        String id = loginMember.getId();
        System.out.println(id);
        // 3. 업무로직
        int result = memberService.deleteMember(id);
        // 세션 해제
        session.invalidate();

        // 세션 새로 생성. msg 속성 저장
        session = req.getSession();
        session.setAttribute("msg", "탈퇴가 완료되었습니다.");

        // 4. view(redirect) - post는 redirect
        resp.sendRedirect(req.getContextPath() + "/");
    }



}