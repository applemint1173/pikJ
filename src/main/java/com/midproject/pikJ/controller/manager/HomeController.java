package com.midproject.pikJ.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/manager/home")
    public String home() {
        return "manager/home/home";
    }

    @GetMapping({"", "/"})
    public String home2() {
        return "user/home/home";
    }

}
