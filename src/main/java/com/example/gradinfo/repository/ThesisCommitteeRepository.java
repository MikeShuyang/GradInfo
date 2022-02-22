package com.example.gradinfo.repository;

import com.example.gradinfo.entity.SysThesisCommitteeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThesisCommitteeRepository extends JpaRepository<SysThesisCommitteeEntity, Long> {
    List<SysThesisCommitteeEntity> getSysThesisCommitteeEntitiesByStudentPostId(String studentPostId);
}
