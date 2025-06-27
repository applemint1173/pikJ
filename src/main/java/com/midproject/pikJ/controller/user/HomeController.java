// 박지영
package com.midproject.pikJ.controller.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController implements ErrorController {

    @GetMapping({"", "/"})
    public String home() {
        return "user/home/home";
    }

    @RequestMapping("/error")
    public String errorPage(HttpServletRequest request) {
        Object status = request.getAttribute("javax.servlet.error.status_code");

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            // 404 에러
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "user/login/onlyManagerError";
            }
        }

        // 그 외 모든 에러 및 status가 null
        return "user/login/onlyManagerError";
    }

}
