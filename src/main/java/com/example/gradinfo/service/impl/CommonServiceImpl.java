package com.example.gradinfo.service.impl;

import com.example.gradinfo.dto.response.StudentInfoResponse;
import com.example.gradinfo.entity.SysStudentEntity;
import com.example.gradinfo.entity.SysStudentPostEntity;
import com.example.gradinfo.repository.StudentPostRepository;
import com.example.gradinfo.repository.StudentRepository;
import com.example.gradinfo.service.CommonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CommonServiceImpl implements CommonService {
    private final StudentRepository studentRepository;
    private final StudentPostRepository studentPostRepository;

    @Autowired
    public CommonServiceImpl(StudentRepository studentRepository,StudentPostRepository studentPostRepository) {
        this.studentRepository = studentRepository;
        this.studentPostRepository = studentPostRepository;
    }

    @Override
    public StudentInfoResponse getStudentInfoByStudentId(String studentId) {
        StudentInfoResponse studentInfoDto = new StudentInfoResponse();
        List<String> postNumber = new ArrayList<>();

        SysStudentEntity sysStudentEntity = studentRepository.findSysStudentEntityBystudentId(studentId);
        if (sysStudentEntity == null) {
            return studentInfoDto;
        }
        ModelMapper modelMapper = new ModelMapper();
        studentInfoDto = modelMapper.map(sysStudentEntity, StudentInfoResponse.class);

        List<SysStudentPostEntity> sysStudentPostEntities = studentPostRepository.getSysStudentPostEntitiesByStudentId(studentId);


        for (SysStudentPostEntity sysStudentPostEntity : sysStudentPostEntities) {
            postNumber.add(sysStudentPostEntity.getSpPostNumber());
        }
        studentInfoDto.setSpPostNumbers(postNumber);

        return studentInfoDto;
    }

    @Override
    public SysStudentPostEntity getStudentPostEntitiesByStudentIdAndSpPostNumber(String studentId, String sp_post_number) {
        List<SysStudentPostEntity> sysStudentPostEntityList  = studentPostRepository.getSysStudentPostEntitiesByStudentId(studentId);
        SysStudentPostEntity sysStudentPostEntity = new SysStudentPostEntity();

        for (SysStudentPostEntity cur : sysStudentPostEntityList) {
            if (cur.getSpPostNumber().equals(sp_post_number)) {
                sysStudentPostEntity = cur;
            }
        }

        return sysStudentPostEntity;
    }

}
