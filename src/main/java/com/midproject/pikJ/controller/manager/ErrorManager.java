package com.midproject.pikJ.controller.manager;

import com.midproject.pikJ.dto.MemberDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class ErrorManager {

    public static String notManager() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attr == null) return "redirect:/onlyManagerError";

        HttpSession session = attr.getRequest().getSession(false);
        if (session == null) return "redirect:/onlyManagerError";

        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        if (memberDTO == null || !"관리자".equals(memberDTO.getType())) {
            return "redirect:/onlyManagerError";
        }

        return null;
    }

}
