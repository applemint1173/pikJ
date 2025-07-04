// 박지영
package com.midproject.pikJ.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;

    private String name;

    private String sort;

    private String location;

    private String sido;

    private String sgg;

    private String phone;

    @OneToMany(mappedBy = "school")
    private List<Management> managementList;

}