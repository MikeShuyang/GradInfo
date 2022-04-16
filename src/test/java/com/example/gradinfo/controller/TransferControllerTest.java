package com.example.gradinfo.controller;

import com.example.gradinfo.dto.response.*;
import com.example.gradinfo.service.TransferService;
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
class TransferControllerTest {

    @Mock private TransferService transferService;
    @Mock private TransferCourseResponse transferCourseResponse;
    @Mock private TransferInstitution transferInstitution;
    @Mock private BachelorDegreeResponse bachelorDegreeResponse;
    @Mock private TransferCourseApplyResponse transferCourseApplyResponse;
    @InjectMocks private TransferController transferController;

    @Nested
    @DisplayName("Tests for getTransferCourseTableDataByIDAndPostNumber")
    class GetTransferCourseTableDataByIDAndPostNumberTest {
        @Nested
        @DisplayName("When success")
        class WhenSuccessTest {
            private List<TransferCourseResponse> transferCourseResponseList;
            @BeforeEach
            void setUp() {
                transferCourseResponseList = new ArrayList<>();
                transferCourseResponse = new TransferCourseResponse();
                transferCourseResponse.setTrCourseName("577AB");
                transferCourseResponseList.add(transferCourseResponse);
            }
            @Test
            void validIdAndPostNumber_dataExists() {
                // Arrange
                when(transferService.getTransferCourseTableDataByIDAndPostNumber(
                        "valid student id", "valid post number"))
                        .thenReturn(transferCourseResponseList);
                // Act
                ResponseEntity<List<TransferCourseResponse>> response =
                        transferController.getTransferCourseTableDataByIDAndPostNumber(
                                "valid student id", "valid post number");
                // Assert
                assertEquals("577AB",
                        Objects.requireNonNull(response.getBody()).get(0).getTrCourseName());
            }
        }
    }

    @Nested
    @DisplayName("Tests for getTransferProgramOfStudyByIDAndPostNumber")
    class GetTransferProgramOfStudyByIDAndPostNumberTest {
        @Nested
        @DisplayName("When success")
        class WhenSuccessTest {
            private List<TransferInstitution> transferInstitutionList;
            @BeforeEach
            void setUp() {
                transferInstitutionList = new ArrayList<>();
                transferInstitution = new TransferInstitution();
                transferInstitution.setInstitutionName("UCLA");
                transferInstitutionList.add(transferInstitution);
            }
            @Test
            void validIdAndPostNumber_dataExists() {
                // Arrange
                when(transferService.getTransferInfoByIDAndPostNumber(
                        "valid student id", "valid post number"))
                        .thenReturn(transferInstitutionList);
                // Act
                ResponseEntity<List<TransferInstitution>> response =
                        transferController.getTransferProgramOfStudyByIDAndPostNumber(
                                "valid student id", "valid post number");
                // Assert
                assertEquals("UCLA",
                        Objects.requireNonNull(response.getBody()).get(0).getInstitutionName());
            }
        }
    }

    @Nested
    @DisplayName("Tests for getBachelorDegreeInfoByID")
    class GetBachelorDegreeInfoByIDTest {
        @Mock private BachelorDegree bachelorDegree;
        @Mock private List<BachelorDegree> bachelorDegreeList;
        @Nested
        @DisplayName("When success")
        class WhenSuccessTest {
            @BeforeEach
            void setUp() {
                bachelorDegreeResponse = new BachelorDegreeResponse();
                bachelorDegree = new BachelorDegree();
                bachelorDegreeList = new ArrayList<>();
                bachelorDegree.setSbName("SbName");
                bachelorDegreeList.add(bachelorDegree);
                bachelorDegreeResponse.setBachelorDegreeList(bachelorDegreeList);
            }
            @Test
            void validId_dataExists() {
                // Arrange
                when(transferService.getBachelorDegreeInfoByID("valid_id"))
                        .thenReturn(bachelorDegreeResponse);
                // Act
                ResponseEntity<BachelorDegreeResponse> response =
                        transferController.getBachelorDegreeInfoByID("valid_id");
                // Assert
                assertEquals("SbName",
                        Objects.requireNonNull(response.getBody()).getBachelorDegreeList().get(0).getSbName());
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
                transferCourseApplyResponse = new TransferCourseApplyResponse();
                transferCourseApplyResponse.setFlag(true);
            }
            @Test
            void successUpdate_trueFlag() {
                // Arrange
                when(transferService.postTransferCourseTableDataByNewArr(any()))
                        .thenReturn(transferCourseApplyResponse);
                // Act
                ResponseEntity<TransferCourseApplyResponse> response =
                        transferController.postAdmissionCourseTableDataByNewArr(any());
                // Assert
                assertTrue(Objects.requireNonNull(response.getBody()).isFlag());
            }
        }
    }
}