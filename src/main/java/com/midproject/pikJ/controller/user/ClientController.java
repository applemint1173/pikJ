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
        MemberDTO memberUser = (MemberDTO) session.getAttribute("member");

        String url = userFolderName + "/chugaNotice";

        if (memberUser == null) {
            url = "redirect:/login";
        } else {
            model.addAttribute("returnDTO", memberUser);
        }

        return url;
    }

    @GetMapping("/chuga")
    public String userChuga(
            Model model,
            MemberDTO submitDTO
    ) {
        MemberDTO returnDTO = memberService.getSelectLoginOne(submitDTO);
        List<SchoolDTO> schoolList = schoolService.getSelectAll();
        model.addAttribute("returnDTO", returnDTO);
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
            CounselorDTO counselorDTO = new CounselorDTO();
            counselorDTO.setId("imsiSangdamsa");
            counselorDTO.setPwd("imsiSangdamsa");
            counselorDTO.setName("-");
            counselorDTO.setBirthDate(Date.valueOf("1900-01-01"));
            counselorDTO.setEmail("-");
            counselorDTO.setPhone("-"); // 추가
            counselorDTO.setPhoto("-");
            counselorDTO.setLicense("-");
            counselorDTO.setIntro("-");

            counselorService.setInsert(counselorDTO);
            counselorDTO = counselorService.getSelectLoginOne(counselorDTO); // 추가

            model.addAttribute("defaultCounselorDTO", counselorDTO);
        }

        return userFolderName + "/chuga";
    }

    @PostMapping("/chugaProc")
    public String userChugaProc(
            ManagementDTO managementDTO
    ) {
        managementService.setInsert(managementDTO);
        return "redirect:/homeImsi";
    }

    @PostMapping("/list")
    public String list(
            Model model,
            MemberDTO memberDTO
    ) {
        List<ManagementDTO> list = managementService.getSelectByMemberId(memberDTO.getId());
        model.addAttribute("list",list);

        return userFolderName + "/list";
    }

    @GetMapping("/view/{no}")
    public String view(ManagementDTO managementDTO, Model model) {
        ManagementDTO returnDTO = managementService.getSelectOne(managementDTO);
        model.addAttribute("returnDTO",returnDTO);
        return userFolderName + "/view";
    }

}
