package com.ohgiraffers.chap04exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OtherController {

    @GetMapping("other-controller-null")
    public String otherNullPointException() {
        String str = null;

        System.out.println(str.charAt(0));
        return "/";
    }

    @GetMapping("other-controller-user")
    public String otherUserException() throws MemberRegistException {
        boolean check = true;

        if (check) {
            throw new MemberRegistException("회원가입이 불가능합니다.");

        }
        return "/";
    }

    @GetMapping("other-controller-array")
    public String otherArrayException() {
        double[] array = new double[0];
        System.out.println(array[0]);

        return "/";
    }
}
