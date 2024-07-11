package com.ohgiraffers.chap05interceptor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 필터와 서블릿
 *
 * filter
 * servlet 스펙에 정의된 기능으로 서블릿 컨테이너 레벨에서 동작한다.
 * spring mvc와는 독립적으로 동작하며 주로 요청이 서블릿에 도달하기 전에 작동한다.
 * 핕터는 모든 요청과 응답을 가로 채며, 주로 보안, 로깅, 인증 등과 같은 작업을 수행한다.
 *
 * interceptor
 * spring mvc 레벨에서 동작하면 spring의 handler interceptor 인터페이스를 구현한다.
 * 컨트롤러에 요청이 도달하기 전과 후, 그리고 뷰가 렌더링 된 후에 동작한다.
 * 주로 비즈니스 로직과 전처리 및 후처리 작업을 수행한다.
 *
 * 적용 범위
 * filter
 * 모든 http 요청과 응답을 대상으로 한다.
 * 서블릿 컨텍스트에 등록하여 특정 url 패턴에 적용할 수 있다.
 *
 * interceptor
 * spring mvc의 핸들러 메서드에만 적용된다.
 * 특정 url 패턴이나 특정 핸들러 메서드에만 적용할 수 있다.
 */

@SpringBootApplication
public class Chap05InterceptorApplication {

    public static void main(String[] args) {
        SpringApplication.run(Chap05InterceptorApplication.class, args);
    }

}
