package com.example.gradinfo.repository;

import com.example.gradinfo.entity.SysInstitutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstitutionRepository extends JpaRepository<SysInstitutionEntity, Long> {
    SysInstitutionEntity getSysInstitutionEntitiesByInstitutionId(String institutionId);
}
