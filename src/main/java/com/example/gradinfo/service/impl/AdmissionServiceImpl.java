package com.example.gradinfo.service.impl;

import com.example.gradinfo.bo.StudentGpaAndUnit;
import com.example.gradinfo.dto.request.AdmissionCourseRequest;
import com.example.gradinfo.dto.response.*;
import com.example.gradinfo.entity.*;
import com.example.gradinfo.repository.*;
import com.example.gradinfo.service.AdmissionService;
import com.example.gradinfo.service.CommonService;
import com.example.gradinfo.mapper.CommonMapper;
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
    public AdmissionCourseListResponse getAdmissionCourseDataByStudentIDAndPostNumber(String studentId, String spPostNumber) {
        String studentPostId = commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(studentId, spPostNumber).getStudentPostId();
        AdmissionCourseListResponse admissionCourseListResponse = new AdmissionCourseListResponse();
        List<SysAdmissionCourseEntity> sysAdmissionCourseEntityList = admissionCourseRepository.getSysAdmissionCourseEntitiesByStudentPostId(studentPostId);
        List<AdmissionCourseResponse> admissionCourseResponseList = new ArrayList<>();

        for (SysAdmissionCourseEntity sysAdmissionCourseEntity : sysAdmissionCourseEntityList) {
            AdmissionCourseResponse admissionCourseResponse;
            admissionCourseResponse = CommonMapper.convertToDto(sysAdmissionCourseEntity, AdmissionCourseResponse.class);
            List<SysAdHistoryEntity> sysAdHistoryEntityList = admissionHistoryRepository.getSysAdHistoryEntitiesByAdCourseId(admissionCourseResponse.getAdCourseId());
            System.out.println(sysAdHistoryEntityList.size());

            List<AdmissionCourseHistory> admissionCourseHistoryList = new ArrayList<>();
            if (sysAdHistoryEntityList != null && sysAdHistoryEntityList.size() != 0) {
                for (SysAdHistoryEntity sysAdHistoryEntity : sysAdHistoryEntityList) {
                    admissionCourseHistoryList.add(CommonMapper.convertToDto(sysAdHistoryEntity, AdmissionCourseHistory.class));
                }
            }
            admissionCourseResponse.setAdCourseHistory(admissionCourseHistoryList);

            admissionCourseResponseList.add(admissionCourseResponse);
        }

        admissionCourseListResponse.setAdmissionCourseList(admissionCourseResponseList);
        return admissionCourseListResponse;
    }

    @Override
    public AdmissionCourseApplyResponse getPostAdmissionCourseTableDataByNewArr(AdmissionCourseRequest admissionCourseRequest) {
        String studentId = admissionCourseRequest.getStudentInfo().getStudentId();
        String spPostNumber = admissionCourseRequest.getStudentInfo().getSpPostNumber();
        SysStudentPostEntity sysStudentPostEntity = commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(studentId, spPostNumber);
        String studentPostId = sysStudentPostEntity.getStudentPostId();
        List<SysAdmissionCourseEntity> sysAdmissionCourseEntityList = admissionCourseRepository.getSysAdmissionCourseEntitiesByStudentPostId(studentPostId);
        List<SysTransferCourseEntity> sysTransferCourseEntityList = transferCourseRepository.getSysTransferCourseEntitiesByStudentPostId(studentPostId);

        return null;
    }

    private String CheckAdmissionCourseAndReturnReason(AdmissionCourseRequest admissionCourseRequest, List<SysAdmissionCourseEntity> sysAdmissionCourseEntityList, List<SysTransferCourseEntity> sysTransferCourseEntityList, SysStudentPostEntity sysStudentPostEntity) {
        // according to the fifth key point of API document, write this function;
        int visitCourseScore = 0;
        for (SysAdmissionCourseEntity sysAdmissionCourseEntity: sysAdmissionCourseEntityList) {
            if(sysAdmissionCourseEntity.getAdCourseApplyStatus() == 1) {
                if(sysAdmissionCourseEntity.getAdCourseApplyCode().equals("X")){
                    String RestrictedCourseName = sysAdmissionCourseEntity.getAdCourseName();
                    return String.format("Restricted course %s cannot be applied",RestrictedCourseName);
                }
                if(sysAdmissionCourseEntity.getAdCourseApplyCode().equals("V")) {
                    visitCourseScore += sysAdmissionCourseEntity.getAdCourseUnits();
                }
            }
            if(visitCourseScore > 12){
                return "Visitor course pass visit limit";
            }
        }
        for (SysTransferCourseEntity sysTransferCourseEntity:sysTransferCourseEntityList) {
            if (sysTransferCourseEntity.getTrCourseApplyStatus() == 1) {
                if (sysTransferCourseEntity.getTrCourseApplyCode().equals("X")) {
                    return String.format("Restricted course %s cannot be applied", sysTransferCourseEntity.getTrCourseName());
                }
            }
        }
        return "";
    }

    private StudentGpaAndUnit CalculateGpaAndUnit(AdmissionCourseRequest admissionCourseRequest, List<SysAdmissionCourseEntity> sysAdmissionCourseEntityList, List<SysTransferCourseEntity> sysTransferCourseEntityList, SysStudentPostEntity sysStudentPostEntity) {
        // according to the second key point of API document, write this function
        StudentGpaAndUnit studentGpaAndUnit = new StudentGpaAndUnit();
        Double AppliedGPTs = 0.0 ,TotalGPTs = 0.0, AppliedUnits = 0.0, TotalUnits = 0.0, RGUnits = 0.0;
        for (SysAdmissionCourseEntity sysAdmissionCourseEntity: sysAdmissionCourseEntityList) {
            if(sysAdmissionCourseEntity.getAdCourseGrade().equals("RG")){
                if(sysAdmissionCourseEntity.getAdCourseApplyStatus() == 1){
                    RGUnits += sysAdmissionCourseEntity.getAdCourseUnits();
                }
                continue;
            }
            TotalGPTs += sysAdmissionCourseEntity.getAdCourseGpts();
            TotalUnits += sysAdmissionCourseEntity.getAdCourseUnits();
            if(sysAdmissionCourseEntity.getAdCourseApplyStatus() == 1){
                AppliedGPTs += sysAdmissionCourseEntity.getAdCourseGpts();
                AppliedUnits += sysAdmissionCourseEntity.getAdCourseUnits();
            }
        }
        for (SysTransferCourseEntity sysTransferCourseEntity:sysTransferCourseEntityList){
            if(sysTransferCourseEntity.getTrCourseApplyStatus() == 1){
                AppliedGPTs += sysTransferCourseEntity.getTrCourseGpts();
                AppliedUnits += sysTransferCourseEntity.getTrCourseUnits();
            }
        }
        double SpGpaAll = (double) (Math.round( (TotalGPTs/TotalUnits) *100) / 100);
        double SpGpaApply = (double) (Math.round((AppliedGPTs/AppliedUnits)*100) / 100);
        studentGpaAndUnit.setSpGpaAll(SpGpaAll);
        studentGpaAndUnit.setSpGpaApply(SpGpaApply);
        studentGpaAndUnit.setSpRgunits(RGUnits);
        studentGpaAndUnit.setSpEarnunits(AppliedUnits);
        return studentGpaAndUnit;
    }
}
