package com.ohgiraffers.section01.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Map;

import static javax.swing.UIManager.put;

/**
 * AOP(Aspect-oriented Programming, 관점 지향 프로그래밍)
 * 프로그램의 관심사를 분리하여 모듈화 하는 것을 목표로 한다.
 * aop는 특히 애플리케이션의 핵심 비즈니스 로직과는 별도로 부가적인 기능 (예 : 로깅, 보안, 트렌젝션 관리 등)을 분리하여
 * 코드의 모듈성을 높이고 유지보수성을 향상시키는데 유용하다.
 * <p>
 * 어드바이스란 어스펙트가 수행해야할 실제 동작을 정의하는 부분이다.
 */

/**
 * 어드바이스란 어스펙트가 수행해야할 실제 동작을 정의하는 부분이다.
 */

/**
 * pointcut : 특정 조건에 의해 필터링된 조인포인트, 수많은 조인포인트 중에 특정 메소드만 횡단 공통기능을 수행하기 위해서 사용한다.
 * advice : 횡단 관심에 해당하는 공통 기능의 코드, 독립된 클래스의 메서드로 작성한다.
 *   - before : 메소드 실행 전에 동작
 *   - After : 메소드 실행 후에 동작
 *   - After -returning : 메소드가 정상적으로 실행된 후에 동작
 *   - After -throwing : 예외가 발생한 후에 동작
 *   - Aroung : 메소드 호출 이전, 이후, 예외발생 등 모든 시점에 동작
 */

@Aspect
@Component
public class LogginAspect {

    /**
     * Pointcut : 관심 조언 포인트를 결정하여 어드바이스가 실행되는 시기를 제어한다.
     * execution : 메서드 실행 조인 포인트를 매칭한다.
     * execution ([수식어] 리턴타입 [클래스이름].이름(파라미터))
     * 수식어는 생략이 가능하다 public price protected default (접근제어자)
     * *Service.*(..) : 매개변수가 0개 이상인 모든 메서드
     * *Service.*(*) : 매개변수가 1개인 모든 메서드
     * *Service.*(*,..) : 매개변수가 2개인 모든 메서드
     */

    @Pointcut("execution(* com.ohgiraffers.section01.aop.*Service.*(..))")
    public void logPointcut() {

    }

    @Before("LogginAspect.logPointcut()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("before joinPoint.getTarget() " + joinPoint.getTarget());
        System.out.println("before joinPoint.getSignature() " + joinPoint.getSignature());
        if (joinPoint.getArgs().length > 0) {
            System.out.println("before joinPoint.getArgs()[0] " + joinPoint.getArgs()[0]);
        }
    }

    @After("logPointcut()")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("after joinPoint.getTarget() " + joinPoint.getTarget());
        System.out.println("after joinPoint.getSignature() " + joinPoint.getSignature());
        if (joinPoint.getArgs().length > 0) {
            System.out.println("after joinPoint.getArgs()[0] " + joinPoint.getArgs()[0]);
        }
    }

    @AfterReturning(pointcut = "logPointcut()", returning = "result")
    public void logafterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("AfterReturning result " + result);
        if (result != null && result instanceof Map) {
            ((Map<Long, MemberDTO>) result).put(100L, new MemberDTO(100L, "반환값 가공"));
        }
    }

    /**
     * aop가 적용될 메소드가 에러를 발생하여 Exception을 던지는 시점을 말한다.
     */

    @AfterThrowing(pointcut = "logPointcut()", throwing = "exception")
    public void logAfterThrowing(Throwable exception) {
        System.out.println("After throwing exception " + exception);
    }

    /**
     * Around Advice는 가장 강력한 어드바이스로
     * 이 어드바이스는 조인 포인트를 완전히 장악하기 때문에
     * 앞에서 살펴 본 어드바이스 모두 Around 어드바이스로 조합할 수 있다.
     * 심지어 원본 조인포인트를 언제 실행할지, 실행 자체를 안할지, 계속 실항할지 여부까지도 제어한다.
     * AroundAdvice의 조인포인트는 매개변수 ProceedingJoinPoint로 고정되어 있다.
     * JoinPoint의 하위 인터페이스로 원본 조인포인트의 진행 시점을 제어할 수 있다.
     * 조인포인트 진행하는 호출을 잊는 경우가 자주 발생하기 때문에 주의해야 하여
     * 최소한의 요건을 충족하면서도 가장 기능이 약한 어드바이스를 쓰는게 바람직하다.
     */

//    @Around("logPointcut()")
//    public Object logAround(ProceedingJoinPoint joinPoint)throws Throwable {
//        System.out.println("Around Before : " + joinPoint.getSignature().getName());
//        Object result = joinPoint.proceed();
//        System.out.println("Around after : " + joinPoint.getSignature().getName());
//        return result;
//    }
}
