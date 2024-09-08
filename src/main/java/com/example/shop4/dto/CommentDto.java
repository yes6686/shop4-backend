package com.example.shop4.dto;

import com.example.shop4.entity.Goods;
import com.example.shop4.entity.Member;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CommentDto {
    private Long id; // 대표키
    private Goods goods; // 해당 댓글의 부모 상품
    private Member member; // 댓글을 단 사람
    private String comment; // 댓글 내용
    private LocalDateTime createdDate; // 댓글 생성 시간
    private LocalDateTime modifiedDate; // 댓글 수정 시간
    private Long like; // 좋아요 수
    private Set<Member> likedMembers = new HashSet<>();
}
