// 박지영
package com.midproject.pikJ.controller.user;

import com.midproject.pikJ.dto.*;
import com.midproject.pikJ.service.CounselorService;
import com.midproject.pikJ.service.ManagementService;
import com.midproject.pikJ.service.MemberService;
import com.midproject.pikJ.service.SchoolService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/management/client")
@RequiredArgsConstructor
public class ClientController {

    private final MemberService memberService;
    private final SchoolService schoolService;
    private final CounselorService counselorService;
    private final ManagementService managementService;

    String folderName = "user/management";
    String userFolderName = "user/management/user";
    String counselorFolderName = "user/management/counselor";

    @GetMapping("/chugaNotice")
    public String userChugaNotice(
            Model model,
            HttpSession session
    ) {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        CounselorDTO counselorDTO = (CounselorDTO) session.getAttribute("counselor");

        String url = userFolderName + "/chugaNotice";

        if (memberDTO == null && counselorDTO == null) {
            url = "redirect:/login";
        } else if (counselorDTO != null && memberDTO == null) {
            url = "redirect:/onlyMemberError";
        } else {
            model.addAttribute("returnDTO", memberDTO);
        }

        return url;
    }

    @GetMapping("/chuga")
    public String userChuga(
            Model model,
            HttpSession session
    ) {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        CounselorDTO counselorDTO = (CounselorDTO) session.getAttribute("counselor");

        if (memberDTO == null && counselorDTO == null) {
            return "redirect:/login";
        } else if (counselorDTO != null && memberDTO == null) {
            return "redirect:/onlyMemberError";
        }

        List<SchoolDTO> schoolList = schoolService.getSelectAll();
        model.addAttribute("returnDTO", memberDTO);
        model.addAttribute("schoolList", schoolList);

        // 임시상담사 계정생성하는 코드(각 컴터마다 DB가 다르기때문에 임시로 설정)
        // 추후에 서버 통합하게 되면, 코드 지우고 관리자모드에서 먼저 생성하는 것을 권장
        List<CounselorDTO> findDefaultCounselorList = counselorService.getSelectAll();
        Boolean isExist = false;

        for (int i = 0; i < findDefaultCounselorList.size(); i++) {
            if (findDefaultCounselorList.get(i).getId().equals("imsiSangdamsa")) {
                isExist = true;
                model.addAttribute("defaultCounselorDTO", findDefaultCounselorList.get(i));
            }
        }

        if (isExist == false) {
            CounselorDTO imsiCounselorDTO = new CounselorDTO();
            imsiCounselorDTO.setId("imsiSangdamsa");
            imsiCounselorDTO.setPwd("imsiSangdamsa");
            imsiCounselorDTO.setName("-");
            imsiCounselorDTO.setBirthDate(Date.valueOf("1900-01-01"));
            imsiCounselorDTO.setEmail("-");
            imsiCounselorDTO.setPhone("-");
            imsiCounselorDTO.setPhoto("");
            imsiCounselorDTO.setLicense("-");
            imsiCounselorDTO.setIntro("-");

            try {
                counselorService.setInsert(imsiCounselorDTO);
                imsiCounselorDTO = counselorService.getSelectLoginOne(imsiCounselorDTO);
                model.addAttribute("defaultCounselorDTO", imsiCounselorDTO);

                return userFolderName + "/chuga";
            } catch (Exception e) {
                return "redirect:/" + userFolderName + "chuga";
            }
        }

        return userFolderName + "/chuga";
    }


    @PostMapping("/chugaProc")
    public String userChugaProc(
            ManagementDTO managementDTO
    ) {
        try {
            managementService.setInsert(managementDTO);
            return "redirect:/";
        }catch (Exception e) {
            return "redirect:/" + userFolderName + "chuga";
        }
    }

    @GetMapping("/list")
    public String list(
            Model model,
            HttpSession session
    ) {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        CounselorDTO counselorDTO = (CounselorDTO) session.getAttribute("counselor");

        if (memberDTO == null && counselorDTO == null) {
            return "redirect:/login";
        } else if (counselorDTO != null && memberDTO == null) {
            return "redirect:/onlyMemberError";
        }

        List<ManagementDTO> list = managementService.getSelectByMemberId(memberDTO.getId());
        model.addAttribute("list",list);

        return userFolderName + "/list";
    }

    @GetMapping("/view/{no}")
    public String view(
            ManagementDTO managementDTO,
            Model model,
            HttpSession session) {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        CounselorDTO counselorDTO = (CounselorDTO) session.getAttribute("counselor");

        if (memberDTO == null && counselorDTO == null) {
            return "redirect:/login";
        } else if (counselorDTO != null && memberDTO == null) {
            return "redirect:/onlyMemberError";
        }

        ManagementDTO returnDTO = managementService.getSelectOne(managementDTO);
        model.addAttribute("returnDTO",returnDTO);

        return userFolderName + "/view";
    }

}
