package com.example.shop4.service;

import com.example.shop4.dto.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto);
    List<CommentDto> getAllComments(Long goodsId);
    CommentDto updateComment(Long commentId, CommentDto updatedComment);
    void deleteComment(Long commentId);

    // 좋아요 중복 방지
    int likeOrUnlikeComment(Long commentId, Long memberId);
    int getLike(Long commentId, Long memberId);

}
