// 박지영
package com.midproject.pikJ.controller.user;

import com.midproject.pikJ.dto.CounselorDTO;
import com.midproject.pikJ.dto.LoginDTO;
import com.midproject.pikJ.dto.ManagementDTO;
import com.midproject.pikJ.dto.MemberDTO;
import com.midproject.pikJ.service.CounselorService;
import com.midproject.pikJ.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;
    private final CounselorService counselorService;

    @GetMapping("/login")
    public String login() {
        return "user/login/login";
    }

    @PostMapping("/loginProc")
    public String loginProc(
            HttpSession httpSession,
            LoginDTO loginDTO
    ) {
        String id = loginDTO.getId();
        String pwd = loginDTO.getPwd();

        String url = "redirect:/";

        if (loginDTO.getCheck().equals("일반")) {
            MemberDTO sendDTO = new MemberDTO();
            sendDTO.setId(id);
            sendDTO.setPwd(pwd);

            MemberDTO memberDTO = memberService.getSelectLoginOne(sendDTO);

            if (memberDTO == null) {
                url = "redirect:/login";
            }

            if (!pwd.equals(memberDTO.getPwd())) {
                url = "redirect:/login";
            }

            httpSession.setAttribute("member", memberDTO);
        }

        if (loginDTO.getCheck().equals("상담사")) {
            CounselorDTO sendDTO = new CounselorDTO();
            sendDTO.setId(id);
            sendDTO.setPwd(pwd);

            CounselorDTO counselorDTO = counselorService.getSelectLoginOne(sendDTO);

            if (counselorDTO == null) {
                url = "redirect:/login";
            }

            if (!pwd.equals(counselorDTO.getPwd())) {
                url = "redirect:/login";
            }

            httpSession.setAttribute("counselor", counselorDTO);
        }

        return url;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();

        return "user/login/logout";
    }

    @GetMapping("/onlyMemberError")
    public String onlyMemberError(HttpSession session) {

        return "user/login/onlyMemberError";
    }

    @GetMapping("/onlyCounselorError")
    public String onlyCounselorError(HttpSession session) {

        return "user/login/onlyCounselorError";
    }

}
