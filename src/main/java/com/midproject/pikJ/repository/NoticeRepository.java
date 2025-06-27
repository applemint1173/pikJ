// 정재백
package com.midproject.pikJ.repository;

import com.midproject.pikJ.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {

    Page<Notice> findAll(Pageable pageable);

    Page<Notice> findBySubjectContaining(String keyword, Pageable pageable);
    Page<Notice> findByWriterContaining(String keyword, Pageable pageable);
    Page<Notice> findByContentContaining(String keyword, Pageable pageable);

}