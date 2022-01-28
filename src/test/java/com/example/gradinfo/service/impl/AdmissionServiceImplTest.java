package com.example.gradinfo.service.impl;

import com.example.gradinfo.dto.response.StudentPostResponse;
import com.example.gradinfo.repository.*;
import com.example.gradinfo.service.CommonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdmissionServiceImplTest {

    @Autowired
    private AdmissionCourseRepository admissionCourseRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentPostRepository studentPostRepository;
    @Autowired
    private TransferCourseRepository transferCourseRepository;
    @Autowired
    private CommonService commonService;
    @Autowired
    private AdmissionHistoryRepository admissionHistoryRepository;
    @Autowired
    private TransferHistoryRepository transferHistoryRepository;

    @Autowired
    AdmissionServiceImpl admissionService;

    @BeforeEach
    void setUp() {
        admissionService = new AdmissionServiceImpl(
                admissionCourseRepository,
                studentRepository,
                studentPostRepository,
                transferCourseRepository,
                commonService,
                admissionHistoryRepository,
                transferHistoryRepository);
    }

    @Test
    @DisplayName("Data should not exist")
    void getStudentPostDataByWrongStudentIDAndPostNumber() {
        StudentPostResponse student = admissionService.getStudentPostDataByStudentIDAndPostNumber("aaa","aaa");
        assertNull(student.getSpObj());
    }

    @Test
    @DisplayName("Data should exist")
    void getStudentPostDataByRightStudentIDAndPostNumber() {
        StudentPostResponse student = admissionService.getStudentPostDataByStudentIDAndPostNumber("0000001","");
        assertNull(student.getSpObj());
    }

    @Test
    void getAdmissionCourseDataByStudentIDAndPostNumber() {
    }

    @Test
    void getPostAdmissionCourseTableDataByNewArr() {
    }
}