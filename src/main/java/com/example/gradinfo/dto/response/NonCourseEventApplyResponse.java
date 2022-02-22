package com.example.gradinfo.dto.response;

import lombok.Data;

import java.util.List;
@Data
public class NonCourseEventApplyResponse {
    private List<String> reasonList;
    private boolean flag;
}
