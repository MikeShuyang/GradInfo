package com.example.gradinfo.service.impl;

import com.example.gradinfo.dto.request.CommentRequest;
import com.example.gradinfo.dto.response.CommentResponse;
import com.example.gradinfo.dto.response.CommonResponse;
import com.example.gradinfo.entity.SysCommentEntity;
import com.example.gradinfo.mapper.CommonMapper;
import com.example.gradinfo.repository.CommentRepository;
import com.example.gradinfo.service.CommentService;
import com.example.gradinfo.service.CommonService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private CommonService commonService;
    private CommentRepository commentRepository;

    public CommentServiceImpl(CommonService commonService, CommentRepository commentRepository) {
        this.commonService = commonService;
        this.commentRepository = commentRepository;
    }

    @Override
    public List<CommentResponse> getCommentsTableDataByIDAndPostNumber(String studentId, String spPostNumber) {
        String studentPostId = commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(studentId, spPostNumber).getStudentPostId();
        List<SysCommentEntity> sysCommentEntityList = commentRepository.getSysCommentEntitiesByStudentPostId(studentPostId);
        List<CommentResponse> commentResponseList = new ArrayList<>();
        for (SysCommentEntity sysCommentEntity : sysCommentEntityList) {
            commentResponseList.add(CommonMapper.convertToDto(sysCommentEntity, CommentResponse.class));
        }
        return commentResponseList;
    }

    @Override
    public CommonResponse postCommentTableDataByCommentObj(CommentRequest commentRequest) {
        SysCommentEntity sysCommentEntity = CommonMapper.convertToDto(commentRequest.getCommentObject(), SysCommentEntity.class);
        sysCommentEntity.setStudentPostId(commonService.getStudentPostEntitiesByStudentIdAndSpPostNumber(commentRequest.getStudentInfo().getStudentId(), commentRequest.getStudentInfo().getSpPostNumber()).getStudentPostId());
        CommonResponse commonResponse = new CommonResponse();
        try {
            commentRepository.save(sysCommentEntity);
        } catch (Exception e) {
            commonResponse.setFlag(false);
            return commonResponse;
        }
        commonResponse.setFlag(true);
        return commonResponse;
    }
}
