//0623 김태준 생성
package com.midproject.pikJ.controller.user;

import com.midproject.pikJ.dto.CounselorDTO;
import com.midproject.pikJ.dto.ManagementDTO;
import com.midproject.pikJ.dto.MemberDTO;
import com.midproject.pikJ.service.CounselorService;
import com.midproject.pikJ.service.ManagementService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/management/counselor")
@RequiredArgsConstructor
public class CounselController {

    private final ManagementService managementService;

    String url = "user/management/counselor";
    String redirectUrl = "management/counselor";

    @GetMapping("/list")
    public String list(
            Model model,
            HttpSession session
    ) {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        CounselorDTO counselorDTO = (CounselorDTO) session.getAttribute("counselor");

        if (memberDTO == null && counselorDTO == null) {
            return "redirect:/login";
        } else if (memberDTO != null && memberDTO.getType().equals("관리자")) {
            return "redirect:/manager/management/list";
        } else if (memberDTO != null && counselorDTO == null) {
            return "redirect:/onlyCounselorError";
        }

        List<ManagementDTO> list = managementService.getSelectByCounselorId(counselorDTO.getId());
        model.addAttribute("list",list);
        return url + "/list";
    }

    // JY 0628 : 미배정 내담자들 리스트
    @GetMapping("/allList")
    public String allList(
            Model model,
            HttpSession session
    ) {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        CounselorDTO counselorDTO = (CounselorDTO) session.getAttribute("counselor");

        if (memberDTO == null && counselorDTO == null) {
            return "redirect:/login";
        } else if (memberDTO != null && memberDTO.getType().equals("관리자")) {
            return "redirect:/manager/management/list";
        } else if (memberDTO != null && counselorDTO == null) {
            return "redirect:/onlyCounselorError";
        }

        List<ManagementDTO> list = managementService.getSelectAll();

        for (int i = list.size() - 1; i >= 0; i--){
            if (!list.get(i).getCounselor().getId().equals("imsiSangdamsa")) {
                list.remove(i);
            }
        }

        model.addAttribute("list",list);
        return url + "/allList";
    }

    @GetMapping("/view/{no}")
    public String view(ManagementDTO managementDTO, Model model, HttpSession session) {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        CounselorDTO counselorDTO = (CounselorDTO) session.getAttribute("counselor");

        if (memberDTO == null && counselorDTO == null) {
            return "redirect:/login";
        } else if (memberDTO != null && memberDTO.getType().equals("관리자")) {
            return "redirect:/manager/management/list";
        } else if (memberDTO != null && counselorDTO == null) {
            return "redirect:/onlyCounselorError";
        }

        ManagementDTO returnDTO = managementService.getSelectOne(managementDTO);
        model.addAttribute("returnDTO",returnDTO);
        return url + "/view";
    }

    // JY 0628 : 상담사 기준에서 내담자 정보 + 상담신청 내용
    @GetMapping("/getUserInfo/{no}")
    public String getUserInfo(ManagementDTO managementDTO, Model model, HttpSession session) {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        CounselorDTO counselorDTO = (CounselorDTO) session.getAttribute("counselor");

        if (memberDTO == null && counselorDTO == null) {
            return "redirect:/login";
        } else if (memberDTO != null && memberDTO.getType().equals("관리자")) {
            return "redirect:/manager/management/list";
        } else if (memberDTO != null && counselorDTO == null) {
            return "redirect:/onlyCounselorError";
        }

        ManagementDTO returnDTO = managementService.getSelectOne(managementDTO);

        model.addAttribute("returnDTO",returnDTO);
        return "user/management/counselor/getUserInfo";
    }

    @GetMapping("/sujung/{no}")
    public String sujung(ManagementDTO managementDTO, Model model, HttpSession session) {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        CounselorDTO counselorDTO = (CounselorDTO) session.getAttribute("counselor");

        if (memberDTO == null && counselorDTO == null) {
            return "redirect:/login";
        } else if (memberDTO != null && memberDTO.getType().equals("관리자")) {
            return "redirect:/manager/management/list";
        } else if (memberDTO != null && counselorDTO == null) {
            return "redirect:/onlyCounselorError";
        }

        ManagementDTO returnDTO = managementService.getSelectOne(managementDTO);
        model.addAttribute("returnDTO",returnDTO);
        return url + "/sujung";
    }

    @PostMapping("/sujungProc")
    public String sujungProc(ManagementDTO managementDTO, HttpSession session) {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        CounselorDTO counselorDTO = (CounselorDTO) session.getAttribute("counselor");

        if (memberDTO == null && counselorDTO == null) {
            return "redirect:/login";
        } else if (memberDTO != null && memberDTO.getType().equals("관리자")) {
            return "redirect:/manager/management/list";
        } else if (memberDTO != null && counselorDTO == null) {
            return "redirect:/onlyCounselorError";
        }

        try {
            managementService.setUpdate(managementDTO);
            return "redirect:/" + redirectUrl + "/view/" + managementDTO.getNo();
        }catch (Exception e) {
            return "redirect:/" + redirectUrl + "sujung/" + managementDTO.getNo();
        }
    }

    // JY 0628 : 상담 예약진행 추가
    @GetMapping("/yeyak/{no}")
    public String yeyak(ManagementDTO managementDTO, Model model, HttpSession session) {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        CounselorDTO counselorDTO = (CounselorDTO) session.getAttribute("counselor");

        if (memberDTO == null && counselorDTO == null) {
            return "redirect:/login";
        } else if (memberDTO != null && memberDTO.getType().equals("관리자")) {
            return "redirect:/manager/management/list";
        } else if (memberDTO != null && counselorDTO == null) {
            return "redirect:/onlyCounselorError";
        }

        ManagementDTO returnDTO = managementService.getSelectOne(managementDTO);

        model.addAttribute("returnDTO",returnDTO);

        return url + "/yeyak";
    }

    @PostMapping("/yeyakProc")
    public String yeyakProc(ManagementDTO managementDTO, HttpSession session) {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        CounselorDTO counselorDTO = (CounselorDTO) session.getAttribute("counselor");

        if (memberDTO == null && counselorDTO == null) {
            return "redirect:/login";
        } else if (memberDTO != null && memberDTO.getType().equals("관리자")) {
            return "redirect:/manager/management/list";
        } else if (memberDTO != null && counselorDTO == null) {
            return "redirect:/onlyCounselorError";
        }

        try {
            managementDTO.setCounselor_no(counselorDTO.getNo());

            managementService.setUpdate(managementDTO);
            return "redirect:/" + redirectUrl + "/list";
        }catch (Exception e) {
            return "redirect:/" + redirectUrl + "/yeyak/" + managementDTO.getNo();
        }
    }

}