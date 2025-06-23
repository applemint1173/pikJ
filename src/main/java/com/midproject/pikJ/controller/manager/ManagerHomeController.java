package com.midproject.pikJ.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManagerHomeController {

    @GetMapping("/manager/home")
    public String managerHome() {
        return "manager/home/home";
    }

}
