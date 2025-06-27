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
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        List<ManagementDTO> list = service.getSelectAll();
        model.addAttribute("list", list);

        return folderName + "/list";
    }

    @GetMapping("/view/{no}")
    public String view(
            Model model,
            ManagementDTO submitDTO
    ) {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        ManagementDTO returnDTO = service.getSelectOne(submitDTO);
        model.addAttribute("returnDTO", returnDTO);

        return folderName + "/view";
    }

    @GetMapping("/chuga")
    public String chuga(
    ) {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        return folderName + "/chuga";
    }

    @PostMapping("/chugaProc")
    public String chugaProc(
            ManagementDTO submitDTO
    ) {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        try {
            service.setInsert(submitDTO);
            return "redirect:/" + folderName + "/list";
        }catch (Exception e) {
            return "redirect:/" + folderName + "/chuga";
        }
    }

    @GetMapping("/sujung/{no}")
    public String sujung(
            Model model,
            ManagementDTO submitDTO
    ) {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        ManagementDTO returnDTO = service.getSelectOne(submitDTO);

        model.addAttribute("returnDTO", returnDTO);

        return folderName + "/sujung";
    }

    @PostMapping("/sujungProc")
    public String sujungProc(
            ManagementDTO submitDTO
    ) {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        try {
            service.setUpdate(submitDTO);
            return "redirect:/" + folderName + "/view/" + submitDTO.getNo();
        }catch (Exception e) {
            return "redirect:/" + folderName + "/sujung/" + submitDTO.getNo();
        }
    }

    @GetMapping("/sakje/{no}")
    public String sakje(
            Model model,
            ManagementDTO submitDTO
    ) {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        ManagementDTO returnDTO = service.getSelectOne(submitDTO);
        model.addAttribute("returnDTO", returnDTO);

        return folderName + "/sakje";
    }

    @PostMapping("/sakjeProc")
    public String sakjeProc(
            ManagementDTO submitDTO
    ) {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        service.setDelete(submitDTO);

        return "redirect:/" + folderName + "/list";
    }

}
