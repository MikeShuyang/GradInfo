package com.example.gradinfo.controller;

import com.example.gradinfo.dto.request.TransferCourseRequest;
import com.example.gradinfo.dto.response.BachelorDegreeResponse;
import com.example.gradinfo.dto.response.TransferCourseApplyResponse;
import com.example.gradinfo.dto.response.TransferCourseResponse;
import com.example.gradinfo.dto.response.TransferInstitution;
import com.example.gradinfo.service.TransferService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "Transfer Management")
@RequestMapping(path = "transfer")
@CrossOrigin(origins = "*")
public class TransferController {
    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @ApiOperation("Get transfer course data")
    @GetMapping(path = "/getTransferCourseTableDataByIDAndPostNumber")
    public ResponseEntity<List<TransferCourseResponse>> getTransferCourseTableDataByIDAndPostNumber(@RequestParam String studentId, String spPostNumber) {
        List<TransferCourseResponse> response = transferService.getTransferCourseTableDataByIDAndPostNumber(studentId, spPostNumber);
        return new ResponseEntity<List<TransferCourseResponse>>(response, HttpStatus.OK);
    }

    @ApiOperation("Get transfer program of study")
    @GetMapping(path = "/getTransferProgramOfStudyByIDAndPostNumber")
    public ResponseEntity<List<TransferInstitution>> getTransferProgramOfStudyByIDAndPostNumber(@RequestParam String studentId, String spPostNumber) {
        List<TransferInstitution> response = transferService.getTransferInfoByIDAndPostNumber(studentId, spPostNumber);
        return new ResponseEntity<List<TransferInstitution>>(response, HttpStatus.OK);
    }

    @ApiOperation("Get bachelor degree info")
    @GetMapping(path = "/getBachelorDegreeInfoByID")
    public ResponseEntity<BachelorDegreeResponse> getBachelorDegreeInfoByID(@RequestParam String studentId) {
        BachelorDegreeResponse response = transferService.getBachelorDegreeInfoByID(studentId);
        return new ResponseEntity<BachelorDegreeResponse>(response, HttpStatus.OK);
    }

    @ApiOperation("Update transfer course data")
    @PostMapping(path = "postTransferCourseTableDataByNewArr")
    public ResponseEntity<TransferCourseApplyResponse> postAdmissionCourseTableDataByNewArr(@RequestBody TransferCourseRequest transferCourseRequest) {
        TransferCourseApplyResponse response = transferService.postTransferCourseTableDataByNewArr(transferCourseRequest);

        return new ResponseEntity<TransferCourseApplyResponse>(response, HttpStatus.OK);
    }

}
