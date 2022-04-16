package com.example.gradinfo.service.impl;

import com.example.gradinfo.dto.request.CommentObject;
import com.example.gradinfo.dto.request.CommentRequest;
import com.example.gradinfo.dto.request.StudentInfo;
import com.example.gradinfo.dto.response.CommentResponse;
import com.example.gradinfo.dto.response.CommonResponse;
import com.example.gradinfo.entity.SysCommentEntity;
import com.example.gradinfo.entity.SysStudentPostEntity;
import com.example.gradinfo.repository.CommentRepository;
import com.example.gradinfo.service.CommonService;
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
class CommentServiceImplTest {

    @Mock private CommonService commonService;
    @Mock private CommentRepository commentRepository;

    @InjectMocks private CommentServiceImpl commentService;

    @Nested
    @DisplayName("Tests for getCommentsTableDataByIDAndPostNumber")
    class GetCommentsTableDataByIDAndPostNumberTest {

        @Mock private SysStudentPostEntity sysStudentPostEntity;
        @Mock private SysCommentEntity sysCommentEntity;
        @Mock private List<SysCommentEntity> sysCommentEntityList;

        @Nested
        @DisplayName("When exist")
        class WhenExistTest {

            @BeforeEach
            void setUp() {
                sysStudentPostEntity = new SysStudentPostEntity();
                sysStudentPostEntity.setStudentPostId("valid student post id");

                sysCommentEntity = new SysCommentEntity();
                sysCommentEntity.setCommentContent("This is a dummy comment");

                sysCommentEntityList = new ArrayList<>();
                sysCommentEntityList.add(sysCommentEntity);
            }

            @Test
            void nonNullList_dataExists() {
                // Arrange
                when(commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(
                        "valid student id", "valid post number"))
                        .thenReturn(sysStudentPostEntity);
                when(commentRepository.getSysCommentEntitiesByStudentPostId(
                        "valid student post id"))
                        .thenReturn(sysCommentEntityList);
                // Act
                List<CommentResponse> commentResponseList =
                        commentService.getCommentsTableDataByIDAndPostNumber(
                                "valid student id", "valid post number");
                // Assert
                assertEquals("This is a dummy comment", commentResponseList.get(0).getCommentContent());
            }
        }

        @Nested
        @DisplayName("When not exist")
        class WhenNotExistTest {

            @BeforeEach
            void setUp() {
                sysStudentPostEntity = new SysStudentPostEntity();
                sysStudentPostEntity.setStudentPostId("valid student post id");

                sysCommentEntityList = new ArrayList<>();
            }

            @Test
            void nullList_dataNotExist() {
                // Arrange
                when(commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(
                        "valid student id", "valid post number"))
                        .thenReturn(sysStudentPostEntity);
                when(commentRepository.getSysCommentEntitiesByStudentPostId(
                        "valid student post id"))
                        .thenReturn(sysCommentEntityList);
                // Act
                List<CommentResponse> commentResponseList =
                        commentService.getCommentsTableDataByIDAndPostNumber(
                                "valid student id", "valid post number");
                // Assert
                assertTrue(commentResponseList.isEmpty());
            }
        }
    }

    @Nested
    @DisplayName("Tests for postCommentTableDataByCommentObj")
    class PostCommentTableDataByCommentObjTest {

        @Mock private CommentRequest commentRequest;
        @Mock private SysStudentPostEntity sysStudentPostEntity;

        @Nested
        @DisplayName("When success")
        class WhenSuccessTest {

            @BeforeEach
            void setUp() {
                commentRequest = new CommentRequest();
                commentRequest.setCommentObject(new CommentObject());
                commentRequest.setStudentInfo(new StudentInfo());
                sysStudentPostEntity = new SysStudentPostEntity();
                sysStudentPostEntity.setStudentPostId("valid id");
            }

            @Test
            void trueFlag_noException() {
                // Arrange
                when(commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(
                        any(),any()))
                        .thenReturn(sysStudentPostEntity);
                when(commentRepository.save(
                        any()))
                        .thenReturn(new SysCommentEntity());
                // Act
                CommonResponse commonResponse =
                        commentService.postCommentTableDataByCommentObj(commentRequest);
                // Assert
                assertTrue(commonResponse.isFlag());
            }
        }

        @Nested
        @DisplayName("When fail")
        class WhenFailTest {

            @BeforeEach
            void setUp() {
                commentRequest = new CommentRequest();
                commentRequest.setCommentObject(new CommentObject());
                commentRequest.setStudentInfo(new StudentInfo());
                sysStudentPostEntity = new SysStudentPostEntity();
                sysStudentPostEntity.setStudentPostId("invalid id");
            }

            @Test
            void trueFlag_noException() {
                // Arrange
                when(commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(
                        any(),any()))
                        .thenReturn(sysStudentPostEntity);
                when(commentRepository.save(
                        any()))
                        .thenThrow(new RuntimeException());
                // Act
                CommonResponse commonResponse =
                        commentService.postCommentTableDataByCommentObj(commentRequest);
                // Assert
                assertFalse(commonResponse.isFlag());
            }
        }

    }

}