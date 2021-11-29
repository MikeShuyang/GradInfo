package com.example.gradinfo.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class StudentInfoResponse {
    private String studentId;
    private String studentName;
    private List<String> spPostNumbers;
}
