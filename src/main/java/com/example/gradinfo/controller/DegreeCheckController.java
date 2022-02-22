package com.example.gradinfo.controller;

import com.example.gradinfo.dto.request.DegreeCheckRequest;
import com.example.gradinfo.dto.response.CommonResponse;
import com.example.gradinfo.dto.response.DegreeCheckResponse;
import com.example.gradinfo.service.DegreeCheckService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "degreecheck")
@CrossOrigin(origins = "*")
public class DegreeCheckController {
    private final DegreeCheckService degreeCheckService;

    public DegreeCheckController(DegreeCheckService degreeCheckService) {
        this.degreeCheckService = degreeCheckService;
    }

    @PostMapping(path = "/postDegreeCheckByDegreeCheckObj")
    public ResponseEntity<CommonResponse> postDegreeCheckByDegreeCheckObj(@RequestBody DegreeCheckRequest degreeCheckRequest) {
        CommonResponse response = degreeCheckService.postDegreeCheckByDegreeCheckObj(degreeCheckRequest);
        return new ResponseEntity<CommonResponse>(response, HttpStatus.OK);
    }
    @GetMapping(path = "/getDegreeCheckTableDataByStudentIDAndPostNumber")
    public ResponseEntity<List<DegreeCheckResponse>> getDegreeCheckTableDataByStudentIDAndPostNumber(@RequestParam String studentId, String spPostNumber) {
        List<DegreeCheckResponse> responses = degreeCheckService.getDegreeCheckTableDataByStudentIDAndPostNumber(studentId,spPostNumber);
        return new ResponseEntity<List<DegreeCheckResponse>>(responses, HttpStatus.OK);
    }
}
