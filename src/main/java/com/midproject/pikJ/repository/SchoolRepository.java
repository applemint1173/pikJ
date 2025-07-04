// 박지영
package com.midproject.pikJ.repository;

import com.midproject.pikJ.entity.Program;
import com.midproject.pikJ.entity.School;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Integer> {
    Page<School> findAll(Pageable pageable);

    Page<School> findByNameContainingOrSortContainingOrLocationContainingOrPhoneContaining(
            String nameKeyword, String sorKeyword, String locationKeyword, String phoneKeyword, Pageable pageable);
    Page<School> findByNameContaining(String keyword, Pageable pageable);
    Page<School> findBySortContaining(String keyword, Pageable pageable);
    Page<School> findByLocationContaining(String keyword, Pageable pageable);
    Page<School> findByPhoneContaining(String keyword, Pageable pageable);
}