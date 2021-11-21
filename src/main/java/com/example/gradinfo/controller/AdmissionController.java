package com.example.gradinfo.controller;

import com.example.gradinfo.dto.AdmissionCourseDto;
import com.example.gradinfo.dto.AdmissionCourseListDto;
import com.example.gradinfo.dto.StudentPostDto;
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
    public ResponseEntity<StudentPostDto> getStudentPostDataByStudentIDAndPostNumber(@RequestParam String studentId, String spPostNumber) {

        StudentPostDto response = admissionService.getStudentPostDataByStudentIDAndPostNumber(studentId, spPostNumber);

        return new ResponseEntity<StudentPostDto>(response, HttpStatus.OK);
    }


    @GetMapping(path = "getAdmissionCourseTableDataByIDAndPostNumber")
    public ResponseEntity<AdmissionCourseListDto> getAdmissionCourseTableDataByIDAndPostNumber(@RequestParam String studentId, String spPostNumber) {
        AdmissionCourseListDto response = admissionService.getAdmissionCourseDataByStudentIDAndPostNumber(studentId, spPostNumber);

        return new ResponseEntity<AdmissionCourseListDto>(response, HttpStatus.OK);
    }
}
