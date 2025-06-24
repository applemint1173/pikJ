package com.midproject.pikJ.controller.user;

import com.midproject.pikJ.dto.ProgramDTO;
import com.midproject.pikJ.service.ProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProgramsController {

    private final ProgramService service;

    String experienceFolderName = "user/program/experience";
    String lectureFolderName = "user/program/lecture";

    @GetMapping("/program/experience/list")
    public String experienceList(
            Model model
    ){
        List<ProgramDTO> programDTOList = service.getSelectAll("체험");
        model.addAttribute("list", programDTOList);

        return experienceFolderName + "/list";
    }

    @GetMapping("/program/experience/view/{no}")
    public String experienceView(
            Model model,
            ProgramDTO submitDTO
    ){
        ProgramDTO returnDTO = service.getSelectOne(submitDTO);
        model.addAttribute("returnDTO", returnDTO);
        return experienceFolderName + "/view";
    }

    @GetMapping("/program/lecture/list")
    public String lectureList(
            Model model
    ){
        List<ProgramDTO> programDTOList = service.getSelectAll("강연");
        model.addAttribute("list", programDTOList);

        return lectureFolderName + "/list";
    }

    @GetMapping("/program/lecture/view/{no}")
    public String lectureView(
            Model model,
            ProgramDTO submitDTO
    ){
        ProgramDTO returnDTO = service.getSelectOne(submitDTO);
        model.addAttribute("returnDTO", returnDTO);
        return lectureFolderName + "/view";
    }
}