package com.ohgiraffers.section02.common;

import org.springframework.stereotype.Component;

// @Component : 해당 클래스를 bean으로 지정
@Component
public class Charmander implements Poketmon {

    @Override
    public void attack() {
        System.out.println("파이리 불꽃 공격");
    }
}
