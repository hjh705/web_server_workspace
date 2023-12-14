package com.sh.mvc.member.controller;

import com.sh.mvc.member.model.entity.Gender;
import com.sh.mvc.member.model.entity.Member;
import com.sh.mvc.member.model.entity.Role;
import com.sh.mvc.member.model.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@WebServlet("/member/memberUpdate")
public class MemberUpdateServlet extends HttpServlet {
    private MemberService memberService = new MemberService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 인코딩 처리
        // req.setCharacterEncoding("utf-8");
        // 2. 사용자 입력값 가져오기
        Member loginMember = (Member) req.getSession().getAttribute("loginMember");
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String _birthday = req.getParameter("birthday");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String _gender = req.getParameter("gender");
        String[] _hobby = req.getParameterValues("hobby");
        System.out.println(id + ", " +  name + ", " + _birthday + ", " + email + ", " + phone + ", " + _gender + ", "  + _hobby);

        //input:date는 text계열이라 작성하지 않아도 "" 이 전송됨
        LocalDate birthday = _birthday != null && !"".equals(_birthday) ?
                LocalDate.parse(_birthday, DateTimeFormatter.ISO_DATE) : null;
        Gender gender = _gender !=null ? Gender.valueOf(_gender) : null;
        List<String> hobby = _hobby !=null ? Arrays.asList(_hobby) : null;

        Member member = new Member(id, null, name, null, gender, birthday, email, phone, hobby, 0, null);
        System.out.println(member);

        // 3. 업무로직
        int result = memberService.updateMember(member);
        // db정보가 성공적으로 수정되었다면, 해당 내용으로 session의 속성 loginMember도 업데이트 해준다
        Member memberUpdated = memberService.findById(id);
        req.getSession().setAttribute("loginMember", memberUpdated);

        // 4. view(redirect) : /mvc/member/memberDetail
        resp.sendRedirect(req.getContextPath() + "/member/memberDetail");

    }
}