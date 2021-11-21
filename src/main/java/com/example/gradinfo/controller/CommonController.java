package com.example.gradinfo.controller;


import com.example.gradinfo.dto.StudentInfoDto;
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

    private CommonService commonService;

    public CommonController(CommonService commonService) {
        this.commonService = commonService;
    }

    @RequestMapping(path = "/getStudentInfoByStudentID")
    public ResponseEntity<StudentInfoDto> getStudentInfoByStudentID(@RequestParam String student_id) {

        StudentInfoDto response = commonService.getStudentInfoByStudentId(student_id);
        return new ResponseEntity<StudentInfoDto>(response, HttpStatus.OK);
    }
}
