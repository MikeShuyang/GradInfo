package com.example.gradinfo.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class TransferCourseRequest {
    private StudentInfo studentInfo;
    private List<Course> courseList;
}
