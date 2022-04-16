package com.example.gradinfo.service;

import com.example.gradinfo.dto.response.CodeDescriptionResponse;
import com.example.gradinfo.dto.response.LoginResponse;
import com.example.gradinfo.dto.response.SpPostNumberResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LoginService {
    LoginResponse getUserInfoByUsernameAndPassword(String userName, String userPassword);
    List<CodeDescriptionResponse> getCodeAndDescription();
    List<SpPostNumberResponse> getPostNumberByUserID(String userId);

    List<SpPostNumberResponse> getAllSpPostNumber();
}
