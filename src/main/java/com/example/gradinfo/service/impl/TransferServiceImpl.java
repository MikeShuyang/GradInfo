package com.example.gradinfo.service.impl;

import com.example.gradinfo.bo.StudentGpaAndUnit;
import com.example.gradinfo.dto.request.AdmissionCourseRequest;
import com.example.gradinfo.dto.request.TransferCourseRequest;
import com.example.gradinfo.dto.response.*;
import com.example.gradinfo.entity.SysAdmissionCourseEntity;
import com.example.gradinfo.entity.SysStudentPostEntity;
import com.example.gradinfo.entity.SysTransferCourseEntity;
import com.example.gradinfo.entity.SysTransferHistoryEntity;
import com.example.gradinfo.mapper.CommonMapper;
import com.example.gradinfo.repository.*;
import com.example.gradinfo.service.CommonService;
import com.example.gradinfo.service.TransferService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TransferServiceImpl implements TransferService {
    private final CommonService commonService;
    private final TransferCourseRepository transferCourseRepository;
    private final TransferHistoryRepository transferHistoryRepository;
    private final InstitutionRepository institutionRepository;
    private final BachelorDegreeRepository bachelorDegreeRepository;
    private final AdmissionCourseRepository admissionCourseRepository;

    public TransferServiceImpl(CommonService commonService, TransferCourseRepository transferCourseRepository, TransferHistoryRepository transferHistoryRepository, InstitutionRepository institutionRepository, BachelorDegreeRepository bachelorDegreeRepository, AdmissionCourseRepository admissionCourseRepository) {
        this.commonService = commonService;
        this.transferCourseRepository = transferCourseRepository;
        this.transferHistoryRepository = transferHistoryRepository;
        this.institutionRepository = institutionRepository;
        this.bachelorDegreeRepository = bachelorDegreeRepository;
        this.admissionCourseRepository = admissionCourseRepository;
    }


    @Override
    public List<TransferCourseResponse> getTransferCourseTableDataByIDAndPostNumber(String studentId, String spPostNumber) {
        String studentPostId = commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(studentId, spPostNumber).getStudentPostId();
        List<SysTransferCourseEntity> SysTransferCourseEntityList = transferCourseRepository.getSysTransferCourseEntitiesByStudentPostId(studentPostId);
        List<TransferCourseResponse> transferCourseResponseList = new ArrayList<>();

        for (SysTransferCourseEntity SysTransferCourseEntity : SysTransferCourseEntityList) {
            TransferCourseResponse transferCourseResponse;
            transferCourseResponse = CommonMapper.convertToDto(SysTransferCourseEntity, TransferCourseResponse.class);
            List<SysTransferHistoryEntity> sysTransferHistoryEntityList = transferHistoryRepository.getSysTrHistoryEntitiesByTrCourseId(transferCourseResponse.getTrCourseId());

            List<TransferCourseHistory> transferCourseHistoryList = new ArrayList<>();
            if (sysTransferHistoryEntityList != null && sysTransferHistoryEntityList.size() != 0) {
                for (SysTransferHistoryEntity sysTransferHistoryEntity : sysTransferHistoryEntityList) {
                    transferCourseHistoryList.add(CommonMapper.convertToDto(sysTransferHistoryEntity, TransferCourseHistory.class));
                }
            }
            transferCourseResponse.setTrCourseHistory(transferCourseHistoryList);

            transferCourseResponseList.add(transferCourseResponse);
        }

        return transferCourseResponseList;
    }

    @Override
    public List<TransferInstitution> getTransferInfoByIDAndPostNumber(String studentId, String spPostNumber) {
        String studentPostId = commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(studentId, spPostNumber).getStudentPostId();
        List<TransferInstitution> transferInstitutionList = new ArrayList<>();
        List<String> institutionIdList = new ArrayList<>();
        Set<String> set = new HashSet<>();

        for (SysTransferCourseEntity sysTransferCourseEntity: transferCourseRepository.getSysTransferCourseEntitiesByStudentPostId(studentPostId)) {
            institutionIdList.add(sysTransferCourseEntity.getInstitutionId());
        }
        for (int index = 0; index < institutionIdList.size(); index++) {

            if (set.add(institutionIdList.get(index))) {
                TransferInstitution transferInstitution = CommonMapper.convertToDto(institutionRepository.getSysInstitutionEntitiesByInstitutionId(institutionIdList.get(index)), TransferInstitution.class);
                transferInstitutionList.add(transferInstitution);
            }
        }
        return transferInstitutionList;
    }

    @Override
    public BachelorDegreeResponse getBachelorDegreeInfoByID(String studentId) {
        BachelorDegreeResponse bachelorDegreeResponse = CommonMapper.convertToDto(bachelorDegreeRepository.getSysStudentBachelorEntityByStudentId(studentId), BachelorDegreeResponse.class);
        return bachelorDegreeResponse;
    }

    @Override
    public TransferCourseApplyResponse postTransferCourseTableDataByNewArr(TransferCourseRequest transferCourseRequest) {
        TransferCourseApplyResponse transferCourseApplyResponse = new TransferCourseApplyResponse();
        String studentId = transferCourseRequest.getStudentInfo().getStudentId();
        String spPostNumber = transferCourseRequest.getStudentInfo().getSpPostNumber();
        SysStudentPostEntity sysStudentPostEntity = commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(studentId, spPostNumber);
        String studentPostId = sysStudentPostEntity.getStudentPostId();
        List<SysAdmissionCourseEntity> sysAdmissionCourseEntityList = admissionCourseRepository.getSysAdmissionCourseEntitiesByStudentPostId(studentPostId);
        List<SysTransferCourseEntity> sysTransferCourseEntityList = transferCourseRepository.getSysTransferCourseEntitiesByStudentPostId(studentPostId);
        return null;
    }

    private List<String> CheckAdmissionCourseAndReturnReason(AdmissionCourseRequest admissionCourseRequest, List<SysAdmissionCourseEntity> sysAdmissionCourseEntityList, List<SysTransferCourseEntity> sysTransferCourseEntityList, SysStudentPostEntity sysStudentPostEntity) {
        // according to the 6 key point of API document, write this function
        List<String> list = new ArrayList<>();
        return list;
    }
}
