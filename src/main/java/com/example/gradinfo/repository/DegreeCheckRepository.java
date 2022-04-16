package com.example.gradinfo.repository;

import com.example.gradinfo.entity.SysDegreeCheckEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DegreeCheckRepository extends JpaRepository<SysDegreeCheckEntity, String> {
    List<SysDegreeCheckEntity> getSysDegreeCheckEntitiesByStudentPostId(String studentPostId);
    SysDegreeCheckEntity getSysDegreeCheckEntityByDegreeCheckId(Integer degreeCheckId);
}
