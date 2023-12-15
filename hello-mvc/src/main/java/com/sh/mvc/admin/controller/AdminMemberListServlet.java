package com.sh.mvc.admin.controller;

import com.sh.mvc.member.model.entity.Member;
import com.sh.mvc.member.model.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/memberList")
public class AdminMemberListServlet extends HttpServlet {

    private MemberService memberService = new MemberService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 사용자입력값 가져오기

        // 2. 업무로직
        List<Member> members = memberService.findAll();
        System.out.println(members);
        req.setAttribute("members", members);

        // 3. view단처리
        req.getRequestDispatcher("/WEB-INF/views/admin/memberList.jsp").forward(req, resp);
    }
}