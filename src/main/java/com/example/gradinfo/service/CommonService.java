package com.example.gradinfo.service;

import com.example.gradinfo.bo.CourseGradesAndUnits;
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
    StudentInfoResponse getStudentInfoByStudentId(String studentId);
    SysStudentPostEntity getStudentPostEntitiesByStudentIdAndSpPostNumber(String studentId, String postNumber);
    List<String> calculateGpaAndUnit(String studentPostId, SysStudentPostEntity sysStudentPostEntity, List<SysAdmissionCourseEntity> sysAdmissionCourseEntityList, List<SysTransferCourseEntity> sysTransferCourseEntityList);
    List<String> CheckAdmissionCourseAndReturnReason(List<SysAdmissionCourseEntity> sysAdmissionCourseEntityList, List<SysTransferCourseEntity> sysTransferCourseEntityList);
    double gpaRules(String Grade);
    List<String> checkReason(List<SysAdmissionCourseEntity> sysAdmissionCourseEntityList, List<SysTransferCourseEntity> sysTransferCourseEntityList);
}
