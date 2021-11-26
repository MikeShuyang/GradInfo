package com.example.gradinfo.service;

import com.example.gradinfo.dto.response.BachelorDegreeResponse;
import com.example.gradinfo.dto.response.TransferCourseListResponse;
import com.example.gradinfo.dto.response.TransferCourseResponse;
import com.example.gradinfo.dto.response.TransferInstitutionListResponse;
import org.springframework.stereotype.Service;

@Service
public interface TransferService {
    TransferCourseListResponse getTransferCourseTableDataByIDAndPostNumber(String studentId, String spPostNumber);
    TransferInstitutionListResponse getTransferInfoByIDAndPostNumber(String studentId, String spPostNumber);
    BachelorDegreeResponse getBachelorDegreeInfoByID(String studentId);
}
