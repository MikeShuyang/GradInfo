package com.example.gradinfo.service;

import com.example.gradinfo.dto.request.DegreeCheckRequest;
import com.example.gradinfo.dto.response.CommonResponse;
import com.example.gradinfo.dto.response.DegreeCheckResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DegreeCheckService {
    CommonResponse postDegreeCheckByDegreeCheckObj(DegreeCheckRequest degreeCheckRequest);
    List<DegreeCheckResponse> getDegreeCheckTableDataByStudentIDAndPostNumber(String studentId, String spPostNumber);

}
