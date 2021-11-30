package com.example.gradinfo.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class AdmissionCourseApplyResponse {
    private boolean flag;
    private List<String> reasonList;
}
