package com.example.gradinfo.service.impl;

import com.example.gradinfo.dto.request.AdmissionCourseRequest;
import com.example.gradinfo.dto.request.Course;
import com.example.gradinfo.dto.response.*;
import com.example.gradinfo.entity.*;
import com.example.gradinfo.repository.*;
import com.example.gradinfo.service.AdmissionService;
import com.example.gradinfo.service.CommonService;
import com.example.gradinfo.mapper.CommonMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdmissionServiceImpl implements AdmissionService {

    private AdmissionCourseRepository admissionCourseRepository;
    private StudentRepository studentRepository;
    private StudentPostRepository studentPostRepository;
    private TransferCourseRepository transferCourseRepository;
    private CommonService commonService;
    private AdmissionHistoryRepository admissionHistoryRepository;
    private TransferHistoryRepository transferHistoryRepository;

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
        SysStudentPostEntity sysStudentPostEntity = commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(admissionCourseRequest.getStudentInfo().getStudentId(), admissionCourseRequest.getStudentInfo().getSpPostNumber());
        String studentPostId = sysStudentPostEntity.getStudentPostId();
        List<SysAdmissionCourseEntity> sysAdmissionCourseEntityList = admissionCourseRepository.getSysAdmissionCourseEntitiesByStudentPostId(studentPostId);
        List<SysTransferCourseEntity> sysTransferCourseEntityList = transferCourseRepository.getSysTransferCourseEntitiesByStudentPostId(studentPostId);
        List<String> reason = CheckAdmissionCourseAndReturnReason(admissionCourseRequest, sysAdmissionCourseEntityList, sysTransferCourseEntityList, sysStudentPostEntity);

        admissionCourseApplyResponse.setReasonList(reason);
        if (reason.size() != 0) {
            admissionCourseApplyResponse.setFlag(false);
            return admissionCourseApplyResponse;
        }

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
        commonService.calculateGpaAndUnit(studentPostId, sysStudentPostEntity, sysAdmissionCourseEntityList);

        return admissionCourseApplyResponse;
    }

    private List<String> CheckAdmissionCourseAndReturnReason(AdmissionCourseRequest admissionCourseRequest, List<SysAdmissionCourseEntity> sysAdmissionCourseEntityList, List<SysTransferCourseEntity> sysTransferCourseEntityList, SysStudentPostEntity sysStudentPostEntity) {
        // according to the fifth key point of API document, write this function
        List<String> reason = new ArrayList<>();
        if (admissionCourseRequest.getCourseList().size() == 0) {
            reason.add(String.format("no selection"));
        }

        int visitCourseScore = 0;
        for (SysAdmissionCourseEntity sysAdmissionCourseEntity: sysAdmissionCourseEntityList) {
            if(sysAdmissionCourseEntity.getAdCourseApplyStatus() == 1) {
                if(sysAdmissionCourseEntity.getAdCourseApplyCode().equals("X")){
                    String RestrictedCourseName = sysAdmissionCourseEntity.getAdCourseName();
                    reason.add(String.format("Restricted course %s cannot be applied", RestrictedCourseName));
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
