package com.example.gradinfo.dto.request;

import lombok.Data;

@Data
public class NonCourseEventRequest {
    private StudentInfo studentInfo;
    private EventObject eventObject;
}
