package com.ohgiraffers.chap04exception;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionHandlerController {

    @GetMapping("controller-null")
    public String nullPointExceptionTest() {
        String str = null;

        System.out.println(str.charAt(0));
        return "/";
    }


    // 클래스 레벨의 exception 처리
    @ExceptionHandler(NullPointerException.class)
    public String nullPointExceptionHandler(NullPointerException exception) {
        System.out.println("controller 레벨의 exception 처리");
        return "error/nullPointer";
    }

    @GetMapping("controller-user")
    public String userExceptionTest() throws MemberRegistException {
        boolean check = true;

        if (check) {
            throw new MemberRegistException("회원가입이 불가능합니다.");
        }


        return "/";
    }

    @ExceptionHandler(MemberRegistException.class)
    public String userExceptionHandler(Model model, MemberRegistException e) {
        System.out.println(e.getClass());
        System.out.println("controller 레벨의 exception 처리");
        model.addAttribute("exception", e);
        return "error/memberregist";
    }
}
