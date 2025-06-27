// 김태준
package com.midproject.pikJ.controller.manager;

import com.midproject.pikJ.dto.CounselorDTO;
import com.midproject.pikJ.dto.ManagementDTO;
import com.midproject.pikJ.dto.MemberDTO;
import com.midproject.pikJ.service.CounselorService;
import com.midproject.pikJ.service.ManagementService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/manager/counselor")
@Controller
@RequiredArgsConstructor
public class CounselorController {

    private final CounselorService service;
    private final ManagementService managementService;

    String folderName = "manager/counselor/";

    @GetMapping("/list")
    public String list(
            Model model) {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        List<CounselorDTO> list = service.getSelectAll();
        model.addAttribute("list",list);
        return folderName + "list";
    }

    @GetMapping("/view/{no}")
    public String view(
            CounselorDTO counselorDTO,
            Model model
    ) {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        CounselorDTO returnDTO = service.getSelectOne(counselorDTO);
        List<ManagementDTO> list = managementService.getSelectByCounselorId(returnDTO.getId());

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
    public String chugaProc(
            CounselorDTO counselorDTO
    ){
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        try {
            service.setInsert(counselorDTO);
            return "redirect:/" + folderName + "list";
        }catch (Exception e) {
            return "redirect:/" + folderName + "chuga";
        }
    }

    @GetMapping("/sujung/{no}")
    public String sujung(CounselorDTO counselorDTO, Model model) {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        CounselorDTO returnDTO = service.getSelectOne(counselorDTO);
        model.addAttribute("returnDTO",returnDTO);
        return folderName + "sujung";
    }

    @PostMapping("/sujungProc")
    public String sujungProc(
            CounselorDTO counselorDTO
    ) {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        try {
            service.setUpdate(counselorDTO);
            return "redirect:/" + folderName + "view/" + counselorDTO.getNo();
        }catch (Exception e) {
            return "redirect:/" + folderName + "sujung/" + counselorDTO.getNo();
        }
    }

    @PostMapping("/sakjeProc")
    public String sakjeProc(CounselorDTO counselorDTO) {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        try {
            service.setDelete(counselorDTO);
            return "redirect:/" + folderName + "list";
        }catch (Exception e) {
            return "redirect:/" + folderName + "view/" + counselorDTO.getNo();
        }
    }

}
