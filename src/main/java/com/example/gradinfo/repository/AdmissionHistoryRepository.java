package com.example.gradinfo.repository;

import com.example.gradinfo.entity.SysAdHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdmissionHistoryRepository extends JpaRepository<SysAdHistoryEntity,Long> {
    List<SysAdHistoryEntity> getSysAdHistoryEntitiesByAdCourseId(String adCourseId);
}
