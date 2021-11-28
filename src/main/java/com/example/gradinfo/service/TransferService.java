package com.example.gradinfo.service;

import com.example.gradinfo.dto.request.TransferCourseRequest;
import com.example.gradinfo.dto.response.*;
import org.springframework.stereotype.Service;

@Service
public interface TransferService {
    TransferCourseListResponse getTransferCourseTableDataByIDAndPostNumber(String studentId, String spPostNumber);
    TransferInstitutionListResponse getTransferInfoByIDAndPostNumber(String studentId, String spPostNumber);
    BachelorDegreeResponse getBachelorDegreeInfoByID(String studentId);
    TransferCourseApplyResponse postTransferCourseTableDataByNewArr(TransferCourseRequest transferCourseRequest);
}
