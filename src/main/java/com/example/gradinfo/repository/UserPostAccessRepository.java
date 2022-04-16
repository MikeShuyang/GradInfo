package com.example.gradinfo.repository;

import com.example.gradinfo.entity.SysUserPostAccessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPostAccessRepository extends JpaRepository<SysUserPostAccessEntity, String> {
    List<SysUserPostAccessEntity> getSysUserPostAccessEntitiesByUserId(String userId);
}
