// 김태준
package com.midproject.pikJ.repository;

import com.midproject.pikJ.entity.Counselor;
import com.midproject.pikJ.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CounselorRepository extends JpaRepository<Counselor, Integer> {
    // 박지영 : 아이디 값으로 찾기
    Optional<Counselor> findById(String id);

    Page<Counselor> findAll(Pageable pageable);

    Page<Counselor> findByNameContainingOrPhoneContaining(
            String nameKeyword, String phoneKeyword, Pageable pageable);
    Page<Counselor> findByNameContaining(String keyword, Pageable pageable);
    Page<Counselor> findByPhoneContaining(String keyword, Pageable pageable);

}