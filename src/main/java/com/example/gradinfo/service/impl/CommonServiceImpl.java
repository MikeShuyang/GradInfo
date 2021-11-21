package com.example.gradinfo.service.impl;


import com.example.gradinfo.dto.StudentInfoDto;
import com.example.gradinfo.entity.SysStudentEntity;
import com.example.gradinfo.entity.SysStudentPostEntity;
import com.example.gradinfo.repository.StudentPostRepository;
import com.example.gradinfo.repository.StudentRepository;
import com.example.gradinfo.service.CommonService;
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
    public StudentInfoDto getStudentInfoByStudentId(String student_id) {
        StudentInfoDto studentInfoDto = new StudentInfoDto();
        List<String> postNumber = new ArrayList<>();


        SysStudentEntity sysStudentEntity = studentRepository.findSysStudentEntityBystudentId(student_id);
        if (sysStudentEntity == null) {
            return studentInfoDto;
        }
        studentInfoDto.setStudent_id(student_id);
        studentInfoDto.setStudent_name(sysStudentEntity.getStudentName());

        List<SysStudentPostEntity> sysStudentPostEntities = studentPostRepository.getSysStudentPostEntitiesByStudentId(student_id);

        for (SysStudentPostEntity sysStudentPostEntity : sysStudentPostEntities) {
            postNumber.add(sysStudentPostEntity.getSpPostNumber());
        }
        studentInfoDto.setSp_post_numbers(postNumber);

        return studentInfoDto;
    }

    @Override
    public SysStudentPostEntity getStudentPostEntitiesByStudentIdAndSpPostNumber(String student_id, String sp_post_number) {
        List<SysStudentPostEntity> sysStudentPostEntityList  = studentPostRepository.getSysStudentPostEntitiesByStudentId(student_id);
        SysStudentPostEntity sysStudentPostEntity = new SysStudentPostEntity();

        for (SysStudentPostEntity cur : sysStudentPostEntityList) {
            if (cur.getSpPostNumber().equals(sp_post_number)) {
                sysStudentPostEntity = cur;
            }
        }

        return sysStudentPostEntity;
    }
}
