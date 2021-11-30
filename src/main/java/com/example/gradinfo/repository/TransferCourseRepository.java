package com.example.gradinfo.repository;

import com.example.gradinfo.entity.SysTransferCourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferCourseRepository extends JpaRepository<SysTransferCourseEntity, Long> {
    List<SysTransferCourseEntity> getSysTransferCourseEntitiesByStudentPostId(String studentPostId);
    List<SysTransferCourseEntity> getSysTransferCourseEntitiesByStudentPostIdAndTrCourseApplyStatusIs(String studentPostId, byte num);
}
