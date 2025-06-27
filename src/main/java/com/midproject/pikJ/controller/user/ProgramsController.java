package com.midproject.pikJ.controller.user;

import com.midproject.pikJ.dto.ProgramDTO;
import com.midproject.pikJ.service.ProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProgramsController {

    private final ProgramService service;

    String experienceFolderName = "user/program/experience";
    String lectureFolderName = "user/program/lecture";

    // 김태준 수정
    @GetMapping("/program/experience/list")
    public String experienceList(
            Model model, ProgramDTO programDTO
    ){
        List<ProgramDTO> list = service.getSelectByTypeAndStage(programDTO);
        model.addAttribute("list",list);
        model.addAttribute("type",programDTO.getType());
        model.addAttribute("stage",programDTO.getStage());

        return experienceFolderName + "/list";
    }

    // JY: 태준님이 보시고 지우거나 수정요청~
    @PostMapping("/program/experience/listProc")
    public String experienceListProc(
            Model model,
            ProgramDTO programDTO
    ){
        List<ProgramDTO> programDTOList = service.getSelectAll("체험");


        System.out.println(programDTO.getStage() + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        if (programDTO.getStage().equals("진행중,진행예정,종료")) {
//            model.addAttribute("stage", programDTO.getStage());
            model.addAttribute("list", programDTOList);
            model.addAttribute("stage1", "true");
            model.addAttribute("stage2", "true");
            model.addAttribute("stage3", "true");
        } else if (programDTO.getStage().equals("진행중,진행예정")) {
            model.addAttribute("list", programDTOList);
            model.addAttribute("stage1", "true");
            model.addAttribute("stage2", "true");
            model.addAttribute("stage3", "false");
        }


        return "redirect:/program/experience/list";
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

    // 김태준 수정 중이여서 주석 처리 함
//    @GetMapping("/program/lecture/list")
//    public String lectureList(
//            Model model
//    ){
//        List<ProgramDTO> programDTOList = service.getSelectByType("강연");
//        model.addAttribute("list", programDTOList);
//
//        return lectureFolderName + "/list";
//    }
//
//    @GetMapping("/program/lecture/view/{no}")
//    public String lectureView(
//            Model model,
//            ProgramDTO submitDTO
//    ){
//        ProgramDTO returnDTO = service.getSelectOne(submitDTO);
//        model.addAttribute("returnDTO", returnDTO);
//        return lectureFolderName + "/view";
//    }
}