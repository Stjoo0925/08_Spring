package spring.practice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PageController {

    @GetMapping("/Refresh")
    public String Refresh() {
        return "redirect:/";  // 홈 페이지 URL로 리다이렉트
    }

    @GetMapping("go-page1")
    public String showPage1(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "page1";
    }

    @GetMapping("/process-page")
    public String processPage(@RequestParam String pageSelect, @RequestParam String message, RedirectAttributes redirectAttributes) {
        if ("success".equals(pageSelect)) {
            redirectAttributes.addFlashAttribute("message", message);
            return "redirect:/success-response";
        } else if ("failure".equals(pageSelect)) {
            redirectAttributes.addFlashAttribute("error", "잘못된 요청입니다.");
            return "redirect:/failure-response";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/success-response")
    public String successResponse(RedirectAttributes redirectAttributes) {
        // 성공 처리 로직
        return "page2";  // 성공 시 보여줄 페이지
    }

    @GetMapping("/failure-response")
    public String failureResponse(RedirectAttributes redirectAttributes) {
        // 실패 처리 로직
        return "index";  // 실패 시 보여줄 페이지
    }
}
