package com.example.gradinfo.service.impl;

import com.example.gradinfo.dto.response.LoginResponse;
import com.example.gradinfo.repository.UserInfoRepository;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoginServiceImplTest {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private LoginServiceImpl loginService;

    @Before
    public void setUp() {
        loginService = new LoginServiceImpl(userInfoRepository);
    }

    @Nested
    @DisplayName("Tests for getUserInfoByUsernameAndPassword")
    class GetUserInfoByUsernameAndPasswordTest {
        @Test
        void correctPassword() {
            LoginResponse loginResponse =
                    loginService.getUserInfoByUsernameAndPassword("Test User1", "123456789");
            assertNotNull(loginResponse);
        }

        @Test
        void wrongPassword() {
            LoginResponse loginResponse =
                    loginService.getUserInfoByUsernameAndPassword("Test User1", "1");
            assertNull(loginResponse.getUserId());
        }
    }
}