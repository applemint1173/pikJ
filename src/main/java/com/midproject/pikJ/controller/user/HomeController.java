// 박지영
package com.midproject.pikJ.controller.user;

import com.midproject.pikJ.dto.LoginDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"", "/"})
    public String home() {
        return "user/home/home";
    }

    @GetMapping("/homeImsi")
    public String homedImsi(
            HttpSession httpSession,
            Model model
    ) {
        String id = (String) httpSession.getAttribute("id");

        model.addAttribute("id", id);
        return "user/home/homeImsi";
    }

}
