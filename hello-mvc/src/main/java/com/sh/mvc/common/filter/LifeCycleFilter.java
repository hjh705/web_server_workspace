package com.sh.mvc.common.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter의 생명주기
 * - 서버 구동시 Filter 객체를 만들고, 그 이후 해당 객체를 재사용(싱글턴)
 * 1. 생성자 호출
 * 2. init 호출
 *
 * 3. doFilter (전처리 / 후처리)
 *   - Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
 *   - HttpFilter#doFilter(HttpServletRequest, HttpServletResponse, FilterChain)
 *
 * 4. destroy 호출
 */
// @WebFilter("/*") //주석해두면 미사용
public class LifeCycleFilter extends HttpFilter {

    public LifeCycleFilter() {
        System.out.println("LifeCycleFilter 생성자 호출");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("LifeCycleFilter#init 호출");
        super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("LifeCycleFilter#doFilter(ServletRequest/ServletResponse) 호출");
        super.doFilter(request, response, chain);
    }

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("LifeCycleFilter#doFilter(HttpServletRequest/HttpServletResponse) 호출");
        super.doFilter(request, response, chain);
    }

    @Override
    public void destroy() {
        System.out.println("LifeCycleFilter#destroy 호출");
        super.destroy();
    }
}
