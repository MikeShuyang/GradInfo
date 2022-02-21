package com.example.gradinfo.service.impl;

import com.example.gradinfo.dto.response.StudentInfoResponse;
import com.example.gradinfo.entity.SysAdmissionCourseEntity;
import com.example.gradinfo.entity.SysStudentPostEntity;
import com.example.gradinfo.entity.SysTransferCourseEntity;
import com.example.gradinfo.repository.AdmissionCourseRepository;
import com.example.gradinfo.repository.StudentPostRepository;
import com.example.gradinfo.repository.StudentRepository;
import com.example.gradinfo.repository.TransferCourseRepository;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class CommonServiceImplTest {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentPostRepository studentPostRepository;
    @Autowired
    private AdmissionCourseRepository admissionCourseRepository;
    @Autowired
    private TransferCourseRepository transferCourseRepository;

    @Autowired
    private CommonServiceImpl commonService;

    @Before
    public void setUp() {
        commonService = new CommonServiceImpl(
                studentRepository,
                studentPostRepository,
                admissionCourseRepository,
                transferCourseRepository);
    }

    @Nested
    @DisplayName("Tests for getStudentInfoByStudentId")
    class GetStudentInfoByStudentIdTest {
        @Test
        @DisplayName("Student should exist")
        void getStudentInfoByCorrectStudentId() {
            StudentInfoResponse studentInfoResponse = commonService.getStudentInfoByStudentId("0000001");
            assertNotNull(studentInfoResponse.getStudentId());
            assertNotNull(studentInfoResponse.getStudentName());
            assertNotNull(studentInfoResponse.getSpPostNumbers());
        }
        @Test
        @DisplayName("Student should not exist")
        void getStudentInfoByWrongStudentId() {
            StudentInfoResponse studentInfoResponse = commonService.getStudentInfoByStudentId("abc");
            assertNull(studentInfoResponse.getStudentId());
            assertNull(studentInfoResponse.getStudentName());
            assertNull(studentInfoResponse.getSpPostNumbers());
        }
    }

    @Nested
    @DisplayName("Tests for getStudentPostEntitiesByStudentIdAndSpPostNumber")
    class GetStudentPostEntitiesByStudentIdAndSpPostNumberTest {
        @Test
        @DisplayName("Student's post should be fetched correctly")
        void getStudentPostEntitiesByStudentIdAndSpPostNumber() {
            StudentInfoResponse studentInfoResponse = commonService.getStudentInfoByStudentId("0000001");
            for (String postNumber : studentInfoResponse.getSpPostNumbers()){
                SysStudentPostEntity sysStudentPostEntity =
                        commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(
                                "0000001",
                                postNumber);
                assertEquals(postNumber,sysStudentPostEntity.getSpPostNumber());
                assertNotNull(sysStudentPostEntity.getStudentPostId());
                assertNotNull(sysStudentPostEntity.getSpEarnunits());
                assertNotNull(sysStudentPostEntity.getStudentId());
                assertNotNull(sysStudentPostEntity.getSpGpaAll());
                assertNotNull(sysStudentPostEntity.getSpGpaApply());
                assertNotNull(sysStudentPostEntity.getSpRgunits());
                assertNotNull(sysStudentPostEntity.getSpCreditLimits());
                assertNotNull(sysStudentPostEntity.getSpEarnunits());
                assertNotNull(sysStudentPostEntity.getSpAdmit());
                assertNotNull(sysStudentPostEntity.getSpConfu());
                assertNotNull(sysStudentPostEntity.getSpMajor());
                assertNotNull(sysStudentPostEntity.getSpNdocs());
                assertNotNull(sysStudentPostEntity.getSpObj());
                // assertNotNull(sysStudentPostEntity.getSpReadmit(), "This student's post doesn't have readmit info");
                assertNotNull(sysStudentPostEntity.getSpTransdate());
            }
        }
    }

    @Nested
    @DisplayName("Tests for calculateGpaAndUnit")
    @RunWith(MockitoJUnitRunner.class)
    @Disabled("Not implemented yet")
    class CalculateGpaAndUnitTest {
        private String studentPostId;
        private SysStudentPostEntity sysStudentPostEntity;
        private List<SysAdmissionCourseEntity> sysAdmissionCourseEntityList;
        private List<SysTransferCourseEntity> sysTransferCourseEntityList;

        @Mock
        StudentPostRepository mockStudentPostRepository;

        @InjectMocks
        CommonServiceImpl mockCommonService = new CommonServiceImpl(
                studentRepository,
                studentPostRepository,
                admissionCourseRepository,
                transferCourseRepository);

        @BeforeEach
        void setUp() {
            studentPostId = "0000000001";
            sysStudentPostEntity = commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber("0000001", "654");
            sysAdmissionCourseEntityList = admissionCourseRepository.getSysAdmissionCourseEntitiesByStudentPostId(studentPostId);
            System.out.println(sysAdmissionCourseEntityList.size());
            sysTransferCourseEntityList = transferCourseRepository.getSysTransferCourseEntitiesByStudentPostId(studentPostId);
        }

        @Test
        void calculateGpaAndUnit() {
            when(mockStudentPostRepository.save(any(SysStudentPostEntity.class))).thenReturn(new SysStudentPostEntity());
            List<String> result = mockCommonService.calculateGpaAndUnit(
                    studentPostId, sysStudentPostEntity, sysAdmissionCourseEntityList, sysTransferCourseEntityList);
            assertTrue(result.isEmpty());
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
            sysAdmissionCourseEntity.setAdCourseUnits(13.0);
            sysAdmissionCourseEntityList.add(sysAdmissionCourseEntity);

            List<String> reason = commonService.checkReason(sysAdmissionCourseEntityList, null);
            assertEquals("Visitor course pass visit limit", reason.get(0));
        }
        @Test
        void notPassLimit() {
            sysAdmissionCourseEntity.setAdCourseUnits(11.0);
            sysAdmissionCourseEntityList.add(sysAdmissionCourseEntity);

            List<String> reason = commonService.checkReason(sysAdmissionCourseEntityList, null);
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
        void xAdmissionCourse() {
            SysAdmissionCourseEntity sysAdmissionCourseEntity = new SysAdmissionCourseEntity();
            sysAdmissionCourseEntity.setAdCourseApplyStatus((byte) 1);
            sysAdmissionCourseEntity.setAdCourseApplyCode("X");
            sysAdmissionCourseEntity.setAdCourseName("Dummy Admission Course");
            sysAdmissionCourseEntityList.add(sysAdmissionCourseEntity);

            List<String> reason = commonService.CheckAdmissionCourseAndReturnReason(
                    sysAdmissionCourseEntityList, sysTransferCourseEntityList);
            assertEquals("Restricted course Dummy Admission Course cannot be applied", reason.get(0));
            assertEquals("Restricted course Dummy Transfer Course cannot be applied", reason.get(1));
        }

        @Test
        void visitorAdmissionCourseAndOverflowScore() {
            SysAdmissionCourseEntity sysAdmissionCourseEntity = new SysAdmissionCourseEntity();
            sysAdmissionCourseEntity.setAdCourseApplyStatus((byte) 1);
            sysAdmissionCourseEntity.setAdCourseApplyCode("V");
            sysAdmissionCourseEntity.setAdCourseUnits(13.0);
            sysAdmissionCourseEntityList.add(sysAdmissionCourseEntity);

            List<String> reason = commonService.CheckAdmissionCourseAndReturnReason(
                    sysAdmissionCourseEntityList, sysTransferCourseEntityList);
            assertEquals("Visitor course pass visit limit", reason.get(0));
            assertEquals("Restricted course Dummy Transfer Course cannot be applied", reason.get(1));
        }

        @Test
        void visitorAdmissionCourseAndNotOverflowScore() {
            SysAdmissionCourseEntity sysAdmissionCourseEntity = new SysAdmissionCourseEntity();
            sysAdmissionCourseEntity.setAdCourseApplyStatus((byte) 1);
            sysAdmissionCourseEntity.setAdCourseApplyCode("V");
            sysAdmissionCourseEntity.setAdCourseUnits(11.0);
            sysAdmissionCourseEntityList.add(sysAdmissionCourseEntity);

            List<String> reason = commonService.CheckAdmissionCourseAndReturnReason(
                    sysAdmissionCourseEntityList, sysTransferCourseEntityList);
            assertEquals("Restricted course Dummy Transfer Course cannot be applied", reason.get(0));
        }
    }

    @Test
    void checkTransferCourseAndReturnReason() {
        SysTransferCourseEntity sysTransferCourseEntity = new SysTransferCourseEntity();
        sysTransferCourseEntity.setTrCourseApplyStatus((byte) 1);
        sysTransferCourseEntity.setTrCourseApplyCode("X");
        sysTransferCourseEntity.setTrCourseName("Dummy Transfer Course");
        List<SysTransferCourseEntity> sysTransferCourseEntityList = new ArrayList<>();
        sysTransferCourseEntityList.add(sysTransferCourseEntity);
        List<String> reason = commonService.CheckTransferCourseAndReturnReason(
                null,
                sysTransferCourseEntityList);
        assertEquals("Restricted course Dummy Transfer Course cannot be applied", reason.get(0));
    }

    @Test
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