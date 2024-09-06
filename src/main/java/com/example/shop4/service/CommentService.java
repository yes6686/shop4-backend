package com.example.shop4.service;

import com.example.shop4.dto.CartDto;
import com.example.shop4.dto.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto);
    List<CommentDto> getAllComments(Long goodsId);
    CommentDto updateComment(Long commentId, CommentDto updatedComment);
    void deleteComment(Long commentId);

}
