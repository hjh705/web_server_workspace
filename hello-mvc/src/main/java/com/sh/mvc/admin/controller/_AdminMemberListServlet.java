package com.sh.mvc.admin.controller;

import com.sh.mvc.common.HelloMvcUtils;
import com.sh.mvc.member.model.entity.Member;
import com.sh.mvc.member.model.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 페이징
 * 1. content
 *   - page 현재 페이지
 *   - limit 한 페이지당 표시할 개체 수
 *
 * 2. pagebar
 *   - page 현재 페이지
 *   - limit 한 페이지당 표시할 개체 수
 *   - totalCount 전체 개체 수
 *   - totalPage 전체 페이지 수
 *   - pagebarSize 페이지바의 숫자 개수
 *   - pageNo 페이지 증감변수
 *   - pagebarStart | pagebarEnd 페이지 증감변수의 범위
 *   - url 요청 url
 */
// @WebServlet("/admin/memberList")
public class _AdminMemberListServlet extends HttpServlet {

    private MemberService memberService = new MemberService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 사용자입력값 가져오기
        int page = 1;
        int limit = 10;

        try {
            page = Integer.parseInt(req.getParameter("page"));
        } catch (NumberFormatException ignore) {}

        Map<String, Object> param = Map.of("page", page, "limit", limit);
        // System.out.println(param);

        // 2. 업무로직
        // content 영역 : 전체조회 쿼리 + RowBounds | Top-n분석 쿼리
        List<Member> members = memberService.findAll(param);
        // System.out.println(members);
        req.setAttribute("members", members);

        // pagebar 영역
        int totalCount = memberService.getTotalCount();
        String url = req.getRequestURI();
        String pagebar = HelloMvcUtils.getPagebar(page,limit,totalCount, url);
        req.setAttribute("pagebar", pagebar);

        // 3. view단처리
        req.getRequestDispatcher("/WEB-INF/views/admin/memberList.jsp").forward(req, resp);
    }
}