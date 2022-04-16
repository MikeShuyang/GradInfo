package com.example.gradinfo.controller;

import com.example.gradinfo.dto.response.*;
import com.example.gradinfo.service.NonCourseEventService;
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
class NonCourseEventControllerTest {

    @Mock private NonCourseEventService nonCourseEventService;
    @Mock private NonCourseEventTableDataResponse nonCourseEventTableDataResponse;
    @Mock private ExamTableDataResponse examTableDataResponse;
    @Mock private ThesisCommitteeResponse thesisCommitteeResponse;
    @Mock private NonCourseEventApplyResponse nonCourseEventApplyResponse;
    @Mock private CommonResponse commonResponse;
    @InjectMocks private NonCourseEventController nonCourseEventController;

    @Nested
    @DisplayName("Tests for getNonCourseRelatedEventTableDataByIDAndPostNumber")
    class GetNonCourseRelatedEventTableDataByIDAndPostNumberTest {
        private List<NonCourseEventTableDataResponse> nonCourseEventTableDataResponsesList;
        @Nested
        @DisplayName("When success")
        class WhenSuccessTest {
            @BeforeEach
            void setUp() {
                nonCourseEventTableDataResponsesList = new ArrayList<>();
                nonCourseEventTableDataResponse = new NonCourseEventTableDataResponse();
                nonCourseEventTableDataResponse.setNcrerOper("Tester");
                nonCourseEventTableDataResponsesList.add(nonCourseEventTableDataResponse);
            }
            @Test
            void validIdAndPostNumber_dataExists() {
                // Arrange
                when(nonCourseEventService.getNonCourseRelatedEventTableDataByIDAndPostNumber(
                        "valid student id", "valid post number"))
                        .thenReturn(nonCourseEventTableDataResponsesList);
                // Act
                ResponseEntity<List<NonCourseEventTableDataResponse>> response =
                        nonCourseEventController.getNonCourseRelatedEventTableDataByIDAndPostNumber(
                                "valid student id", "valid post number");
                // Assert
                assertEquals("Tester",
                        Objects.requireNonNull(response.getBody()).get(0).getNcrerOper());
            }
        }
    }

    @Nested
    @DisplayName("Tests for getExamCommitteeTableDataByIDAndPostNumber")
    class GetExamCommitteeTableDataByIDAndPostNumberTest {
        private List<ExamTableDataResponse> examTableDataResponseList;
        @Nested
        @DisplayName("When success")
        class WhenSuccessTest {
            @BeforeEach
            void setUp() {
                examTableDataResponseList = new ArrayList<>();
                examTableDataResponse = new ExamTableDataResponse();
                examTableDataResponse.setExamCommitteeName("Atlas");
                examTableDataResponseList.add(examTableDataResponse);
            }
            @Test
            void validIdAndPostNumber_dataExists() {
                // Arrange
                when(nonCourseEventService.getExamCommitteeTableDataByIDAndPostNumber(
                        "valid student id", "valid post number"))
                        .thenReturn(examTableDataResponseList);
                // Act
                ResponseEntity<List<ExamTableDataResponse>> response =
                        nonCourseEventController.getExamCommitteeTableDataByIDAndPostNumber(
                                "valid student id", "valid post number");
                // Assert
                assertEquals("Atlas",
                        Objects.requireNonNull(response.getBody()).get(0).getExamCommitteeName());
            }
        }
    }

    @Nested
    @DisplayName("Tests for getThesisCommitteeTableDataByIDAndPostNumber")
    class GetThesisCommitteeTableDataByIDAndPostNumberTest {}

    @Nested
    @DisplayName("Tests for postExamCommitteeTableDataByCommitteeObj")
    class PostExamCommitteeTableDataByCommitteeObjTest {}

    @Nested
    @DisplayName("Tests for getStudentPostDataByStudentIDAndPostNumber")
    class GetStudentPostDataByStudentIDAndPostNumberTest {}

    @Nested
    @DisplayName("Tests for postThesisCommitteeTableDataByCommitteeObj")
    class PostThesisCommitteeTableDataByCommitteeObjTest {}
}