package com.example.gradinfo.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class TransferCourseApplyResponse {
    private boolean flag;
    private List<String> reasonList;
}
