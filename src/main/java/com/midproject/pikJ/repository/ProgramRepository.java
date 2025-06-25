// 김태준
package com.midproject.pikJ.repository;

import com.midproject.pikJ.entity.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProgramRepository extends JpaRepository<Program, Integer> {

    @Query(value = "select * from program where type = :type " +
            "order by field (stage, '진행중', '진행예정', '진행완료') desc, start_date desc",
            nativeQuery = true)
    List<Program> findByType(@Param("type") String type);

}