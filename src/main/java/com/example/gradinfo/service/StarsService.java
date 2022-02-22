package com.example.gradinfo.service;

import com.example.gradinfo.dto.request.StarsRequest;
import com.example.gradinfo.dto.response.CommonResponse;
import org.springframework.stereotype.Service;

@Service
public interface StarsService {
    CommonResponse postStarsReportByStarsObj(StarsRequest starsRequest);
}
