package com.example.shop4.mapper.chat;


import com.example.shop4.dto.chat.UserChatRoomDto;
import com.example.shop4.entity.Member;
import com.example.shop4.entity.chat.ChatRoom;
import com.example.shop4.entity.chat.UserChatRoom;

public class UserChatRoomMapper {
    public static UserChatRoomDto mapToUserChatRoomDto(UserChatRoom userChatRoom) {
        return new UserChatRoomDto(
                userChatRoom.getId(),
                userChatRoom.getMember(),
                userChatRoom.getChatRoom(),
                userChatRoom.getMyLastReadMessageId(),
                userChatRoom.getFriendLastReadMessageId(),
                userChatRoom.getFriendId(),
                userChatRoom.getFriendName(),
                userChatRoom.getHide(),
                userChatRoom.getCountMessage()
        );
    }

    public static UserChatRoom mapToUserChatRoom(UserChatRoomDto userChatRoomDto, Member member, ChatRoom chatRoom) {
        return new UserChatRoom(
                userChatRoomDto.getId(),
                userChatRoomDto.getMember(), // '나'의 Member 객체
                userChatRoomDto.getChatRoom(), // 채팅방 객체
                userChatRoomDto.getMyLastReadMessageId(),
                userChatRoomDto.getFriendLastReadMessageId(),
                userChatRoomDto.getFriendId(),
                userChatRoomDto.getFriendName(),
                userChatRoomDto.getHide(),
                userChatRoomDto.getCountMessage()
        );
    }
}