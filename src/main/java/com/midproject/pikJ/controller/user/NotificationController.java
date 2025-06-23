package com.midproject.pikJ.controller.user;

import com.midproject.pikJ.dto.NoticeDTO;
import com.midproject.pikJ.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
            Model model
    ) {
        List<NoticeDTO> list = noticeService.getSelectAll();
        model.addAttribute("list", list);

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
