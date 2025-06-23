// 정재백
package com.midproject.pikJ.repository;

import com.midproject.pikJ.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    // 박지영 : 아이디 값으로 찾기
    Optional<Member> findById(String id);
}