package com.example.gradinfo.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class NonCourseEventTableDataResponse {
    private String eventCode;
    private String eventDescription;
    private String ncrerRelated;
    private String ncrerOper;
    private String ncrerTransdate;
}
