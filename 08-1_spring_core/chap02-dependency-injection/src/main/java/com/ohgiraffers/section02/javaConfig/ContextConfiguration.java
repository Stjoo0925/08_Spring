package com.ohgiraffers.section02.javaConfig;


import com.ohgiraffers.common.Account;
import com.ohgiraffers.common.MemberDTO;
import com.ohgiraffers.common.PersonalAccount;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContextConfiguration {

    @Bean(name = "account")
    public Account accountGenerator(){
        return new PersonalAccount(20,"3333-03-233232");
    }

    @Bean(name = "member")
    public MemberDTO memberGenerator(){
        MemberDTO member = new MemberDTO();
        member.setSequence(1);
        member.setName("홍길동");
        member.setPhone("010-2323-2323");
        member.setEmail("gildong@gmail.com");
        member.setPersonalAccount(accountGenerator());
        return member;
    }
}
