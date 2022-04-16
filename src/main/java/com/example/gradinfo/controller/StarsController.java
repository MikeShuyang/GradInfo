package com.example.gradinfo.controller;

import com.example.gradinfo.dto.request.StarsRequest;
import com.example.gradinfo.dto.response.CommonResponse;
import com.example.gradinfo.dto.response.StarsResponse;
import com.example.gradinfo.service.StarsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "StarsControl")
@RequestMapping(path = "stars")
@CrossOrigin(origins = "*")
public class StarsController {
    private StarsService starsService;

    public StarsController(StarsService starsService) {
        this.starsService = starsService;
    }

    @ApiOperation("Update stars report")
    @PostMapping(path = "/postStarsExceptionByStarsObj")
    public ResponseEntity<CommonResponse> postStarsReportByStarsObj(@RequestBody StarsRequest starsRequest) {
        CommonResponse response = starsService.postStarsExceptionByStarsObj(starsRequest);
        return new ResponseEntity<CommonResponse>(response, HttpStatus.OK);
    }

    @ApiOperation("Get starts exception by studentID and post number")
    @GetMapping(path = "/getStarsExceptionByStudentIDAndPostNumber")
    public ResponseEntity<List<StarsResponse>> getStarsExceptionByStudentIDAndPostNumber(@RequestParam String studentId, String spPostNumber) {
        List<StarsResponse> responses = starsService.getStarsExceptionByStudentIDAndPostNumber(studentId,spPostNumber);
        return new ResponseEntity<List<StarsResponse>>(responses, HttpStatus.OK);
    }
}
