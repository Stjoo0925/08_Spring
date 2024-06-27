package com.ohgiraffers.section01.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application01 {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.ohgiraffers.section01");
        MemberService memberService = context.getBean("memberService", MemberService.class);

        System.out.println("=========== select Members ===========");

//        System.out.println(memberService.selectMembers());

        System.out.println("=========== select Member ===========");
        System.out.println(memberService.selectMember(10L));
    }
}
