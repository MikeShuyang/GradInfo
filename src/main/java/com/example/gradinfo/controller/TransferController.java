package com.example.gradinfo.controller;

import com.example.gradinfo.dto.request.AdmissionCourseRequest;
import com.example.gradinfo.dto.request.TransferCourseRequest;
import com.example.gradinfo.dto.response.*;
import com.example.gradinfo.service.TransferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "transfer")
@CrossOrigin(origins = "*")
public class TransferController {
    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @GetMapping(path = "/getTransferCourseTableDataByIDAndPostNumber")
    public ResponseEntity<List<TransferCourseResponse>> getTransferCourseTableDataByIDAndPostNumber(@RequestParam String studentId, String spPostNumber) {
        List<TransferCourseResponse> response = transferService.getTransferCourseTableDataByIDAndPostNumber(studentId, spPostNumber);
        return new ResponseEntity<List<TransferCourseResponse>>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/getTransferProgramOfStudyByIDAndPostNumber")
    public ResponseEntity<List<TransferInstitution>> getTransferProgramOfStudyByIDAndPostNumber(@RequestParam String studentId, String spPostNumber) {
        List<TransferInstitution> response = transferService.getTransferInfoByIDAndPostNumber(studentId, spPostNumber);
        return new ResponseEntity<List<TransferInstitution>>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/getBachelorDegreeInfoByID")
    public ResponseEntity<BachelorDegreeResponse> getBachelorDegreeInfoByID(@RequestParam String studentId) {
        BachelorDegreeResponse response = transferService.getBachelorDegreeInfoByID(studentId);
        return new ResponseEntity<BachelorDegreeResponse>(response, HttpStatus.OK);
    }

    @PostMapping(path = "postTransferCourseTableDataByNewArr")
    public ResponseEntity<TransferCourseApplyResponse> postAdmissionCourseTableDataByNewArr(@RequestBody TransferCourseRequest transferCourseRequest) {
        TransferCourseApplyResponse response = transferService.postTransferCourseTableDataByNewArr(transferCourseRequest);

        return new ResponseEntity<TransferCourseApplyResponse>(response, HttpStatus.OK);
    }

}
