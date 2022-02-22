package com.example.gradinfo.controller;

import com.example.gradinfo.dto.request.StarsRequest;
import com.example.gradinfo.dto.response.CommonResponse;
import com.example.gradinfo.service.StarsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "stars")
@CrossOrigin(origins = "*")
public class StarsController {
    private StarsService starsService;

    public StarsController(StarsService starsService) {
        this.starsService = starsService;
    }

    @PostMapping(path = "/postStarsReportByStarsObj")
    public ResponseEntity<CommonResponse> postStarsReportByStarsObj(@RequestBody StarsRequest starsRequest) {
        CommonResponse response = starsService.postStarsReportByStarsObj(starsRequest);
        return new ResponseEntity<CommonResponse>(response, HttpStatus.OK);
    }
}
