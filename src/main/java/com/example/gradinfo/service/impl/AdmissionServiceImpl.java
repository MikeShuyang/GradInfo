package com.example.gradinfo.service.impl;

import com.example.gradinfo.dto.request.AdmissionCourseRequest;
import com.example.gradinfo.dto.request.Course;
import com.example.gradinfo.dto.response.AdmissionCourseApplyResponse;
import com.example.gradinfo.dto.response.AdmissionCourseHistory;
import com.example.gradinfo.dto.response.AdmissionCourseResponse;
import com.example.gradinfo.dto.response.StudentPostResponse;
import com.example.gradinfo.entity.*;
import com.example.gradinfo.mapper.CommonMapper;
import com.example.gradinfo.repository.*;
import com.example.gradinfo.service.AdmissionService;
import com.example.gradinfo.service.CommonService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdmissionServiceImpl implements AdmissionService {

    private final AdmissionCourseRepository admissionCourseRepository;
    private final StudentRepository studentRepository;
    private final StudentPostRepository studentPostRepository;
    private final TransferCourseRepository transferCourseRepository;
    private final CommonService commonService;
    private final AdmissionHistoryRepository admissionHistoryRepository;
    private final TransferHistoryRepository transferHistoryRepository;

    public AdmissionServiceImpl(AdmissionCourseRepository admissionCourseRepository, StudentRepository studentRepository, StudentPostRepository studentPostRepository, TransferCourseRepository transferCourseRepository, CommonService commonService, AdmissionHistoryRepository admissionHistoryRepository, TransferHistoryRepository transferHistoryRepository) {
        this.admissionCourseRepository = admissionCourseRepository;
        this.studentRepository = studentRepository;
        this.studentPostRepository = studentPostRepository;
        this.transferCourseRepository = transferCourseRepository;
        this.commonService = commonService;
        this.admissionHistoryRepository = admissionHistoryRepository;
        this.transferHistoryRepository = transferHistoryRepository;
    }


    @Override
    public StudentPostResponse getStudentPostDataByStudentIDAndPostNumber(String studentId, String spPostNumber) {
        // StudentPostResponse studentPostDto = new StudentPostResponse();
        // SysStudentEntity sysStudentEntity = studentRepository.findSysStudentEntityBystudentId(studentId);
        SysStudentPostEntity sysStudentPostEntity = commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(studentId, spPostNumber);

        return CommonMapper.convertToDto(sysStudentPostEntity, StudentPostResponse.class);
    }

    @Override
    public List<AdmissionCourseResponse> getAdmissionCourseDataByStudentIDAndPostNumber(String studentId, String spPostNumber) {
        String studentPostId = commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(studentId, spPostNumber).getStudentPostId();
        List<SysAdmissionCourseEntity> sysAdmissionCourseEntityList = admissionCourseRepository.getSysAdmissionCourseEntitiesByStudentPostId(studentPostId);
        List<AdmissionCourseResponse> admissionCourseResponseList = new ArrayList<>();

        for (SysAdmissionCourseEntity sysAdmissionCourseEntity : sysAdmissionCourseEntityList) {
            AdmissionCourseResponse admissionCourseResponse;
            admissionCourseResponse = CommonMapper.convertToDto(sysAdmissionCourseEntity, AdmissionCourseResponse.class);
            List<SysAdHistoryEntity> sysAdHistoryEntityList = admissionHistoryRepository.getSysAdHistoryEntitiesByAdCourseId(admissionCourseResponse.getAdCourseId());

            List<AdmissionCourseHistory> admissionCourseHistoryList = new ArrayList<>();
            if (sysAdHistoryEntityList != null && sysAdHistoryEntityList.size() != 0) {
                for (SysAdHistoryEntity sysAdHistoryEntity : sysAdHistoryEntityList) {
                    admissionCourseHistoryList.add(CommonMapper.convertToDto(sysAdHistoryEntity, AdmissionCourseHistory.class));
                }
            }
            admissionCourseResponse.setAdCourseHistory(admissionCourseHistoryList);

            admissionCourseResponseList.add(admissionCourseResponse);
        }

        return admissionCourseResponseList;
    }

    @Override
    public AdmissionCourseApplyResponse getPostAdmissionCourseTableDataByNewArr(AdmissionCourseRequest admissionCourseRequest) {
        AdmissionCourseApplyResponse admissionCourseApplyResponse = new AdmissionCourseApplyResponse();
        SysStudentPostEntity sysStudentPostEntity = commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(admissionCourseRequest.getStudentInfo().getStudentId(), admissionCourseRequest.getStudentInfo().getSpPostNumber());
        String studentPostId = sysStudentPostEntity.getStudentPostId();
        List<SysAdmissionCourseEntity> sysAdmissionCourseEntityList = admissionCourseRepository.getSysAdmissionCourseEntitiesByStudentPostId(studentPostId);
        List<SysTransferCourseEntity> sysTransferCourseEntityList = transferCourseRepository.getSysTransferCourseEntitiesByStudentPostId(studentPostId);
        List<String> list = new ArrayList<>();
        for (Course course : admissionCourseRequest.getCourseList()) {
            list.add(course.getCourseId());

        }

        List<SysAdmissionCourseEntity> sysAdmissionCourseEntityListForReason = admissionCourseRepository.getSysAdmissionCourseEntitiesByStudentPostIdAndAdCourseIdIsIn(studentPostId, list);
        List<SysTransferCourseEntity> sysTransferCourseEntityListForReason = transferCourseRepository.getSysTransferCourseEntitiesByStudentPostIdAndTrCourseIdIsIn(studentPostId, list);
        List<String> reason = commonService.checkReason(sysAdmissionCourseEntityListForReason, sysTransferCourseEntityListForReason);
        System.out.println("start111");
        if (reason.size() != 0) {
            admissionCourseApplyResponse.setFlag(false);
            admissionCourseApplyResponse.setReasonList(reason);
            return admissionCourseApplyResponse;
        }
        System.out.println("start222");
        admissionCourseApplyResponse.setFlag(true);
        Map<String, Course> map = new HashMap<>();

        for (int index = 0; index < admissionCourseRequest.getCourseList().size(); index++) {
            map.put(admissionCourseRequest.getCourseList().get(index).getCourseId(), admissionCourseRequest.getCourseList().get(index));
        }

        for (SysAdmissionCourseEntity sysAdmissionCourseEntity :sysAdmissionCourseEntityList) {
            String courseId = sysAdmissionCourseEntity.getAdCourseId();
            SysAdHistoryEntity sysAdHistoryEntity = new SysAdHistoryEntity();
            if (map.containsKey(courseId)) {
                if (sysAdmissionCourseEntity.getAdCourseApplyStatus() == 0) {
                    sysAdHistoryEntity.setAdCourseId(courseId);
                    sysAdHistoryEntity.setAdHistoryCourseName(sysAdmissionCourseEntity.getAdCourseName());
                    sysAdHistoryEntity.setAdHistoryCourseOper(admissionCourseRequest.getUserInfo().getUserOper());
                    sysAdHistoryEntity.setAdHistoryCourseApplyStatus(Byte.valueOf("1"));
                    sysAdHistoryEntity.setAdHistoryCourseTransdate(admissionCourseRequest.getUserInfo().getTransDate());
                    admissionHistoryRepository.save(sysAdHistoryEntity);
                }
                sysAdmissionCourseEntity.setAdCourseApplyStatus(Byte.valueOf("1"));
                sysAdmissionCourseEntity.setAdCourseTransdate(admissionCourseRequest.getUserInfo().getTransDate());
                sysAdmissionCourseEntity.setAdCourseOper(admissionCourseRequest.getUserInfo().getUserOper());
            } else {
                if (sysAdmissionCourseEntity.getAdCourseApplyStatus() != 0) {
                    sysAdmissionCourseEntity.setAdCourseTransdate(admissionCourseRequest.getUserInfo().getTransDate());
                    sysAdmissionCourseEntity.setAdCourseOper(admissionCourseRequest.getUserInfo().getUserOper());
                    sysAdmissionCourseEntity.setAdCourseApplyStatus(Byte.valueOf("0"));
                    sysAdHistoryEntity.setAdCourseId(courseId);
                    sysAdHistoryEntity.setAdHistoryCourseName(sysAdmissionCourseEntity.getAdCourseName());
                    sysAdHistoryEntity.setAdHistoryCourseOper(admissionCourseRequest.getUserInfo().getUserOper());
                    sysAdHistoryEntity.setAdHistoryCourseApplyStatus(Byte.valueOf("0"));
                    sysAdHistoryEntity.setAdHistoryCourseTransdate(admissionCourseRequest.getUserInfo().getTransDate());
                    admissionHistoryRepository.save(sysAdHistoryEntity);
                }
            }
            admissionCourseRepository.save(sysAdmissionCourseEntity);
        }
        sysAdmissionCourseEntityList = admissionCourseRepository.getSysAdmissionCourseEntitiesByStudentPostId(studentPostId);
        sysTransferCourseEntityList = transferCourseRepository.getSysTransferCourseEntitiesByStudentPostId(studentPostId);


        reason = commonService.calculateGpaAndUnit(studentPostId, sysStudentPostEntity, sysAdmissionCourseEntityList, sysTransferCourseEntityList);

        admissionCourseApplyResponse.setReasonList(reason);
        if (reason.size() != 0) {
            admissionCourseApplyResponse.setFlag(false);
            return admissionCourseApplyResponse;
        }

        return admissionCourseApplyResponse;
    }



}
