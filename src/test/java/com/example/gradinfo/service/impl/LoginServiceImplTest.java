package com.example.gradinfo.service.impl;

import com.example.gradinfo.dto.response.LoginResponse;
import com.example.gradinfo.entity.SysUserEntity;
import com.example.gradinfo.repository.EventRepository;
import com.example.gradinfo.repository.UserInfoRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginServiceImplTest {

    @Mock private UserInfoRepository userInfoRepository;
    @Mock private EventRepository eventRepository;

    @InjectMocks private LoginServiceImpl loginService;

    @Nested
    @DisplayName("Tests for getUserInfoByUsernameAndPassword")
    class GetUserInfoByUsernameAndPasswordTest {

        @Mock private SysUserEntity existSysUserEntity;

        @Nested
        @DisplayName("When userName and Password are correct")
        class WhenCorrectTest {

            @BeforeEach
            void setUp() {
                existSysUserEntity = new SysUserEntity();
                existSysUserEntity.setUserId("10001");
            }

            @Test
            void shouldReturnLoginResponseWithNonNullUserId_whenUserNameAndPasswordAreCorrect() {
                // Arrange
                when(userInfoRepository.getSysUserEntityByUserNameAndUserPassword(
                        "Test User1", "123456789"))
                        .thenReturn(existSysUserEntity);
                // Act
                LoginResponse loginResponse =
                        loginService.getUserInfoByUsernameAndPassword("Test User1", "123456789");
                // Assert
                assertEquals("10001", loginResponse.getUserId());
            }
        }

        @Nested
        @DisplayName("When userName and Password are not correct")
        class WhenInCorrectTest {

            @BeforeEach
            void setUp() {
                existSysUserEntity = new SysUserEntity();
            }

            @Test
            void shouldReturnLoginResponseWithNullUserId_whenUserNameAndPasswordAreNotCorrect() {
                // Arrange
                when(userInfoRepository.getSysUserEntityByUserNameAndUserPassword(
                        "Test User1", "1"))
                        .thenReturn(existSysUserEntity);
                // Act
                LoginResponse loginResponse =
                        loginService.getUserInfoByUsernameAndPassword("Test User1", "1");
                // Assert
                assertNull(loginResponse.getUserId());
            }
        }
    }
}