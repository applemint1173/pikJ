// 김태준
package com.midproject.pikJ.controller.manager;

import com.midproject.pikJ.dto.ManagementDTO;
import com.midproject.pikJ.dto.MemberDTO;
import com.midproject.pikJ.service.ManagementService;
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
    private final ManagementService managementService;

    String folderName = "manager/member/";

    @GetMapping("/list")
    public String list(Model model) {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        List<MemberDTO> list = service.getSelectAll();
        model.addAttribute("list",list);
        return folderName + "list";
    }

    @GetMapping("/view/{no}")
    public String view(MemberDTO memberDTO, Model model) {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        MemberDTO returnDTO = service.getSelectOne(memberDTO);
        List<ManagementDTO> list = managementService.getSelectByMemberId(returnDTO.getId());

        model.addAttribute("returnDTO",returnDTO);
        model.addAttribute("list",list);

        return folderName + "view";
    }

    @GetMapping("/chuga")
    public String chuga() {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        return folderName + "chuga";
    }

    @PostMapping("/chugaProc")
    public String chugaProc(MemberDTO memberDTO) {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        service.setInsert(memberDTO);
        return "redirect:/" + folderName + "list";
    }

    @GetMapping("/sujung/{no}")
    public String sujung(MemberDTO memberDTO, Model model) {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        MemberDTO returnDTO = service.getSelectOne(memberDTO);
        model.addAttribute("returnDTO",returnDTO);
        return folderName + "sujung";
    }

    @PostMapping("/sujungProc")
    public String sujungProc(MemberDTO memberDTO) {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        service.setUpdate(memberDTO);
        return "redirect:/" + folderName + "view/" + memberDTO.getNo();
    }

    @PostMapping("/sakjeProc")
    public String sakjeProc(MemberDTO memberDTO) {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        service.setDelete(memberDTO);
        return "redirect:/" + folderName + "list";
    }
}
