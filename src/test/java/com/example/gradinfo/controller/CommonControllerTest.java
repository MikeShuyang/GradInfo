package com.example.gradinfo.controller;

import com.example.gradinfo.dto.response.StudentInfoResponse;
import com.example.gradinfo.service.CommonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommonControllerTest {

    @Mock private CommonService commonService;
    @Mock private StudentInfoResponse studentInfoResponse;
    @InjectMocks private CommonController commonController;

    @Nested
    @DisplayName("Tests for getStudentInfoByStudentID")
    class GetStudentInfoByStudentIDTest {
        @Nested
        @DisplayName("When success")
        class WhenSuccessTest {
            @BeforeEach
            void setUp() {
                studentInfoResponse = new StudentInfoResponse();
                studentInfoResponse.setStudentId("110");
                studentInfoResponse.setStudentName("Tester");
            }
            @Test
            void validStudentID_dataExists() {
                // Arrange
                when(commonService.getStudentInfoByStudentId("110"))
                        .thenReturn(studentInfoResponse);
                // Act
                ResponseEntity<StudentInfoResponse> response =
                        commonController.getStudentInfoByStudentID("110");
                // Assert
                assertEquals("Tester", Objects.requireNonNull(response.getBody()).getStudentName());
            }
        }
    }
}