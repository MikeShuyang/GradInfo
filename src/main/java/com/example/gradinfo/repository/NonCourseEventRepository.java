package com.example.gradinfo.repository;

import com.example.gradinfo.entity.SysNonCourseRelatedEventRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NonCourseEventRepository extends JpaRepository<SysNonCourseRelatedEventRecordEntity,Long> {
    List<SysNonCourseRelatedEventRecordEntity> getSysNonCourseRelatedEventRecordEntitiesByStudentPostId(String studentPostId);
}
