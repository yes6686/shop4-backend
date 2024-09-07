package com.example.shop4.entity;

import com.example.shop4.dto.CommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class) // @CreatedDate와 @LastModifiedDate 활성화
public class Comment {
    @Id // 대표키 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 자동으로 1씩 증가
    private Long id; // 대표키

    @ManyToOne(fetch = FetchType. EAGER) // Comment 엔티티와 Goods 엔티티를 다대일 관계로 설정
    @JoinColumn(name="goods_id")
    private Goods goods; // 해당 댓글의 부모 상품

    @ManyToOne(fetch = FetchType. EAGER)
    @JoinColumn(name="member_id")
    private Member member; // 댓글을 단 사람

    @Column
    private String comment; // 댓글 내용

    @Column(name = "created_date")
    @CreatedDate
    private LocalDateTime createdDate; // 댓글 생성 시간

    @Column(name = "modified_date")
    //@LastModifiedDate 해당 애노테이션 추가 시 좋아요 버튼 클릭 하면 댓글 수정 시간도 변경됨
    private LocalDateTime modifiedDate; // 댓글 수정 시간

    @Column(name = "like_count")
    private Long like; // 좋아요 수


    // 댓글 수정
    public void update(CommentDto commentDto){
        if(commentDto.getComment() !=null){
            this.comment = commentDto.getComment();
            this.modifiedDate = LocalDateTime.now(); // 댓글 내용 수정 시 수정 시간 업데이트
        }
        if(commentDto.getLike() !=null){
            this.like = commentDto.getLike();
        }
    }
}
