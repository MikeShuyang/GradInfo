package com.example.gradinfo.service.impl;

import com.example.gradinfo.dto.request.AdmissionCourseRequest;
import com.example.gradinfo.dto.request.Course;
import com.example.gradinfo.dto.request.StudentInfo;
import com.example.gradinfo.dto.request.UserInfo;
import com.example.gradinfo.dto.response.AdmissionCourseApplyResponse;
import com.example.gradinfo.dto.response.AdmissionCourseResponse;
import com.example.gradinfo.dto.response.StudentPostResponse;
import com.example.gradinfo.entity.SysAdHistoryEntity;
import com.example.gradinfo.entity.SysAdmissionCourseEntity;
import com.example.gradinfo.entity.SysStudentPostEntity;
import com.example.gradinfo.repository.*;
import com.example.gradinfo.service.CommonService;
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
class AdmissionServiceImplTest {

    @Autowired
    private AdmissionCourseRepository admissionCourseRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentPostRepository studentPostRepository;
    @Autowired
    private TransferCourseRepository transferCourseRepository;
    @Autowired
    private CommonService commonService;
    @Autowired
    private AdmissionHistoryRepository admissionHistoryRepository;
    @Autowired
    private TransferHistoryRepository transferHistoryRepository;

    @Autowired
    private AdmissionServiceImpl admissionService;

    @BeforeEach
    void setUp() {
        admissionService = new AdmissionServiceImpl(
                admissionCourseRepository,
                studentRepository,
                studentPostRepository,
                transferCourseRepository,
                commonService,
                admissionHistoryRepository,
                transferHistoryRepository);
    }

    @Nested
    @DisplayName("Tests for getStudentPostDataByStudentIDAndPostNumber")
    class GetStudentPostDataByStudentIDAndPostNumberTest {
        @Test
        @DisplayName("This student should not exist")
        void wrongStudentIDAndPostNumber() {
            StudentPostResponse student =
                    admissionService.getStudentPostDataByStudentIDAndPostNumber("aaa","aaa");
            assertNull(student.getSpObj());
        }
        @Test
        @DisplayName("This student should exist")
        void correctStudentIDAndPostNumber() {
            StudentPostResponse student =
                    admissionService.getStudentPostDataByStudentIDAndPostNumber("0000001","654");
            assertNotNull(student.getSpMajor());
        }
    }

    @Nested
    @DisplayName("Tests for getAdmissionCourseDataByStudentIDAndPostNumber")
    class GetAdmissionCourseDataByStudentIDAndPostNumberTest {
        @Test
        @DisplayName("At least one AdmissionCourseData should exist")
        void correctStudentIDAndPostNumber() {
            List<AdmissionCourseResponse> admissionCourseResponseList =
                    admissionService.getAdmissionCourseDataByStudentIDAndPostNumber("0000001", "654");
            assertNotNull(admissionCourseResponseList);
            assertNotNull(admissionCourseResponseList.get(0).getAdCourseName());
        }
        @Test
        @DisplayName("Admission course data should not exist")
        void wrongStudentIDAndPostNumber() {
            List<AdmissionCourseResponse> admissionCourseResponseList =
                    admissionService.getAdmissionCourseDataByStudentIDAndPostNumber("a", "654");
            assertTrue(admissionCourseResponseList.isEmpty());
        }
    }

    @Nested
    @DisplayName("Tests for getPostAdmissionCourseTableDataByNewArr")
    @RunWith(MockitoJUnitRunner.class)
    @Disabled("Not implemented yet")
    class GetPostAdmissionCourseTableDataByNewArrTest {
        private final UserInfo userInfo = new UserInfo();
        private final StudentInfo studentInfo = new StudentInfo();
        private final Course course = new Course();
        private final AdmissionCourseRequest admissionCourseRequest = new AdmissionCourseRequest();
        private final List<Course> courseList = new ArrayList<>();

        @InjectMocks
        AdmissionServiceImpl mockAdmissionService = new AdmissionServiceImpl(
                admissionCourseRepository,
                studentRepository,
                studentPostRepository,
                transferCourseRepository,
                commonService,
                admissionHistoryRepository,
                transferHistoryRepository);

        @Mock
        AdmissionCourseRepository mockAdmissionCourseRepository;

        @Mock
        AdmissionHistoryRepository mockAdmissionHistoryRepository;

        @Mock
        CommonService mockCommonService;

        @BeforeEach
        void setUp() {
            userInfo.setUserOper("Tester Mike");
            userInfo.setTransDate(LocalDate.now().toString());
            studentInfo.setStudentId("0000001");
            studentInfo.setSpPostNumber("654");
            course.setCourseId("0000000001");
            courseList.add(course);
            admissionCourseRequest.setUserInfo(userInfo);
            admissionCourseRequest.setStudentInfo(studentInfo);
            admissionCourseRequest.setCourseList(courseList);
        }

        @Test
        void success() {
            SysStudentPostEntity sysStudentPostEntity = new SysStudentPostEntity();
            sysStudentPostEntity.setStudentPostId("0000000001");
            when(mockAdmissionCourseRepository.save(any(SysAdmissionCourseEntity.class))).thenReturn(new SysAdmissionCourseEntity());
            when(mockAdmissionHistoryRepository.save(any(SysAdHistoryEntity.class))).thenReturn(new SysAdHistoryEntity());
            when(mockCommonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(any(),any())).thenReturn(sysStudentPostEntity);
            when(mockCommonService.calculateGpaAndUnit(any(),any(),any(),any())).thenReturn(new ArrayList<>());
            AdmissionCourseApplyResponse admissionCourseApplyResponse =
                    mockAdmissionService.getPostAdmissionCourseTableDataByNewArr(admissionCourseRequest);
            assertTrue(admissionCourseApplyResponse.isFlag());
        }

        @Test
        void failed() {
            System.out.println("asdasd");
        }
    }
}