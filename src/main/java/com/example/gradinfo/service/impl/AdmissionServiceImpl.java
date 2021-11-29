package com.example.gradinfo.service.impl;

import com.example.gradinfo.bo.StudentGpaAndUnit;
import com.example.gradinfo.dto.request.AdmissionCourseRequest;
import com.example.gradinfo.dto.response.*;
import com.example.gradinfo.entity.*;
import com.example.gradinfo.repository.*;
import com.example.gradinfo.service.AdmissionService;
import com.example.gradinfo.service.CommonService;
import com.example.gradinfo.mapper.CommonMapper;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdmissionServiceImpl implements AdmissionService {

    private AdmissionCourseRepository admissionCourseRepository;
    private StudentRepository studentRepository;
    private StudentPostRepository studentPostRepository;
    private TransferCourseRepository transferCourseRepository;
    private CommonService commonService;
    private AdmissionHistoryRepository admissionHistoryRepository;

    public AdmissionServiceImpl(AdmissionCourseRepository admissionCourseRepository, StudentRepository studentRepository, StudentPostRepository studentPostRepository, TransferCourseRepository transferCourseRepository, CommonService commonService, AdmissionHistoryRepository admissionHistoryRepository) {
        this.admissionCourseRepository = admissionCourseRepository;
        this.studentRepository = studentRepository;
        this.studentPostRepository = studentPostRepository;
        this.transferCourseRepository = transferCourseRepository;
        this.commonService = commonService;
        this.admissionHistoryRepository = admissionHistoryRepository;
    }


    @Override
    public StudentPostResponse getStudentPostDataByStudentIDAndPostNumber(String studentId, String spPostNumber) {
        StudentPostResponse studentPostDto = new StudentPostResponse();
        SysStudentEntity sysStudentEntity = studentRepository.findSysStudentEntityBystudentId(studentId);
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
        String studentId = admissionCourseRequest.getStudentInfo().getStudentId();
        String spPostNumber = admissionCourseRequest.getStudentInfo().getSpPostNumber();
        SysStudentPostEntity sysStudentPostEntity = commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(studentId, spPostNumber);
        String studentPostId = sysStudentPostEntity.getStudentPostId();
        List<SysAdmissionCourseEntity> sysAdmissionCourseEntityList = admissionCourseRepository.getSysAdmissionCourseEntitiesByStudentPostId(studentPostId);
        List<SysTransferCourseEntity> sysTransferCourseEntityList = transferCourseRepository.getSysTransferCourseEntitiesByStudentPostId(studentPostId);
        List<String> reason = CheckAdmissionCourseAndReturnReason(admissionCourseRequest, sysAdmissionCourseEntityList, sysTransferCourseEntityList, sysStudentPostEntity);
        admissionCourseApplyResponse.setReasonList(reason);
        StudentGpaAndUnit studentGpaAndUnit = commonService.CalculateGpaAndUnit(admissionCourseRequest, sysAdmissionCourseEntityList, sysTransferCourseEntityList, sysStudentPostEntity);
        admissionCourseApplyResponse.setFlag(true);

        sysStudentPostEntity.setSpEarnunits(studentGpaAndUnit.getSpEarnunits());
        sysStudentPostEntity.setSpRgunits(studentGpaAndUnit.getSpRgunits());
        sysStudentPostEntity.setSpGpaApply(studentGpaAndUnit.getSpGpaApply());
        sysStudentPostEntity.setSpGpaAll(studentGpaAndUnit.getSpGpaAll());
        studentPostRepository.save(sysStudentPostEntity);
        if (reason.equals("")) {
            admissionCourseApplyResponse.setFlag(false);
        }

        return admissionCourseApplyResponse;
    }

    private List<String> CheckAdmissionCourseAndReturnReason(AdmissionCourseRequest admissionCourseRequest, List<SysAdmissionCourseEntity> sysAdmissionCourseEntityList, List<SysTransferCourseEntity> sysTransferCourseEntityList, SysStudentPostEntity sysStudentPostEntity) {
        // according to the fifth key point of API document, write this function
        List<String> reason = new ArrayList<>();
        int visitCourseScore = 0;
        for (SysAdmissionCourseEntity sysAdmissionCourseEntity: sysAdmissionCourseEntityList) {
            if(sysAdmissionCourseEntity.getAdCourseApplyStatus() == 1) {
                if(sysAdmissionCourseEntity.getAdCourseApplyCode().equals("X")){
                    String RestrictedCourseName = sysAdmissionCourseEntity.getAdCourseName();
                    reason.add(String.format("Restricted course %s cannot be applied",RestrictedCourseName));
                }
                if(sysAdmissionCourseEntity.getAdCourseApplyCode().equals("V")) {
                    visitCourseScore += sysAdmissionCourseEntity.getAdCourseUnits();
                }
            }
            if(visitCourseScore > 12){
                reason.add("Visitor course pass visit limit");
            }
        }
        for (SysTransferCourseEntity sysTransferCourseEntity:sysTransferCourseEntityList) {
            if (sysTransferCourseEntity.getTrCourseApplyStatus() == 1) {
                if (sysTransferCourseEntity.getTrCourseApplyCode().equals("X")) {
                    reason.add(String.format("Restricted course %s cannot be applied", sysTransferCourseEntity.getTrCourseName()));
                }
            }
        }
        return reason;
    }

}
