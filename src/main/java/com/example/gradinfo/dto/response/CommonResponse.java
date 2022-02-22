package com.example.gradinfo.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class CommonResponse {
    private boolean flag;
    private List<String> reasonList;
}
