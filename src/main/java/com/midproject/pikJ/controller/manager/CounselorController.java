// 김태준
package com.midproject.pikJ.controller.manager;

import com.midproject.pikJ.dto.CounselorDTO;
import com.midproject.pikJ.service.CounselorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/manager/counselor")
@Controller
@RequiredArgsConstructor
public class CounselorController {

    private final CounselorService service;

    String folderName = "manager/counselor/";

    @GetMapping("/list")
    public String list(Model model) {
        List<CounselorDTO> list = service.getSelectAll();
        model.addAttribute("list",list);
        return folderName + "list";
    }

    @GetMapping("/view/{no}")
    public String view(CounselorDTO counselorDTO, Model model) {
        CounselorDTO returnDTO = service.getSelectOne(counselorDTO);
        model.addAttribute("returnDTO",returnDTO);
        return folderName + "view";
    }

    @GetMapping("/chuga")
    public String chuga() {
        return folderName + "chuga";
    }

    @PostMapping("/chugaProc")
    public String chugaProc(CounselorDTO counselorDTO) {
        service.setInsert(counselorDTO);
        return "redirect:/" + folderName + "list";
    }

    @GetMapping("/sujung/{no}")
    public String sujung(CounselorDTO counselorDTO, Model model) {
        CounselorDTO returnDTO = service.getSelectOne(counselorDTO);
        model.addAttribute("returnDTO",returnDTO);
        return folderName + "sujung";
    }

    @PostMapping("/sujungProc")
    public String sujungProc(CounselorDTO counselorDTO) {
        service.setUpdate(counselorDTO);
        return "redirect:/" + folderName + "view/" + counselorDTO.getNo();
    }

    @GetMapping("/sakje/{no}")
    public String sakje(CounselorDTO counselorDTO, Model model) {
        CounselorDTO returnDTO = service.getSelectOne(counselorDTO);
        model.addAttribute("returnDTO",returnDTO);
        return folderName + "sakje";
    }

    @PostMapping("/sakjeProc")
    public String sakjeProc(CounselorDTO counselorDTO) {
        service.setDelete(counselorDTO);
        return "redirect:/" + folderName + "list";
    }
}
