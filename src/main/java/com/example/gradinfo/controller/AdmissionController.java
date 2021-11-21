package com.example.gradinfo.controller;

import com.example.gradinfo.dto.StudentInfoDto;
import com.example.gradinfo.dto.StudentPostDto;
import com.example.gradinfo.entity.SysStudentPostEntity;
import com.example.gradinfo.service.AdmissionService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<StudentPostDto> getStudentPostDataByStudentIDAndPostNumber(@RequestParam String student_id, String sp_post_number) {

        StudentPostDto response = admissionService.getStudentPostDataByStudentIDAndPostNumber(student_id, sp_post_number);

        return new ResponseEntity<StudentPostDto>(response, HttpStatus.OK);
    }


//    @GetMapping(path = "getAdmissionCourseTableDataByIDAndPostNumber")
//    public ResponseEntity<> getAdmissionCourseTableDataByIDAndPostNumber() {
//
//
//    }
}
