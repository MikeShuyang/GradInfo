package com.example.gradinfo.service.impl;

import com.example.gradinfo.dto.request.Course;
import com.example.gradinfo.dto.request.StudentInfo;
import com.example.gradinfo.dto.request.TransferCourseRequest;
import com.example.gradinfo.dto.request.UserInfo;
import com.example.gradinfo.dto.response.BachelorDegreeResponse;
import com.example.gradinfo.dto.response.TransferCourseApplyResponse;
import com.example.gradinfo.dto.response.TransferCourseResponse;
import com.example.gradinfo.dto.response.TransferInstitution;
import com.example.gradinfo.entity.SysTransferCourseEntity;
import com.example.gradinfo.entity.SysTransferHistoryEntity;
import com.example.gradinfo.repository.*;
import com.example.gradinfo.service.CommonService;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class TransferServiceImplTest {

    @Autowired
    private CommonService commonService;
    @Autowired
    private TransferCourseRepository transferCourseRepository;
    @Autowired
    private TransferHistoryRepository transferHistoryRepository;
    @Autowired
    private InstitutionRepository institutionRepository;
    @Autowired
    private BachelorDegreeRepository bachelorDegreeRepository;
    @Autowired
    private AdmissionCourseRepository admissionCourseRepository;
    @Autowired
    private StudentPostRepository studentPostRepository;

    @Autowired
    private TransferServiceImpl transferService;

    @Before
    public void setUp() {
        transferService = new TransferServiceImpl(
                commonService,
                transferCourseRepository,
                transferHistoryRepository,
                institutionRepository,
                bachelorDegreeRepository,
                admissionCourseRepository,
                studentPostRepository
        );
    }

    @Nested
    @DisplayName("Tests for getTransferCourseTableDataByIDAndPostNumber")
    class GetTransferCourseTableDataByIDAndPostNumberTest {
        @Test
        void existData() {
            List<TransferCourseResponse> transferCourseResponses =
                    transferService.getTransferCourseTableDataByIDAndPostNumber("0000002", "674");
            assertNotNull(transferCourseResponses);
        }
        @Test
        void notExistData() {
            List<TransferCourseResponse> transferCourseResponses =
                    transferService.getTransferCourseTableDataByIDAndPostNumber("0000001", "654");
            assertTrue(transferCourseResponses.isEmpty());
        }
    }

    @Nested
    @DisplayName("Tests for getTransferInfoByIDAndPostNumber")
    class GetTransferInfoByIDAndPostNumberTest {
        @Test
        void existData() {
            List<TransferInstitution> transferInstitutionList =
                    transferService.getTransferInfoByIDAndPostNumber("0000002", "674");
            assertNotNull(transferInstitutionList);
            assertEquals(1, transferInstitutionList.size());
        }
        @Test
        void notExistData() {
            List<TransferInstitution> transferInstitutionList =
                    transferService.getTransferInfoByIDAndPostNumber("0000001", "654");
            assertTrue(transferInstitutionList.isEmpty());
        }
    }

    @Nested
    @DisplayName("Tests for getBachelorDegreeInfoByID")
    class GetBachelorDegreeInfoByIDTest {
        @Test
        void existData() {
            BachelorDegreeResponse bachelorDegreeResponse =
                    transferService.getBachelorDegreeInfoByID("0000001");
            assertEquals("4835", bachelorDegreeResponse.getBachelorDegreeList().get(0).getSbCeeb());
        }
        @Test
        void notExistData() {
            BachelorDegreeResponse bachelorDegreeResponse =
                    transferService.getBachelorDegreeInfoByID("0000000");
            assertTrue(bachelorDegreeResponse.getBachelorDegreeList().isEmpty());
        }
    }

    @Nested
    @DisplayName("Tests for postTransferCourseTableDataByNewArr")
    @RunWith(MockitoJUnitRunner.class)
    @Disabled("Not implemented yet")
    class PostTransferCourseTableDataByNewArrTest {
        private final UserInfo userInfo = new UserInfo();
        private final StudentInfo studentInfo = new StudentInfo();
        private final Course course = new Course();
        private final TransferCourseRequest transferCourseRequest = new TransferCourseRequest();
        private final List<Course> courseList = new ArrayList<>();

        @Mock
        TransferHistoryRepository mockTransferHistoryRepository;

        @Mock
        TransferCourseRepository mockTransferCourseRepository;

        @Mock
        CommonService mockCommonService;

        @InjectMocks
        TransferServiceImpl mockTransferService = new TransferServiceImpl(
                commonService,
                transferCourseRepository,
                transferHistoryRepository,
                institutionRepository,
                bachelorDegreeRepository,
                admissionCourseRepository,
                studentPostRepository
        );

        @BeforeEach
        void setUp() {
            userInfo.setUserOper("Tester Mike");
            userInfo.setTransDate(LocalDate.now().toString());
            studentInfo.setStudentId("0000001");
            studentInfo.setSpPostNumber("654");
            course.setCourseId("0000000001");
            courseList.add(course);
            transferCourseRequest.setUserInfo(userInfo);
            transferCourseRequest.setStudentInfo(studentInfo);
            transferCourseRequest.setCourseList(courseList);
        }

        @Test
        void success() {
            when(mockTransferHistoryRepository.save(any())).thenReturn(new SysTransferHistoryEntity());
            when(mockTransferCourseRepository.save(any())).thenReturn(new SysTransferCourseEntity());
            when(mockCommonService.calculateGpaAndUnit(any(),any(),any(),any())).thenReturn(new ArrayList<>());
            TransferCourseApplyResponse transferCourseApplyResponse =
                    mockTransferService.postTransferCourseTableDataByNewArr(transferCourseRequest);
            assertTrue(transferCourseApplyResponse.isFlag());
        }

        @Test
        void fail() {
        }
    }

}