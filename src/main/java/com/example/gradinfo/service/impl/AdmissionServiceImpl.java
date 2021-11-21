package com.example.gradinfo.service.impl;

import com.example.gradinfo.dto.StudentPostDto;
import com.example.gradinfo.entity.SysAdmissionCourseEntity;
import com.example.gradinfo.entity.SysStudentEntity;
import com.example.gradinfo.entity.SysStudentPostEntity;
import com.example.gradinfo.entity.SysTransferCourseEntity;
import com.example.gradinfo.repository.AdmissionCourseRepository;
import com.example.gradinfo.repository.StudentPostRepository;
import com.example.gradinfo.repository.StudentRepository;
import com.example.gradinfo.repository.TransferCourseRepository;
import com.example.gradinfo.service.AdmissionService;
import com.example.gradinfo.service.CommonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdmissionServiceImpl implements AdmissionService {

    private AdmissionCourseRepository admissionCourseRepository;
    private StudentRepository studentRepository;
    private StudentPostRepository studentPostRepository;
    private TransferCourseRepository transferCourseRepository;
    private CommonService commonService;

    public AdmissionServiceImpl(AdmissionCourseRepository admissionCourseRepository, StudentRepository studentRepository, StudentPostRepository studentPostRepository, TransferCourseRepository transferCourseRepository, CommonService commonService) {
        this.admissionCourseRepository = admissionCourseRepository;
        this.studentRepository = studentRepository;
        this.studentPostRepository = studentPostRepository;
        this.transferCourseRepository = transferCourseRepository;
        this.commonService = commonService;
    }


    @Override
    public StudentPostDto getStudentPostDataByStudentIDAndPostNumber(String student_id, String sp_post_number) {
        System.out.println(student_id + "fdsafsafas" + sp_post_number);
        StudentPostDto studentPostDto = new StudentPostDto();
        SysStudentEntity sysStudentEntity = studentRepository.findSysStudentEntityBystudentId(student_id);
        SysStudentPostEntity sysStudentPostEntity = commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(student_id, sp_post_number);
        String sp_post_id = sysStudentPostEntity.getStudentPostId();

//        List<SysAdmissionCourseEntity> sysAdmissionCourseEntityList = admissionCourseRepository.getSysAdmissionCourseEntitiesByStudentPostId(sp_post_id);
//        List<SysTransferCourseEntity> sysTransferCourseEntityList = transferCourseRepository.getSysTransferCourseEntitiesByStudentPostId(sp_post_id);

        return convertToDto(sysStudentPostEntity);
    }

    private StudentPostDto convertToDto(SysStudentPostEntity sysStudentPostEntity) {
        ModelMapper modelMapper = new ModelMapper();

        StudentPostDto studentPostDto = modelMapper.map(sysStudentPostEntity, StudentPostDto.class);

        return  studentPostDto;
    }

//    private double getSpGpaApply(List<SysAdmissionCourseEntity> sysAdmissionCourseEntityList, List<SysTransferCourseEntity> sysTransferCourseEntityList) {
//        double spGpaApply = 0.00;
//
//        for (SysAdmissionCourseEntity sysAdmissionCourseEntity : sysAdmissionCourseEntityList) {
//            if (sysAdmissionCourseEntity.getAdCourseApplyStatus().equals(true)) {
//                spGpaApply += Integer.valueOf(sysAdmissionCourseEntity.getAdCourseGrade());
//            }
//        }
//
//        return spGpaApply ;
//    }

}
