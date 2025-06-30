// 임소희
package com.midproject.pikJ.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@EntityListeners(value = {AuditingEntityListener.class})
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;

    @Column(length = 20, unique = true)//, updatable = false)
    private String id;

    private String pwd;

    @Column(length = 100)
    private String name;

    @Column//(updatable = false)
    private Date birthDate;

    @Column(length = 20)
    private String phone;

    @Column(length = 100)
    private String email;

    @Column(length = 20)
    private String type;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime regiDate;

    @OneToMany(mappedBy = "member")
    private List<Management> managementList;

}