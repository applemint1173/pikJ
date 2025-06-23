// 박지영
package com.midproject.pikJ.repository;

import com.midproject.pikJ.entity.Management;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManagementRepository extends JpaRepository<Management, Integer> {

    //김태준
    //counselor id가 ? 인 Management 찾기
    List<Management> findByCounselorCounselorId(String id);

    //김태준
    //member id가 ?인 Management 찾기
    List<Management> findByMemberMemberId(String id);

}