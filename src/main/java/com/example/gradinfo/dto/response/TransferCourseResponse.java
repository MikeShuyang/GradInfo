package com.example.gradinfo.dto.response;

import lombok.Data;

import java.util.List;
@Data
public class TransferCourseResponse {
    private String trCourseId;
    private String trCourseName;
    private String trCourseTerm;
    private String trCourseGrade;
    private double trCourseUnits;
    private double trCourseGpts;
    private String trCourseApplyCode;
    private String trCourseOper;
    private String trCourseTransdate;
    List<TransferCourseHistory> trCourseHistory;
    private boolean trCourseApplyStatus;
}
