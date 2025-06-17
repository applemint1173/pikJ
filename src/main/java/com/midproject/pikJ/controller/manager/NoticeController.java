package com.midproject.pikJ.controller.manager;

import com.midproject.pikJ.dto.NoticeDTO;
import com.midproject.pikJ.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/manager/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService service;

    String folderName = "manager/notice";

    @GetMapping("/list")
    public String list(
            Model model
    ) {
        List<NoticeDTO> list = service.getSelectAll();
        model.addAttribute("list", list);

        return folderName + "/list";
    }

    @GetMapping("/view/{no}")
    public String view(
            Model model,
            NoticeDTO submitDTO
    ) {
        NoticeDTO returnDTO = service.getSelectOne(submitDTO);
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
            NoticeDTO submitDTO
    ) {
        service.setInsert(submitDTO);

        return "redirect:/" + folderName + "/list";
    }

    @GetMapping("/sujung/{no}")
    public String sujung(
            Model model,
            NoticeDTO submitDTO
    ) {
        NoticeDTO returnDTO = service.getSelectOne(submitDTO);

        model.addAttribute("returnDTO", returnDTO);

        return folderName + "/sujung";
    }

    @PostMapping("/sujungProc")
    public String sujungProc(
            NoticeDTO submitDTO
    ) {
        service.setUpdate(submitDTO);

        return "redirect:/" + folderName + "/view/" + submitDTO.getNo();
    }

    @GetMapping("/sakje/{no}")
    public String sakje(
            Model model,
            NoticeDTO submitDTO
    ) {
        NoticeDTO returnDTO = service.getSelectOne(submitDTO);
        model.addAttribute("returnDTO", returnDTO);

        return folderName + "/sakje";
    }

    @PostMapping("/sakjeProc")
    public String sakjeProc(
            NoticeDTO submitDTO
    ) {
        service.setDelete(submitDTO);

        return "redirect:/" + folderName + "/list";
    }

}
