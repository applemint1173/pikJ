// 박지영
package com.midproject.pikJ.dto;

import com.midproject.pikJ.entity.Management;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SchoolDTO {

    private int no;

    private String name;

    private String sort;

    private String location;

    private String sido;

    private String sgg;

    private String phone;

    private List<Management> managementList;

}