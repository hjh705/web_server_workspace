package com.sh.mvc.index.controller;

import com.sh.mvc.member.model.entity.Member;
import com.sh.mvc.notification.model.entity.Notification;
import com.sh.mvc.notification.model.service.NotificationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("")
public class IndexServlet extends HttpServlet {
    private NotificationService notificationService = new NotificationService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("index...");
        // 로그인 후 필요한 데이터를 로드해서 jsp에 전달
        Member loginMember = (Member) req.getSession().getAttribute("loginMember");
        if(loginMember != null){
            List<Notification> notifications = notificationService.findByMemberId(loginMember.getId());
            req.setAttribute("notifications", notifications);
        }

        req.getRequestDispatcher("/index.jsp").forward(req,resp);
    }
}