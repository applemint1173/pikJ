//0623 김태준 생성
package com.midproject.pikJ.controller.user;

import com.midproject.pikJ.dto.CounselorDTO;
import com.midproject.pikJ.dto.ManagementDTO;
import com.midproject.pikJ.service.ManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/management/counselor")
@RequiredArgsConstructor
public class CounselController {

    private final ManagementService managementService;

    String url = "user/management/counselor";

    @PostMapping("/list")
    public String list(
            Model model,
            CounselorDTO counselorDTO
    ) {
        List<ManagementDTO> list = managementService.getSelectByCounselorId(counselorDTO.getId());
        model.addAttribute("list",list);

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