package com.example.gradinfo.service.impl;

import com.example.gradinfo.bo.CourseGradesAndUnits;
import com.example.gradinfo.bo.StudentGpaAndUnit;
import com.example.gradinfo.dto.response.StudentInfoResponse;
import com.example.gradinfo.entity.SysAdmissionCourseEntity;
import com.example.gradinfo.entity.SysStudentEntity;
import com.example.gradinfo.entity.SysStudentPostEntity;
import com.example.gradinfo.entity.SysTransferCourseEntity;
import com.example.gradinfo.repository.AdmissionCourseRepository;
import com.example.gradinfo.repository.StudentPostRepository;
import com.example.gradinfo.repository.StudentRepository;
import com.example.gradinfo.repository.TransferCourseRepository;
import com.example.gradinfo.service.CommonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;


@Service
public class CommonServiceImpl implements CommonService {
    private final StudentRepository studentRepository;
    private final StudentPostRepository studentPostRepository;
    private final AdmissionCourseRepository admissionCourseRepository;
    private final TransferCourseRepository transferCourseRepository;

    @Autowired
    public CommonServiceImpl(StudentRepository studentRepository, StudentPostRepository studentPostRepository, AdmissionCourseRepository admissionCourseRepository, TransferCourseRepository transferCourseRepository) {
        this.studentRepository = studentRepository;
        this.studentPostRepository = studentPostRepository;
        this.admissionCourseRepository = admissionCourseRepository;
        this.transferCourseRepository = transferCourseRepository;
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


    @Override
    public StudentGpaAndUnit calculateGpaAndUnit(String studentPostId, SysStudentPostEntity sysStudentPostEntity, List<SysAdmissionCourseEntity> sysAdmissionCourseEntityList, List<SysTransferCourseEntity> sysTransferCourseEntityList) {
        // according to the second key point of API document, write this function


        List<SysAdmissionCourseEntity> applySysAdmissionCourseEntityList = admissionCourseRepository.getSysAdmissionCourseEntitiesByStudentPostIdAndAdCourseApplyStatusIs(studentPostId, Byte.valueOf("1"));
        List<SysTransferCourseEntity> applySysTransferCourseEntityList = transferCourseRepository.getSysTransferCourseEntitiesByStudentPostIdAndTrCourseApplyStatusIs(studentPostId, Byte.valueOf("1"));
        List<CourseGradesAndUnits> studentGpaAndUnitList = new ArrayList<>();

        for (SysAdmissionCourseEntity sysAdmissionCourseEntity : applySysAdmissionCourseEntityList) {
            CourseGradesAndUnits courseGradesAndUnits = new CourseGradesAndUnits();
            courseGradesAndUnits.setCourseGrade(sysAdmissionCourseEntity.getAdCourseGrade());
            courseGradesAndUnits.setCourseGpts(sysAdmissionCourseEntity.getAdCourseGpts());
            courseGradesAndUnits.setUnits(sysAdmissionCourseEntity.getAdCourseUnits());
            studentGpaAndUnitList.add(courseGradesAndUnits);
        }

        int creditLimits = sysStudentPostEntity.getSpCreditLimits();
        Collections.sort(applySysTransferCourseEntityList, Comparator.comparingInt(s -> s.getTrCourseGrade().charAt(0)));

        for (SysTransferCourseEntity sysTransferCourseEntity : applySysTransferCourseEntityList) {
            creditLimits -= sysTransferCourseEntity.getTrCourseUnits();
            CourseGradesAndUnits courseGradesAndUnits = new CourseGradesAndUnits();
            if (creditLimits < 0) {
                courseGradesAndUnits.setUnits(sysTransferCourseEntity.getTrCourseUnits() - Math.abs(creditLimits));
            } else {
                courseGradesAndUnits.setUnits(sysTransferCourseEntity.getTrCourseUnits());
            }
            courseGradesAndUnits.setCourseGrade(sysTransferCourseEntity.getTrCourseGrade());
            courseGradesAndUnits.setCourseGpts(sysTransferCourseEntity.getTrCourseGpts());

            studentGpaAndUnitList.add(courseGradesAndUnits);
        }


        StudentGpaAndUnit studentGpaAndUnit = new StudentGpaAndUnit();

        double AppliedGpa = 0.0 ,TotalGpa = 0.0, AppliedUnits = 0.0, TotalUnits = 0.0, RGUnits = 0.0;

        for (SysAdmissionCourseEntity sysAdmissionCourseEntity : sysAdmissionCourseEntityList) {
            TotalGpa += (gpaRules(sysAdmissionCourseEntity.getAdCourseGrade()) * sysAdmissionCourseEntity.getAdCourseUnits());
            TotalUnits += sysAdmissionCourseEntity.getAdCourseUnits();
        }


        for (CourseGradesAndUnits CourseGradesAndUnits : studentGpaAndUnitList) {

            if(CourseGradesAndUnits.getCourseGrade().equals("RG")){
                RGUnits += CourseGradesAndUnits.getUnits();
                continue;
            }
            AppliedGpa += (gpaRules(CourseGradesAndUnits.getCourseGrade()) * CourseGradesAndUnits.getUnits());
            AppliedUnits += CourseGradesAndUnits.getUnits();

        }

        System.out.println(AppliedUnits + " " + TotalUnits);
        double SpGpaAll = new BigDecimal(((TotalGpa / TotalUnits) * 100) / 100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        double SpGpaApply = new BigDecimal((((AppliedGpa / AppliedUnits) * 100) / 100)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

        studentGpaAndUnit.setSpGpaAll(SpGpaAll);
        studentGpaAndUnit.setSpGpaApply(SpGpaApply);
        studentGpaAndUnit.setSpRgunits(RGUnits);
        studentGpaAndUnit.setSpEarnunits(AppliedUnits);

        sysStudentPostEntity.setSpEarnunits(studentGpaAndUnit.getSpEarnunits());
        sysStudentPostEntity.setSpRgunits(studentGpaAndUnit.getSpRgunits());
        sysStudentPostEntity.setSpGpaApply(studentGpaAndUnit.getSpGpaApply());
        sysStudentPostEntity.setSpGpaAll(studentGpaAndUnit.getSpGpaAll());


        System.out.println(sysStudentPostEntity.getSpEarnunits());
        System.out.println(sysStudentPostEntity.getSpRgunits());
        System.out.println(sysStudentPostEntity.getSpGpaApply());
        System.out.println(sysStudentPostEntity.getSpGpaAll());

        studentPostRepository.save(sysStudentPostEntity);


        return studentGpaAndUnit;
    }

    public double gpaRules(String courseGrade) {
        switch (courseGrade) {
            case "A":
                return 4.0;
            case "A-":
                return 3.7;
            case "B+":
                return 3.3;
            case "B":
                return 3.0;
            case "B-":
                return 2.7;
            case "C+":
                return 2.3;
            case "C":
                return 2.0;
            case "C-":
                return 1.7;
            case "D+":
                return 1.3;
            case "D":
                return 1.0;
            case "D-":
                return 0.7;
            case "F":
            case "UW":
            case "IX":
                return 0;
        }
        return 0;
    }
}
