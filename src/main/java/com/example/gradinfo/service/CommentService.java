package com.example.gradinfo.service;

import com.example.gradinfo.dto.request.CommentRequest;
import com.example.gradinfo.dto.response.CommentResponse;
import com.example.gradinfo.dto.response.CommonResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    List<CommentResponse> getCommentsTableDataByIDAndPostNumber(String studentId, String spPostNumber);
    CommonResponse postCommentTableDataByCommentObj(CommentRequest commentRequest);
}
