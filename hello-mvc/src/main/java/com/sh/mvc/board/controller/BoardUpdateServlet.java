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
import java.nio.file.attribute.FileTime;
import java.util.List;
import java.util.UUID;

@WebServlet("/board/boardUpdate")
public class BoardUpdateServlet extends HttpServlet {

    private BoardService boardService = new BoardService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 사용자 입력값 처리
        long id = Long.parseLong(req.getParameter("id"));
        System.out.println(id);
        // 2. 업무로직
        BoardVo board = boardService.findById(id);
        System.out.println(board);
        req.setAttribute("board", board);
        // 3. forward
        req.getRequestDispatcher("/WEB-INF/views/board/boardUpdate.jsp").forward(req,resp);
    }

/**
 * 파일 업로드
 * 1. commons-io, commons-fileupload 의존 추가
 * 2. form[method=post][enctype=mulitpart/formdata] 설정
 * 3. DiskFileItemFactory / ServletFileUpload 요청처리
 * - 저장 경로
 * - 파일 최대 크기
 *
 */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 0. 세팅
        // DiskFileItemFactory - ServletFileUpload
        // 파일 업로드를 위해 enctype = multipart/form/data 형식으로 전달하면, 기존 parameter 형식으론 값을 전달할 수 없음
        // 파일이 저장될 주소 - 무조건 절대주소로 줘야함
        // fileItem 안에 name, content, upFile 이 각각 fileItem 객체 하나로 각각 저장되어있다.
        File repository = new File("/Users/hanjunhee/WorkSpaces/web_server_workspace/hello-mvc/src/main/webapp/upload/board");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(repository);
        factory.setSizeThreshold(10*1024*1024); // 10mb
        ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
        BoardVo board = new BoardVo();
        // 1. 사용자 입력값 처리
        try {
            List<FileItem> fileItemList = servletFileUpload.parseRequest(req);
            for (FileItem fileItem : fileItemList) { // 반복문
                // FieldName = input tag의 name값
                String name = fileItem.getFieldName();
                /**
                 * 기존 board.setId
                 *          .setName
                 * 식으로 수기로 작성하던걸 setValue로 자동설정하게 switch 처리했다.
                 */
                if (fileItem.isFormField()) {
                    // form field
                    String value = fileItem.getString("utf-8");
                    board.setValue(name, value);
                } else {
                    // file
                    if (fileItem.getSize() > 0) {
                        // getName = 파일의 실제 이름 가져오기
                        String originalFilename = fileItem.getName();
                        int dotIndex = originalFilename.lastIndexOf(".");
                        String ext = dotIndex > -1 ? originalFilename.substring(dotIndex) : "";
                        String renamedFilename = UUID.randomUUID() + ext;
                        Attachment attach = new Attachment();
                        attach.setOriginalFilename(originalFilename);
                        attach.setRenamedFilename(renamedFilename);
                        board.addAttachment(attach);

                        File upFile = new File(repository, renamedFilename);
                        fileItem.write(upFile); // 파일 출력
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(board);

        // 2. 업무로직
        // board테이블 수정
        int result = boardService.updateBoard(board);


        req.getSession().setAttribute("msg", "게시물을 성공적으로 수정했습니다.😁");
        // 3. redirect
        resp.sendRedirect(req.getContextPath() + "/board/boardDetail?id=" + board.getId());

    }
}