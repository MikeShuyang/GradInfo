package com.example.gradinfo.controller;

import com.example.gradinfo.dto.response.CodeDescriptionResponse;
import com.example.gradinfo.dto.response.LoginResponse;
import com.example.gradinfo.dto.response.SpPostNumberResponse;
import com.example.gradinfo.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "Advisor Login")
@RequestMapping(path = "login")
@CrossOrigin(origins = "*")
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @ApiOperation("Get user info")
    @GetMapping(path = "/getUserInfoByUsernameAndPassword")
    public ResponseEntity<LoginResponse> getUserInfoByUsernameAndPassword(@RequestParam String userName, String userPassword) {
        LoginResponse response = loginService.getUserInfoByUsernameAndPassword(userName, userPassword);
        return new ResponseEntity<LoginResponse>(response, HttpStatus.OK);
    }

    @ApiOperation("Get user's code and description")
    @GetMapping(path = "/getCodeAndDescription")
    public ResponseEntity<List<CodeDescriptionResponse>> getCodeAndDescription() {
        List<CodeDescriptionResponse> responses = loginService.getCodeAndDescription();
        return new ResponseEntity<List<CodeDescriptionResponse>>(responses, HttpStatus.OK);
    }

    @ApiOperation("Get all SpPostNumber")
    @GetMapping(path = "/getAllSpPostNumber")
    public ResponseEntity<List<SpPostNumberResponse>> getAllSpPostNumber() {
        List<SpPostNumberResponse> responses = loginService.getAllSpPostNumber();
        return new ResponseEntity<List<SpPostNumberResponse>>(responses, HttpStatus.OK);
    }

    @ApiOperation("Get post number by userID")
    @GetMapping(path = "/getPostNumberByUserID")
    public ResponseEntity<List<SpPostNumberResponse>> getPostNumberByUserID(String userId) {
        List<SpPostNumberResponse> responses = loginService.getPostNumberByUserID(userId);
        return new ResponseEntity<List<SpPostNumberResponse>>(responses, HttpStatus.OK);
    }
}
