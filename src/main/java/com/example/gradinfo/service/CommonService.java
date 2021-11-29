package com.example.gradinfo.service;

import com.example.gradinfo.bo.StudentGpaAndUnit;
import com.example.gradinfo.dto.request.AdmissionCourseRequest;
import com.example.gradinfo.dto.response.StudentInfoResponse;
import com.example.gradinfo.entity.SysAdmissionCourseEntity;
import com.example.gradinfo.entity.SysStudentPostEntity;
import com.example.gradinfo.entity.SysTransferCourseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommonService {
    StudentInfoResponse getStudentInfoByStudentId(String student_id);
    SysStudentPostEntity getStudentPostEntitiesByStudentIdAndSpPostNumber(String student_id, String post_number);
    StudentGpaAndUnit CalculateGpaAndUnit(AdmissionCourseRequest admissionCourseRequest, List<SysAdmissionCourseEntity> sysAdmissionCourseEntityList, List<SysTransferCourseEntity> sysTransferCourseEntityList, SysStudentPostEntity sysStudentPostEntity);
}
