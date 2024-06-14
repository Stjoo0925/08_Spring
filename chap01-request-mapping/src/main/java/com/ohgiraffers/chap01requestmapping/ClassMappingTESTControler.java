package com.ohgiraffers.chap01requestmapping;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/*
 * 클래스 레벨에 @RequestMapping 어노테이션 사용이 가능하다.
 * 클래스레벨에 url을 공통 부분을 이용해 설정하고 나며 매번 핸들러 메소드에 url의 중복되는 내용은 작성하지 않아도 된다.(RequestMapping)
 * 이 때 와일드 카드를 이용해서 조금 더 포괄적인 url패턴을 설정할 수 있다. (* : 와일드 카드)
 * */

// 실행의 주체가 개발자가 아닐때 프레임워크라고 부르고, 주체가 개발자일때 라이브러리 라고 한다.
// 사용자의 요청을 처리해주는 클래스
// 사용자의 요청과 클래스를 매칭하는것

@Controller
@RequestMapping("/orders/*")
public class ClassMappingTESTControler {

    // class 레벨 매핑
    @GetMapping("/regist")
    public String registOrder(Model model) {

        model.addAttribute("message", "get 방식의 주문 등록용 핸들러 메소드를 호출함");

        return "mappingResult";
    }

    @PostMapping("/regist")
    public String registOrderPost(Model model) {

        model.addAttribute("message", "post 방식의 주문 등록용 핸들러 메소드를 호출함");

        return "mappingResult";
    }

    // 여러 개의 패턴 매칭
    // value 속성에 중괄호를 이용해 매핑할 url을 나열한다.
    @RequestMapping(value = {"modify", "delete"}, method = RequestMethod.POST)
    public String modifyAndDelete(Model model) {

        model.addAttribute("message", "post 방식의 주문 정보 수정과 주문 정보 삭제 공통 철용 핸들러 메소드 호출함");

        return "mappingResult";
    }

    // order/1 : get => path variable
    // order?name=아메리카노 : get => Query String Parameter
    // {orderNo} 와 public의 orderNo 를 매칭해줘야함

    /* path variable
    * @PathVariable 어노테이션을 이용해 요청 Path 로 부터 변수를 받아올 수 있다.
    * Path Variable 로 전달 되는 {변수명} 값은 반드시 매개변수명과 동일해야 한다.
    * 만약 동일하지 않으면 @PathVariable("이름")을 설정해주어야 한다.
    * 이는 Rest형 웹 서비스를 설계할 때 유용하게 사용된다.
    */



    @GetMapping("/detail/{orderNo}")
    public String selectOrderDetail(Model model, @PathVariable("orderNo") int orderNo) {

        model.addAttribute("message", orderNo + "번 주문 상세 내용 조회용 핸들러");

        return "mappingResult";
    }

    @GetMapping("/")
    public String registOrders(Model model, @RequestParam String test, @RequestParam String value) {

        model.addAttribute("message", test + " : " + value);

        return "mappingResult";
    }

    /*
    * 그 외의 다른 요청
    * @RequestMapping 어노테이션에 아무런 url 을 설정하지 않으면 요청 처리에 대한 핸들러 메소드가
    * 준비 되지 않았을 때 해당 메소드를 호출한다.
     */

    @RequestMapping
    public String OrderRequest(Model model) {

        model.addAttribute("message","order 요청이긴 하지만 다른 기능 준비 안함");

        return "OrderError";
    }
}
















