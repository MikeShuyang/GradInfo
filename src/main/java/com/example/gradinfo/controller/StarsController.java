package com.example.gradinfo.controller;

import com.example.gradinfo.dto.request.StarsRequest;
import com.example.gradinfo.dto.response.CommonResponse;
import com.example.gradinfo.service.StarsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping(path = "/postStarsReportByStarsObj")
    public ResponseEntity<CommonResponse> postStarsReportByStarsObj(@RequestBody StarsRequest starsRequest) {
        CommonResponse response = starsService.postStarsReportByStarsObj(starsRequest);
        return new ResponseEntity<CommonResponse>(response, HttpStatus.OK);
    }
}
