package com.example.gradinfo.service.impl;

import com.example.gradinfo.dto.response.CodeDescriptionResponse;
import com.example.gradinfo.dto.response.LoginResponse;
import com.example.gradinfo.dto.response.SpPostNumberResponse;
import com.example.gradinfo.entity.SysEventEntity;
import com.example.gradinfo.entity.SysStudentPostEntity;
import com.example.gradinfo.entity.SysUserEntity;
import com.example.gradinfo.entity.SysUserPostAccessEntity;
import com.example.gradinfo.mapper.CommonMapper;
import com.example.gradinfo.repository.EventRepository;
import com.example.gradinfo.repository.StudentPostRepository;
import com.example.gradinfo.repository.UserInfoRepository;
import com.example.gradinfo.repository.UserPostAccessRepository;
import com.example.gradinfo.service.LoginService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {
    private final UserInfoRepository userInfoRepository;
    private final EventRepository eventRepository;
    private final StudentPostRepository studentPostRepository;
    private final UserPostAccessRepository userPostAccessRepository;

    public LoginServiceImpl(UserInfoRepository userInfoRepository, EventRepository eventRepository, StudentPostRepository studentPostRepository, UserPostAccessRepository userPostAccessRepository) {
        this.userInfoRepository = userInfoRepository;
        this.eventRepository = eventRepository;
        this.studentPostRepository = studentPostRepository;
        this.userPostAccessRepository = userPostAccessRepository;
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

    @Override
    public List<CodeDescriptionResponse> getCodeAndDescription() {
        List<CodeDescriptionResponse> codeDescriptionResponseList = new ArrayList<>();
        List<SysEventEntity> sysEventEntityList = eventRepository.findAll();
        for (SysEventEntity sysEventEntity : sysEventEntityList) {
            codeDescriptionResponseList.add(CommonMapper.convertToDto(sysEventEntity, CodeDescriptionResponse.class));
        }

        return codeDescriptionResponseList;
    }

    @Override
    public List<SpPostNumberResponse> getAllSpPostNumber() {
        List<SysStudentPostEntity> sysStudentPostEntityList = studentPostRepository.findAllBySpPostNumberIsNotNull();
        List<SpPostNumberResponse> spPostNumberResponseList = new ArrayList<>();
        for (SysStudentPostEntity sysStudentPostEntity : sysStudentPostEntityList) {

            spPostNumberResponseList.add(CommonMapper.convertToDto(sysStudentPostEntity, SpPostNumberResponse.class));
        }
        return spPostNumberResponseList;
    }

    @Override
    public List<SpPostNumberResponse> getPostNumberByUserID(String userId) {
        List<SysUserPostAccessEntity> sysUserPostAccessEntityList = userPostAccessRepository.getSysUserPostAccessEntitiesByUserId(userId);
        List<SpPostNumberResponse> spPostNumberResponseList = new ArrayList<>();
        for (SysUserPostAccessEntity sysUserPostAccessEntity : sysUserPostAccessEntityList) {
            SpPostNumberResponse spPostNumberResponse = new SpPostNumberResponse();
            spPostNumberResponse.setSpPostNumber(sysUserPostAccessEntity.getPostNumber());
            spPostNumberResponseList.add(spPostNumberResponse);
        }
        return spPostNumberResponseList;
    }
}
