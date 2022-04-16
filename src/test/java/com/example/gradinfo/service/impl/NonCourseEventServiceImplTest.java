package com.example.gradinfo.impl;

import com.example.gradinfo.dto.request.*;
import com.example.gradinfo.dto.response.*;
import com.example.gradinfo.entity.*;
import com.example.gradinfo.repository.EventRepository;
import com.example.gradinfo.repository.ExamCommitteeRepository;
import com.example.gradinfo.repository.NonCourseEventRepository;
import com.example.gradinfo.repository.ThesisCommitteeRepository;
import com.example.gradinfo.service.CommonService;
import com.example.gradinfo.service.impl.NonCourseEventServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NonCourseEventServiceImplTest {

    @Mock private CommonService commonService;
    @Mock private NonCourseEventRepository nonCourseEventRepository;
    @Mock private ExamCommitteeRepository examCommitteeRepository;
    @Mock private ThesisCommitteeRepository thesisCommitteeRepository;
    @Mock private EventRepository eventRepository;

    @InjectMocks private NonCourseEventServiceImpl nonCourseEventService;

    @Nested
    @DisplayName("Tests for getNonCourseRelatedEventTableDataByIDAndPostNumber")
    class GetNonCourseRelatedEventTableDataByIDAndPostNumberTest {

        @Mock private SysStudentPostEntity sysStudentPostEntity;
        @Mock private SysNonCourseRelatedEventRecordEntity sysNonCourseRelatedEventRecordEntity;
        @Mock private List<SysNonCourseRelatedEventRecordEntity> sysNonCourseRelatedEventRecordEntityList;

        @Nested
        @DisplayName("When exist")
        class WhenExistTest {

            @BeforeEach
            void setUp() {
                sysStudentPostEntity = new SysStudentPostEntity();
                sysStudentPostEntity.setStudentPostId("Valid student post id");

                sysNonCourseRelatedEventRecordEntity = new SysNonCourseRelatedEventRecordEntity();
                sysNonCourseRelatedEventRecordEntity.setNcrerDate("Some date");

                sysNonCourseRelatedEventRecordEntityList = new ArrayList<>();
                sysNonCourseRelatedEventRecordEntityList.add(sysNonCourseRelatedEventRecordEntity);
            }

            @Test
            void nonNullList_dataExist() {
                // Arrange
                when(commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(
                        any(),any()))
                        .thenReturn(sysStudentPostEntity);
                when(nonCourseEventRepository.getSysNonCourseRelatedEventRecordEntitiesByStudentPostId(
                        "Valid student post id"))
                        .thenReturn(sysNonCourseRelatedEventRecordEntityList);
                // Act
                List<NonCourseEventTableDataResponse> nonCourseEventTableDataResponseList =
                        nonCourseEventService.getNonCourseRelatedEventTableDataByIDAndPostNumber(
                                "Valid student id", "Valid post number");
                // Assert
                assertEquals("Some date", nonCourseEventTableDataResponseList.get(0).getNcrerDate());
            }
        }
    }

    @Nested
    @DisplayName("Tests for getExamCommitteeTableDataByIDAndPostNumber")
    class GetExamCommitteeTableDataByIDAndPostNumberTest {

        @Mock private SysStudentPostEntity sysStudentPostEntity;
        @Mock private SysExamCommitteeEntity sysExamCommitteeEntity;
        @Mock private List<SysExamCommitteeEntity> sysExamCommitteeEntityList;

        @Nested
        @DisplayName("When exist")
        class WhenExistTest {
            @BeforeEach
            void setUp() {
                sysStudentPostEntity = new SysStudentPostEntity();
                sysStudentPostEntity.setStudentPostId("Valid student post id");

                sysExamCommitteeEntity = new SysExamCommitteeEntity();
                sysExamCommitteeEntity.setExamCommitteeName("Some name");

                sysExamCommitteeEntityList = new ArrayList<>();
                sysExamCommitteeEntityList.add(sysExamCommitteeEntity);
            }

            @Test
            void nonNullList_dataExist() {
                // Arrange
                when(commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(
                        any(),any()))
                        .thenReturn(sysStudentPostEntity);
                when(examCommitteeRepository.getSysExamCommitteeEntitiesByStudentPostId(
                        "Valid student post id"))
                        .thenReturn(sysExamCommitteeEntityList);
                // Act
                List<ExamTableDataResponse> examTableDataResponseList =
                        nonCourseEventService.getExamCommitteeTableDataByIDAndPostNumber(
                                "Valid student id", "Valid post number");
                // Assert
                assertEquals("Some name", examTableDataResponseList.get(0).getExamCommitteeName());
            }
        }
    }

    @Nested
    @DisplayName("Tests for getThesisCommitteeTableDataByIDAndPostNumber")
    class GetThesisCommitteeTableDataByIDAndPostNumberTest {

        @Mock private SysStudentPostEntity sysStudentPostEntity;
        @Mock private SysThesisCommitteeEntity sysThesisCommitteeEntity;
        @Mock private List<SysThesisCommitteeEntity> sysThesisCommitteeEntityList;

        @Nested
        @DisplayName("When exist")
        class WhenExistTest {

            @BeforeEach
            void setUp() {
                sysStudentPostEntity = new SysStudentPostEntity();
                sysStudentPostEntity.setStudentPostId("Valid student post id");

                sysThesisCommitteeEntity = new SysThesisCommitteeEntity();
                sysThesisCommitteeEntity.setThesisCommitteeChar("Some title");

                sysThesisCommitteeEntityList = new ArrayList<>();
                sysThesisCommitteeEntityList.add(sysThesisCommitteeEntity);
            }

            @Test
            void nonNullList_dataExist() {
                // Arrange
                when(commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(
                        any(), any()))
                        .thenReturn(sysStudentPostEntity);
                when(thesisCommitteeRepository.getSysThesisCommitteeEntitiesByStudentPostId(
                        "Valid student post id"))
                        .thenReturn(sysThesisCommitteeEntityList);
                // Act
                List<ThesisCommitteeResponse> thesisCommitteeResponseList =
                        nonCourseEventService.getThesisCommitteeTableDataByIDAndPostNumber(
                                "Valid student id", "Valid post number");
                // Assert
                assertEquals("Some title", thesisCommitteeResponseList.get(0).getThesisCommitteeChar());
            }
        }
    }

    @Nested
    @DisplayName("Tests for postNonCourseRelatedEventTableDataByEventObj")
    class PostNonCourseRelatedEventTableDataByEventObjTest {

        @Mock private NonCourseEventRequest nonCourseEventRequest;
        @Mock private SysStudentPostEntity sysStudentPostEntity;
        @Mock private SysEventEntity sysEventEntity;

        @Nested
        @DisplayName("When success")
        class WhenSuccessTest {

            @BeforeEach
            void setUp() {
                nonCourseEventRequest = new NonCourseEventRequest();
                nonCourseEventRequest.setEventObject(new EventObject());
                nonCourseEventRequest.setStudentInfo(new StudentInfo());

                sysStudentPostEntity = new SysStudentPostEntity();
                sysStudentPostEntity.setStudentPostId("Valid student post id");

                sysEventEntity = new SysEventEntity();
                sysEventEntity.setEventId("Valid event id");
            }

            @Test
            void trueFlag_noException() {
                // Arrange
                when(commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(
                        any(),any()))
                        .thenReturn(sysStudentPostEntity);
                when(eventRepository.getSysEventEntityByEventCodeAndAndEventDescription(
                        any(),any()))
                        .thenReturn(sysEventEntity);
                when(nonCourseEventRepository.save(
                        any()))
                        .thenReturn(new SysNonCourseRelatedEventRecordEntity());
                // Act
                NonCourseEventApplyResponse nonCourseEventApplyResponse =
                        nonCourseEventService.postNonCourseRelatedEventTableDataByEventObj(nonCourseEventRequest);
                // Assert
                assertTrue(nonCourseEventApplyResponse.isFlag());
            }
        }

        @Nested
        @DisplayName("When fail")
        class WhenFailTest {

            @BeforeEach
            void setUp() {
                nonCourseEventRequest = new NonCourseEventRequest();
                nonCourseEventRequest.setEventObject(new EventObject());
                nonCourseEventRequest.setStudentInfo(new StudentInfo());

                sysStudentPostEntity = new SysStudentPostEntity();
                sysStudentPostEntity.setStudentPostId("Invalid student post id");

                sysEventEntity = new SysEventEntity();
                sysEventEntity.setEventId("Invalid event id");
            }

            @Test
            @Disabled("Known Issue")
            void falseFlag_runtimeException() {
                // Arrange
                when(commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(
                        any(),any()))
                        .thenReturn(sysStudentPostEntity);
                when(eventRepository.getSysEventEntityByEventCodeAndAndEventDescription(
                        any(),any()))
                        .thenReturn(sysEventEntity);
                when(nonCourseEventRepository.save(
                        any()))
                        .thenThrow(new RuntimeException());
                // Act
                NonCourseEventApplyResponse nonCourseEventApplyResponse =
                        nonCourseEventService.postNonCourseRelatedEventTableDataByEventObj(nonCourseEventRequest);
                // Assert
                assertFalse(nonCourseEventApplyResponse.isFlag());
                assertEquals("Fail to add the data", nonCourseEventApplyResponse.getReasonList().get(0));
            }
        }
    }

    @Nested
    @DisplayName("Tests for postExamCommitteeTableDataByCommitteeObj")
    class PostExamCommitteeTableDataByCommitteeObjTest {

        @Mock private ExamCommitteeRequest examCommitteeRequest;
        @Mock private SysStudentPostEntity sysStudentPostEntity;

        @Nested
        @DisplayName("When success")
        class WhenSuccessTest {

            @BeforeEach
            void setUp() {
                examCommitteeRequest = new ExamCommitteeRequest();
                examCommitteeRequest.setExamCommitteeObject(new ExamCommitteeObject());
                examCommitteeRequest.setStudentInfo(new StudentInfo());

                sysStudentPostEntity = new SysStudentPostEntity();
                sysStudentPostEntity.setStudentPostId("Valid student post id");
            }

            @Test
            void trueFlag_noException() {
                // Arrange
                when(commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(
                        any(),any()))
                        .thenReturn(sysStudentPostEntity);
                when(examCommitteeRepository.save(
                        any()))
                        .thenReturn(new SysExamCommitteeEntity());
                // Act
                CommonResponse commonResponse =
                        nonCourseEventService.postExamCommitteeTableDataByCommitteeObj(examCommitteeRequest);
                // Assert
                assertTrue(commonResponse.isFlag());
            }
        }

        @Nested
        @DisplayName("When fail")
        class WhenFailTest {

            @BeforeEach
            void setUp() {
                examCommitteeRequest = new ExamCommitteeRequest();
                examCommitteeRequest.setExamCommitteeObject(new ExamCommitteeObject());
                examCommitteeRequest.setStudentInfo(new StudentInfo());

                sysStudentPostEntity = new SysStudentPostEntity();
                sysStudentPostEntity.setStudentPostId("Invalid student post id");
            }

            @Test
            void falseFlag_runtimeException() {
                // Arrange
                when(commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(
                        any(),any()))
                        .thenReturn(sysStudentPostEntity);
                when(examCommitteeRepository.save(
                        any()))
                        .thenThrow(new RuntimeException());
                // Act
                CommonResponse commonResponse =
                        nonCourseEventService.postExamCommitteeTableDataByCommitteeObj(examCommitteeRequest);
                // Assert
                assertFalse(commonResponse.isFlag());
            }
        }
    }

    @Nested
    @DisplayName("Tests for postThesisCommitteeTableDataByCommitteeObj")
    class PostThesisCommitteeTableDataByCommitteeObjTest {

        @Mock private ThesisCommitteeRequest thesisCommitteeRequest;
        @Mock private SysStudentPostEntity sysStudentPostEntity;

        @Nested
        @DisplayName("When success")
        class WhenSuccessTest {

            @BeforeEach
            void setUp() {
                thesisCommitteeRequest = new ThesisCommitteeRequest();
                thesisCommitteeRequest.setThesisCommitteeObject(new ThesisCommitteeObject());
                thesisCommitteeRequest.setStudentInfo(new StudentInfo());

                sysStudentPostEntity = new SysStudentPostEntity();
                sysStudentPostEntity.setStudentPostId("Valid student post id");
            }

            @Test
            void trueFlag_noException() {
                // Arrange
                when(commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(
                        any(),any()))
                        .thenReturn(sysStudentPostEntity);
                when(thesisCommitteeRepository.save(
                        any()))
                        .thenReturn(new SysThesisCommitteeEntity());
                // Act
                CommonResponse commonResponse =
                        nonCourseEventService.postThesisCommitteeTableDataByCommitteeObj(thesisCommitteeRequest);
                // Assert
                assertTrue(commonResponse.isFlag());
            }
        }

        @Nested
        @DisplayName("When fail")
        class WhenFailTest {

            @BeforeEach
            void setUp() {
                thesisCommitteeRequest = new ThesisCommitteeRequest();
                thesisCommitteeRequest.setThesisCommitteeObject(new ThesisCommitteeObject());
                thesisCommitteeRequest.setStudentInfo(new StudentInfo());

                sysStudentPostEntity = new SysStudentPostEntity();
                sysStudentPostEntity.setStudentPostId("Invalid student post id");
            }

            @Test
            void falseFlag_runtimeException() {
                // Arrange
                when(commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(
                        any(),any()))
                        .thenReturn(sysStudentPostEntity);
                when(thesisCommitteeRepository.save(
                        any()))
                        .thenThrow(new RuntimeException());
                // Act
                CommonResponse commonResponse =
                        nonCourseEventService.postThesisCommitteeTableDataByCommitteeObj(thesisCommitteeRequest);
                // Assert
                assertFalse(commonResponse.isFlag());
            }
        }
    }

}