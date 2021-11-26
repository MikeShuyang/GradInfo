package com.example.gradinfo.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class TransferCourseListResponse {
    List<TransferCourseResponse> transferCourseResponseList;
}
