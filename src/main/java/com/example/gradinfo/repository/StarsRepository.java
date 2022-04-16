package com.example.gradinfo.repository;

import com.example.gradinfo.entity.SysStarsExceptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StarsRepository extends JpaRepository<SysStarsExceptionEntity, Long> {
    List<SysStarsExceptionEntity> getSysStarsExceptionEntitiesByStudentPostId(String studentPostId);
}
