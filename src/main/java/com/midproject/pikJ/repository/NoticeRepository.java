// 정재백
package com.midproject.pikJ.repository;

import com.midproject.pikJ.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {

    //김태준
    Page<Notice> findAll(Pageable pageable);
}