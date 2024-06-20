package com.ohgiraffers.section02.annotation.subSection04.resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application01 {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.ohgiraffers.section02");

        String[] beanNames = context.getBeanDefinitionNames();

        for (String bean : beanNames) {
            System.out.println(bean);
        }

        PoketmonService poketmonService = context.getBean("poketmonServiceResource", PoketmonService.class);

        poketmonService.poketmonAttack();
    }
}
