package com.ohgiraffers.section02.initDestory.subSection03.xml;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class Owner {

    @PostConstruct // 생성때 실행될 메서드
    public void openShop() {
        System.out.println("사장님이 가게 문을 열었습니다. 이제 쇼핑을 하실 수 있습니다.");
    }

    @PreDestroy // 소멸될때 실행될 메서드
    public void closeShop() {
        System.out.println("사장님이 가게 문을 닫았습니다. 이제 쇼핑을 하실 수 없습니다.");
    }
}
