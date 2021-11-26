package com.example.gradinfo.service;

import com.example.gradinfo.dto.response.StudentInfoResponse;
import com.example.gradinfo.entity.SysStudentPostEntity;
import org.springframework.stereotype.Service;

@Service
public interface CommonService {
    StudentInfoResponse getStudentInfoByStudentId(String student_id);
    SysStudentPostEntity getStudentPostEntitiesByStudentIdAndSpPostNumber(String student_id, String post_number);
}
