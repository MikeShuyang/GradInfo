package com.example.gradinfo.repository;

import com.example.gradinfo.entity.SysStudentBachelorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BachelorDegreeRepository extends JpaRepository<SysStudentBachelorEntity, Long> {
    List<SysStudentBachelorEntity> getSysStudentBachelorEntitiesByStudentId(String studentId);
//    SysStudentBachelorEntity getSysStudentBachelorEntityByStudentId(String studentId);
}
