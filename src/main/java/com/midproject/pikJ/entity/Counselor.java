// 김태준
package com.midproject.pikJ.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@EntityListeners(value = {AuditingEntityListener.class})
public class Counselor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;

    @Column(length = 20, unique = true, updatable = false)
    private String id;

    private String pwd;

    @Column(length = 100)
    private String name;

    private Date birthDate;

    @Column(length = 20)
    private String phone;

    @Column(length = 100)
    private String email;

    @Column(columnDefinition = "TEXT")
    private String photo;

    @Column(columnDefinition = "TEXT")
    private String intro;

    @Column(columnDefinition = "TEXT")
    private String license;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime regiDate;

    @OneToMany(mappedBy = "counselor")
    private List<Management> managementList;

}
