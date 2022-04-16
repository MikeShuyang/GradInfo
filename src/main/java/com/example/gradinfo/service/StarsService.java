package com.example.gradinfo.service;

import com.example.gradinfo.dto.request.StarsRequest;
import com.example.gradinfo.dto.response.CommonResponse;
import com.example.gradinfo.dto.response.StarsResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StarsService {
    CommonResponse postStarsExceptionByStarsObj(StarsRequest starsRequest);
    List<StarsResponse> getStarsExceptionByStudentIDAndPostNumber(String studentId, String spPostNumber);
}
