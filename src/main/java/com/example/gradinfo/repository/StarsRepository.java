package com.example.gradinfo.repository;

import com.example.gradinfo.entity.SysStarsExceptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StarsRepository extends JpaRepository<SysStarsExceptionEntity, Long> {
}
