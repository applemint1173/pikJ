// 김태준
package com.midproject.pikJ.controller.manager;

import com.midproject.pikJ.dto.ManagementDTO;
import com.midproject.pikJ.dto.MemberDTO;
import com.midproject.pikJ.dto.SchoolDTO;
import com.midproject.pikJ.service.ManagementService;
import com.midproject.pikJ.service.MemberService;
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

@Controller
@RequestMapping("/manager/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService service;
    private final ManagementService managementService;

    String folderName = "manager/member/";

    @GetMapping("/list")
    public String list(
            Model model,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "searchType", required = false) String searchType,
            @RequestParam(value = "keyword", required = false) String keyword
    ) {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        Page<MemberDTO> pageList = service.getSelectAll(page, searchType, keyword);
        List<MemberDTO> list = pageList.getContent();

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
            MemberDTO memberDTO,
            Model model,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "searchType", required = false) String searchType,
            @RequestParam(value = "keyword", required = false) String keyword) {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        MemberDTO returnDTO = service.getSelectOne(memberDTO);

        model.addAttribute("returnDTO",returnDTO);

        // JY 0628 : 페이징 수정
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
            if (listCopy.get(i).getMember().getNo() != memberDTO.getNo()) {
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
    public String chugaProc(MemberDTO memberDTO) {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        service.setInsert(memberDTO);
        return "redirect:/" + folderName + "list";
    }

    @GetMapping("/sujung/{no}")
    public String sujung(MemberDTO memberDTO, Model model) {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        MemberDTO returnDTO = service.getSelectOne(memberDTO);
        model.addAttribute("returnDTO",returnDTO);
        return folderName + "sujung";
    }

    @PostMapping("/sujungProc")
    public String sujungProc(MemberDTO memberDTO) {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        service.setUpdate(memberDTO);
        return "redirect:/" + folderName + "view/" + memberDTO.getNo();
    }

    @PostMapping("/sakjeProc")
    public String sakjeProc(MemberDTO memberDTO) {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        service.setDelete(memberDTO);
        return "redirect:/" + folderName + "list";
    }
}
