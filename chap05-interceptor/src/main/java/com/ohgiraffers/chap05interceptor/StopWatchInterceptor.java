package com.ohgiraffers.chap05interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 핸들러 인터셉터 상속받기
 */

@Component
public class StopWatchInterceptor implements HandlerInterceptor {

    private final MenuService menuService;

    public StopWatchInterceptor(MenuService menuService) {
        this.menuService = menuService;
    }

    @Override // 전처리를 위한 핸들러 filter -> dispatcherServlet -> interceptor -> controller
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        System.out.println("preHandle 동작함...");
        long startTime = System.currentTimeMillis();

        request.setAttribute("startTime", startTime);

        return true;
    }

    @Override // 후처리를 위한 핸들러 filter -> controller -> interceptor -> dispatcherServlet
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {

        System.out.println("postHandle 동작함...");
        long startTime = (Long) request.getAttribute("startTime");

        request.removeAttribute("startTime");
        long endTime = System.currentTimeMillis(); // currentTimeMillis : 현재의 시간을 초단위로 나누어 반환

        modelAndView.addObject("interval", endTime - startTime);
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {
        System.out.println("afterCompletion 동작함...");

        menuService.method();
    }
}
