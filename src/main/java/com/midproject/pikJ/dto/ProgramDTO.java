// 김태준
package com.midproject.pikJ.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Setter
public class ProgramDTO {

    private int no;

    private Date startDate;

    private Date endDate;

    private String subject;

    private String content;

    private String attachment;

    private String stage;

    private String type;

    private LocalDateTime regiDate;

    private MultipartFile attachmentFile;

}