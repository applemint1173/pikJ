// 임소희
package com.midproject.pikJ.dto;

import com.midproject.pikJ.entity.Management;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class MemberDTO {

    private int no;

    private String id;

    private String pwd;

    private String name;

    private Date birthDate;

    private String phone;

    private String email;

    private String type;

    private LocalDateTime regiDate;

    private List<Management> managementList;

}