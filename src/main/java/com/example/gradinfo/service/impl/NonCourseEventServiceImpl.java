package com.example.gradinfo.service.impl;

import com.example.gradinfo.dto.response.ExamTableDataResponse;
import com.example.gradinfo.dto.response.NonCourseEventTableDataResponse;
import com.example.gradinfo.entity.SysExamCommitteeEntity;
import com.example.gradinfo.entity.SysNonCourseRelatedEventRecordEntity;
import com.example.gradinfo.entity.SysStudentPostEntity;
import com.example.gradinfo.mapper.CommonMapper;
import com.example.gradinfo.repository.ExamCommitteeRepository;
import com.example.gradinfo.repository.NonCourseEventRepository;
import com.example.gradinfo.service.CommonService;
import com.example.gradinfo.service.NonCourseEventService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NonCourseEventServiceImpl implements NonCourseEventService {

    private CommonService commonService;
    private NonCourseEventRepository nonCourseEventRepository;
    private ExamCommitteeRepository examCommitteeRepository;


    public NonCourseEventServiceImpl(CommonService commonService, NonCourseEventRepository nonCourseEventRepository, ExamCommitteeRepository examCommitteeRepository) {
        this.commonService = commonService;
        this.nonCourseEventRepository = nonCourseEventRepository;
        this.examCommitteeRepository = examCommitteeRepository;
    }

    @Override
    public List<NonCourseEventTableDataResponse> getNonCourseRelatedEventTableDataByIDAndPostNumber(String studentId, String spPostNumber) {
        SysStudentPostEntity sysStudentPostEntity = commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(studentId, spPostNumber);
        List<SysNonCourseRelatedEventRecordEntity> sysNonCourseRelatedEventRecordEntityList = nonCourseEventRepository.getSysNonCourseRelatedEventRecordEntitiesByStudentPostId(sysStudentPostEntity.getStudentPostId());
        List<NonCourseEventTableDataResponse> nonCourseEventTableDataList = new ArrayList<>();
        for (SysNonCourseRelatedEventRecordEntity sysNonCourseRelatedEventRecordEntity : sysNonCourseRelatedEventRecordEntityList) {
            nonCourseEventTableDataList.add(CommonMapper.convertToDto(sysNonCourseRelatedEventRecordEntity, NonCourseEventTableDataResponse.class));
        }
        return nonCourseEventTableDataList;
    }

    @Override
    public List<ExamTableDataResponse> getExamCommitteeTableDataByIDAndPostNumber(String studentId, String spPostNumber) {
        SysStudentPostEntity sysStudentPostEntity = commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(studentId, spPostNumber);
        List<SysExamCommitteeEntity> sysExamCommitteeEntityList = examCommitteeRepository.getSysExamCommitteeEntitiesByStudentPostId(sysStudentPostEntity.getStudentPostId());
        List<ExamTableDataResponse> examTableDataResponseList = new ArrayList<>();
        for (SysExamCommitteeEntity sysExamCommitteeEntity : sysExamCommitteeEntityList) {
            examTableDataResponseList.add(CommonMapper.convertToDto(sysExamCommitteeEntity, ExamTableDataResponse.class));
        }
        return examTableDataResponseList;
    }
}
