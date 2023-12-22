package com.sh.mvc.member.controller;

import com.sh.mvc.common.HelloMvcUtils;
import com.sh.mvc.member.model.entity.Member;
import com.sh.mvc.member.model.service.MemberService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Http 통신은 stateless 하다.
 * - 상태를 관리하지 않는다.
 * - 매 요청은 독립적이다.
 * - 요청 / 응답 후에는 연결이 끊긴다.
 * - 사용자의 상태도 관리할 수 없다. (로그인을 유지할 수 없다)
 *
 * Session - Cookie 를 이용한 사용자 상태관리
 * - session 정보를 server(tomcat) 측에서 관리. 로그인 사용자 정보
 * - cookie 정보를 client(browser) 측에서 관리. session id
 *
 * 1. client의 첫 접속 시 session은 session id를 발급, 응답 해더에 추가한다.
 *   - 응답헤더 Set-Cookie 확인
 * 2. Set-Cookie 응답을 받은 client는 브라우저의 cookie 항목에 session id를 저장한다.
 *   - Application - Cookie 확인 가능
 * 3. 다음 매 요청마다 client는 Cookie 항목으로 session id를 함께 전송한다.
 *   - 요청헤더 Cookie 확인
 * 4. 요청헤더의 Cookie를 확인한 Server는 업무로직 수행 시 해당 session 객체를 사용하게 된다.
 *   - session id가 유효하지 않다면, 새로 session객체를 생성하고 id를 발급해서 1번부터 다시 반복하게 된다.
 *
 */

@WebServlet("/member/memberLogin")
public class MemberLoginServlet extends HttpServlet {

    private MemberService memberService = new MemberService();
    /**
     * 로그인 폼페이지
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Referer (사용자가 머물렀던 페이지)를 session에 저장하기
        String referer = req.getHeader("Referer");
        System.out.println("referer = " + referer);

        // 로그인 실패 시 로그인 페이지가 이전 주소가 안되게끔 설정 
        if(!referer.contains("member/memberLogin")){
        req.getSession().setAttribute("next", referer);
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/member/memberLogin.jsp");
        requestDispatcher.forward(req, resp);
    }

    /**
     * 로그인처리
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 사용자 입력값 인코딩 처리
        // req.setCharacterEncoding("utf-8");

        // 2. 사용자 입력값을 가져오기
        String id = req.getParameter("id");// form의 name값 가져오기
        String password = HelloMvcUtils.getEncryptedPassword(req.getParameter("password"),id);
        // System.out.println(id + ", " + password);

        // 3. 업무 로직을 작성 (이번 요청에 처리할 작업) -> login(인증)
        // id / password - db에서 읽어온 데이터(member객체)와 비교
        // 로그인 성공 (id / password 모두 일치)
        // 로그인 실패 (존재하지 않는 id | password가 틀린 경우)
        Member member = memberService.findById(id);
        // System.out.println(member);

        //세션 생성 / 가져오기
        // getSession(), getSession(true) : 세션이
        HttpSession session = req.getSession();
        if(member != null && password.equals(member.getPassword())){
            //로그인 성공
            // System.out.println("로그인 성공");
            // pageContext, request, session, application 컨텍스트 객체 중에 login처리에 적합한 것은 session
            // session 객체는 사용자가 서버 첫 접속부터 세션 해제시까지 유효
            session.setAttribute("loginMember", member);

            String location = req.getContextPath() + "/";
            String next = (String) req.getSession().getAttribute("next");
            if(next != null){
                location = next;
                req.getSession().removeAttribute("next");
            }
            resp.sendRedirect(location);
        }
        else {
            //로그인 실패
            session.setAttribute("msg","아이디가 존재하지 않거나, 비밀번호가 틀립니다.");
            resp.sendRedirect(req.getContextPath() + "/member/memberLogin"); // GET
        }

        // 4. view단 처리 (forwarding) || redirect 처리 (url을 변경해야할 때 사용함)
        // DML 요청(POST), 로그인 요청 등은 반드시 redirect로 처리해서 URL을 변경해야 한다
        // / 가 없으면 브라우저에서 붙여주는 과정을 추가한 뒤 실행되기에 마지막에 붙여주는 것이 좋다
        // resp.sendRedirect(req.getContextPath() + "/");

    }

}
