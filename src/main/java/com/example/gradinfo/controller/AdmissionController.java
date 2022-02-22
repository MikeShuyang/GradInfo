package com.example.gradinfo.controller;

import com.example.gradinfo.dto.request.AdmissionCourseRequest;
import com.example.gradinfo.dto.response.AdmissionCourseApplyResponse;
import com.example.gradinfo.dto.response.AdmissionCourseResponse;
import com.example.gradinfo.dto.response.StudentPostResponse;
import com.example.gradinfo.service.AdmissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "Admission Management")
@RequestMapping(path = "admission")
@CrossOrigin(origins = "*")
public class AdmissionController {

    private final AdmissionService admissionService;

    public AdmissionController(AdmissionService admissionService) {
        this.admissionService = admissionService;
    }

    @ApiOperation("Get student post data")
    @GetMapping(path = "/getStudentPostDataByStudentIDAndPostNumber")
    public ResponseEntity<StudentPostResponse> getStudentPostDataByStudentIDAndPostNumber(@RequestParam String studentId, String spPostNumber) {

        StudentPostResponse response = admissionService.getStudentPostDataByStudentIDAndPostNumber(studentId, spPostNumber);

        return new ResponseEntity<StudentPostResponse>(response, HttpStatus.OK);
    }

    @ApiOperation("Get admission course data")
    @GetMapping(path = "getAdmissionCourseTableDataByIDAndPostNumber")
    public ResponseEntity<List<AdmissionCourseResponse>> getAdmissionCourseTableDataByIDAndPostNumber(@RequestParam String studentId, String spPostNumber) {
        List<AdmissionCourseResponse> response = admissionService.getAdmissionCourseDataByStudentIDAndPostNumber(studentId, spPostNumber);

        return new ResponseEntity<List<AdmissionCourseResponse>>(response, HttpStatus.OK);
    }

    @ApiOperation("Update admission course data")
    @PostMapping(path = "postAdmissionCourseTableDataByNewArr")
    public ResponseEntity<AdmissionCourseApplyResponse> postAdmissionCourseTableDataByNewArr(@RequestBody AdmissionCourseRequest admissionCourseRequest) {
        AdmissionCourseApplyResponse response = admissionService.getPostAdmissionCourseTableDataByNewArr(admissionCourseRequest);
        
        return new ResponseEntity<AdmissionCourseApplyResponse>(response, HttpStatus.OK);
    }
}
