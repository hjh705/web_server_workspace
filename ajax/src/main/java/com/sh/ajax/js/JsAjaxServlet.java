package com.sh.ajax.js;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/js")
public class JsAjaxServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 0. 인코딩 설정
        req.setCharacterEncoding("utf-8");
        // 1. 사용자 입력값 처리
        String name = req.getParameter("name");
        int num = Integer.parseInt(req.getParameter("num"));
        System.out.println("name =" + name);
        System.out.println("num =" + num);
        // 2. 업무로직

        // 3. view 단 처리
        resp.setContentType("text/html; charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.println("이름은" + name + "입니다");
        out.println("숫자는" + num + "입니다");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}