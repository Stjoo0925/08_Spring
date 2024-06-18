package com.ohgiraffers.chap07thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("lecture")
// 우리는 특정 uri로 요청을 보내면 Controller에서 어떠한 방식으로 처리할지 정의를 한다.
// 이때 들어온 요청을 특정 메서드와 매핑하기 위해 사용하는 것이 @RequestMapping이다.
public class LectureController {

    @GetMapping("expression") // URL에 데이터를 포함시켜 요청
    public ModelAndView expression(ModelAndView mv) {
        // ModelAndView : Model + View를 합쳐놓은 방식이다.
        // 값을 넣을 때는 addObject()를 사용하고, 값을 보낼 View를 세팅하는 것은 setViewName()을 사용한다.
        mv.setViewName("lecture/expression");
        return mv;
    }

    @GetMapping("etc")
    public void etc() {

    }

}
