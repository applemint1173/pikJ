package com.midproject.pikJ.controller.manager;

import com.midproject.pikJ.dto.MemberDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManagerHomeController {

    @GetMapping("/manager/home")
    public String managerHome() {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        return "manager/home/home";
    }

}
