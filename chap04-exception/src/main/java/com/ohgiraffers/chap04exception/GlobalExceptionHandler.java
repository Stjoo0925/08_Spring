package com.ohgiraffers.chap04exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public String nullPointExceptionHandler(NullPointerException exception) {
        System.out.println("Global 레벨의 exception 처리");
        return "error/nullPointer";
    }

    @ExceptionHandler(MemberRegistException.class)
    public String userExceptionHandler(Model model, MemberRegistException e) {
        System.out.println("Global 레벨의 exception 처리");
        model.addAttribute("exception", e);
        return "error/memberregist";
    }

    // 모든 예외를 처리하는 핸들러
    @ExceptionHandler(Exception.class)
    public String nullPointExceptionHandler(Exception exception) {
        System.out.println("Global exception 처리");
        return "error/default";
    }
}
