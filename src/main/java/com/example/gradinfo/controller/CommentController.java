package com.example.gradinfo.controller;

import com.example.gradinfo.dto.request.CommentRequest;
import com.example.gradinfo.dto.response.CommentResponse;
import com.example.gradinfo.dto.response.CommonResponse;
import com.example.gradinfo.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(tags = "Comment Management")
@RequestMapping(path = "comment")
@CrossOrigin(origins = "*")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @ApiOperation("Get comment data")
    @GetMapping(path = "/getCommentsTableDataByIDAndPostNumber")
    public ResponseEntity<List<CommentResponse>> getCommentsTableDataByIDAndPostNumber(@RequestParam String studentId, String spPostNumber) {
        List<CommentResponse> responses = commentService.getCommentsTableDataByIDAndPostNumber(studentId, spPostNumber);
        return new ResponseEntity<List<CommentResponse>>(responses, HttpStatus.OK);
    }

    @ApiOperation("Update comment data")
    @PostMapping(path = "/postCommentTableDataByCommentObj")
    public ResponseEntity<CommonResponse> postCommentTableDataByCommentObj(@RequestBody CommentRequest commentRequest) {
        CommonResponse responses = commentService.postCommentTableDataByCommentObj(commentRequest);
        return new ResponseEntity<CommonResponse>(responses, HttpStatus.OK);
    }
}
