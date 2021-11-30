package com.example.gradinfo.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class AdmissionCourseRequest {
    private UserInfo userInfo;
    private StudentInfo studentInfo;
    private List<Course> courseList;
}