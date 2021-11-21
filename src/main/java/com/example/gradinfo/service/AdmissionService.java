package com.example.gradinfo.service;


import com.example.gradinfo.dto.AdmissionCourseDto;
import com.example.gradinfo.dto.AdmissionCourseListDto;
import com.example.gradinfo.dto.StudentPostDto;
import org.springframework.stereotype.Service;

@Service
public interface AdmissionService {

    StudentPostDto getStudentPostDataByStudentIDAndPostNumber(String studentId, String spPostNumber);
    AdmissionCourseListDto getAdmissionCourseDataByStudentIDAndPostNumber(String studentId, String spPostNumber);
}
