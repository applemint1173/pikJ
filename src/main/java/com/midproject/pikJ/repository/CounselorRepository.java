// 김태준
package com.midproject.pikJ.repository;

import com.midproject.pikJ.entity.Counselor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CounselorRepository extends JpaRepository<Counselor, Integer> {
    // 박지영 : 아이디 값으로 찾기
    Optional<Counselor> findById(String id);
}