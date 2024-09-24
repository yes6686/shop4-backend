package com.example.shop4.controller.chat;

import com.example.shop4.dto.chat.MessageDto;
import com.example.shop4.entity.Member;
import com.example.shop4.entity.chat.ChatRoom;
import com.example.shop4.entity.chat.Message;
import com.example.shop4.entity.chat.UserChatRoom;
import com.example.shop4.repository.MemberRepository;
import com.example.shop4.repository.chat.UserChatRoomRepository;
import com.example.shop4.service.chat.ChatRoomService;
import com.example.shop4.service.chat.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin("*")
@Controller
@AllArgsConstructor
@RequestMapping("/api/chatSystem")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private UserChatRoomRepository userChatRoomRepository;

    @Autowired
    private MemberRepository memberRepository;

    //메세지 전송 api
    @PostMapping
    public ResponseEntity<String> createMessage(@RequestBody MessageDto messageDto) {
        messageService.createMessage(messageDto);
        return new ResponseEntity<>("메세지 전송 완료", HttpStatus.OK);
    }

    // A와 B 간의 고유채팅방 ID를 반환하는 API
    @GetMapping("/getChatRoomNum/{userIdA}/{userIdB}")
    public ResponseEntity<Map<String, Object>> getChatRoomNum(@PathVariable Long userIdA, @PathVariable Long userIdB) {
        ChatRoom chatRoom = chatRoomService.getOrCreateChatRoom(userIdA, userIdB);
        Long chatRoomId = chatRoom.getId();

        // 각 사용자의 UserChatRoom 조회
        Member userA = memberRepository.findById(userIdA)
                .orElseThrow(() -> new RuntimeException("User A not found"));
        Member userB = memberRepository.findById(userIdB)
                .orElseThrow(() -> new RuntimeException("User B not found"));

        UserChatRoom userAChatRoom = userChatRoomRepository.findByMemberAndChatRoom(userA, chatRoom)
                .orElseThrow(() -> new RuntimeException("User A's UserChatRoom not found"));
        UserChatRoom userBChatRoom = userChatRoomRepository.findByMemberAndChatRoom(userB, chatRoom)
                .orElseThrow(() -> new RuntimeException("User B's UserChatRoom not found"));

        Map<String, Object> response = new HashMap<>();
        response.put("chatRoomId", chatRoomId);
        response.put("userAChatRoomId", userAChatRoom.getId());
        response.put("userBChatRoomId", userBChatRoom.getId());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
