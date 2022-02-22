package com.example.gradinfo.dto.request;

import lombok.Data;

@Data
public class ThesisCommitteeRequest {
    private StudentInfo studentInfo;
    private ThesisCommitteeObject thesisCommitteeObject;
}
