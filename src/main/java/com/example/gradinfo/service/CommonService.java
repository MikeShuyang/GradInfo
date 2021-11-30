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
    StudentGpaAndUnit calculateGpaAndUnit(String studentPostId, SysStudentPostEntity sysStudentPostEntity, List<SysAdmissionCourseEntity> sysAdmissionCourseEntityList);

    double gpaRules(String Grade);
}
