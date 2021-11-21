package com.example.gradinfo.dto;

import lombok.Data;

import java.util.List;

@Data
public class AdmissionCourseDto {
        private String adCourseId;
        private String adCourseName;
        private String adCourseTerm;
        private String adCourseGrade;
        private double adCourseUnits;
        private double adCourseGpts;
        private String adCourseApplyCode;
        private String adCourseOper;
        private String adCourseTransdate;
        List<AdmissionCourseHistoryDto> adCourseHistory;
        private String adCourseApplyStatus;
}

