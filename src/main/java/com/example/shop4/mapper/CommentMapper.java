package com.example.shop4.mapper;

import com.example.shop4.dto.CommentDto;
import com.example.shop4.entity.Comment;

public class CommentMapper {
    public static CommentDto mapToCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getGoods(),
                comment.getMember(),
                comment.getComment(),
                comment.getCreatedDate(),
                comment.getModifiedDate(),
                comment.getLike()
        );

    }

    public static Comment mapToComment(CommentDto commentDto) {
        return new Comment(
                commentDto.getId(),
                commentDto.getGoods(),
                commentDto.getMember(),
                commentDto.getComment(),
                commentDto.getCreatedDate(),
                commentDto.getModifiedDate(),
                commentDto.getLike()
        );
    }
}
