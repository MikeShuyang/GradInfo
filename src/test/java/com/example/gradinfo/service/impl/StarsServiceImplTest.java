package com.example.gradinfo.service.impl;

import com.example.gradinfo.dto.request.StarsObject;
import com.example.gradinfo.dto.request.StarsRequest;
import com.example.gradinfo.dto.request.StudentInfo;
import com.example.gradinfo.dto.response.CommonResponse;
import com.example.gradinfo.entity.SysStarsExceptionEntity;
import com.example.gradinfo.entity.SysStudentPostEntity;
import com.example.gradinfo.repository.StarsRepository;
import com.example.gradinfo.service.CommonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StarsServiceImplTest {

    @Mock private CommonService commonService;
    @Mock private StarsRepository starsRepository;

    @InjectMocks private StarsServiceImpl starsService;

    @Nested
    @DisplayName("Tests for postStarsExceptionByStarsObj")
    class PostStarsExceptionByStarsObjTest {

        @Mock private StarsRequest starsRequest;
        @Mock private SysStudentPostEntity sysStudentPostEntity;


        @Nested
        @DisplayName("When success")
        class WhenSuccessTest {

            @BeforeEach
            void setUp() {
                starsRequest = new StarsRequest();
                starsRequest.setStarsObject(new StarsObject());
                starsRequest.setStudentInfo(new StudentInfo());
                sysStudentPostEntity = new SysStudentPostEntity();
                sysStudentPostEntity.setStudentPostId("valid id");
            }

            @Test
            void trueFlag_noException() {
                // Arrange
                when(commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(
                        any(),any()))
                        .thenReturn(sysStudentPostEntity);
                when(starsRepository.save(
                        any()))
                        .thenReturn(new SysStarsExceptionEntity());
                // Act
                CommonResponse commonResponse =
                        starsService.postStarsExceptionByStarsObj(starsRequest);
                // Assert
                assertTrue(commonResponse.isFlag());
            }
        }

        @Nested
        @DisplayName("When fail")
        class WhenFailTest {

            @BeforeEach
            void setUp() {
                starsRequest = new StarsRequest();
                starsRequest.setStarsObject(new StarsObject());
                starsRequest.setStudentInfo(new StudentInfo());
                sysStudentPostEntity = new SysStudentPostEntity();
                sysStudentPostEntity.setStudentPostId("invalid id");
            }

            @Test
            void falseFlag_runtimeException() {
                // Arrange
                when(commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(
                        any(),any()))
                        .thenReturn(sysStudentPostEntity);
                when(starsRepository.save(any())).thenThrow(new RuntimeException());
                // Act
                CommonResponse commonResponse = starsService.postStarsExceptionByStarsObj(starsRequest);
                // Assert
                assertFalse(commonResponse.isFlag());
            }
        }

    }
}