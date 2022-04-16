package com.example.gradinfo.impl;

import com.example.gradinfo.dto.request.DegreeCheckObject;
import com.example.gradinfo.dto.request.DegreeCheckRequest;
import com.example.gradinfo.dto.request.StudentInfo;
import com.example.gradinfo.dto.response.CommonResponse;
import com.example.gradinfo.dto.response.DegreeCheckResponse;
import com.example.gradinfo.entity.SysDegreeCheckEntity;
import com.example.gradinfo.entity.SysStudentPostEntity;
import com.example.gradinfo.repository.DegreeCheckRepository;
import com.example.gradinfo.service.CommonService;
import com.example.gradinfo.service.impl.DegreeCheckServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
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
class DegreeCheckServiceImplTest {

    @Mock private CommonService commonService;
    @Mock private DegreeCheckRepository degreeCheckRepository;

    @InjectMocks private DegreeCheckServiceImpl degreeCheckService;

    @Nested
    @DisplayName("Tests for postDegreeCheckByDegreeCheckObj")
    class PostDegreeCheckByDegreeCheckObjTest {

        @Mock private DegreeCheckObject degreeCheckObject;
        @Mock private DegreeCheckRequest degreeCheckRequest;
        @Mock private StudentInfo studentInfo;
        @Mock private SysStudentPostEntity sysStudentPostEntity;
        @Mock private SysDegreeCheckEntity sysDegreeCheckEntity;

        @Nested
        @DisplayName("When success")
        class WhenSuccessTest {

            @BeforeEach
            void setUp() {
                degreeCheckObject = new DegreeCheckObject();
                degreeCheckObject.setDegreeCheckId("1");
                degreeCheckObject.setDegreeName("Some name");
                degreeCheckObject.setDegreeCatalogYear("Some year");
                degreeCheckObject.setDegreeAdmissionTerm("Some term");
                degreeCheckObject.setDegreeGraduationTerm("Some term");
                degreeCheckObject.setDegreeForeignLanguage("Some language");

                studentInfo = new StudentInfo();
                studentInfo.setStudentId("Valid student id");
                studentInfo.setSpPostNumber("Valid SpPost number");

                degreeCheckRequest = new DegreeCheckRequest();
                degreeCheckRequest.setDegreeCheckObject(degreeCheckObject);
                degreeCheckRequest.setStudentInfo(studentInfo);

                sysStudentPostEntity = new SysStudentPostEntity();
                sysStudentPostEntity.setStudentPostId("valid studentPost id");

                sysDegreeCheckEntity.setDegreeName("Some name");
                sysDegreeCheckEntity.setDegreeCatalogYear("Some year");
                sysDegreeCheckEntity.setDegreeAdmissionTerm("Some term");
                sysDegreeCheckEntity.setDegreeGraduationTerm("Some term");
                sysDegreeCheckEntity.setDegreeForeignLanguage("Some language");
            }

            @Test
            void trueFlag_noException() {
                // Arrange
                when(commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(
                        any(),any()))
                        .thenReturn(sysStudentPostEntity);
                when(degreeCheckRepository.getSysDegreeCheckEntityByDegreeCheckId(
                        any()))
                        .thenReturn(sysDegreeCheckEntity);
                when(degreeCheckRepository.save(
                        any()))
                        .thenReturn(new SysDegreeCheckEntity());
                // Act
                CommonResponse commonResponse =
                        degreeCheckService.postDegreeCheckByDegreeCheckObj(degreeCheckRequest);
                // Assert
                assertTrue(commonResponse.isFlag());
            }
        }

        @Nested
        @DisplayName("When fail")
        class WhenFailTest {

            @BeforeEach
            void setUp() {

                degreeCheckObject = new DegreeCheckObject();
                degreeCheckObject.setDegreeCheckId("1");
                degreeCheckObject.setDegreeName("Some name");
                degreeCheckObject.setDegreeCatalogYear("Some year");
                degreeCheckObject.setDegreeAdmissionTerm("Some term");
                degreeCheckObject.setDegreeGraduationTerm("Some term");
                degreeCheckObject.setDegreeForeignLanguage("Some language");

                studentInfo = new StudentInfo();
                studentInfo.setStudentId("student id");
                studentInfo.setSpPostNumber("SpPost number");

                degreeCheckRequest = new DegreeCheckRequest();
                degreeCheckRequest.setDegreeCheckObject(degreeCheckObject);
                degreeCheckRequest.setStudentInfo(studentInfo);

                sysStudentPostEntity = new SysStudentPostEntity();
                sysStudentPostEntity.setStudentPostId("studentPost id");

                sysDegreeCheckEntity.setDegreeName("Some name");
                sysDegreeCheckEntity.setDegreeCatalogYear("Some year");
                sysDegreeCheckEntity.setDegreeAdmissionTerm("Some term");
                sysDegreeCheckEntity.setDegreeGraduationTerm("Some term");
                sysDegreeCheckEntity.setDegreeForeignLanguage("Some language");
            }

            @Test
            void falseFlag_runtimeException() {
                // Arrange
                when(commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(
                        any(),any()))
                        .thenReturn(sysStudentPostEntity);
                when(degreeCheckRepository.getSysDegreeCheckEntityByDegreeCheckId(
                        any()))
                        .thenReturn(sysDegreeCheckEntity);
                when(degreeCheckRepository.save(
                        any()))
                        .thenThrow(new RuntimeException());
                // Act
                CommonResponse commonResponse =
                        degreeCheckService.postDegreeCheckByDegreeCheckObj(degreeCheckRequest);
                // Assert
                assertFalse(commonResponse.isFlag());
            }
        }

    }

    @Nested
    @DisplayName("Tests for getDegreeCheckTableDataByStudentIDAndPostNumber")
    class GetDegreeCheckTableDataByStudentIDAndPostNumberTest {

        @Mock private SysStudentPostEntity sysStudentPostEntity;
        @Mock private SysDegreeCheckEntity sysDegreeCheckEntity;
        @Mock private List<SysDegreeCheckEntity> sysDegreeCheckEntityList;

        @Nested
        @DisplayName("When exist")
        class WhenExistTest {

            @BeforeEach
            void setUp() {
                sysStudentPostEntity = new SysStudentPostEntity();
                sysStudentPostEntity.setStudentPostId("valid student post id");

                sysDegreeCheckEntity = new SysDegreeCheckEntity();
                sysDegreeCheckEntity.setDegreeName("valid degree name");

                sysDegreeCheckEntityList = new ArrayList<>();
                sysDegreeCheckEntityList.add(sysDegreeCheckEntity);
            }

            @Test
            void nonNullList_dataExists() {
                // Arrange
                when(commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(
                        "valid student id", "valid post number"))
                        .thenReturn(sysStudentPostEntity);
                when(degreeCheckRepository.getSysDegreeCheckEntitiesByStudentPostId(
                        "valid student post id"))
                        .thenReturn(sysDegreeCheckEntityList);
                // Act
                List<DegreeCheckResponse> degreeCheckResponseList =
                        degreeCheckService.getDegreeCheckTableDataByStudentIDAndPostNumber(
                                "valid student id", "valid post number");
                // Assert
                assertEquals("valid degree name", degreeCheckResponseList.get(0).getDegreeName());
            }
        }

        @Nested
        @DisplayName("When not exist")
        class WhenNotExistTest {

            @BeforeEach
            void setUp() {
                sysStudentPostEntity = new SysStudentPostEntity();
                sysStudentPostEntity.setStudentPostId("valid student post id");

                sysDegreeCheckEntityList = new ArrayList<>();
            }

            @Test
            void nullList_dataNotExist() {
                // Arrange
                when(commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(
                        "valid student id", "valid post number"))
                        .thenReturn(sysStudentPostEntity);
                when(degreeCheckRepository.getSysDegreeCheckEntitiesByStudentPostId(
                        "valid student post id"))
                        .thenReturn(sysDegreeCheckEntityList);
                // Act
                List<DegreeCheckResponse> degreeCheckResponseList =
                        degreeCheckService.getDegreeCheckTableDataByStudentIDAndPostNumber(
                                "valid student id", "valid post number");
                // Assert
                assertTrue(degreeCheckResponseList.isEmpty());
            }
        }

    }

}