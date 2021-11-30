package com.example.gradinfo.service;

import com.example.gradinfo.dto.response.LoginResponse;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    LoginResponse getUserInfoByUsernameAndPassword(String userName, String userPassword);
}
