// 박지영
package com.midproject.pikJ.controller.manager;

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
@RequestMapping("/manager/management")
@RequiredArgsConstructor
public class ManagementController {

    private final ManagementService service;
    String folderName = "manager/management";

    @GetMapping("/list")
    public String list(
            Model model
    ) {
        List<ManagementDTO> list = service.getSelectAll();
        model.addAttribute("list", list);

        return folderName + "/list";
    }

    @GetMapping("/view/{no}")
    public String view(
            Model model,
            ManagementDTO submitDTO
    ) {
        ManagementDTO returnDTO = service.getSelectOne(submitDTO);
        model.addAttribute("returnDTO", returnDTO);

        return folderName + "/view";
    }

    @GetMapping("/chuga")
    public String chuga(
    ) {
        return folderName + "/chuga";
    }

    @PostMapping("/chugaProc")
    public String chugaProc(
            ManagementDTO submitDTO
    ) {
        service.setInsert(submitDTO);

        // 존재하지 않는 내담자, 상담사, 학교 정보 처리할 것
        // 서비스 반환형 이용해서 실패시 경고, 성공시 처리

        return "redirect:/" + folderName + "/list";
    }

    @GetMapping("/sujung/{no}")
    public String sujung(
            Model model,
            ManagementDTO submitDTO
    ) {
        ManagementDTO returnDTO = service.getSelectOne(submitDTO);

        model.addAttribute("returnDTO", returnDTO);

        return folderName + "/sujung";
    }

    @PostMapping("/sujungProc")
    public String sujungProc(
            ManagementDTO submitDTO
    ) {
        service.setUpdate(submitDTO);

        return "redirect:/" + folderName + "/view/" + submitDTO.getNo();
    }

    @GetMapping("/sakje/{no}")
    public String sakje(
            Model model,
            ManagementDTO submitDTO
    ) {
        ManagementDTO returnDTO = service.getSelectOne(submitDTO);
        model.addAttribute("returnDTO", returnDTO);

        return folderName + "/sakje";
    }

    @PostMapping("/sakjeProc")
    public String sakjeProc(
            ManagementDTO submitDTO
    ) {
        System.out.println(submitDTO.getNo() + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        service.setDelete(submitDTO);

        return "redirect:/" + folderName + "/list";
    }

}
