package com.example.gradinfo.dto.request;

import lombok.Data;

@Data
public class CommentRequest {
    private StudentInfo studentInfo;
    private CommentObject commentObject;
}
