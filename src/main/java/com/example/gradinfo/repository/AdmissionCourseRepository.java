package com.example.gradinfo.repository;

import com.example.gradinfo.entity.SysAdmissionCourseEntity;
import com.example.gradinfo.entity.SysStudentPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdmissionCourseRepository extends JpaRepository<SysAdmissionCourseEntity,Long> {
    List<SysAdmissionCourseEntity> getSysAdmissionCourseEntitiesByStudentPostId(String studentPostId);
    List<SysAdmissionCourseEntity> getSysAdmissionCourseEntitiesByStudentPostIdAndAdCourseApplyStatusIs(String studentPostId, byte num);
    boolean findSysAdmissionCourseEntityByStudentPostIdAndAdCourseApplyStatusIs(String studentPostId, byte num);
}
