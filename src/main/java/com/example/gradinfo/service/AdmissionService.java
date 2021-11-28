package com.example.gradinfo.service;


import com.example.gradinfo.dto.request.AdmissionCourseRequest;
import com.example.gradinfo.dto.response.AdmissionCourseApplyResponse;
import com.example.gradinfo.dto.response.AdmissionCourseResponse;
import com.example.gradinfo.dto.response.StudentPostResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdmissionService {
    StudentPostResponse getStudentPostDataByStudentIDAndPostNumber(String studentId, String spPostNumber);
    List<AdmissionCourseResponse> getAdmissionCourseDataByStudentIDAndPostNumber(String studentId, String spPostNumber);
    AdmissionCourseApplyResponse getPostAdmissionCourseTableDataByNewArr(AdmissionCourseRequest admissionCourseRequest);
}
