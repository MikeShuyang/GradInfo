package com.example.gradinfo.service.impl;

import com.example.gradinfo.repository.AdmissionCourseRepository;
import com.example.gradinfo.repository.StudentPostRepository;
import com.example.gradinfo.repository.StudentRepository;
import com.example.gradinfo.repository.TransferCourseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommonServiceImplTest {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentPostRepository studentPostRepository;
    @Autowired
    AdmissionCourseRepository admissionCourseRepository;
    @Autowired
    TransferCourseRepository transferCourseRepository;

    @Autowired
    CommonServiceImpl commonService;

    @BeforeEach
    void setUp() {
        commonService = new CommonServiceImpl(
                studentRepository,
                studentPostRepository,
                admissionCourseRepository,
                transferCourseRepository);
    }

    @Test
    void getStudentInfoByStudentId() {
    }

    @Test
    @DisplayName("Student should exist")
    void getStudentPostEntitiesByStudentIdAndSpPostNumber() {
        assertNotNull(commonService.getStudentInfoByStudentId("0000001").getSpPostNumbers());
    }

    @Test
    void calculateGpaAndUnit() {
    }

    @Test
    void checkReason() {
    }

    @Test
    void checkAdmissionCourseAndReturnReason() {
    }

    @Test
    void checkTransferCourseAndReturnReason() {
    }

    @Test
    void gpaRules() {
    }
}