package com.example.gradinfo.controller;

import com.example.gradinfo.dto.response.CommonResponse;
import com.example.gradinfo.dto.response.DegreeCheckResponse;
import com.example.gradinfo.service.DegreeCheckService;
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
class DegreeCheckControllerTest {

    @Mock private DegreeCheckService degreeCheckService;
    @Mock private CommonResponse commonResponse;
    @Mock private DegreeCheckResponse degreeCheckResponse;
    @InjectMocks private DegreeCheckController degreeCheckController;

    @Nested
    @DisplayName("Tests for postDegreeCheckByDegreeCheckObj")
    class PostDegreeCheckByDegreeCheckObjTest {
        @Nested
        @DisplayName("When success")
        class WhenSuccessTest {
            @BeforeEach
            void setUp() {
                commonResponse = new CommonResponse();
                commonResponse.setFlag(true);
            }
            @Test
            void successUpdate_trueFlag() {
                // Arrange
                when(degreeCheckService.postDegreeCheckByDegreeCheckObj(any()))
                        .thenReturn(commonResponse);
                // Act
                ResponseEntity<CommonResponse> response =
                        degreeCheckController.postDegreeCheckByDegreeCheckObj(any());
                // Assert
                assertTrue(Objects.requireNonNull(response.getBody()).isFlag());
            }
        }
    }

    @Nested
    @DisplayName("Tests for getDegreeCheckTableDataByStudentIDAndPostNumber")
    class GetDegreeCheckTableDataByStudentIDAndPostNumberTest {
        @Nested
        @DisplayName("When success")
        class WhenSuccessTest {
            private List<DegreeCheckResponse> degreeCheckResponseList;
            @BeforeEach
            void setUp() {
                degreeCheckResponseList = new ArrayList<>();
                degreeCheckResponse = new DegreeCheckResponse();
                degreeCheckResponse.setDegreeName("Master");
                degreeCheckResponseList.add(degreeCheckResponse);
            }
            @Test
            void validIdAndPostNumber_dataExists() {
                // Arrange
                when(degreeCheckService.getDegreeCheckTableDataByStudentIDAndPostNumber(
                        "valid student id", "valid post number"))
                        .thenReturn(degreeCheckResponseList);
                // Act
                ResponseEntity<List<DegreeCheckResponse>> response =
                        degreeCheckController.getDegreeCheckTableDataByStudentIDAndPostNumber(
                                "valid student id", "valid post number");
                // Assert
                assertEquals("Master",
                        Objects.requireNonNull(response.getBody()).get(0).getDegreeName());
            }
        }
    }
}