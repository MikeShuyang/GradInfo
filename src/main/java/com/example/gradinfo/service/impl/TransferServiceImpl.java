package com.example.gradinfo.service.impl;

import com.example.gradinfo.dto.request.Course;
import com.example.gradinfo.dto.request.TransferCourseRequest;
import com.example.gradinfo.dto.response.*;
import com.example.gradinfo.entity.*;
import com.example.gradinfo.mapper.CommonMapper;
import com.example.gradinfo.repository.*;
import com.example.gradinfo.service.CommonService;
import com.example.gradinfo.service.TransferService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TransferServiceImpl implements TransferService {
    private final CommonService commonService;
    private final TransferCourseRepository transferCourseRepository;
    private final TransferHistoryRepository transferHistoryRepository;
    private final InstitutionRepository institutionRepository;
    private final BachelorDegreeRepository bachelorDegreeRepository;
    private final AdmissionCourseRepository admissionCourseRepository;

    public TransferServiceImpl(CommonService commonService, TransferCourseRepository transferCourseRepository, TransferHistoryRepository transferHistoryRepository, InstitutionRepository institutionRepository, BachelorDegreeRepository bachelorDegreeRepository, AdmissionCourseRepository admissionCourseRepository, StudentPostRepository studentPostRepository) {
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
        BachelorDegreeResponse bachelorDegreeResponse = new BachelorDegreeResponse();
        List<SysStudentBachelorEntity> bachelorEntityList = bachelorDegreeRepository.getSysStudentBachelorEntitiesByStudentId(studentId);
        List<BachelorDegree> bachelorDegreeList = new ArrayList<>();
        for (SysStudentBachelorEntity sysStudentBachelorEntity : bachelorEntityList) {
            BachelorDegree bachelorDegree = CommonMapper.convertToDto(sysStudentBachelorEntity, BachelorDegree.class);
            bachelorDegreeList.add(bachelorDegree);
        }
        bachelorDegreeResponse.setBachelorDegreeList(bachelorDegreeList);

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
        List<String> list = new ArrayList<>();
        for (Course course : transferCourseRequest.getCourseList()) {
            list.add(course.getCourseId());
        }
        List<SysAdmissionCourseEntity> sysAdmissionCourseEntityListForReason = admissionCourseRepository.getSysAdmissionCourseEntitiesByStudentPostIdAndAdCourseIdIsIn(studentPostId, list);
        List<SysTransferCourseEntity> sysTransferCourseEntityListForReason = transferCourseRepository.getSysTransferCourseEntitiesByStudentPostIdAndTrCourseIdIsIn(studentPostId, list);
        List<String> reason = commonService.checkReason(sysAdmissionCourseEntityListForReason, sysTransferCourseEntityListForReason);

        try {
            if (reason.size() != 0) {
                transferCourseApplyResponse.setFlag(false);
                transferCourseApplyResponse.setReasonList(reason);
                return transferCourseApplyResponse;
            }

            transferCourseApplyResponse.setFlag(true);
            Map<String, Course> map = new HashMap<>();

            for (int index = 0; index < transferCourseRequest.getCourseList().size(); index++) {
                map.put(transferCourseRequest.getCourseList().get(index).getCourseId(), transferCourseRequest.getCourseList().get(index));
            }

            List<SysTransferHistoryEntity> sysTransferHistoryEntityList = new ArrayList<>();
            List<SysTransferCourseEntity> sysTransferCourseEntityArrayList = new ArrayList<>();
            for (SysTransferCourseEntity sysTransferCourseEntity : sysTransferCourseEntityList) {
                String courseId = sysTransferCourseEntity.getTrCourseId();
                SysTransferHistoryEntity sysTransferHistoryEntity = new SysTransferHistoryEntity();


                if (map.containsKey(courseId)) {
                    if (sysTransferCourseEntity.getTrCourseApplyStatus() == 0) {
                        sysTransferHistoryEntity.setTrCourseId(courseId);
                        sysTransferHistoryEntity.setTrHistoryCourseName(sysTransferCourseEntity.getTrCourseName());
                        sysTransferHistoryEntity.setTrHistoryCourseOper(transferCourseRequest.getUserInfo().getUserOper());
                        sysTransferHistoryEntity.setTrHistoryCourseApplyStatus(Byte.valueOf("1"));
                        sysTransferHistoryEntity.setTrHistoryCourseTransdate(transferCourseRequest.getUserInfo().getTransDate());
                        sysTransferHistoryEntityList.add(sysTransferHistoryEntity);
                    }
                    sysTransferCourseEntity.setTrCourseApplyStatus(Byte.valueOf("1"));
                    sysTransferCourseEntity.setTrCourseTransdate(transferCourseRequest.getUserInfo().getTransDate());
                    sysTransferCourseEntity.setTrCourseOper(transferCourseRequest.getUserInfo().getUserOper());

                } else {
                    if (sysTransferCourseEntity.getTrCourseApplyStatus() != 0) {
                        sysTransferCourseEntity.setTrCourseTransdate(transferCourseRequest.getUserInfo().getTransDate());
                        sysTransferCourseEntity.setTrCourseOper(transferCourseRequest.getUserInfo().getUserOper());
                        sysTransferCourseEntity.setTrCourseApplyStatus(Byte.valueOf("0"));
                        sysTransferHistoryEntity.setTrCourseId(courseId);
                        sysTransferHistoryEntity.setTrHistoryCourseName(sysTransferCourseEntity.getTrCourseName());
                        sysTransferHistoryEntity.setTrHistoryCourseOper(transferCourseRequest.getUserInfo().getUserOper());
                        sysTransferHistoryEntity.setTrHistoryCourseApplyStatus(Byte.valueOf("0"));
                        sysTransferHistoryEntity.setTrHistoryCourseTransdate(transferCourseRequest.getUserInfo().getTransDate());
                        sysTransferHistoryEntityList.add(sysTransferHistoryEntity);

                    }
                }
                sysTransferCourseEntityArrayList.add(sysTransferCourseEntity);

            }
            transferHistoryRepository.saveAllAndFlush(sysTransferHistoryEntityList);
            transferCourseRepository.saveAllAndFlush(sysTransferCourseEntityArrayList);

            sysAdmissionCourseEntityList = admissionCourseRepository.getSysAdmissionCourseEntitiesByStudentPostId(studentPostId);
            sysTransferCourseEntityList = transferCourseRepository.getSysTransferCourseEntitiesByStudentPostId(studentPostId);


            reason = commonService.calculateGpaAndUnit(studentPostId, sysStudentPostEntity, sysAdmissionCourseEntityList, sysTransferCourseEntityList);

            transferCourseApplyResponse.setReasonList(reason);
            if (reason.size() != 0) {
                transferCourseApplyResponse.setFlag(false);
                return transferCourseApplyResponse;
            }
        } catch (Exception e) {
            transferCourseApplyResponse.setFlag(false);
            return transferCourseApplyResponse;
        }


        return transferCourseApplyResponse;
    }


}
