package com.example.gradinfo.repository;

import com.example.gradinfo.entity.SysExamCommitteeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamCommitteeRepository extends JpaRepository<SysExamCommitteeEntity,Long> {
    List<SysExamCommitteeEntity> getSysExamCommitteeEntitiesByStudentPostId(String studentPostId);
}
