package com.example.gradinfo.service.impl;

import com.example.gradinfo.dto.response.CodeDescriptionResponse;
import com.example.gradinfo.dto.response.LoginResponse;
import com.example.gradinfo.entity.SysEventEntity;
import com.example.gradinfo.entity.SysUserEntity;
import com.example.gradinfo.repository.EventRepository;
import com.example.gradinfo.repository.UserInfoRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

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
            void loginResponseWithValidUserId_validUserNameAndPassword() {
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
            void loginResponseWithNullUserId_inValidUserNameAndPassword() {
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

    @Nested
    @DisplayName("Tests for getCodeAndDescription")
    class GetCodeAndDescriptionTest {

        @Mock private SysEventEntity sysEventEntity;
        @Mock private List<SysEventEntity> sysEventEntityList;

        @Nested
        @DisplayName("When exist")
        class WhenExistTest {

            @BeforeEach
            void setUp() {
                sysEventEntity = new SysEventEntity();
                sysEventEntity.setEventDescription("Some Description");

                sysEventEntityList = new ArrayList<>();
                sysEventEntityList.add(sysEventEntity);
            }

            @Test
            void nonNullList_dataExists() {
                // Arrange
                when(eventRepository.findAll())
                        .thenReturn(sysEventEntityList);
                // Act
                List<CodeDescriptionResponse> codeDescriptionResponseList =
                        loginService.getCodeAndDescription();
                // Assert
                assertEquals("Some Description", codeDescriptionResponseList.get(0).getEventDescription());
            }
        }
    }
}