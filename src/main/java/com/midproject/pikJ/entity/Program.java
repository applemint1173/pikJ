// 김태준
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
@EntityListeners(value = AuditingEntityListener.class)
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;

    private Date startDate;

    private Date endDate;

    @Column(length = 100)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(columnDefinition = "TEXT")
    private String attachment;

    @Column(length = 20)
    private String stage;

    @Column(length = 20)
    private String type;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime regiDate;

}
