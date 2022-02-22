package com.example.gradinfo.dto.request;

import lombok.Data;

@Data
public class DegreeCheckRequest {
    private StudentInfo studentInfo;
    private DegreeCheckObject degreeCheckObject;
}
