package com.sh.ajax.xml;

import com.sh.ajax.celeb.model.entity.Celeb;
import com.sh.ajax.celeb.model.service.CelebService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/xml/celeb/findAll")
public class XmlCelebFindAllServlet extends HttpServlet {
    private CelebService celebService = new CelebService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 사용자 입력값처리

        // 2. 업무로직
        List<Celeb> celebs = celebService.findAll();
        System.out.println(celebs);
        req.setAttribute("celebs", celebs);
        // 3. view단 처리 (jsp를 이용해서 xml을 동적으로 생성하기)
        req.getRequestDispatcher("/WEB-INF/views/xml/celeb.jsp").forward(req,resp);
    }
}