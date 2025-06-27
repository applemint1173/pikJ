// 김태준
package com.midproject.pikJ.controller.user;

import com.midproject.pikJ.dto.ProgramDTO;
import com.midproject.pikJ.service.ProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String stage
    ) {
        ProgramDTO dto = new ProgramDTO();
        dto.setType("체험");
        dto.setStage("전체".equals(stage) || stage == null || stage.isEmpty() ? null : stage);

        Page<ProgramDTO> result = service.getSelectByTypeAndStage(dto, page);

        model.addAttribute("list", result.getContent());
        model.addAttribute("paging", result);
        model.addAttribute("type", "체험");
        model.addAttribute("stage", stage);

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

    // 김태준 수정
    @GetMapping("/program/lecture/list")
    public String lectureList(
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String stage
    ) {
        ProgramDTO dto = new ProgramDTO();
        dto.setType("강연");
        dto.setStage("전체".equals(stage) || stage == null || stage.isEmpty() ? null : stage);

        Page<ProgramDTO> result = service.getSelectByTypeAndStage(dto, page);

        model.addAttribute("list", result.getContent());
        model.addAttribute("paging", result);
        model.addAttribute("type", "강연");
        model.addAttribute("stage", stage);

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