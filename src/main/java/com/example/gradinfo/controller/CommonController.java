package com.example.gradinfo.controller;


import com.example.gradinfo.dto.response.StudentInfoResponse;
import com.example.gradinfo.service.CommonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "common")
@CrossOrigin(origins = "*")
public class CommonController {

    private final CommonService commonService;

    public CommonController(CommonService commonService) {
        this.commonService = commonService;
    }

    @RequestMapping(path = "/getStudentInfoByStudentID")
    public ResponseEntity<StudentInfoResponse> getStudentInfoByStudentID(@RequestParam String studentId) {

        StudentInfoResponse response = commonService.getStudentInfoByStudentId(studentId);
        return new ResponseEntity<StudentInfoResponse>(response, HttpStatus.OK);
    }
}
