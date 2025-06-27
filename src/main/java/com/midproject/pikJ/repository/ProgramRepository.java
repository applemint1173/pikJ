// 김태준
package com.midproject.pikJ.repository;

import com.midproject.pikJ.entity.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProgramRepository extends JpaRepository<Program, Integer> {

    List<Program> findByType(String type);

}