package com.example.gradinfo.controller;

import com.example.gradinfo.dto.response.AdmissionCourseApplyResponse;
import com.example.gradinfo.dto.response.AdmissionCourseResponse;
import com.example.gradinfo.dto.response.StudentPostResponse;
import com.example.gradinfo.service.AdmissionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdmissionControllerTest {

    @Mock private AdmissionService admissionService;
    @Mock private StudentPostResponse studentPostResponse;
    @Mock private AdmissionCourseResponse admissionCourseResponse;
    @Mock private AdmissionCourseApplyResponse admissionCourseApplyResponse;
    @InjectMocks private AdmissionController admissionController;

    @Nested
    @DisplayName("Tests for getStudentPostDataByStudentIDAndPostNumber")
    class GetStudentPostDataByStudentIDAndPostNumberTest {
        @Nested
        @DisplayName("When success")
        class WhenSuccessTest {
            @BeforeEach
            void setUp() {
                studentPostResponse = new StudentPostResponse();
                studentPostResponse.setSpMajor("Computer Science");
            }
            @Test
            void validIdAndPostNumber_dataExists() {
                // Arrange
                when(admissionService.getStudentPostDataByStudentIDAndPostNumber(
                        "valid student id", "valid post number"))
                        .thenReturn(studentPostResponse);
                // Act
                ResponseEntity<StudentPostResponse> response =
                        admissionController.getStudentPostDataByStudentIDAndPostNumber(
                                "valid student id", "valid post number");
                // Assert
                assertEquals("Computer Science", Objects.requireNonNull(response.getBody()).getSpMajor());
            }
        }

        @Nested
        @DisplayName("When fail")
        class WhenFailTest {
            @BeforeEach
            void setUp() {
                studentPostResponse = new StudentPostResponse();
            }
            @Test
            void inValidIdAndPostNumber_dataNotExist() {
                // Arrange
                when(admissionService.getStudentPostDataByStudentIDAndPostNumber(
                        "invalid student id", "invalid post number"))
                        .thenReturn(studentPostResponse);
                // Act
                ResponseEntity<StudentPostResponse> response =
                        admissionController.getStudentPostDataByStudentIDAndPostNumber(
                                "invalid student id", "invalid post number");
                // Assert
                assertNull(response.getBody().getSpMajor());
            }
        }
    }

    @Nested
    @DisplayName("Tests for getAdmissionCourseTableDataByIDAndPostNumber")
    class GetAdmissionCourseTableDataByIDAndPostNumberTest {
        @Nested
        @DisplayName("When success")
        class WhenSuccessTest {
            private List<AdmissionCourseResponse> admissionCourseResponseList;
            @BeforeEach
            void setUp() {
                admissionCourseResponse = new AdmissionCourseResponse();
                admissionCourseResponse.setAdCourseName("577");
                admissionCourseResponseList = new ArrayList<>();
                admissionCourseResponseList.add(admissionCourseResponse);
            }
            @Test
            void validIdAndPostNumber_dataExists() {
                // Arrange
                when(admissionService.getAdmissionCourseDataByStudentIDAndPostNumber(
                        "valid student id", "valid post number"))
                        .thenReturn(admissionCourseResponseList);
                // Act
                ResponseEntity<List<AdmissionCourseResponse>> response =
                        admissionController.getAdmissionCourseTableDataByIDAndPostNumber(
                                "valid student id", "valid post number");
                // Assert
                assertEquals("577", Objects.requireNonNull(response.getBody()).get(0).getAdCourseName());
            }
        }
    }

    @Nested
    @DisplayName("Tests for postAdmissionCourseTableDataByNewArr")
    class PostAdmissionCourseTableDataByNewArrTest {
        @Nested
        @DisplayName("When success")
        class WhenSuccessTest {
            @BeforeEach
            void setUp() {
                admissionCourseApplyResponse = new AdmissionCourseApplyResponse();
                admissionCourseApplyResponse.setFlag(true);
            }
            @Test
            void successUpdate_trueFlag() {
                // Arrange
                when(admissionService.getPostAdmissionCourseTableDataByNewArr(any()))
                        .thenReturn(admissionCourseApplyResponse);
                // Act
                ResponseEntity<AdmissionCourseApplyResponse> response =
                        admissionController.postAdmissionCourseTableDataByNewArr(any());
                // Assert
                assertTrue(Objects.requireNonNull(response.getBody()).isFlag());
            }
        }
    }
}