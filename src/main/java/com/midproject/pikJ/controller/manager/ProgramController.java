// 박지영
package com.midproject.pikJ.controller.manager;

import com.midproject.pikJ.dto.ProgramDTO;
import com.midproject.pikJ.service.ProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/manager/program")
@RequiredArgsConstructor
public class ProgramController {

    private final ProgramService service;

    String folderName = "manager/program";

    @GetMapping("/list")
    public String list(
            Model model
    ) {
        List<ProgramDTO> list = service.getSelectAll();
        model.addAttribute("list", list);

        return folderName + "/list";
    }

    @GetMapping("/view/{no}")
    public String view(
            Model model,
            ProgramDTO submitDTO
    ) {
        ProgramDTO returnDTO = service.getSelectOne(submitDTO);
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
            ProgramDTO submitDTO
    ) {
        service.setInsert(submitDTO);

        return "redirect:/" + folderName + "/list";
    }

    @GetMapping("/sujung/{no}")
    public String sujung(
            Model model,
            ProgramDTO submitDTO
    ) {
        ProgramDTO returnDTO = service.getSelectOne(submitDTO);

        model.addAttribute("returnDTO", returnDTO);

        return folderName + "/sujung";
    }

    @PostMapping("/sujungProc")
    public String sujungProc(
            ProgramDTO submitDTO
    ) {
        service.setUpdate(submitDTO);

        return "redirect:/" + folderName + "/view/" + submitDTO.getNo();
    }

    @GetMapping("/sakje/{no}")
    public String sakje(
            Model model,
            ProgramDTO submitDTO
    ) {
        ProgramDTO returnDTO = service.getSelectOne(submitDTO);
        model.addAttribute("returnDTO", returnDTO);

        return folderName + "/sakje";
    }

    @PostMapping("/sakjeProc")
    public String sakjeProc(
            ProgramDTO submitDTO
    ) {
        service.setDelete(submitDTO);

        return "redirect:/" + folderName + "/list";
    }

}
