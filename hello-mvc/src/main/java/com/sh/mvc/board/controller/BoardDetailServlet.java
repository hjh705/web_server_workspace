package com.sh.mvc.board.controller;

import com.sh.mvc.board.model.BoardException;
import com.sh.mvc.board.model.entity.Board;
import com.sh.mvc.board.model.service.BoardService;
import com.sh.mvc.board.model.vo.BoardVo;
import com.sh.mvc.common.HelloMvcUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * xss 공격
 * - Cross-site Scripting 공격
 * - 악성 스크립트를 웹페이지에 삽입해서 클라이언트 개인정보를 탈취하는 공격법
 * - script 태그로 구성된 내용을 필터링 없이 그대로 화면에 출력할 때 취약할 수 있음
 * - Escape Html 처리를 통해서 사용자 입력값이 html 요소로 작동하지 못하게 해야한다
 * - jsp에서 ${fn:escapeXml()} 구문을 통해 작동하지 못하게 하거나, servlet에서 사전에 변환처리를 해줘야한다
 */
@WebServlet("/board/boardDetail")
public class BoardDetailServlet extends HttpServlet {
    private BoardService boardService = new BoardService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // 1. 사용자 입력값 처리
            long id = Long.parseLong(req.getParameter("id"));
            System.out.println(id);
            // 2. 업무로직
            // 쿠키로 조회수 관련 처리
            Cookie[] cookies = req.getCookies();
            List<String> boardCookieValues = getBoardCookieValues(cookies);
            boolean hasRead = boardCookieValues.contains(String.valueOf(id)); //현재 게시글 조회 여부

            System.out.println(hasRead); // true 이미 읽었음, false 라면 처음 읽음

            //조회
            BoardVo board = boardService.findById(id, hasRead);

            System.out.println(board);

            // xss 공격 대비 escapeHtml 처리
            String safeHtml = HelloMvcUtils.escapeHtml(board.getContent());
            // 개행문자 (\n) -> <br>
            board.setContent(HelloMvcUtils.convertLineFeedToBr(safeHtml));
            req.setAttribute("board", board);

            /**
             * 응답 쿠키 생성
             * 만료시간 쿠키 종류
             * - session cookie -1 지정한 경우, session 만료 시 쿠키 자동삭제
             * - persistant cookie n초 지정한 경우 n초동안 보관
             */
            if(!hasRead) { // false 일 때만 작동
                boardCookieValues.add(String.valueOf(id)); // 현재 게시글 id를 추가
                String value = String.join("/", boardCookieValues); // [12, 34, 56] -> "12/34/56"
                Cookie cookie = new Cookie("board", value);
                cookie.setMaxAge(365 * 24 * 60 * 60); // 초단위 보관 시간 설정, 음수인 경우 session종료 시 삭제, 0인 경우 즉시 삭제
                cookie.setPath(req.getContextPath()+"/board/boardDetail"); // 지정한 경로일 때만 클라이언트에서 서버로 쿠키 전송
                resp.addCookie(cookie);
            }

            // 3. forward
            req.getRequestDispatcher("/WEB-INF/views/board/boardDetail.jsp").forward(req,resp);
        } catch (Exception e) {
            // 예외 전환해서 던지기 : 사용자 친화적 메세지, 원인 제외 wrapping
            // 기존 doGet 구문을 전부 선택 후 try/catch
            throw new BoardException("게시글 상세보기 오류", e);
        }
    }

    private List<String> getBoardCookieValues(Cookie[] cookies) {
        List<String> boardCookieValues = new ArrayList<>();
        if (cookies != null){
            // Arrays.asList 로 생성 시 immutable(변경불가) 하기 때문에 별도로 배열을 생성시켜서 만들어줘야만함
            for(Cookie cookie : cookies){
                String name = cookie.getName();
                String value = cookie.getValue();
                System.out.println(name + " = " + value);
                if("board".equals(name)){
                    String[] temp = value.split("/"); // 23/34/56
                    for(String _id : temp){
                        boardCookieValues.add(_id);
                    }
                }
            }
        }
        return boardCookieValues;
    }
}