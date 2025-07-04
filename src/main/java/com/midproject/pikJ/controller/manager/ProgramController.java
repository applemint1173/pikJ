// 박지영
package com.midproject.pikJ.controller.manager;

import com.midproject.pikJ.dto.NoticeDTO;
import com.midproject.pikJ.dto.ProgramDTO;
import com.midproject.pikJ.service.ProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/manager/program")
@RequiredArgsConstructor
public class ProgramController {


    private final ProgramService service;

    String folderName = "manager/program";

    @GetMapping("/list")
    public String list(
            Model model,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "searchType", required = false) String searchType,
            @RequestParam(value = "keyword", required = false) String keyword
    ) {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        Page<ProgramDTO> pageList = service.getSelectAll(page, searchType, keyword);
        List<ProgramDTO> list = pageList.getContent();

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
            ProgramDTO submitDTO
    ) {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        ProgramDTO returnDTO = service.getSelectOne(submitDTO);
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
            ProgramDTO submitDTO
    ) throws ParseException {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        LocalDate date = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String todayString = date.format(dateTimeFormatter);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date today = dateFormat.parse(todayString);
        Boolean nextStart  = today.after(submitDTO.getStartDate());
        Boolean nextEnd  = today.after(submitDTO.getEndDate());

        if (nextStart == false && nextEnd == false) {
            submitDTO.setStage("진행예정");
        }

        if (nextStart == true && nextEnd == false) {
            submitDTO.setStage("진행중");
        }

        if (nextStart == true && nextEnd == true) {
            submitDTO.setStage("종료");
        }

        try {
            service.setInsert(submitDTO);
            return "redirect:/" + folderName + "/list";
        }catch (Exception e) {
            return "redirect:/" + folderName + "/chuga";
        }
    }

    @GetMapping("/sujung/{no}")
    public String sujung(
            Model model,
            ProgramDTO submitDTO
    ) {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        ProgramDTO returnDTO = service.getSelectOne(submitDTO);

        model.addAttribute("returnDTO", returnDTO);

        return folderName + "/sujung";
    }

    @PostMapping("/sujungProc")
    public String sujungProc(
            ProgramDTO submitDTO
    ) throws ParseException {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        LocalDate date = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String todayString = date.format(dateTimeFormatter);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date today = dateFormat.parse(todayString);
        Boolean nextStart  = today.after(submitDTO.getStartDate());
        Boolean nextEnd  = today.after(submitDTO.getEndDate());

        if (nextStart == false && nextEnd == false) {
            submitDTO.setStage("진행예정");
        }

        if (nextStart == true && nextEnd == false) {
            submitDTO.setStage("진행중");
        }

        if (nextStart == true && nextEnd == true) {
            submitDTO.setStage("종료");
        }

        try {
            service.setUpdate(submitDTO);
            return "redirect:/" + folderName + "/view/" + submitDTO.getNo();
        }catch (Exception e) {
            return "redirect:/" + folderName + "/sujung/" + submitDTO.getNo();
        }
    }

    @GetMapping("/sakje/{no}")
    public String sakje(
            Model model,
            ProgramDTO submitDTO
    ) {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        ProgramDTO returnDTO = service.getSelectOne(submitDTO);
        model.addAttribute("returnDTO", returnDTO);

        return folderName + "/sakje";
    }

    @PostMapping("/sakjeProc")
    public String sakjeProc(
            ProgramDTO submitDTO
    ) {
        String redirect = ErrorManager.notManager();
        if (redirect != null) return redirect;

        try {
            service.setDelete(submitDTO);
            return "redirect:/" + folderName + "/list";
        }catch (Exception e) {
            return "redirect:/" + folderName + "/view/" + submitDTO.getNo();
        }
    }

}
