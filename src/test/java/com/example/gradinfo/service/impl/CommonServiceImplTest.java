package com.example.gradinfo.service.impl;

import com.example.gradinfo.dto.response.StudentInfoResponse;
import com.example.gradinfo.entity.*;
import com.example.gradinfo.repository.AdmissionCourseRepository;
import com.example.gradinfo.repository.StudentPostRepository;
import com.example.gradinfo.repository.StudentRepository;
import com.example.gradinfo.repository.TransferCourseRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommonServiceImplTest {

    @Mock private StudentRepository studentRepository;
    @Mock private StudentPostRepository studentPostRepository;
    @Mock private AdmissionCourseRepository admissionCourseRepository;
    @Mock private TransferCourseRepository transferCourseRepository;

    @InjectMocks private CommonServiceImpl commonService;

    @Nested
    @DisplayName("Tests for getStudentInfoByStudentId")
    class GetStudentInfoByStudentIdTest {

        @Mock private SysStudentEntity sysStudentEntity;
        @Mock private SysStudentPostEntity sysStudentPostEntity;
        @Mock private List<SysStudentPostEntity> sysStudentPostEntities;

        @Nested
        @DisplayName("When Student Exists")
        class WhenExistsTest {

            @BeforeEach
            void setUp() {
                sysStudentEntity = new SysStudentEntity();
                sysStudentEntity.setStudentId("0000001");

                sysStudentPostEntity = new SysStudentPostEntity();
                sysStudentPostEntity.setStudentPostId("0000000001");

                sysStudentPostEntities = new ArrayList<>();
                sysStudentPostEntities.add(sysStudentPostEntity);
            }

            @Test
            @DisplayName("Student should exist")
            void getStudentInfoByCorrectStudentId() {
                // Arrange
                when(studentRepository.findSysStudentEntityBystudentId(
                        "0000001"))
                        .thenReturn(sysStudentEntity);
                when(studentPostRepository.getSysStudentPostEntitiesByStudentId(
                        "0000001"))
                        .thenReturn(sysStudentPostEntities);
                // Act
                StudentInfoResponse studentInfoResponse = commonService.getStudentInfoByStudentId("0000001");
                // Assert
                assertNotNull(studentInfoResponse.getStudentId());
                assertNotNull(studentInfoResponse.getSpPostNumbers());
            }
        }

        @Nested
        @DisplayName("When Student Doesn't Exist")
        class WhenNotExistTest {
            @BeforeEach
            void setUp() {
                sysStudentEntity = new SysStudentEntity();
            }

            @Test
            @DisplayName("Student should not exist")
            void getStudentInfoByWrongStudentId() {
                // Arrange
                when(studentRepository.findSysStudentEntityBystudentId(
                        "abc"))
                        .thenReturn(sysStudentEntity);
                // Act
                StudentInfoResponse studentInfoResponse = commonService.getStudentInfoByStudentId("abc");
                // Assert
                assertNull(studentInfoResponse.getStudentId());
            }
        }
    }

    @Nested
    @DisplayName("Tests for getStudentPostEntitiesByStudentIdAndSpPostNumber")
    class GetStudentPostEntitiesByStudentIdAndSpPostNumberTest {

        @Mock private SysStudentPostEntity sysStudentPostEntity;
        @Mock private List<SysStudentPostEntity> sysStudentPostEntities;

        @Nested
        @DisplayName("When student post entity exists")
        class WhenExistsTest {

            @BeforeEach
            void setUp() {
                sysStudentPostEntity = new SysStudentPostEntity();
                sysStudentPostEntity.setSpPostNumber("654");

                sysStudentPostEntities = new ArrayList<>();
                sysStudentPostEntities.add(sysStudentPostEntity);
            }

            @Test
            @DisplayName("Should return non-null SysStudentPostEntity")
            void getStudentPostEntitiesByStudentIdAndSpPostNumber() {
                // Arrange
                when(studentPostRepository.getSysStudentPostEntitiesByStudentId(
                        "0000001"))
                        .thenReturn(sysStudentPostEntities);
                // Act
                SysStudentPostEntity sysStudentPostEntity =
                        commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(
                                "0000001", "654");
                // Assert
                assertEquals("654", sysStudentPostEntity.getSpPostNumber());
            }
        }
    }

    @Nested
    @DisplayName("Tests for calculateGpaAndUnit")
    class CalculateGpaAndUnitTest {

        private String studentPostId;
        @Mock private SysStudentPostEntity sysStudentPostEntity;
        @Mock private SysAdmissionCourseEntity sysAdmissionCourseEntity;
        @Mock private SysTransferCourseEntity sysTransferCourseEntity;
        @Mock private List<SysAdmissionCourseEntity> sysAdmissionCourseEntityList;
        @Mock private List<SysTransferCourseEntity> sysTransferCourseEntityList;

        @Nested
        @DisplayName("When Apply Admission Course")
        class WhenAdmissionTest {

            @BeforeEach
            void setUp() {
                sysStudentPostEntity = new SysStudentPostEntity();
                sysStudentPostEntity.setSpCreditLimits(30);
                sysStudentPostEntity.setSpEarnunits(11.0);
                sysStudentPostEntity.setSpRgunits(0.0);
                sysStudentPostEntity.setSpGpaApply(3.26);
                sysStudentPostEntity.setSpGpaAll(3.47);

                sysAdmissionCourseEntity = new SysAdmissionCourseEntity();
                sysAdmissionCourseEntity.setAdCourseApplyStatus((byte) 1);
                sysAdmissionCourseEntity.setAdCourseApplyCode("Y");
                sysAdmissionCourseEntity.setAdCourseGrade("B");
                sysAdmissionCourseEntity.setAdCourseUnits(3.0);
                sysAdmissionCourseEntity.setAdCourseGpts(9.0);

                sysTransferCourseEntity = new SysTransferCourseEntity();

                studentPostId = "0000000001";
                sysAdmissionCourseEntityList = new ArrayList<>();
                sysTransferCourseEntityList = new ArrayList<>();
                sysAdmissionCourseEntityList.add(sysAdmissionCourseEntity);
            }

            @Test
            void calculateGpaAndUnit() {
                // Arrange
                when(admissionCourseRepository
                        .getSysAdmissionCourseEntitiesByStudentPostIdAndAdCourseApplyStatusIs(studentPostId, (byte) 1))
                        .thenReturn(sysAdmissionCourseEntityList);
                when(transferCourseRepository
                        .getSysTransferCourseEntitiesByStudentPostIdAndTrCourseApplyStatusIs(studentPostId, (byte) 1))
                        .thenReturn(sysTransferCourseEntityList);
                when(studentPostRepository
                        .save(any(SysStudentPostEntity.class)))
                        .thenReturn(new SysStudentPostEntity());
                // Act
                List<String> result = commonService.calculateGpaAndUnit(
                        studentPostId, sysStudentPostEntity, sysAdmissionCourseEntityList, sysTransferCourseEntityList);
                // Assert
                assertTrue(result.isEmpty());
            }
        }

        @Nested
        @DisplayName("When Apply Transfer Course")
        @Disabled("Not Implemented Yet")
        class WhenTransferTest {

            @BeforeEach
            void setUp() {
                sysStudentPostEntity = new SysStudentPostEntity();
                sysStudentPostEntity.setSpCreditLimits(28);
                sysStudentPostEntity.setSpEarnunits(35.0);
                sysStudentPostEntity.setSpRgunits(0.0);
                sysStudentPostEntity.setSpGpaApply(3.57);
                sysStudentPostEntity.setSpGpaAll(3.57);

                sysAdmissionCourseEntity = new SysAdmissionCourseEntity();

                sysTransferCourseEntity = new SysTransferCourseEntity();

                studentPostId = "0000000003";
                sysAdmissionCourseEntityList = new ArrayList<>();
                sysTransferCourseEntityList = new ArrayList<>();
                sysTransferCourseEntityList.add(sysTransferCourseEntity);
            }

            @Test
            void calculateGpaAndUnit() {
                // Arrange
                when(admissionCourseRepository
                        .getSysAdmissionCourseEntitiesByStudentPostIdAndAdCourseApplyStatusIs(studentPostId, (byte) 1))
                        .thenReturn(sysAdmissionCourseEntityList);
                when(transferCourseRepository
                        .getSysTransferCourseEntitiesByStudentPostIdAndTrCourseApplyStatusIs(studentPostId, (byte) 1))
                        .thenReturn(sysTransferCourseEntityList);
                when(studentPostRepository
                        .save(any(SysStudentPostEntity.class)))
                        .thenReturn(new SysStudentPostEntity());
                // Act
                List<String> result = commonService.calculateGpaAndUnit(
                        studentPostId, sysStudentPostEntity, sysAdmissionCourseEntityList, sysTransferCourseEntityList);
                // Assert

            }

        }

    }

    @Nested
    @DisplayName("Tests for checkReason")
    class CheckReasonTest {

        private final List<SysAdmissionCourseEntity> sysAdmissionCourseEntityList = new ArrayList<>();
        private final SysAdmissionCourseEntity sysAdmissionCourseEntity = new SysAdmissionCourseEntity();

        @BeforeEach
        void setUp() {
            sysAdmissionCourseEntityList.clear();
            sysAdmissionCourseEntity.setAdCourseApplyCode("V");
        }

        @Test
        void passLimit() {
            // Arrange
            sysAdmissionCourseEntity.setAdCourseUnits(13.0);
            sysAdmissionCourseEntityList.add(sysAdmissionCourseEntity);
            // Act
            List<String> reason = commonService.checkReason(sysAdmissionCourseEntityList, null);
            // Assert
            assertEquals("Visitor course pass visit limit", reason.get(0));
        }

        @Test
        void notPassLimit() {
            // Arrange
            sysAdmissionCourseEntity.setAdCourseUnits(11.0);
            sysAdmissionCourseEntityList.add(sysAdmissionCourseEntity);
            // Act
            List<String> reason = commonService.checkReason(sysAdmissionCourseEntityList, null);
            // Assert
            assertTrue(reason.isEmpty());
        }
    }

    @Nested
    @DisplayName("Tests for checkAdmissionCourseAndReturnReason")
    class CheckAdmissionCourseAndReturnReasonTest {

        private final List<SysTransferCourseEntity> sysTransferCourseEntityList = new ArrayList<>();
        private final List<SysAdmissionCourseEntity> sysAdmissionCourseEntityList = new ArrayList<>();

        @BeforeEach
        void setUp() {
            sysTransferCourseEntityList.clear();
            sysAdmissionCourseEntityList.clear();
            SysTransferCourseEntity sysTransferCourseEntity = new SysTransferCourseEntity();
            sysTransferCourseEntity.setTrCourseApplyStatus((byte) 1);
            sysTransferCourseEntity.setTrCourseApplyCode("X");
            sysTransferCourseEntity.setTrCourseName("Dummy Transfer Course");
            sysTransferCourseEntityList.add(sysTransferCourseEntity);
        }

        @Test
        void hasXCodeAdmissionCourse() {
            // Arrange
            SysAdmissionCourseEntity sysAdmissionCourseEntity = new SysAdmissionCourseEntity();
            sysAdmissionCourseEntity.setAdCourseApplyStatus((byte) 1);
            sysAdmissionCourseEntity.setAdCourseApplyCode("X");
            sysAdmissionCourseEntity.setAdCourseName("Dummy Admission Course");
            sysAdmissionCourseEntityList.add(sysAdmissionCourseEntity);
            // Act
            List<String> reason = commonService.CheckAdmissionCourseAndReturnReason(
                    sysAdmissionCourseEntityList, sysTransferCourseEntityList);
            // Assert
            assertEquals("Restricted course Dummy Admission Course cannot be applied", reason.get(0));
            assertEquals("Restricted course Dummy Transfer Course cannot be applied", reason.get(1));
        }

        @Test
        void hasVisitorAdmissionCourseAndOverflowScore() {
            // Arrange
            SysAdmissionCourseEntity sysAdmissionCourseEntity = new SysAdmissionCourseEntity();
            sysAdmissionCourseEntity.setAdCourseApplyStatus((byte) 1);
            sysAdmissionCourseEntity.setAdCourseApplyCode("V");
            sysAdmissionCourseEntity.setAdCourseUnits(13.0);
            sysAdmissionCourseEntityList.add(sysAdmissionCourseEntity);
            // Act
            List<String> reason = commonService.CheckAdmissionCourseAndReturnReason(
                    sysAdmissionCourseEntityList, sysTransferCourseEntityList);
            // Assert
            assertEquals("Visitor course pass visit limit", reason.get(0));
            assertEquals("Restricted course Dummy Transfer Course cannot be applied", reason.get(1));
        }

        @Test
        void hasVisitorAdmissionCourseAndNotOverflowScore() {
            // Arrange
            SysAdmissionCourseEntity sysAdmissionCourseEntity = new SysAdmissionCourseEntity();
            sysAdmissionCourseEntity.setAdCourseApplyStatus((byte) 1);
            sysAdmissionCourseEntity.setAdCourseApplyCode("V");
            sysAdmissionCourseEntity.setAdCourseUnits(11.0);
            sysAdmissionCourseEntityList.add(sysAdmissionCourseEntity);
            // Act
            List<String> reason = commonService.CheckAdmissionCourseAndReturnReason(
                    sysAdmissionCourseEntityList, sysTransferCourseEntityList);
            // Assert
            assertEquals("Restricted course Dummy Transfer Course cannot be applied", reason.get(0));
        }
    }

    @Test
    @DisplayName("Tests for checkTransferCourseAndReturnReason")
    void checkTransferCourseAndReturnReason() {
        // Arrange
        SysTransferCourseEntity sysTransferCourseEntity = new SysTransferCourseEntity();
        sysTransferCourseEntity.setTrCourseApplyStatus((byte) 1);
        sysTransferCourseEntity.setTrCourseApplyCode("X");
        sysTransferCourseEntity.setTrCourseName("Dummy Transfer Course");
        List<SysTransferCourseEntity> sysTransferCourseEntityList = new ArrayList<>();
        sysTransferCourseEntityList.add(sysTransferCourseEntity);
        // Act
        List<String> reason = commonService.CheckTransferCourseAndReturnReason(
                null,
                sysTransferCourseEntityList);
        // Assert
        assertEquals("Restricted course Dummy Transfer Course cannot be applied", reason.get(0));
    }

    @Test
    @DisplayName("Tests for gpaRules")
    void gpaRules() {
        assertEquals(4.0, commonService.gpaRules("A"));
        assertEquals(3.7, commonService.gpaRules("A-"));
        assertEquals(3.3, commonService.gpaRules("B+"));
        assertEquals(3.0, commonService.gpaRules("B"));
        assertEquals(2.7, commonService.gpaRules("B-"));
        assertEquals(2.3, commonService.gpaRules("C+"));
        assertEquals(2.0, commonService.gpaRules("C"));
        assertEquals(1.7, commonService.gpaRules("C-"));
        assertEquals(1.3, commonService.gpaRules("D+"));
        assertEquals(1.0, commonService.gpaRules("D"));
        assertEquals(0.7, commonService.gpaRules("D-"));
        assertEquals(0, commonService.gpaRules("F"));
        assertEquals(0, commonService.gpaRules("UW"));
        assertEquals(0, commonService.gpaRules("IX"));
        assertEquals(0, commonService.gpaRules(""));
        assertEquals(0, commonService.gpaRules("<"));
    }
}