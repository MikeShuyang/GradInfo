package com.example.gradinfo.service.impl;

import com.example.gradinfo.dto.request.StarsRequest;
import com.example.gradinfo.dto.response.CommonResponse;
import com.example.gradinfo.dto.response.StarsResponse;
import com.example.gradinfo.entity.SysStarsExceptionEntity;
import com.example.gradinfo.mapper.CommonMapper;
import com.example.gradinfo.repository.StarsRepository;
import com.example.gradinfo.service.CommonService;
import com.example.gradinfo.service.StarsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StarsServiceImpl implements StarsService {
    private final CommonService commonService;
    private final StarsRepository starsRepository;

    public StarsServiceImpl(CommonService commonService, StarsRepository starsRepository) {
        this.commonService = commonService;
        this.starsRepository = starsRepository;
    }

    @Override
    public CommonResponse postStarsExceptionByStarsObj(StarsRequest starsRequest) {
        SysStarsExceptionEntity sysStarsExceptionEntity = CommonMapper.convertToDto(starsRequest.getStarsObject(), SysStarsExceptionEntity.class);
        sysStarsExceptionEntity.setStudentPostId(commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(starsRequest.getStudentInfo().getStudentId(), starsRequest.getStudentInfo().getSpPostNumber()).getStudentPostId());
        CommonResponse commonResponse = new CommonResponse();
        try {
            starsRepository.save(sysStarsExceptionEntity);
        } catch (Exception e) {
            commonResponse.setFlag(false);
            return commonResponse;
        }
        commonResponse.setFlag(true);
        return commonResponse;
    }

    @Override
    public List<StarsResponse> getStarsExceptionByStudentIDAndPostNumber(String studentId, String spPostNumber) {
        List<SysStarsExceptionEntity> sysStarsExceptionEntityList = starsRepository.getSysStarsExceptionEntitiesByStudentPostId(commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(studentId,spPostNumber).getStudentPostId());
        List<StarsResponse> starsResponseList = new ArrayList<>();
        for (SysStarsExceptionEntity sysStarsExceptionEntity : sysStarsExceptionEntityList) {
            starsResponseList.add(CommonMapper.convertToDto(sysStarsExceptionEntity, StarsResponse.class));
        }
        return starsResponseList;
    }
}
