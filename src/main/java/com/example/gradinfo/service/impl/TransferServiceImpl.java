package com.example.gradinfo.service.impl;

import com.example.gradinfo.dto.response.*;
import com.example.gradinfo.entity.SysTransferCourseEntity;
import com.example.gradinfo.entity.SysTransferHistoryEntity;
import com.example.gradinfo.mapper.CommonMapper;
import com.example.gradinfo.repository.BachelorDegreeRepository;
import com.example.gradinfo.repository.InstitutionRepository;
import com.example.gradinfo.repository.TransferCourseRepository;
import com.example.gradinfo.repository.TransferHistoryRepository;
import com.example.gradinfo.service.CommonService;
import com.example.gradinfo.service.TransferService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransferServiceImpl implements TransferService {
    private final CommonService commonService;
    private final TransferCourseRepository transferCourseRepository;
    private final TransferHistoryRepository transferHistoryRepository;
    private final InstitutionRepository institutionRepository;
    private final BachelorDegreeRepository bachelorDegreeRepository;

    public TransferServiceImpl(CommonService commonService, TransferCourseRepository transferCourseRepository, TransferHistoryRepository transferHistoryRepository, InstitutionRepository institutionRepository, BachelorDegreeRepository bachelorDegreeRepository) {
        this.commonService = commonService;
        this.transferCourseRepository = transferCourseRepository;
        this.transferHistoryRepository = transferHistoryRepository;
        this.institutionRepository = institutionRepository;
        this.bachelorDegreeRepository = bachelorDegreeRepository;
    }


    @Override
    public TransferCourseListResponse getTransferCourseTableDataByIDAndPostNumber(String studentId, String spPostNumber) {
        String studentPostId = commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(studentId, spPostNumber).getStudentPostId();
        TransferCourseListResponse TransferCourseListResponse = new TransferCourseListResponse();
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

        TransferCourseListResponse.setTransferCourseResponseList(transferCourseResponseList);
        return TransferCourseListResponse;
    }

    @Override
    public TransferInstitutionListResponse getTransferInfoByIDAndPostNumber(String studentId, String spPostNumber) {
        String studentPostId = commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(studentId, spPostNumber).getStudentPostId();
        List<TransferInstitution> transferInstitutionList = new ArrayList<>();
        TransferInstitutionListResponse transferInstitutionListResponse = new TransferInstitutionListResponse();
        List<String> institutionIdList = new ArrayList<>();

        for (SysTransferCourseEntity sysTransferCourseEntity: transferCourseRepository.getSysTransferCourseEntitiesByStudentPostId(studentPostId)) {
            institutionIdList.add(sysTransferCourseEntity.getInstitutionId());
        }
        for (int index = 0; index < institutionIdList.size(); index++) {
            TransferInstitution transferInstitution = CommonMapper.convertToDto(institutionRepository.getSysInstitutionEntitiesByInstitutionId(institutionIdList.get(index)), TransferInstitution.class);
            transferInstitutionList.add(transferInstitution);
        }
        transferInstitutionListResponse.setTransferInstitutionList(transferInstitutionList);
        return transferInstitutionListResponse;
    }

    @Override
    public BachelorDegreeResponse getBachelorDegreeInfoByID(String studentId) {
        BachelorDegreeResponse bachelorDegreeResponse = CommonMapper.convertToDto(bachelorDegreeRepository.getSysStudentBachelorEntityByStudentId(studentId), BachelorDegreeResponse.class);
        return bachelorDegreeResponse;
    }
}
