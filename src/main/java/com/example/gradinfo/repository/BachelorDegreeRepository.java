package com.example.gradinfo.repository;

import com.example.gradinfo.entity.SysStudentBachelorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BachelorDegreeRepository extends JpaRepository<SysStudentBachelorEntity, Long> {
    SysStudentBachelorEntity getSysStudentBachelorEntityByStudentId(String studentId);
}
