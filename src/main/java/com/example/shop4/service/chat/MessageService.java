package com.example.shop4.service.chat;

import com.example.shop4.dto.chat.MessageDto;
import com.example.shop4.entity.chat.Message;

import java.util.List;

public interface MessageService {
    void createMessage(MessageDto messageDto);

    List<Message> getAllMessage(Long userChatRoomId);

    void updateLastReadMessageIds(Long userChatRoomId);
}
