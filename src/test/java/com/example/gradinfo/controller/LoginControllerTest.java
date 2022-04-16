package com.example.gradinfo.controller;

import com.example.gradinfo.dto.response.CodeDescriptionResponse;
import com.example.gradinfo.dto.response.LoginResponse;
import com.example.gradinfo.service.LoginService;
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
class LoginControllerTest {

    @Mock private LoginService loginService;
    @Mock private LoginResponse loginResponse;
    @Mock private CodeDescriptionResponse codeDescriptionResponse;
    @InjectMocks private LoginController loginController;

    @Nested
    @DisplayName("Tests for getUserInfoByUsernameAndPassword")
    class GetUserInfoByUsernameAndPasswordTest {
        @Nested
        @DisplayName("When success")
        class WhenSuccessTest {
            @BeforeEach
            void setUp() {
                loginResponse = new LoginResponse();
                loginResponse.setUserName("Valid_user");
            }
            @Test
            void validUsernameAndPassword_userExists() {
                // Arrange
                when(loginService.getUserInfoByUsernameAndPassword(
                        "Valid_user", "Valid_password"))
                        .thenReturn(loginResponse);
                // Act
                ResponseEntity<LoginResponse> response =
                        loginController.getUserInfoByUsernameAndPassword(
                                "Valid_user", "Valid_password");
                // Assert
                assertEquals("Valid_user",
                        Objects.requireNonNull(response.getBody()).getUserName());
            }
        }
    }

    @Nested
    @DisplayName("Tests for getCodeAndDescription")
    class GetCodeAndDescriptionTest {
        @Nested
        @DisplayName("When success")
        class WhenSuccessTest {
            private List<CodeDescriptionResponse> codeDescriptionResponseList;
            @BeforeEach
            void setUp() {
                codeDescriptionResponseList = new ArrayList<>();
                codeDescriptionResponse = new CodeDescriptionResponse();
                codeDescriptionResponse.setEventDescription("Description");
                codeDescriptionResponseList.add(codeDescriptionResponse);
            }
            @Test
            void validData_successReturn() {
                // Arrange
                when(loginService.getCodeAndDescription())
                        .thenReturn(codeDescriptionResponseList);
                // Act
                ResponseEntity<List<CodeDescriptionResponse>> response =
                        loginController.getCodeAndDescription();
                // Assert
                assertEquals("Description",
                        Objects.requireNonNull(response.getBody()).get(0).getEventDescription());
            }
        }
    }
}