// 박지영
package com.midproject.pikJ.controller.manager;

import com.midproject.pikJ.dto.ProgramDTO;
import com.midproject.pikJ.dto.SchoolDTO;
import com.midproject.pikJ.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/manager/school")
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService service;

    String folderName = "manager/school";

    @GetMapping("/list")
    public String list(
            Model model,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "searchType", required = false) String searchType,
            @RequestParam(value = "keyword", required = false) String keyword
    ) {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        Page<SchoolDTO> pageList = service.getSelectAll(page, searchType, keyword);
        List<SchoolDTO> list = pageList.getContent();

        if(keyword == null){
            keyword = "";
        }
        if(searchType == null){
            searchType = "";
        }

        model.addAttribute("list", list);
        model.addAttribute("paging", pageList);
        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);

        return folderName + "/list";
    }

    @GetMapping("/view/{no}")
    public String view(
            Model model,
            SchoolDTO submitDTO
    ) {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        SchoolDTO returnDTO = service.getSelectOne(submitDTO);
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
            SchoolDTO submitDTO
    ) {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        service.setInsert(submitDTO);

        return "redirect:/" + folderName + "/list";
    }

    @GetMapping("/sujung/{no}")
    public String sujung(
            Model model,
            SchoolDTO submitDTO
    ) {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        SchoolDTO returnDTO = service.getSelectOne(submitDTO);

        model.addAttribute("returnDTO", returnDTO);

        return folderName + "/sujung";
    }

    @PostMapping("/sujungProc")
    public String sujungProc(
            SchoolDTO submitDTO
    ) {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        service.setUpdate(submitDTO);

        return "redirect:/" + folderName + "/view/" + submitDTO.getNo();
    }

    @GetMapping("/sakje/{no}")
    public String sakje(
            Model model,
            SchoolDTO submitDTO
    ) {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        SchoolDTO returnDTO = service.getSelectOne(submitDTO);
        model.addAttribute("returnDTO", returnDTO);

        return folderName + "/sakje";
    }

    @PostMapping("/sakjeProc")
    public String sakjeProc(
            SchoolDTO submitDTO
    ) {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        service.setDelete(submitDTO);

        return "redirect:/" + folderName + "/list";
    }

}
