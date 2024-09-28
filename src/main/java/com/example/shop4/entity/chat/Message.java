package com.example.shop4.entity.chat;

import com.example.shop4.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_chat_room_id", nullable = false)
    private UserChatRoom userChatRoom; // 각 사용자의 채팅방과 연관된 메시지

    @ManyToOne
    @JoinColumn(name = "chatRoom_id", nullable = false)
    private ChatRoom chatRoom;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Member sender; //보낸사람

    @Column
    private String content; //메세지 내용

    @Column
    @CreatedDate
    private Timestamp timestamp; //메세지 발송 시각


}
