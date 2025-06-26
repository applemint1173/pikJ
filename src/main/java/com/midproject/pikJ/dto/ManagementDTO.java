// 박지영
package com.midproject.pikJ.dto;

import com.midproject.pikJ.entity.School;
import com.midproject.pikJ.entity.Counselor;
import com.midproject.pikJ.entity.Member;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Setter
public class ManagementDTO {

    private int no;

    private Member member;

    private Counselor counselor;

    private School school;

    private String grade;

    private String studentName;

    private String concern;

    private Date meetingDate;

    private String address;

    private String addressDetail;

    private String comments;

    private String attachment;

    private String checks;

    private LocalDateTime regiDate;

    private int member_no;

    private int counselor_no;

    private int school_no;

    private String location_address;

    private String location_detailAddress;

    private MultipartFile attachmentFile;

}