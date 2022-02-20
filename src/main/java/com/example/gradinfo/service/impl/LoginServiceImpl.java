package com.example.gradinfo.service.impl;

import com.example.gradinfo.dto.response.LoginResponse;
import com.example.gradinfo.entity.SysUserEntity;
import com.example.gradinfo.mapper.CommonMapper;
import com.example.gradinfo.repository.UserInfoRepository;
import com.example.gradinfo.service.LoginService;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    private final UserInfoRepository userInfoRepository;

    public LoginServiceImpl(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @Override
    public LoginResponse getUserInfoByUsernameAndPassword(String userName, String userPassword) {
        SysUserEntity sysUserEntity = userInfoRepository.getSysUserEntityByUserNameAndUserPassword(userName, userPassword);
        LoginResponse loginResponse = new LoginResponse();
        if (sysUserEntity == null) {

        } else {
            loginResponse = CommonMapper.convertToDto(sysUserEntity, LoginResponse.class);
        }


        return loginResponse;
    }
}
