package com.example.gradinfo.dto.request;

import lombok.Data;

@Data
public class ExamCommitteeRequest {
    private StudentInfo studentInfo;
    private ExamCommitteeObject examCommitteeObject;
}
