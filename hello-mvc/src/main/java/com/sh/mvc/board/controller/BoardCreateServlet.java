package com.sh.mvc.board.controller;

import com.sh.mvc.board.model.entity.Attachment;
import com.sh.mvc.board.model.service.BoardService;
import com.sh.mvc.board.model.vo.BoardVo;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet("/board/boardCreate")
public class BoardCreateServlet extends HttpServlet {
    private BoardService boardService = new BoardService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/board/boardCreate.jsp").forward(req, resp);

    }


    /**
     * 파일 업로드
     * 1. commons-io, commons-fileupload 의존 추가
     * 2. form[method=post][enctype=mulitpart/formdata] 설정
     * 3. DiskFileItemFactory / ServletFileUpload 요청처리
     * - 저장 경로
     * - 파일 최대 크기
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 사용자 입력값 처리 및 파일 업로드
        File repository = new File("/Users/hanjunhee/WorkSpaces/web_server_workspace/hello-mvc/src/main/webapp/upload/board");
        int sizeThreshold = 10 * 1024 * 1024; // 10 * 1kb * 1kb

        DiskFileItemFactory factory = new DiskFileItemFactory(); // common 하위에 있는 항목
        factory.setRepository(repository);
        factory.setSizeThreshold(sizeThreshold);

        BoardVo board = new BoardVo();
        // ServletFileUpload 실제 요청을 핸들링할 객체
        ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
        try {
            // 전송된 값을 하나의 FileItem으로 관리
            List<FileItem> fileItemList = servletFileUpload.parseRequest(req);

            for (FileItem item : fileItemList) {
                String name = item.getFieldName(); // input[name]
                if (item.isFormField()) {
                    // 일반 텍스트필드 : Board 객체에 설정
                    String value = item.getString("utf-8"); // 인코딩 있어야 한글이 안깨짐
                    System.out.println(name + "=" + value);
                    // Board 객체에 설정자 로직을 구현
                    board.setValue(name, value);
                } else {
                    // 파일 : 서버 컴퓨터에 저장, 파일 정보를 db에 저장
                    if (item.getSize() > 0) { // 비어있는 객체를 안넣기 위한 거름망
                        System.out.println(name);
                        String originalFilename = item.getName(); // 업로드한 파일명
                        System.out.println("파일 : " + originalFilename);
                        System.out.println("크기 : " + item.getSize() + "byte");

                        int dotIndex = originalFilename.lastIndexOf(".");
                        String ext = dotIndex > -1 ? originalFilename.substring(dotIndex) : ""; // . 이 없을 때를 대비

                        UUID uuid = UUID.randomUUID(); // 고유한 문자열 토큰을 발행해줌
                        String renamedFilename = uuid + ext; // 저장된 파일명 (파일 덮어쓰기 방지, 인코딩 이슈 방지)
                        System.out.println("새 파일명 : " + renamedFilename);

                        // 서버 컴퓨터 파일 저장
                        File upFile = new File(repository, renamedFilename);
                        item.write(upFile); // throw Exception (2번째 선택지)

                        // Attachment 객체 생성
                        Attachment attach = new Attachment();
                        attach.setOriginalFilename(originalFilename);
                        attach.setRenamedFilename(renamedFilename);
                        board.addAttachment(attach);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(board); // board 객체, attach 객체들
        // 2. 업무로직
        int result = boardService.insertBoard(board);

        req.getSession().setAttribute("msg", "게시글이 등록되었습니다.");
        // 3. 목록 페이지로 redirect
        resp.sendRedirect(req.getContextPath() + "/board/boardList");
    }
}