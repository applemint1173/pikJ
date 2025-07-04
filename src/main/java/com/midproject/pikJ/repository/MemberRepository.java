// 정재백
package com.midproject.pikJ.repository;

import com.midproject.pikJ.entity.Counselor;
import com.midproject.pikJ.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    // 박지영 : 아이디 값으로 찾기
    Optional<Member> findById(String id);

    // 박지영 : 타입 값으로 찾기
    Optional<Member> findByType(String type);

    Page<Member> findAll(Pageable pageable);

    Page<Member> findByNameContainingOrPhoneContainingOrTypeContaining(
            String nameKeyword, String phoneKeyword, String typeKeyword, Pageable pageable);
    Page<Member> findByNameContaining(String keyword, Pageable pageable);
    Page<Member> findByPhoneContaining(String keyword, Pageable pageable);
    Page<Member> findByTypeContaining(String keyword, Pageable pageable);

}