package com.example.gradinfo.repository;

import com.example.gradinfo.entity.SysCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<SysCommentEntity, String> {
    List<SysCommentEntity> getSysCommentEntitiesByStudentPostId(String studentPostId);
}
