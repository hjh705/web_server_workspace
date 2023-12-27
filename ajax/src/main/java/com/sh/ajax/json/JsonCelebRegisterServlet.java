package com.sh.ajax.json;

import com.google.gson.Gson;
import com.sh.ajax.celeb.model.entity.Celeb;
import com.sh.ajax.celeb.model.entity.Type;
import com.sh.ajax.celeb.model.service.CelebService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/json/celeb/register")
public class JsonCelebRegisterServlet extends HttpServlet {
    private CelebService celebService = new CelebService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 0. multipart/form-data 처리
        // DiskFileItemFactory
        // absolute path 복붙해넣기
        File repository = new File("/Users/hanjunhee/WorkSpaces/web_server_workspace/ajax/src/main/webapp/images/celeb");
        int sizeThreshold = 10 * 1024 * 1024; // 10mb
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(repository);
        factory.setSizeThreshold(sizeThreshold);
        // ServletFileUpload
        ServletFileUpload servletFileUpload = new ServletFileUpload(factory);

        // 1. 사용자 입력값 처리
        Celeb celeb = new Celeb();

        try {
            // FileItem
            Map<String, List<FileItem>> fileItemMap = servletFileUpload.parseParameterMap(req);

            // 텍스트 처리
            String name = fileItemMap.get("name").get(0).getString("utf-8"); // List<FileItem> -> FileItem -> 실제값
            Type type = Type.valueOf(fileItemMap.get("type").get(0).getString("utf-8")); // List<FileItem> -> FileItem -> 실제값 -> enum으로 변환
            celeb.setName(name);
            celeb.setType(type);

            // 파일 처리
            FileItem profileFileItem = fileItemMap.get("profile").get(0);
            if(profileFileItem.getSize() > 0){
                // 파일명 가져오기
                String profile = profileFileItem.getName(); // 사용자가 업로드한 파일명
                celeb.setProfile(profile);
                // 파일 저장
                profileFileItem.write(new File(repository, profile)); // 부모 디렉토리 , 파일명
                // throw Exception
            }
        }  catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println(celeb);

        // 2. 업무로직
        int result = celebService.insertCeleb(celeb);

        // 3. 비동기로 DML 요청 시 redirect 없음. 적절한 json 응답을 주면 됨 (성공/실패)
        // 사용자 메세징
        Map<String, Object> resultMap = Map.of("msg", "정상등록 되었습니다!");
        resp.setContentType("application/json; charset=utf-8");
        new Gson().toJson(resultMap, resp.getWriter());
    }
}