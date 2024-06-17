package com.ohgiraffers.chap03viewresolver;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping(value = { "/"})
    public String main() {
        return "main";
    }

    /*
    *   RequestToViewNameTranslator(interface)
    *   spring 에서 반환 타입이 void 인 경우 요청 url 을 기반으로 뷰를 해석하려고하는데
    *   이때 RequestToViewNameTranslator 를 사용하여 url 을 뷰의 이름으로 변환한다.
    * */


    @RequestMapping(value= "/main")
    public void mainPage() {

    }
}
