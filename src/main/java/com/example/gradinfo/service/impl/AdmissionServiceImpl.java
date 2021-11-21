package com.example.gradinfo.service.impl;

import com.example.gradinfo.dto.AdmissionCourseDto;
import com.example.gradinfo.dto.AdmissionCourseHistoryDto;
import com.example.gradinfo.dto.AdmissionCourseListDto;
import com.example.gradinfo.dto.StudentPostDto;
import com.example.gradinfo.entity.SysAdHistoryEntity;
import com.example.gradinfo.entity.SysAdmissionCourseEntity;
import com.example.gradinfo.entity.SysStudentEntity;
import com.example.gradinfo.entity.SysStudentPostEntity;
import com.example.gradinfo.repository.*;
import com.example.gradinfo.service.AdmissionService;
import com.example.gradinfo.service.CommonService;
import com.example.gradinfo.tool.DtoTools;
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
    public StudentPostDto getStudentPostDataByStudentIDAndPostNumber(String studentId, String spPostNumber) {
        StudentPostDto studentPostDto = new StudentPostDto();
        SysStudentEntity sysStudentEntity = studentRepository.findSysStudentEntityBystudentId(studentId);
        SysStudentPostEntity sysStudentPostEntity = commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(studentId, spPostNumber);

        return DtoTools.convertToDto(sysStudentPostEntity, StudentPostDto.class);
    }

    @Override
    public AdmissionCourseListDto getAdmissionCourseDataByStudentIDAndPostNumber(String studentId, String spPostNumber) {
        String studentPostId = commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(studentId, spPostNumber).getStudentPostId();
        AdmissionCourseListDto admissionCourseListDto = new AdmissionCourseListDto();
        List<SysAdmissionCourseEntity> sysAdmissionCourseEntityList = admissionCourseRepository.getSysAdmissionCourseEntitiesByStudentPostId(studentPostId);
        List<AdmissionCourseDto> admissionCourseDtoList = new ArrayList<>();

        for (SysAdmissionCourseEntity sysAdmissionCourseEntity : sysAdmissionCourseEntityList) {
            AdmissionCourseDto admissionCourseDto;
            admissionCourseDto = DtoTools.convertToDto(sysAdmissionCourseEntity, AdmissionCourseDto.class);
            List<SysAdHistoryEntity> sysAdHistoryEntityList = admissionHistoryRepository.getSysAdHistoryEntitiesByAdCourseId(admissionCourseDto.getAdCourseId());
            System.out.println(sysAdHistoryEntityList.size());

            List<AdmissionCourseHistoryDto> admissionCourseHistoryDtoList = new ArrayList<>();
            if (sysAdHistoryEntityList != null && sysAdHistoryEntityList.size() != 0) {
                for (SysAdHistoryEntity sysAdHistoryEntity : sysAdHistoryEntityList) {
                    admissionCourseHistoryDtoList.add(DtoTools.convertToDto(sysAdHistoryEntity, AdmissionCourseHistoryDto.class));
                }
            }
            admissionCourseDto.setAdCourseHistory(admissionCourseHistoryDtoList);

            admissionCourseDtoList.add(admissionCourseDto);
        }

        admissionCourseListDto.setAdmissionCourseList(admissionCourseDtoList);
        return admissionCourseListDto;
    }



}
