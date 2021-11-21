package com.example.gradinfo.dto;

import lombok.Data;

import java.util.List;

@Data
public class StudentInfoDto {
    private String studentId;
    private String studentName;
    private List<String> spPostNumbers;
}
