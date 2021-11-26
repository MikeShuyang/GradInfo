package com.example.gradinfo.controller;

import com.example.gradinfo.dto.request.AdmissionCourseRequest;
import com.example.gradinfo.dto.response.AdmissionCourseApplyResponse;
import com.example.gradinfo.dto.response.AdmissionCourseListResponse;
import com.example.gradinfo.dto.response.StudentPostResponse;
import com.example.gradinfo.service.AdmissionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "admission")
@CrossOrigin(origins = "*")
public class AdmissionController {

    private AdmissionService admissionService;

    public AdmissionController(AdmissionService admissionService) {
        this.admissionService = admissionService;
    }

    @GetMapping(path = "/getStudentPostDataByStudentIDAndPostNumber")
    public ResponseEntity<StudentPostResponse> getStudentPostDataByStudentIDAndPostNumber(@RequestParam String studentId, String spPostNumber) {

        StudentPostResponse response = admissionService.getStudentPostDataByStudentIDAndPostNumber(studentId, spPostNumber);

        return new ResponseEntity<StudentPostResponse>(response, HttpStatus.OK);
    }

    @GetMapping(path = "getAdmissionCourseTableDataByIDAndPostNumber")
    public ResponseEntity<AdmissionCourseListResponse> getAdmissionCourseTableDataByIDAndPostNumber(@RequestParam String studentId, String spPostNumber) {
        AdmissionCourseListResponse response = admissionService.getAdmissionCourseDataByStudentIDAndPostNumber(studentId, spPostNumber);

        return new ResponseEntity<AdmissionCourseListResponse>(response, HttpStatus.OK);
    }

    @PostMapping(path = "postAdmissionCourseTableDataByNewArr")
    public ResponseEntity<AdmissionCourseApplyResponse> postAdmissionCourseTableDataByNewArr(@RequestBody AdmissionCourseRequest admissionCourseRequest) {
        AdmissionCourseApplyResponse response = new AdmissionCourseApplyResponse();
        
        return new ResponseEntity<AdmissionCourseApplyResponse>(response, HttpStatus.OK);
    }
}
