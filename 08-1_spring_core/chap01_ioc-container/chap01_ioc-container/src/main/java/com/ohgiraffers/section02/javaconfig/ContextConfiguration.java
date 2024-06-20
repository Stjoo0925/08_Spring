package com.ohgiraffers.section02.javaconfig;

import com.ohgiraffers.common.MemberDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// spring-context.xml과 같은 역할을 하는 클래스
@Configuration("configurationSection02")
public class ContextConfiguration {

    // Configuration : 구성

    @Bean(name = "member")
    public MemberDTO getMember() {
        return new MemberDTO(1, "user01", "pass01", "홍길동");
    }

}
