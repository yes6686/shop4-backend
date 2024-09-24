package com.example.shop4.service.chat;

import com.example.shop4.entity.chat.ChatRoom;

public interface ChatRoomService {
     ChatRoom getOrCreateChatRoom(Long userIdA, Long userIdB); //고유id임
}
