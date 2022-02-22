package com.example.gradinfo.service.impl;

import com.example.gradinfo.dto.request.AdmissionCourseRequest;
import com.example.gradinfo.dto.request.Course;
import com.example.gradinfo.dto.request.StudentInfo;
import com.example.gradinfo.dto.request.UserInfo;
import com.example.gradinfo.dto.response.AdmissionCourseApplyResponse;
import com.example.gradinfo.dto.response.AdmissionCourseResponse;
import com.example.gradinfo.dto.response.StudentPostResponse;
import com.example.gradinfo.entity.*;
import com.example.gradinfo.repository.*;
import com.example.gradinfo.service.CommonService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdmissionServiceImplTest {

    @Mock private AdmissionCourseRepository admissionCourseRepository;
    @Mock private AdmissionHistoryRepository admissionHistoryRepository;
    @Mock private TransferCourseRepository transferCourseRepository;
    @Mock private CommonService commonService;

    @InjectMocks AdmissionServiceImpl admissionService;

    @Nested
    @DisplayName("Tests for getStudentPostDataByStudentIDAndPostNumber")
    class GetStudentPostDataByStudentIDAndPostNumberTest {

        @Mock private SysStudentPostEntity sysStudentPostEntity;

        @Nested
        @DisplayName("When student post exists")
        class WhenExistsTest {

            @BeforeEach
            void setUp() {
                sysStudentPostEntity = new SysStudentPostEntity();
                sysStudentPostEntity.setSpAdmit("20133");
            }

            @Test
            void correctStudentIDAndPostNumber() {
                // Arrange
                when(commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(
                        "0000001", "654"))
                        .thenReturn(sysStudentPostEntity);
                // Act
                StudentPostResponse studentPostResponse =
                        admissionService.getStudentPostDataByStudentIDAndPostNumber("0000001","654");
                // Assert
                assertEquals("20133", studentPostResponse.getSpAdmit());
            }

        }

        @Nested
        @DisplayName("When student post doesn't exist")
        class WhenNotExistTest {

            @BeforeEach
            void setUp() {
                sysStudentPostEntity = new SysStudentPostEntity();
            }

            @Test
            void wrongStudentIDAndPostNumber() {
                // Arrange
                when(commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(
                        "aaa", "aaa"))
                        .thenReturn(sysStudentPostEntity);
                // Act
                StudentPostResponse studentPostResponse =
                        admissionService.getStudentPostDataByStudentIDAndPostNumber("aaa","aaa");
                // Assert
                assertNull(studentPostResponse.getSpObj());
            }

        }
    }

    @Nested
    @DisplayName("Tests for getAdmissionCourseDataByStudentIDAndPostNumber")
    class GetAdmissionCourseDataByStudentIDAndPostNumberTest {

        @Mock private SysStudentPostEntity sysStudentPostEntity;
        @Mock private SysAdmissionCourseEntity sysAdmissionCourseEntity;
        @Mock private List<SysAdmissionCourseEntity> sysAdmissionCourseEntityList;

        @Nested
        @DisplayName("When at least one AdmissionCourseData exists")
        class WhenExistsTest {

            @BeforeEach
            void setUp() {
                sysStudentPostEntity = new SysStudentPostEntity();
                sysStudentPostEntity.setStudentPostId("0000000001");

                sysAdmissionCourseEntity = new SysAdmissionCourseEntity();
                sysAdmissionCourseEntity.setAdCourseId("0000000001");

                sysAdmissionCourseEntityList = new ArrayList<>();
                sysAdmissionCourseEntityList.add(sysAdmissionCourseEntity);
            }

            @Test
            void withoutHistory() {
                // Arrange
                when(commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(
                        "0000001", "654"))
                        .thenReturn(sysStudentPostEntity);
                when(admissionCourseRepository.getSysAdmissionCourseEntitiesByStudentPostId(
                        "0000000001"))
                        .thenReturn(sysAdmissionCourseEntityList);
                when(admissionHistoryRepository.getSysAdHistoryEntitiesByAdCourseId(
                        "0000000001"))
                        .thenReturn(new ArrayList<>());
                // Act
                List<AdmissionCourseResponse> admissionCourseResponseList =
                        admissionService.getAdmissionCourseDataByStudentIDAndPostNumber(
                                "0000001", "654");
                // Assert
                assertEquals("0000000001", admissionCourseResponseList.get(0).getAdCourseId());
            }
            // TODO Add test case for courses that have a history
        }
    }

    @Nested
    @DisplayName("Tests for getPostAdmissionCourseTableDataByNewArr")
    class GetPostAdmissionCourseTableDataByNewArrTest {

        @Mock private UserInfo userInfo;
        @Mock private StudentInfo studentInfo;
        @Mock private Course course;
        @Mock private AdmissionCourseRequest admissionCourseRequest;
        @Mock private List<Course> courseList;
        @Mock private SysStudentPostEntity sysStudentPostEntity;
        @Mock private SysAdmissionCourseEntity sysAdmissionCourseEntity;
        @Mock private List<SysAdmissionCourseEntity> sysAdmissionCourseEntityList;

        @Nested
        class WhenSuccessTest {

            @BeforeEach
            void setUp() {
                userInfo = new UserInfo();
                userInfo.setUserOper("Tester Mike");
                userInfo.setTransDate(LocalDate.now().toString());

                studentInfo = new StudentInfo();
                studentInfo.setStudentId("0000001");
                studentInfo.setSpPostNumber("654");

                course = new Course();
                course.setCourseId("0000000003");

                courseList = new ArrayList<>();
                courseList.add(course);

                admissionCourseRequest = new AdmissionCourseRequest();
                admissionCourseRequest.setUserInfo(userInfo);
                admissionCourseRequest.setStudentInfo(studentInfo);
                admissionCourseRequest.setCourseList(courseList);

                sysStudentPostEntity = new SysStudentPostEntity();
                sysStudentPostEntity.setStudentPostId("0000000001");

                sysAdmissionCourseEntity = new SysAdmissionCourseEntity();
                sysAdmissionCourseEntity.setAdCourseId("0000000003");
                sysAdmissionCourseEntity.setAdCourseApplyStatus((byte) 1);
                sysAdmissionCourseEntity.setAdCourseName("Engineering and Economic Evaluation of Subsurface Reservoirs");

                sysAdmissionCourseEntityList = new ArrayList<>();
                sysAdmissionCourseEntityList.add(sysAdmissionCourseEntity);
            }

            @Test
            void success() {
                // Arrange
                when(commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(
                        any(),any()))
                        .thenReturn(sysStudentPostEntity);

                when(admissionCourseRepository.getSysAdmissionCourseEntitiesByStudentPostId(
                        "0000000001"))
                        .thenReturn(sysAdmissionCourseEntityList);

                when(admissionCourseRepository.getSysAdmissionCourseEntitiesByStudentPostIdAndAdCourseIdIsIn(
                        any(),any()))
                        .thenReturn(new ArrayList<>());

                when(transferCourseRepository.getSysTransferCourseEntitiesByStudentPostIdAndTrCourseIdIsIn(
                        any(),any()))
                        .thenReturn(new ArrayList<>());

                when(commonService.checkReason(
                        any(),any()))
                        .thenReturn(new ArrayList<>());

                when(admissionCourseRepository.save(
                        any(SysAdmissionCourseEntity.class)))
                        .thenReturn(new SysAdmissionCourseEntity());

                when(transferCourseRepository.getSysTransferCourseEntitiesByStudentPostId(
                        any()))
                        .thenReturn(new ArrayList<>());

                when(commonService.calculateGpaAndUnit(
                        any(),any(),any(),any()))
                        .thenReturn(new ArrayList<>());
                // Act
                AdmissionCourseApplyResponse admissionCourseApplyResponse =
                        admissionService.getPostAdmissionCourseTableDataByNewArr(admissionCourseRequest);
                // Assert
                assertTrue(admissionCourseApplyResponse.isFlag());
            }
        }
    }
}