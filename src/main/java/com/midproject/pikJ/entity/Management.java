// 박지영
package com.midproject.pikJ.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EntityListeners(value = {AuditingEntityListener.class})
public class Management {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Counselor counselor;

    @ManyToOne
    private School school;

    @Column(length = 20)
    private String grade;

    @Column(length = 100)
    private String studentName;

    @Column(columnDefinition = "TEXT")
    private String concern;

    private Date meetingDate;

    private String address;

    private String addressDetail;

    @Column(columnDefinition = "TEXT")
    private String comments;

    @Column(columnDefinition = "TEXT")
    private String attachment;

    @Column(length = 20)
    private String checks;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime regiDate;

}