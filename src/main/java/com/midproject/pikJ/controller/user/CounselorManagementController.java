//0623 김태준 생성
package com.midproject.pikJ.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/management/counselor")
@Controller
public class CounselorManagementController {

    String url = "user/management/counselor";

    @GetMapping("/list")
    public String list() {
        return url + "/list";
    }

    @GetMapping("/view")
    public String view() {
        return url + "/view";
    }

    @GetMapping("/sujung")
    public String sujung() {
        return url + "/sujung";
    }

    @PostMapping("/sujungProc")
    public String sujungProc() {
        return "redirect:/" + url + "/view";
    }

}