package com.example.gradinfo.impl;

import com.example.gradinfo.dto.request.Course;
import com.example.gradinfo.dto.request.StudentInfo;
import com.example.gradinfo.dto.request.TransferCourseRequest;
import com.example.gradinfo.dto.request.UserInfo;
import com.example.gradinfo.dto.response.BachelorDegreeResponse;
import com.example.gradinfo.dto.response.TransferCourseApplyResponse;
import com.example.gradinfo.dto.response.TransferCourseResponse;
import com.example.gradinfo.dto.response.TransferInstitution;
import com.example.gradinfo.entity.*;
import com.example.gradinfo.repository.*;
import com.example.gradinfo.service.CommonService;
import com.example.gradinfo.service.impl.TransferServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransferServiceImplTest {

    @Mock private CommonService commonService;
    @Mock private TransferCourseRepository transferCourseRepository;
    @Mock private TransferHistoryRepository transferHistoryRepository;
    @Mock private InstitutionRepository institutionRepository;
    @Mock private BachelorDegreeRepository bachelorDegreeRepository;
    @Mock private AdmissionCourseRepository admissionCourseRepository;

    @InjectMocks private TransferServiceImpl transferService;

    @Nested
    @DisplayName("Tests for getTransferCourseTableDataByIDAndPostNumber")
    class GetTransferCourseTableDataByIDAndPostNumberTest {

        @Mock private SysStudentPostEntity sysStudentPostEntity;
        @Mock private SysTransferCourseEntity sysTransferCourseEntity;
        @Mock private List<SysTransferCourseEntity> sysTransferCourseEntityList;
        @Mock private SysTransferHistoryEntity sysTransferHistoryEntity;
        @Mock private List<SysTransferHistoryEntity> sysTransferHistoryEntityList;

        @Nested
        @DisplayName("When exist")
        class WhenExistTest {

            @BeforeEach
            void setUp() {
                sysStudentPostEntity = new SysStudentPostEntity();
                sysStudentPostEntity.setStudentPostId("0000000003");

                sysTransferCourseEntity = new SysTransferCourseEntity();
                sysTransferCourseEntity.setTrCourseId("1000000002");

                sysTransferCourseEntityList = new ArrayList<>();
                sysTransferCourseEntityList.add(sysTransferCourseEntity);

                sysTransferHistoryEntity = new SysTransferHistoryEntity();
                sysTransferHistoryEntity.setTrCourseId("1000000002");

                sysTransferHistoryEntityList = new ArrayList<>();
                sysTransferHistoryEntityList.add(sysTransferHistoryEntity);
            }

            @Test
            void existData() {
                // Arrange
                when(commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(
                        "0000002", "674"))
                        .thenReturn(sysStudentPostEntity);
                when(transferCourseRepository.getSysTransferCourseEntitiesByStudentPostId(
                        "0000000003"))
                        .thenReturn(sysTransferCourseEntityList);
                when(transferHistoryRepository.getSysTrHistoryEntitiesByTrCourseId(
                        "1000000002"))
                        .thenReturn(sysTransferHistoryEntityList);
                // Act
                List<TransferCourseResponse> transferCourseResponses =
                        transferService.getTransferCourseTableDataByIDAndPostNumber("0000002", "674");
                // Assert
                assertEquals("1000000002", transferCourseResponses.get(0).getTrCourseId());
            }
        }
    }

    @Nested
    @DisplayName("Tests for getTransferInfoByIDAndPostNumber")
    class GetTransferInfoByIDAndPostNumberTest {
        @Mock private SysStudentPostEntity sysStudentPostEntity;
        @Mock private SysTransferCourseEntity sysTransferCourseEntity;
        @Mock private List<SysTransferCourseEntity> sysTransferCourseEntityList;
        @Mock private SysInstitutionEntity sysInstitutionEntity;

        @Nested
        @DisplayName("When exist")
        class WhenExistTest {

            @BeforeEach
            void setUp() {
                sysStudentPostEntity = new SysStudentPostEntity();
                sysStudentPostEntity.setStudentPostId("0000000003");

                sysTransferCourseEntity = new SysTransferCourseEntity();
                sysTransferCourseEntity.setInstitutionId("000000001");

                sysTransferCourseEntityList = new ArrayList<>();
                sysTransferCourseEntityList.add(sysTransferCourseEntity);

                sysInstitutionEntity = new SysInstitutionEntity();
                sysInstitutionEntity.setInstitutionName("Carbegie-Melon Univ");
            }

            @Test
            void existData() {
                // Arrange
                when(commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(
                        "0000002", "674"))
                        .thenReturn(sysStudentPostEntity);
                when(transferCourseRepository.getSysTransferCourseEntitiesByStudentPostId(
                        "0000000003"))
                        .thenReturn(sysTransferCourseEntityList);
                when(institutionRepository.getSysInstitutionEntitiesByInstitutionId(
                        "000000001"))
                        .thenReturn(sysInstitutionEntity);
                // Act
                List<TransferInstitution> transferInstitutionList =
                        transferService.getTransferInfoByIDAndPostNumber("0000002", "674");
                // Assert
                assertEquals("Carbegie-Melon Univ", transferInstitutionList.get(0).getInstitutionName());
            }
        }

        @Test
        void notExistData() {
        }
    }

    @Nested
    @DisplayName("Tests for getBachelorDegreeInfoByID")
    class GetBachelorDegreeInfoByIDTest {

        @Mock private SysStudentBachelorEntity sysStudentBachelorEntity;
        @Mock private List<SysStudentBachelorEntity> sysStudentBachelorEntityList;

        @Nested
        @DisplayName("When exist")
        class WhenExistTest {

            @BeforeEach
            void setUp() {
                sysStudentBachelorEntity = new SysStudentBachelorEntity();
                sysStudentBachelorEntity.setSbName("Some Bachelor");

                sysStudentBachelorEntityList = new ArrayList<>();
                sysStudentBachelorEntityList.add(sysStudentBachelorEntity);
            }
            @Test
            void existData() {
                // Arrange
                when(bachelorDegreeRepository.getSysStudentBachelorEntitiesByStudentId(
                        "0000001"))
                        .thenReturn(sysStudentBachelorEntityList);
                // Act
                BachelorDegreeResponse bachelorDegreeResponse =
                        transferService.getBachelorDegreeInfoByID("0000001");
                // Assert
                assertEquals("Some Bachelor", bachelorDegreeResponse.getBachelorDegreeList().get(0).getSbName());
            }
        }
    }

    @Nested
    @DisplayName("Tests for postTransferCourseTableDataByNewArr")
    class PostTransferCourseTableDataByNewArrTest {

        @Mock private UserInfo userInfo;
        @Mock private StudentInfo studentInfo;
        @Mock private Course course;
        @Mock private TransferCourseRequest transferCourseRequest;
        @Mock private List<Course> courseList;
        @Mock private SysStudentPostEntity sysStudentPostEntity;
        @Mock private SysTransferCourseEntity sysTransferCourseEntity;
        @Mock private List<SysTransferCourseEntity> sysTransferCourseEntityList;

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
                course.setCourseId("Valid course id");

                courseList = new ArrayList<>();
                courseList.add(course);

                transferCourseRequest = new TransferCourseRequest();
                transferCourseRequest.setUserInfo(userInfo);
                transferCourseRequest.setStudentInfo(studentInfo);
                transferCourseRequest.setCourseList(courseList);

                sysStudentPostEntity = new SysStudentPostEntity();
                sysStudentPostEntity.setStudentPostId("Valid student post id");

                sysTransferCourseEntity = new SysTransferCourseEntity();
                sysTransferCourseEntity.setTrCourseId("Valid course id");
                sysTransferCourseEntity.setTrCourseApplyStatus((byte) 1);
                sysTransferCourseEntity.setTrCourseName("Valid name");

                sysTransferCourseEntityList = new ArrayList<>();
                sysTransferCourseEntityList.add(sysTransferCourseEntity);
            }

            @Test
            void success() {
                // Arrange
                when(commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(
                        any(),any()))
                        .thenReturn(sysStudentPostEntity);

                when(transferCourseRepository.getSysTransferCourseEntitiesByStudentPostId(
                        "Valid student post id"))
                        .thenReturn(sysTransferCourseEntityList);

                when(admissionCourseRepository.getSysAdmissionCourseEntitiesByStudentPostId(
                        any()))
                        .thenReturn(new ArrayList<>());

                when(admissionCourseRepository.getSysAdmissionCourseEntitiesByStudentPostIdAndAdCourseIdIsIn(
                        any(),any()))
                        .thenReturn(new ArrayList<>());

                when(transferCourseRepository.getSysTransferCourseEntitiesByStudentPostIdAndTrCourseIdIsIn(
                        any(),any()))
                        .thenReturn(new ArrayList<>());

                when(commonService.checkReason(
                        any(),any()))
                        .thenReturn(new ArrayList<>());

                when(transferHistoryRepository.saveAllAndFlush(
                        any()))
                        .thenReturn(new ArrayList<>());

                when(transferCourseRepository.saveAllAndFlush(
                        any()))
                        .thenReturn(new ArrayList<>());

                when(commonService.calculateGpaAndUnit(
                        any(),any(),any(),any()))
                        .thenReturn(new ArrayList<>());
                // Act
                TransferCourseApplyResponse transferCourseApplyResponse =
                        transferService.postTransferCourseTableDataByNewArr(transferCourseRequest);
                // Assert
                assertTrue(transferCourseApplyResponse.isFlag());
            }
        }
    }

}