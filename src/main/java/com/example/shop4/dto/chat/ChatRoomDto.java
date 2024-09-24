package com.example.shop4.dto.chat;

import com.example.shop4.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ChatRoomDto {
    private Long id; // 채팅방 고유 아이디
    private Set<Member> users;
}
