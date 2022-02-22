package com.example.gradinfo.controller;

import com.example.gradinfo.dto.request.ExamCommitteeRequest;
import com.example.gradinfo.dto.request.NonCourseEventRequest;
import com.example.gradinfo.dto.request.ThesisCommitteeRequest;
import com.example.gradinfo.dto.response.*;
import com.example.gradinfo.service.NonCourseEventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(tags = "NonCourseEvent")
@RequestMapping(path = "noncourseevent")
@CrossOrigin(origins = "*")
public class NonCourseEventController {

    private NonCourseEventService nonCourseEventService;

    public NonCourseEventController(NonCourseEventService nonCourseEventService) {
        this.nonCourseEventService = nonCourseEventService;
    }

    @ApiOperation("Get non course related event data")
    @GetMapping(path = "/getNonCourseRelatedEventTableDataByIDAndPostNumber")
    public ResponseEntity<List<NonCourseEventTableDataResponse>> getNonCourseRelatedEventTableDataByIDAndPostNumber(@RequestParam String studentId, String spPostNumber) {

        List<NonCourseEventTableDataResponse> response = nonCourseEventService.getNonCourseRelatedEventTableDataByIDAndPostNumber(studentId,spPostNumber);

        return new ResponseEntity<List<NonCourseEventTableDataResponse>>(response, HttpStatus.OK);
    }

    @ApiOperation("Get exam committee data")
    @GetMapping(path = "/getExamCommitteeTableDataByIDAndPostNumber")
    public ResponseEntity<List<ExamTableDataResponse>> getExamCommitteeTableDataByIDAndPostNumber(@RequestParam String studentId, String spPostNumber) {
        List<ExamTableDataResponse> responses = nonCourseEventService.getExamCommitteeTableDataByIDAndPostNumber(studentId, spPostNumber);
        return new ResponseEntity<List<ExamTableDataResponse>>(responses, HttpStatus.OK);
    }

    @ApiOperation("Get thesis committee data")
    @GetMapping(path = "/getThesisCommitteeTableDataByIDAndPostNumber")
    public ResponseEntity<List<ThesisCommitteeResponse>> getThesisCommitteeTableDataByIDAndPostNumber(@RequestParam String studentId, String spPostNumber) {
        List<ThesisCommitteeResponse> responses = nonCourseEventService.getThesisCommitteeTableDataByIDAndPostNumber(studentId, spPostNumber);
        return new ResponseEntity<List<ThesisCommitteeResponse>>(responses, HttpStatus.OK);
    }

    @ApiOperation("Update non course related event data")
    @PostMapping(path = "/postNonCourseRelatedEventTableDataByEventObj")
    public ResponseEntity<NonCourseEventApplyResponse> postNonCourseRelatedEventTableDataByEventObj(@RequestBody NonCourseEventRequest nonCourseEventRequest) {
        NonCourseEventApplyResponse response = nonCourseEventService.postNonCourseRelatedEventTableDataByEventObj(nonCourseEventRequest);
        return new ResponseEntity<NonCourseEventApplyResponse>(response, HttpStatus.OK);
    }

    @ApiOperation("Update exam committee data")
    @PostMapping(path = "/postExamCommitteeTableDataByCommitteeObj")
    public  ResponseEntity<CommonResponse> postExamCommitteeTableDataByCommitteeObj(@RequestBody ExamCommitteeRequest examCommitteeRequest) {
        System.out.println(examCommitteeRequest.getExamCommitteeObject().getExamCommitteeChar());
        CommonResponse response = nonCourseEventService.postExamCommitteeTableDataByCommitteeObj(examCommitteeRequest);
        return new ResponseEntity<CommonResponse>(response, HttpStatus.OK);
    }

    @ApiOperation("Update thesis committee data")
    @PostMapping(path = "/postThesisCommitteeTableDataByCommitteeObj")
    public  ResponseEntity<CommonResponse> postThesisCommitteeTableDataByCommitteeObj(@RequestBody ThesisCommitteeRequest thesisCommitteeRequest) {
        CommonResponse response = nonCourseEventService.postThesisCommitteeTableDataByCommitteeObj(thesisCommitteeRequest);
        return new ResponseEntity<CommonResponse>(response, HttpStatus.OK);
    }
}
