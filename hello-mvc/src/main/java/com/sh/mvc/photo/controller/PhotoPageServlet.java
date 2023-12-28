package com.sh.mvc.photo.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sh.mvc.common.LocalDateTimeSerializer;
import com.sh.mvc.photo.model.entity.Photo;
import com.sh.mvc.photo.model.service.PhotoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@WebServlet("/photo/page")
public class PhotoPageServlet extends HttpServlet {
    private PhotoService photoService = new PhotoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 사용자 입력값 처리
        int page = Integer.parseInt(req.getParameter("page"));
        final int limit = 5;
        Map<String, Object> param = Map.of("page", page, "limit", limit);
        System.out.println(param);
        // 2. 업무 로직
        List<Photo> photos = photoService.findAll(param);
        System.out.println(photos);
        // 3. json 응답 처리
        resp.setContentType("application/json; charset=utf-8");
        // 날짜의 경우 json에서 바로 처리가 불가능하기 때문에 common에 관련 클래스를 만들어주고
        // GsonBuilder 를 생성한 뒤 해당 클래스를 적용시키고 출력한다
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
        Gson gson = gsonBuilder.create();
        gson.toJson(photos,resp.getWriter());
    }
}