// 임소희
package com.midproject.pikJ.controller.user;

import com.midproject.pikJ.dto.ProgramDTO;
import com.midproject.pikJ.repository.ProgramRepository;
import com.midproject.pikJ.service.ProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProgramsController {

    @Autowired
    private final ProgramService service;
    private final ProgramRepository programRepository;
    String folderName1 = "user/program/experience";
    String folderName2 = "user/program/lecture";

    @GetMapping("/program/experience/list")
    public String experiencelist(
            @RequestParam(value = "type", required = false) String type,
            Model model
    ){
        List<ProgramDTO> list = service.getSelectAll();


        List<ProgramDTO> experiences = new ArrayList<>();service.getSelectAll("experience");
        List<ProgramDTO> lectures = new ArrayList<>();service.getSelectAll("lecture");


        if("experience".equalsIgnoreCase(type)) {
            experiences = service.getSelectAll("experience");
        }else if("lecture".equalsIgnoreCase(type)){
            lectures = service.getSelectAll("lecture");
        } else {
            experiences = service.getSelectAll("experience");
            lectures = service.getSelectAll("lecture");
        }



        model.addAttribute("experiences", experiences);
        model.addAttribute("lectures", lectures);
        model.addAttribute("list", list);
        return folderName1 + "/list";
    }

    @GetMapping("/program/experience/view/{no}")
    public String experienceview(
            Model model,
            ProgramDTO submitDTO
    ){
        ProgramDTO returnDTO = service.getSelectOne(submitDTO);
        model.addAttribute("returnDTO", returnDTO);
        return folderName1 + "/view";
    }

    @GetMapping("/program/lecture/list")
    public String lecturelist(Model model){
        List<ProgramDTO> list = service.getSelectAll();
        model.addAttribute("list", list);
        return folderName2 + "/list";
    }

    @GetMapping("/program/lecture/view/{no}")
    public String lectureview(
            Model model,
            ProgramDTO submitDTO
    ){
        ProgramDTO returnDTO = service.getSelectOne(submitDTO);
        model.addAttribute("returnDTO", returnDTO);
        return folderName2 + "/view";
    }
}