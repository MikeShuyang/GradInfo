package com.example.gradinfo.dto.response;

import lombok.Data;

@Data
public class LoginResponse {
    private String userId;
    private String userCode;
    private String userName;
    private String userPassword;
    private String userOper;
    private String userLocked;
    private String userEventAccessLevel;
    private String userSuper;
}
