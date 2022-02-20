package com.example.gradinfo.controller;

import com.example.gradinfo.dto.response.LoginResponse;
import com.example.gradinfo.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "login")
@CrossOrigin(origins = "*")
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping(path = "/getUserInfoByUsernameAndPassword")
    public ResponseEntity<LoginResponse> getUserInfoByUsernameAndPassword(@RequestParam String userName, String userPassword) {
        LoginResponse response = loginService.getUserInfoByUsernameAndPassword(userName, userPassword);
        return new ResponseEntity<LoginResponse>(response, HttpStatus.OK);
    }

}
