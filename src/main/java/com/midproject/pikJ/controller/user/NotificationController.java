//김태준 : 페이징, 검색 추가
package com.midproject.pikJ.controller.user;

import com.midproject.pikJ.dto.NoticeDTO;
import com.midproject.pikJ.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class NotificationController {

    private final NoticeService noticeService;

    @GetMapping("/introduce/view")
    public String introduceView() {
        return "user/introduce/view";
    }

    @GetMapping("/notice/list")
    public String noticeList(
            Model model,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "searchType", required = false) String searchType,
            @RequestParam(value = "keyword", required = false) String keyword
    ) {
        Page<NoticeDTO> pageList = noticeService.getSelectAll(page, searchType, keyword);
        List<NoticeDTO> noticeList = pageList.getContent();

        if(keyword == null){
            keyword = "";
        }
        if(searchType == null){
            searchType = "";
        }

        model.addAttribute("list", noticeList);
        model.addAttribute("paging", pageList);
        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);
        model.addAttribute("totalCount", pageList.getTotalElements());

        return "user/notice/list";
    }

    @GetMapping("/notice/view/{no}")
    public String noticeView(
            Model model,
            NoticeDTO submitDTO
    ) {
        NoticeDTO returnDTO = noticeService.getSelectOne(submitDTO);
        model.addAttribute("returnDTO", returnDTO);

        return "user/notice/view";
    }

}