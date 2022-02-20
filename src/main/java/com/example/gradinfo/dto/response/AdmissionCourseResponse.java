package com.example.gradinfo.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class AdmissionCourseResponse {
        private String adCourseId;
        private String adCourseName;
        private String adCourseNumber;
        private String adCourseTerm;
        private String adCourseGrade;
        private double adCourseUnits;
        private double adCourseGpts;
        private String adCourseApplyCode;
        private String adCourseOper;
        private String adCourseTransdate;
        List<AdmissionCourseHistory> adCourseHistory;
        private boolean adCourseApplyStatus;
}

