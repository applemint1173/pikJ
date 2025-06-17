// 정재백
package com.midproject.pikJ.repository;

import com.midproject.pikJ.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {

}