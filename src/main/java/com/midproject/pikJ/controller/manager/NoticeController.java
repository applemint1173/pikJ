//정재백
package com.midproject.pikJ.controller.manager;

import com.midproject.pikJ.dto.NoticeDTO;
import com.midproject.pikJ.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/manager/notice")
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService service;

    String folderName = "manager/notice";

    @GetMapping("/list")
    public String list(Model model,
                       @PageableDefault(page = 0, size = 10, sort = "regiDate", direction = Sort.Direction.DESC) Pageable pageable,
                       @RequestParam(value = "searchType", required = false) String searchType,
                       @RequestParam(value = "keyword", required = false) String keyword) {

        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        Page<NoticeDTO> pageList = service.getSelectAll(pageable, searchType, keyword);

        int nowPage = pageList.getNumber() + 1;
        int startPage = Math.max(1, nowPage - 4);
        int endPage = Math.min(pageList.getTotalPages(), nowPage + 4);

        model.addAttribute("list", pageList);           // 페이지 객체
        model.addAttribute("nowPage", nowPage);         // 현재 페이지
        model.addAttribute("startPage", startPage);     // 시작 페이지
        model.addAttribute("endPage", endPage);         // 끝 페이지
        model.addAttribute("searchType", searchType);   // 검색타입 (뷰 표시용)
        model.addAttribute("keyword", keyword);         // 키워드 (뷰 유지용)

        return folderName + "/list";
    }

    @GetMapping("/view/{no}")
    public String view(
            Model model,
            NoticeDTO submitDTO
    ) {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        NoticeDTO returnDTO = service.getSelectOne(submitDTO);
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
            NoticeDTO submitDTO
    ) {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        service.setInsert(submitDTO);

        return "redirect:/" + folderName + "/list";
    }

    @GetMapping("/sujung/{no}")
    public String sujung(
            Model model,
            NoticeDTO submitDTO
    ) {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        NoticeDTO returnDTO = service.getSelectOne(submitDTO);

        model.addAttribute("returnDTO", returnDTO);

        return folderName + "/sujung";
    }

    @PostMapping("/sujungProc")
    public String sujungProc(
            NoticeDTO submitDTO
    ) {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        service.setUpdate(submitDTO);

        return "redirect:/" + folderName + "/view/" + submitDTO.getNo();
    }

    @GetMapping("/sakje/{no}")
    public String sakje(
            Model model,
            NoticeDTO submitDTO
    ) {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        NoticeDTO returnDTO = service.getSelectOne(submitDTO);
        model.addAttribute("returnDTO", returnDTO);

        return folderName + "/sakje";
    }

    @PostMapping("/sakjeProc")
    public String sakjeProc(
            NoticeDTO submitDTO
    ) {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        service.setDelete(submitDTO);

        return "redirect:/" + folderName + "/list";
    }
}
