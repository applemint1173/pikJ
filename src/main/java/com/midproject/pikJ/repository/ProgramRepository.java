// 김태준
package com.midproject.pikJ.repository;

import com.midproject.pikJ.entity.Notice;
import com.midproject.pikJ.entity.Program;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProgramRepository extends JpaRepository<Program, Integer> {

    List<Program> findByType(String type);

    Page<Program> findAll(Pageable pageable);

    Page<Program> findBySubjectContainingOrContentContainingOrStageContainingOrTypeContaining(
            String subjectKeyword, String contentKeyword, String stageKeyword, String typeKeyword, Pageable pageable);
    Page<Program> findBySubjectContaining(String keyword, Pageable pageable);
    Page<Program> findByContentContaining(String keyword, Pageable pageable);
    Page<Program> findByStageContaining(String keyword, Pageable pageable);
    Page<Program> findByTypeContaining(String keyword, Pageable pageable);

    Page<Program> findByType(String type, Pageable pageable);
    Page<Program> findByTypeAndStage(String type, String stage, Pageable pageable);

}