package com.example.shop4.service.chat;

import com.example.shop4.entity.chat.ChatRoom;
import com.example.shop4.entity.chat.UserChatRoom;

import java.util.List;

public interface ChatRoomService {
     ChatRoom getOrCreateChatRoom(Long userIdA, Long userIdB); //고유id임

     List<UserChatRoom> getAllChatList(Long userId);
}
