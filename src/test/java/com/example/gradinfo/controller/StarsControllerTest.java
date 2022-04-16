package com.example.gradinfo.controller;

import com.example.gradinfo.dto.response.CommonResponse;
import com.example.gradinfo.service.StarsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StarsControllerTest {

    @Mock private StarsService starsService;
    @Mock private CommonResponse commonResponse;
    @InjectMocks private StarsController starsController;

    @Nested
    @DisplayName("Tests for postStarsReportByStarsObj")
    class PostStarsReportByStarsObjTest {
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
                when(starsService.postStarsExceptionByStarsObj(any()))
                        .thenReturn(commonResponse);
                // Act
                ResponseEntity<CommonResponse> response =
                        starsController.postStarsReportByStarsObj(any());
                // Assert
                assertTrue(Objects.requireNonNull(response.getBody()).isFlag());
            }
        }
    }
}