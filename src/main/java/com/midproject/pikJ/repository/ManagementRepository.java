// 박지영
package com.midproject.pikJ.repository;

import com.midproject.pikJ.entity.Management;
import com.midproject.pikJ.entity.School;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ManagementRepository extends JpaRepository<Management, Integer> {

    //김태준
    //counselor id가 ? 인 Management 찾기
    List<Management> findByCounselorId(String id);

    //김태준
    //member id가 ?인 Management 찾기
    List<Management> findByMemberId(String id);

    Page<Management> findAll(Pageable pageable);

    @Query("""
        SELECT m FROM Management m
        WHERE m.member.name LIKE %:keyword%
           OR m.counselor.name LIKE %:keyword%
           OR m.school.name LIKE %:keyword%
           OR m.checks LIKE %:keyword%
    """)
    Page<Management> findByFourContaining(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT m FROM Management m WHERE m.member.name LIKE %:keyword%")
    Page<Management> findByMemberNameContaining(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT m FROM Management m WHERE m.member.phone LIKE %:keyword%")
    Page<Management> findByMemberPhoneContaining(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT m FROM Management m WHERE m.counselor.name LIKE %:keyword%")
    Page<Management> findByCounselorNameContaining(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT m FROM Management m WHERE m.counselor.phone LIKE %:keyword%")
    Page<Management> findByCounselorPhoneContaining(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT m FROM Management m WHERE m.school.name LIKE %:keyword%")
    Page<Management> findBySchoolNameContaining(@Param("keyword") String keyword, Pageable pageable);

    Page<Management> findByChecksContaining(String keyword, Pageable pageable);

}