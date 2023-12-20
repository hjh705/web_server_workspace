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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// @WebServlet("/admin/searchMember")
public class _AdminSearchMemberServlet extends HttpServlet {
    private MemberService memberService = new MemberService();

    /**
     * - mybatis에서는 식별자(컬럼명, 테이블명)를 동적으로 작성할 수 있는 기능이 있다. ${식별자}
     * - (preparedStatement에는 없음)
     *
     * select * from member where id like ?
     * select * from member where name like ?
     * select * from member where email like ?
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 사용자입력값 가져오기
        int page = 1;
        int limit = 10;
        try {
            page = Integer.parseInt(req.getParameter("page"));
        } catch (NumberFormatException ignore) {};

        String searchType = req.getParameter("search-type");
        String searchKeyword = req.getParameter("search-keyword");

        Map<String, Object> param = new HashMap<>();
        param.put("searchType", searchType);
        param.put("searchKeyword", searchKeyword);
        param.put("page", page);
        param.put("limit", limit);

        // System.out.println(param);

        // 2. 업무로직
        // content 영역
        List<Member> members = memberService.searchMember(param);
        req.setAttribute("members", members);

        // pagebar 영역
        int totalCount = memberService.getTotalCount(param); // 검색 조건에 맞는 총 객체 수
        // 페이지가 넘어가도 검색조건을 유지하도록 구문 작성
        String url = req.getRequestURI() + "?search-type=" + searchType + "&search-keyword=" + searchKeyword ;
        String pagebar = HelloMvcUtils.getPagebar(page, limit, totalCount, url);
        req.setAttribute("pagebar",pagebar);

        // 3. view단처리
        req.getRequestDispatcher("/WEB-INF/views/admin/memberList.jsp").forward(req, resp);
    }
}