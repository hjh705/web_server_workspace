package com.sh.mvc.common.filter;

import com.sh.mvc.member.model.entity.Member;
import com.sh.mvc.member.model.entity.Role;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 관리자 권한 검사 필터
 * - authorization 권한 : 인증받은 사용자가 이 서비스를 이용할 수 있는지 체크
 * - 검사할 url : /admin/*
 * - 인증 확인 및 로그인된 사용자의 권한을 검사 (Role.A)
 */
@WebFilter("/admin/*")
public class AuthorizationFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();
        Member loginMember = (Member) session.getAttribute("loginMember");
        // getAttribute 는 무조건 Object 로 반환하기 때문에 다운캐스팅이 필요하다
        // Role role = loginMember.getRole();
        // System.out.println(role);

        if(loginMember == null || loginMember.getRole() != Role.A) {
            session.setAttribute("msg", "관리자 권한이 필요합니다.");
            resp.sendRedirect(req.getContextPath()+"/");
            return;
        }
        super.doFilter(req, resp, chain);
    }


}
