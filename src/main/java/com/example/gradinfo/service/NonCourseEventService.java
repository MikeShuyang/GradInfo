package com.example.gradinfo.service;

import com.example.gradinfo.dto.request.ExamCommitteeRequest;
import com.example.gradinfo.dto.request.NonCourseEventRequest;
import com.example.gradinfo.dto.request.PaperTitleRequest;
import com.example.gradinfo.dto.request.ThesisCommitteeRequest;
import com.example.gradinfo.dto.response.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NonCourseEventService {
    List<NonCourseEventTableDataResponse> getNonCourseRelatedEventTableDataByIDAndPostNumber(String studentId, String spPostNumber);
    List<ExamTableDataResponse> getExamCommitteeTableDataByIDAndPostNumber(String studentId, String spPostNumber);
    List<ThesisCommitteeResponse> getThesisCommitteeTableDataByIDAndPostNumber(String studentId, String spPostNumber);
    NonCourseEventApplyResponse postNonCourseRelatedEventTableDataByEventObj(NonCourseEventRequest nonCourseEventRequest);
    CommonResponse postExamCommitteeTableDataByCommitteeObj(ExamCommitteeRequest examCommitteeRequest);
    CommonResponse postThesisCommitteeTableDataByCommitteeObj(ThesisCommitteeRequest thesisCommitteeRequest);

    CommonResponse postPaperTitleByPaperTitleObj(PaperTitleRequest paperTitleRequest);
}
