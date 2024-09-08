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

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private MemberRepository memberRepository;

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
        Set<Member> members = comment.getLikedMembers();
        Iterator<Member> iterator = members.iterator();
        while (iterator.hasNext()) {
            Member member = iterator.next();
            member.getLikedComments().remove(comment);
            // 멤버의 변경 사항을 저장
            memberRepository.save(member);
        }
        commentRepository.delete(comment);
    }

    @Override
    public int likeOrUnlikeComment(Long commentId, Long memberId) {
        int isLike = 1;
        Member member = memberRepository.findById(memberId).orElseThrow(
                ()->new ResourceNotFoundException("Member not found id : "+memberId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()->new ResourceNotFoundException("Comment not found id : "+commentId));

        if(comment.getLikedMembers().contains(member)){ // 좋아요를 이미 누른 상태면 해제
            comment.getLikedMembers().remove(member);
            member.getLikedComments().remove(comment);
            comment.setLike(comment.getLike()-1);
            isLike = 0;
        }else{ // 좋아요를 안 누른 상태면 활성화
            comment.getLikedMembers().add(member);
            member.getLikedComments().add(comment);
            comment.setLike(comment.getLike()+1);
        }
        memberRepository.save(member);
        commentRepository.save(comment);
        return isLike;
    }

    @Override
    public int getLike(Long commentId, Long memberId) {
        int isLike = 0;
        Member member = memberRepository.findById(memberId).orElseThrow(
                ()->new ResourceNotFoundException("Member not found id : "+memberId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()->new ResourceNotFoundException("Comment not found id : "+commentId));

        if(comment.getLikedMembers().contains(member)){ // 좋아요가 눌러져 있으면
            isLike = 1;
        }
        return isLike;
    }
}
