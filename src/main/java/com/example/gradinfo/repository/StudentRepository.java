package com.example.gradinfo.repository;

import com.example.gradinfo.entity.SysStudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<SysStudentEntity,Long> {
    SysStudentEntity findSysStudentEntityBystudentId(String student_id);
}
