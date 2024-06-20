package com.ohgiraffers.section01.xmlConfig;

import com.ohgiraffers.common.MemberDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Application01 {

    public static void main(String[] args) {

        ApplicationContext context = new GenericXmlApplicationContext("section01/xmlConfig/spring-config.xml");
        MemberDTO member = context.getBean(MemberDTO.class);
        System.out.println(member);
    }
}
