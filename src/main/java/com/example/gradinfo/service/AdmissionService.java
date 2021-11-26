package com.example.gradinfo.service;


import com.example.gradinfo.dto.request.AdmissionCourseRequest;
import com.example.gradinfo.dto.response.AdmissionCourseApplyResponse;
import com.example.gradinfo.dto.response.AdmissionCourseListResponse;
import com.example.gradinfo.dto.response.StudentPostResponse;
import org.springframework.stereotype.Service;

@Service
public interface AdmissionService {

    StudentPostResponse getStudentPostDataByStudentIDAndPostNumber(String studentId, String spPostNumber);
    AdmissionCourseListResponse getAdmissionCourseDataByStudentIDAndPostNumber(String studentId, String spPostNumber);
    AdmissionCourseApplyResponse getPostAdmissionCourseTableDataByNewArr(AdmissionCourseRequest admissionCourseRequest);
}
