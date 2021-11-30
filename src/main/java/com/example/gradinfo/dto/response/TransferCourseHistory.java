package com.example.gradinfo.dto.response;

import lombok.Data;

@Data
public class TransferCourseHistory {
    private String trHistoryCourseOper;
    private boolean trHistoryCourseApplyStatus;
    private String trHistoryCourseTransdate;
    private String trHistoryCourseName;
}
