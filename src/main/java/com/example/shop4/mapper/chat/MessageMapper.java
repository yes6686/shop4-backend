package com.example.shop4.mapper.chat;


import com.example.shop4.dto.chat.MessageDto;
import com.example.shop4.entity.chat.ChatRoom;
import com.example.shop4.entity.chat.Message;
import com.example.shop4.entity.Member;
import com.example.shop4.entity.chat.UserChatRoom;

public class MessageMapper {

    // Message 엔티티를 MessageDto로 변환
    public static MessageDto mapToMessageDto(Message message) {
        MessageDto messageDto = new MessageDto();
        messageDto.setId(message.getId());
        messageDto.setChatRoom(message.getChatRoom().getId());
        messageDto.setSender(message.getSender().getId());
        messageDto.setContent(message.getContent());
        messageDto.setTimestamp(message.getTimestamp());
        messageDto.setSenderUserChatRoomId(message.getUserChatRoom().getId());

        // messageDto.setFriendId(friendId);
        return messageDto;
    }

    // MessageDto를 Message 엔티티로 변환
    public static Message mapToMessage(MessageDto messageDto, ChatRoom chatRoom, Member sender, UserChatRoom userChatRoom) {
        Message message = new Message();
        message.setId(messageDto.getId());
        message.setChatRoom(chatRoom); // 이미 조회한 ChatRoom 엔티티 설정
        message.setSender(sender); // 이미 조회한 Member 엔티티 설정
        message.setContent(messageDto.getContent());
        message.setTimestamp(messageDto.getTimestamp());
        message.setUserChatRoom(userChatRoom); // 이미 조회한 UserChatRoom 엔티티 설정
        return message;
    }
}
