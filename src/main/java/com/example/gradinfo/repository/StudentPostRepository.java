package com.example.gradinfo.repository;


import com.example.gradinfo.entity.SysStudentEntity;
import com.example.gradinfo.entity.SysStudentPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentPostRepository extends JpaRepository<SysStudentPostEntity,String> {
//    List<SysStudentPostEntity> getSysStudentPostEntitiesBySysStudentByStudentId(SysStudentEntity sysStudentEntity);
    List<SysStudentPostEntity> getSysStudentPostEntitiesByStudentId(String student_id);


//    @Transactional
//    @Modifying
//    @Query("update SysStudentPostEntity xe set xe.spRgunits= :spRgunits where xe.studentPostId=:studentPostId")
//    void updateGpaAndUnit(@Param("spRgunits")double spRgunits, @Param("studentPostId")String studentPostId);
}




