package com.example.gradinfo.service;

import com.example.gradinfo.dto.StudentPostDto;
import org.springframework.stereotype.Service;

@Service
public interface AdmissionService {

    StudentPostDto getStudentPostDataByStudentIDAndPostNumber(String student_id, String sp);
}
