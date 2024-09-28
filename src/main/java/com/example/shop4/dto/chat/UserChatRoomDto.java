package com.example.shop4.dto.chat;

import com.example.shop4.entity.Member;
import com.example.shop4.entity.chat.ChatRoom;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserChatRoomDto {
    private Long id; // UserChatRoom 고유 아이디
    private Member member; // '나'의 아이디
    private ChatRoom chatRoom; // 채팅방 아이디
    private Long myLastReadMessageId; // 내가 읽은 마지막 메시지 ID
    private Long friendLastReadMessageId; // 상대방이 읽은 마지막 메시지 ID
    private Long friendId; // 친구의 ID
    private String friendName; // 친구의 이름
    private Boolean hide; // 채팅방 숨김 여부
    private Long countMessage; // 안 읽은 메시지 숫자
}
