package com.example.gradinfo.repository;

import com.example.gradinfo.entity.SysTransferHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferHistoryRepository extends JpaRepository<SysTransferHistoryEntity, Long> {
    List<SysTransferHistoryEntity> getSysTrHistoryEntitiesByTrCourseId(String trCourseId);
}
