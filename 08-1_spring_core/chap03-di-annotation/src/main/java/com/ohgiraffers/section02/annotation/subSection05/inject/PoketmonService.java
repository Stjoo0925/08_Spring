package com.ohgiraffers.section02.annotation.subSection05.inject;

import com.ohgiraffers.section02.common.Poketmon;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service("poketmonServiceInject")
public class PoketmonService {

//    1. 필드 주입 방식
//    @Inject
//    @Qualifier("charmander")

    private Poketmon poketmon;

//  2. 생성자 주입 방식
    @Inject
    public PoketmonService(@Qualifier("squirtle") Poketmon poketmon) {
        this.poketmon = poketmon;
    }

    public void poketmonAttack(){
        poketmon.attack();
    }

}
