package com.ohgiraffers.chap03viewresolver;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/*")
public class ResoluverController {

    @GetMapping("string")
    public String stringReturning(Model model) {

        model.addAttribute("forwardMessage", "문자열로 뷰 이름 반환함..");

        return "result";
    }

    /**
     *   문자열로 뷰 이름을 반환한다는 것은 반환 후
     *   ThymeleafViewResolver 에게 resources/template/를 prefix 로 .html 을 suffix 로 하여
     *   resources/template/result.html 파일로 응답 뷰를 설정하라는 의미가 된다.
     */

    @GetMapping("string-redirect")
    public String StringRedirect() {
        return "redirect:/";
    }

    /**
     *   기본적으로 redirect 시에는 재요청이 발생하므로 request scope는 소멸된다.
     *   하지만 스프링에서는  redirectAttributes 타입을 통해 redirect 시 속성 값을 저장할 수 있도록
     *   하는 기능을 제공한다.
     */

    @GetMapping("string-redirect-attr")
    public String stringRedirectFlashAttribute(RedirectAttributes rttr) {
        rttr.addFlashAttribute("flashMessage1", "리다이렉트 attr 사용하여 redirect...");
        return "redirect:/";
    }

    /**
     *   리다이렉트 시 flash 영역에 담아서 redirect 할 수 있다.
     *   자동으로 모델에 추가되기 때문에 requestScope 에서 값으 꺼내면 된다.
     *   세션에 임시로 값을 담고 소멸하는 방식이기 때문에 session 에 동일한 키값이 존재하지 않아야 한다.
     */

    @GetMapping("modelandview")
    public ModelAndView modelAndViewReturning(ModelAndView mv) {
        mv.addObject("forwardMessage", "ModelAndView를 이용한 모델과 뷰 반환 ");
        mv.setViewName("result");
        return mv;
    }

    /**
     *   모델과 뷰를 합친 개념이다.
     *   핸들러 어댑터가 핸들러 메소드를 호출하고 반환받은 문자열을 ModelAndView로 만들어 dispatcherServlet에 반환한다.
     *   이 떄 문자열을 반환해도 되지만 ModelAndView를 미리 만들어 반환할 수 도 있다.
     */

    @GetMapping("modelandview-redirect")
    public ModelAndView modelViewRedirect(ModelAndView mv) {
        mv.setViewName("redirect:/");
        return mv;
    }

    @GetMapping("modelandview-redirect-attr")
    public ModelAndView modelAndRedirectAttr(ModelAndView mv, RedirectAttributes rttr) {
        rttr.addFlashAttribute("flashMessage2","ModelAndView를 이용한 redirect attr");
        mv.setViewName("redirect:/");
        return mv;
    }
}
