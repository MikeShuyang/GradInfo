package com.example.gradinfo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("api/health")
    public String testAPI(){
        retur  "Hello World";
    }
}