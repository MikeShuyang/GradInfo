package com.example.gradinfo.repository;

import com.example.gradinfo.entity.SysUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<SysUserEntity, Long> {
    SysUserEntity getSysUserEntityByUserNameAndUserPassword(String userName, String userPassword);
}
