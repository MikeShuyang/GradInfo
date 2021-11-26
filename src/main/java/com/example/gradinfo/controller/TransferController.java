package com.example.gradinfo.controller;

import com.example.gradinfo.dto.response.BachelorDegreeResponse;
import com.example.gradinfo.dto.response.TransferCourseListResponse;
import com.example.gradinfo.dto.response.TransferInstitutionListResponse;
import com.example.gradinfo.service.TransferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "transfer")
@CrossOrigin(origins = "*")
public class TransferController {
    private TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @GetMapping(path = "/getTransferCourseTableDataByIDAndPostNumber")
    public ResponseEntity<TransferCourseListResponse> getTransferCourseTableDataByIDAndPostNumber(@RequestParam String studentId, String spPostNumber) {
        TransferCourseListResponse response = transferService.getTransferCourseTableDataByIDAndPostNumber(studentId, spPostNumber);
        return new ResponseEntity<TransferCourseListResponse>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/getTransferInfoByIDAndPostNumber")
    public ResponseEntity<TransferInstitutionListResponse> getTransferInfoByIDAndPostNumber(@RequestParam String studentId, String spPostNumber) {
        TransferInstitutionListResponse response = transferService.getTransferInfoByIDAndPostNumber(studentId, spPostNumber);
        return new ResponseEntity<TransferInstitutionListResponse>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/getBachelorDegreeInfoByID")
    public ResponseEntity<BachelorDegreeResponse> getBachelorDegreeInfoByID(@RequestParam String studentId) {
        BachelorDegreeResponse response = transferService.getBachelorDegreeInfoByID(studentId);
        return new ResponseEntity<BachelorDegreeResponse>(response, HttpStatus.OK);
    }

}
