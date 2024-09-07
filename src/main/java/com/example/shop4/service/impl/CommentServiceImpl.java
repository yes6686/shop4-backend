package com.example.shop4.service.impl;

import com.example.shop4.dto.CommentDto;
import com.example.shop4.entity.Cart;
import com.example.shop4.entity.Comment;
import com.example.shop4.entity.Member;
import com.example.shop4.exception.ResourceNotFoundException;
import com.example.shop4.mapper.CommentMapper;
import com.example.shop4.repository.CommentRepository;
import com.example.shop4.repository.GoodsRepository;
import com.example.shop4.repository.MemberRepository;
import com.example.shop4.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public CommentDto createComment(CommentDto commentDto) {

        Comment comment = CommentMapper.mapToComment(commentDto);
        Comment savedComment = commentRepository.save(comment);
        return CommentMapper.mapToCommentDto(savedComment);
    }

    @Override
    public List<CommentDto> getAllComments(Long goodsId) {
        List<Comment> comments = commentRepository.findAll().stream()
                .filter(comment -> comment.getGoods().getId().equals(goodsId)).collect(Collectors.toList());
        return comments.stream().map((comment)-> CommentMapper.mapToCommentDto(comment))
                .collect(Collectors.toList());
    }

    @Override
    public CommentDto updateComment(Long commentId, CommentDto updatedComment) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()->new ResourceNotFoundException("Comment not found id : "+commentId)
        );
        comment.update(updatedComment);
        Comment updated = commentRepository.save(comment);
        return CommentMapper.mapToCommentDto(updated);
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()->new ResourceNotFoundException("Comment not found id : "+commentId)
        );
        commentRepository.delete(comment);
    }
    // 게시글을 작성한 유저인지 확인

}
