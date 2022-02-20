package com.example.gradinfo.repository;


import com.example.gradinfo.entity.SysStudentPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentPostRepository extends JpaRepository<SysStudentPostEntity,String> {
//    List<SysStudentPostEntity> getSysStudentPostEntitiesBySysStudentByStudentId(SysStudentEntity sysStudentEntity);
    List<SysStudentPostEntity> getSysStudentPostEntitiesByStudentId(String student_id);

}




