// 김태준
package com.midproject.pikJ.controller.manager;

import com.midproject.pikJ.dto.CounselorDTO;
import com.midproject.pikJ.dto.ManagementDTO;
import com.midproject.pikJ.service.CounselorService;
import com.midproject.pikJ.service.ManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.List;
import com.midproject.pikJ.dto.ManagementDTO;
import com.midproject.pikJ.service.ManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/manager/counselor")
@Controller
@RequiredArgsConstructor
public class CounselorController {

    private final CounselorService service;
    private final ManagementService managementService;

    String folderName = "manager/counselor/";

    @GetMapping("/list")
    public String list(
            Model model,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "searchType", required = false) String searchType,
            @RequestParam(value = "keyword", required = false) String keyword
    ) {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        Page<CounselorDTO> pageList = service.getSelectAll(page, searchType, keyword);
        List<CounselorDTO> list = pageList.getContent();

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

        return folderName + "list";
    }



    @GetMapping("/view/{no}")
    public String view(
            CounselorDTO counselorDTO,
            Model model,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "searchType", required = false) String searchType,
            @RequestParam(value = "keyword", required = false) String keyword
    ) {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        CounselorDTO returnDTO = service.getSelectOne(counselorDTO);

        model.addAttribute("returnDTO",returnDTO);

        Page<ManagementDTO> pageList = managementService.getSelectAll(page, searchType, keyword);
//        List<ManagementDTO> list = pageList.getContent();

        if(keyword == null){
            keyword = "";
        }
        if(searchType == null){
            searchType = "";
        }

        List<ManagementDTO> listCopy = new ArrayList<>(pageList.getContent());
        for (int i = listCopy.size() - 1; i >= 0; i--){
            if (listCopy.get(i).getCounselor().getNo() != counselorDTO.getNo()) {
                listCopy.remove(i);
            }
        }

        model.addAttribute("list",listCopy);
        model.addAttribute("paging", pageList);
        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);

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
