// 박지영
package com.midproject.pikJ.controller.user;

import com.midproject.pikJ.dto.CounselorDTO;
import com.midproject.pikJ.dto.MemberDTO;
import com.midproject.pikJ.service.CounselorService;
import com.midproject.pikJ.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/regist")
@RequiredArgsConstructor
public class RegistController {

    private final MemberService memberService;
    private final CounselorService counselorService;

    String folderName = "user/regist";
    String studentFolderName = "user/regist/student";
    String adultFolderName = "user/regist/adult";
    String counselorFolderName = "user/regist/counselor";

    @GetMapping("")
    public String registMain() {
        return folderName + "/registMain";
    }

    // 학생
    @GetMapping("/student/toS")
    public String studentToS(
    ) {
        return studentFolderName + "/toS";
    }

    @GetMapping("/student/verification")
    public String studentVerification(
            MemberDTO submitDTO
    ) {
        String url = studentFolderName + "/toS";
        if (submitDTO.getAgree1().equals("yes")
                && submitDTO.getAgree2().equals("yes")
                && submitDTO.getAgree3().equals("yes")){

            url = studentFolderName + "/verification";
        }

        return url;
    }

    @GetMapping("/student/chuga")
    public String studentChuga(
    ) {
        return studentFolderName + "/chuga";
    }

    @PostMapping("/student/chugaProc")
    public String studentChugaProc(
            MemberDTO submitDTO
    ) {
        memberService.setInsert(submitDTO);

        return "redirect:/regist/student/finish";
    }

    @GetMapping("/student/finish")
    public String studentFinish(
    ) {
        return studentFolderName + "/finish";
    }

    // 성인
    @GetMapping("/adult/toS")
    public String adultToS(
    ) {
        return adultFolderName + "/toS";
    }

    @GetMapping("/adult/verification")
    public String adultVerification(
            MemberDTO submitDTO
    ) {
        String url = adultFolderName + "/toS";
        if (submitDTO.getAgree1().equals("yes")
            && submitDTO.getAgree2().equals("yes")
            && submitDTO.getAgree3().equals("yes")){

            url = adultFolderName + "/verification";
        }

        return url;
    }

    @GetMapping("/adult/chuga")
    public String adultChuga(
    ) {
        return adultFolderName + "/chuga";
    }

    @PostMapping("/adult/chugaProc")
    public String adultChugaProc(
            MemberDTO submitDTO
    ) {
        memberService.setInsert(submitDTO);

        return "redirect:/regist/adult/finish";
    }

    @GetMapping("/adult/finish")
    public String adultFinish(
    ) {
        return adultFolderName + "/finish";
    }

    // 상담사
    @GetMapping("/counselor/toS")
    public String counselorToS(
    ) {
        return counselorFolderName + "/toS";
    }

    @GetMapping("/counselor/verification")
    public String counselorVerification(
            MemberDTO submitDTO
    ) {
        String url = counselorFolderName + "/toS";
        if (submitDTO.getAgree1().equals("yes")
                && submitDTO.getAgree2().equals("yes")
                && submitDTO.getAgree3().equals("yes")){

            url = counselorFolderName + "/verification";
        }

        return url;
    }

    @GetMapping("/counselor/chuga")
    public String counselorChuga(
    ) {
        return counselorFolderName + "/chuga";
    }

    @PostMapping("/counselor/chugaProc")
    public String counselorChugaProc(
            CounselorDTO submitDTO
    ) {
        counselorService.setInsert(submitDTO);

        return "redirect:/regist/counselor/finish";
    }

    @GetMapping("/counselor/finish")
    public String counselorFinish(
    ) {
        return counselorFolderName + "/finish";
    }

}
