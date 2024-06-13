package com.ohgiraffers.chap01requestmapping;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * 클래스 레벨에 @RequestMapping 어노테이션 사용이 가능하다.
 * 클래스레벨에 url을 공통 부분을 이용해 설정하고 나며 매번 핸들러 메소드에 url의 중복되는 내용은 작성하지 않아도 된다.(RequestMapping)
 * 이 때 와일드 카드를 이용해서 조금 더 포괄적인 url패턴을 설정할 수 있다. (* : 와일드 카드)
 * */

// 실행의 주체가 개발자가 아닐때 프레임워크라고 부르고, 주체가 개발자일때 라이브러리 라고 한다.
// 사용자의 요청을 처리해주는 클래스
@Controller
// 사용자의 요청과 클래스를 매칭하는것
@RequestMapping("/orders/*")
public class ClassMappingTESTControler {

    // class 레벨 매핑
    @GetMapping("/regist")
    public String registOrder(Model model) {
        model.addAttribute("message","get 방식의 주문 등록용 핸들러 메소드를 호출함");
        return "mappingResult";
    }
}
