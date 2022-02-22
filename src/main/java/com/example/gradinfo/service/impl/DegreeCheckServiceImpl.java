package com.example.gradinfo.service.impl;

import com.example.gradinfo.dto.request.DegreeCheckRequest;
import com.example.gradinfo.dto.response.CommonResponse;
import com.example.gradinfo.dto.response.DegreeCheckResponse;
import com.example.gradinfo.entity.SysDegreeCheckEntity;
import com.example.gradinfo.entity.SysStudentPostEntity;
import com.example.gradinfo.mapper.CommonMapper;
import com.example.gradinfo.repository.DegreeCheckRepository;
import com.example.gradinfo.service.CommonService;
import com.example.gradinfo.service.DegreeCheckService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DegreeCheckServiceImpl implements DegreeCheckService {
    private final CommonService commonService;
    private final DegreeCheckRepository degreeCheckRepository;

    public DegreeCheckServiceImpl(CommonService commonService, DegreeCheckRepository degreeCheckRepository) {
        this.commonService = commonService;
        this.degreeCheckRepository = degreeCheckRepository;
    }

    @Override
    public CommonResponse postDegreeCheckByDegreeCheckObj(DegreeCheckRequest degreeCheckRequest) {
        CommonResponse commonResponse = new CommonResponse();

        SysDegreeCheckEntity sysDegreeCheckEntity = CommonMapper.convertToDto(degreeCheckRequest.getDegreeCheckObject(), SysDegreeCheckEntity.class);
        sysDegreeCheckEntity.setStudentPostId(commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(degreeCheckRequest.getStudentInfo().getStudentId(), degreeCheckRequest.getStudentInfo().getSpPostNumber()).getStudentPostId());
        try {
            degreeCheckRepository.save(sysDegreeCheckEntity);
        } catch (Exception e) {
            commonResponse.setFlag(false);
            return commonResponse;
        }
        commonResponse.setFlag(true);
        return commonResponse;
    }

    @Override
    public List<DegreeCheckResponse> getDegreeCheckTableDataByStudentIDAndPostNumber(String studentId, String spPostNumber) {

        SysStudentPostEntity sysStudentPostEntity = commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(studentId, spPostNumber);
        List<SysDegreeCheckEntity> sysDegreeCheckEntityList = degreeCheckRepository.getSysDegreeCheckEntitiesByStudentPostId(sysStudentPostEntity.getStudentPostId());
        List<DegreeCheckResponse> degreeCheckResponseList = new ArrayList<>();
        for (SysDegreeCheckEntity sysDegreeCheckEntity : sysDegreeCheckEntityList) {
            degreeCheckResponseList.add(CommonMapper.convertToDto(sysDegreeCheckEntity, DegreeCheckResponse.class));
        }
        return degreeCheckResponseList;
    }
}
