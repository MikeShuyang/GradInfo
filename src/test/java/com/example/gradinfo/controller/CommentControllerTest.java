package com.example.gradinfo.controller;

import com.example.gradinfo.dto.response.CommentResponse;
import com.example.gradinfo.dto.response.CommonResponse;
import com.example.gradinfo.service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommentControllerTest {

    @Mock private CommentService commentService;
    @Mock private CommentResponse commentResponse;
    @Mock private CommonResponse commonResponse;
    @InjectMocks private CommentController commentController;

    @Nested
    @DisplayName("Tests for getCommentsTableDataByIDAndPostNumber")
    class GetCommentsTableDataByIDAndPostNumberTest {
        @Nested
        @DisplayName("When success")
        class WhenSuccessTest {
            private List<CommentResponse> commentResponseList;
            @BeforeEach
            void setUp() {
                commentResponseList = new ArrayList<>();
                commentResponse = new CommentResponse();
                commentResponse.setCommentContent("A comment");
                commentResponseList.add(commentResponse);
            }
            @Test
            void validIdAndPostNumber_dataExists() {
                // Arrange
                when(commentService.getCommentsTableDataByIDAndPostNumber(
                        "valid student id", "valid post number"))
                        .thenReturn(commentResponseList);
                // Act
                ResponseEntity<List<CommentResponse>> response =
                        commentController.getCommentsTableDataByIDAndPostNumber(
                                "valid student id", "valid post number");
                // Assert
                assertEquals("A comment",
                        Objects.requireNonNull(response.getBody()).get(0).getCommentContent());
            }
        }
    }

    @Nested
    @DisplayName("Tests for postCommentTableDataByCommentObj")
    class PostCommentTableDataByCommentObjTest {
        @Nested
        @DisplayName("When success")
        class WhenSuccessTest {
            @BeforeEach
            void setUp() {
                commonResponse = new CommonResponse();
                commonResponse.setFlag(true);
            }
            @Test
            void successUpdate_trueFlag() {
                // Arrange
                when(commentService.postCommentTableDataByCommentObj(any()))
                        .thenReturn(commonResponse);
                // Act
                ResponseEntity<CommonResponse> response =
                        commentController.postCommentTableDataByCommentObj(any());
                // Assert
                assertTrue(Objects.requireNonNull(response.getBody()).isFlag());
            }
        }
    }
}