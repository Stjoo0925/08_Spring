package com.ohgiraffers.section02.annotation.subSection03.collection;

import com.ohgiraffers.section02.common.Poketmon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("poketmonServiceCollection")
public class PoketmonService {

//    private List<Pocketmon> pocketmonList;
//
//    @Autowired
//    public PoketmonService(List<Pocketmon> pocketmonLsit) {
//        this.pocketmonList = pocketmonLsit;
//    }
//
//
//    public void poketmonAttack(){
//        pocketmonList.forEach(Pocketmon::attack);
//    }

    private Map<String, Poketmon> poketmonMap;

    @Autowired
    public PoketmonService(Map<String, Poketmon> poketmonMap) {
        this.poketmonMap = poketmonMap;
    }

    public void poketmonAttack(){
        poketmonMap.forEach((k,v) -> {
            System.out.println("key : " + k);
            System.out.print("value : ");
            v.attack();
        });
    }
}
