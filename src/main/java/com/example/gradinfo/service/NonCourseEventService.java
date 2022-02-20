package com.example.gradinfo.service;

import com.example.gradinfo.dto.response.ExamTableDataResponse;
import com.example.gradinfo.dto.response.NonCourseEventTableDataResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NonCourseEventService {
    List<NonCourseEventTableDataResponse> getNonCourseRelatedEventTableDataByIDAndPostNumber(String studentId, String spPostNumber);
    List<ExamTableDataResponse> getExamCommitteeTableDataByIDAndPostNumber(String studentId, String spPostNumber);
}
