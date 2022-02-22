package com.example.gradinfo.service.impl;

import com.example.gradinfo.dto.request.ExamCommitteeRequest;
import com.example.gradinfo.dto.request.NonCourseEventRequest;
import com.example.gradinfo.dto.request.ThesisCommitteeRequest;
import com.example.gradinfo.dto.response.*;
import com.example.gradinfo.entity.*;
import com.example.gradinfo.mapper.CommonMapper;
import com.example.gradinfo.repository.EventRepository;
import com.example.gradinfo.repository.ExamCommitteeRepository;
import com.example.gradinfo.repository.NonCourseEventRepository;
import com.example.gradinfo.repository.ThesisCommitteeRepository;
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
    private ThesisCommitteeRepository thesisCommitteeRepository;
    private EventRepository eventRepository;


    public NonCourseEventServiceImpl(CommonService commonService, NonCourseEventRepository nonCourseEventRepository, ExamCommitteeRepository examCommitteeRepository, ThesisCommitteeRepository thesisCommitteeRepository, EventRepository eventRepository) {
        this.commonService = commonService;
        this.nonCourseEventRepository = nonCourseEventRepository;
        this.examCommitteeRepository = examCommitteeRepository;
        this.thesisCommitteeRepository = thesisCommitteeRepository;
        this.eventRepository = eventRepository;
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

    @Override
    public List<ThesisCommitteeResponse> getThesisCommitteeTableDataByIDAndPostNumber(String studentId, String spPostNumber) {
        SysStudentPostEntity sysStudentPostEntity = commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(studentId, spPostNumber);
        List<SysThesisCommitteeEntity> sysThesisCommitteeEntityList = thesisCommitteeRepository.getSysThesisCommitteeEntitiesByStudentPostId(sysStudentPostEntity.getStudentPostId());
        List<ThesisCommitteeResponse> thesisCommitteeResponseList = new ArrayList<>();
        for (SysThesisCommitteeEntity sysThesisCommitteeEntity : sysThesisCommitteeEntityList) {
            thesisCommitteeResponseList.add(CommonMapper.convertToDto(sysThesisCommitteeEntity, ThesisCommitteeResponse.class));
        }
        return thesisCommitteeResponseList;
    }

    @Override
    public NonCourseEventApplyResponse postNonCourseRelatedEventTableDataByEventObj(NonCourseEventRequest nonCourseEventRequest) {
        SysStudentPostEntity sysStudentPostEntity = commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(nonCourseEventRequest.getStudentInfo().getStudentId(), nonCourseEventRequest.getStudentInfo().getSpPostNumber());
        SysEventEntity sysEventEntity = eventRepository.getSysEventEntityByEventCodeAndAndEventDescription(nonCourseEventRequest.getEventObject().getEventCode(), nonCourseEventRequest.getEventObject().getEventDescription());
        NonCourseEventApplyResponse nonCourseEventApplyResponse = new NonCourseEventApplyResponse();
        try {
            SysNonCourseRelatedEventRecordEntity sysNonCourseRelatedEventRecordEntity = CommonMapper.convertToDto(nonCourseEventRequest.getEventObject(), SysNonCourseRelatedEventRecordEntity.class);
            sysNonCourseRelatedEventRecordEntity.setEventId(sysEventEntity.getEventId());
            sysNonCourseRelatedEventRecordEntity.setStudentPostId(sysStudentPostEntity.getStudentPostId());
            nonCourseEventRepository.save(sysNonCourseRelatedEventRecordEntity);
        } catch (Exception e) {
            nonCourseEventApplyResponse.setFlag(false);
            String str = "Fail to add the data";
            nonCourseEventApplyResponse.getReasonList().add(str);
            return nonCourseEventApplyResponse;
        }
        nonCourseEventApplyResponse.setFlag(true);

        return nonCourseEventApplyResponse;
    }

    @Override
    public CommonResponse postExamCommitteeTableDataByCommitteeObj(ExamCommitteeRequest examCommitteeRequest) {
        SysExamCommitteeEntity sysExamCommitteeEntity = CommonMapper.convertToDto(examCommitteeRequest.getExamCommitteeObject(), SysExamCommitteeEntity.class);
        CommonResponse commonResponse = new CommonResponse();
        try {
            sysExamCommitteeEntity.setStudentPostId(commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(examCommitteeRequest.getStudentInfo().getStudentId(), examCommitteeRequest.getStudentInfo().getSpPostNumber()).getStudentPostId());
            examCommitteeRepository.save(sysExamCommitteeEntity);
        } catch (Exception e) {
            commonResponse.setFlag(false);
            return commonResponse;
        }
        commonResponse.setFlag(true);
        return commonResponse;
    }

    @Override
    public CommonResponse postThesisCommitteeTableDataByCommitteeObj(ThesisCommitteeRequest thesisCommitteeRequest) {
        SysThesisCommitteeEntity sysThesisCommitteeEntity = CommonMapper.convertToDto(thesisCommitteeRequest.getThesisCommitteeObject(), SysThesisCommitteeEntity.class);
        CommonResponse commonResponse = new CommonResponse();
        try {
            sysThesisCommitteeEntity.setStudentPostId(commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(thesisCommitteeRequest.getStudentInfo().getStudentId(), thesisCommitteeRequest.getStudentInfo().getSpPostNumber()).getStudentPostId());
            thesisCommitteeRepository.save(sysThesisCommitteeEntity);
        } catch (Exception e) {
            commonResponse.setFlag(false);
            return commonResponse;
        }
        commonResponse.setFlag(true);
        return commonResponse;
    }
}
