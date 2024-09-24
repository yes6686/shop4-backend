package com.example.shop4.mapper.chat;


import com.example.shop4.dto.chat.ChatRoomDto;
import com.example.shop4.entity.chat.ChatRoom;

public class ChatRoomMapper {
    public static ChatRoomDto mapToChatRoomDto(ChatRoom chatRoom) {
        return new ChatRoomDto(
                chatRoom.getId(),
                chatRoom.getUsers()
        );
    }

    public static ChatRoom mapToChatRoom(ChatRoomDto chatRoomDto) {
        return new ChatRoom(
                chatRoomDto.getId(),
                chatRoomDto.getUsers()
        );
    }
}