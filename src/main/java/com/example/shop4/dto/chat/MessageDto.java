package com.example.shop4.dto.chat;

import com.example.shop4.entity.Member;
import com.example.shop4.entity.chat.ChatRoom;
import com.example.shop4.entity.chat.UserChatRoom;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class MessageDto {
    private Long id; // 메시지 고유 아이디
    private Long chatRoom; // 채팅방 고유 아이디
    private Long sender; // 보낸 사람 아이디
    private String content; // 메시지 내용
    private Timestamp timestamp; // 메시지 발송 시각
    private Long senderUserChatRoomId;      // 보낸 사람의 UserChatRoomId
    private Long recipientUserChatRoomId;   // 받는 사람의 UserChatRoomId
    private String friendId;                // 받는 사람의 사용자 ID
}
