package com.sh.web.servlet;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/*
 * Servlet - 업무로직 처리. jsp로 forwarding
 * JSP - html 작성 
 * 
 */
@WebServlet("/testPerson3.do") // 동일한 Servlet이 있으면 작동안한다 
public class TestPersonServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//요청 메세지에 대한 인코딩 처리
		req.setCharacterEncoding("utf-8"); // 대소문자 구별 안함 
		
		//사용자 입력값 처리
		String name = req.getParameter("name");
		String color = req.getParameter("color");
		String animal = req.getParameter("animal");
		String[] foods = req.getParameterValues("food"); //array는 values로 해주기 
		
		System.out.println(name);
		System.out.println(color);
		System.out.println(animal);
		System.out.println(Arrays.toString(foods));
		
		//업무로직 : 사용자의 취향을 고려해 추천 키워드를 제공
		//switch statement : JDK 17 이상
		String recommendation = switch(color) {
		case "빨강" -> "매운 떡볶이";
		case "노랑" -> "노랑 동화책";
		case "초록" -> "초록 상추쌈";
		case "파랑" -> "시원한 사이다 ";
		default -> "몰름";
		};
		System.out.println(recommendation);
		//jsp 전달하기 - request 객체에 속성으로 저장 
		req.setAttribute("recommendation", recommendation);
		
		
		//jsp 포워딩 (RequestDispatcher)
		//파일 경로(절대경로) src/main/webapp(웹루트) 이하부터 작성 
		RequestDispatcher reqDispatcher = 
				req.getRequestDispatcher("/WEB-INF/views/testPersonResult.jsp");
		reqDispatcher.forward(req, resp);
		
	}

}
