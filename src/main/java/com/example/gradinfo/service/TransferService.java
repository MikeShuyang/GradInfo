package com.example.gradinfo.service;

import com.example.gradinfo.dto.request.TransferCourseRequest;
import com.example.gradinfo.dto.response.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransferService {

    List<TransferCourseResponse> getTransferCourseTableDataByIDAndPostNumber(String studentId, String spPostNumber);
    List<TransferInstitution> getTransferInfoByIDAndPostNumber(String studentId, String spPostNumber);
    BachelorDegreeResponse getBachelorDegreeInfoByID(String studentId);
    TransferCourseApplyResponse postTransferCourseTableDataByNewArr(TransferCourseRequest transferCourseRequest);
}
