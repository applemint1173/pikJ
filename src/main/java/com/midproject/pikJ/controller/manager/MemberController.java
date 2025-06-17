// 김태준
package com.midproject.pikJ.controller.manager;

import com.midproject.pikJ.dto.MemberDTO;
import com.midproject.pikJ.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/manager/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService service;

    String folderName = "manager/member/";

    @GetMapping("/list")
    public String list(Model model) {
        List<MemberDTO> list = service.getSelectAll();
        model.addAttribute("list",list);
        return folderName + "list";
    }

    @GetMapping("/view/{no}")
    public String view(MemberDTO memberDTO, Model model) {
        MemberDTO returnDTO = service.getSelectOne(memberDTO);
        model.addAttribute("returnDTO",returnDTO);
        return folderName + "view";
    }

    @GetMapping("/chuga")
    public String chuga() {
        return folderName + "chuga";
    }

    @PostMapping("/chugaProc")
    public String chugaProc(MemberDTO memberDTO) {
        service.setInsert(memberDTO);
        return "redirect:/" + folderName + "list";
    }

    @GetMapping("/sujung/{no}")
    public String sujung(MemberDTO memberDTO, Model model) {
        MemberDTO returnDTO = service.getSelectOne(memberDTO);
        model.addAttribute("returnDTO",returnDTO);
        return folderName + "sujung";
    }

    @PostMapping("/sujungProc")
    public String sujungProc(MemberDTO memberDTO) {
        service.setUpdate(memberDTO);
        return "redirect:/" + folderName + "view/" + memberDTO.getNo();
    }

    @GetMapping("/sakje/{no}")
    public String sakje(MemberDTO memberDTO, Model model) {
        MemberDTO returnDTO = service.getSelectOne(memberDTO);
        model.addAttribute("returnDTO",returnDTO);
        return folderName + "sakje";
    }

    @PostMapping("/sakjeProc")
    public String sakjeProc(MemberDTO memberDTO) {
        service.setDelete(memberDTO);
        return "redirect:/" + folderName + "list";
    }
}
