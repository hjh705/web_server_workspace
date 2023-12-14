package com.sh.mvc.common.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter("/*")
public class EncodingFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //전처리 (servlet 가기 전)
        request.setCharacterEncoding("utf-8");
        System.out.println("utf-8 적용 완료");
        super.doFilter(request, response, chain);
    }
}
