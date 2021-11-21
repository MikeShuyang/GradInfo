package com.example.gradinfo.dto;

import lombok.Data;

import java.util.List;

@Data
public class StudentInfoDto {
    private String student_id;
    private String Student_name;
    private List<String> sp_post_numbers;
}
