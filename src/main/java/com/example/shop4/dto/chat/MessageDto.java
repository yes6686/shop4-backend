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
    private Long chatRoom; // 채팅방 고유 아이디
    private Long sender; // 보낸 사람 아이디
    private String content; // 메시지 내용
    private Long senderUserChatRoomId;      // 보낸 사람의 UserChatRoomId
    private Long recipientUserChatRoomId;   // 받는 사람의 UserChatRoomId

}
