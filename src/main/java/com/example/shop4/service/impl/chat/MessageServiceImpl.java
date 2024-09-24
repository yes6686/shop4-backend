package com.example.shop4.service.impl.chat;

import com.example.shop4.dto.chat.MessageDto;
import com.example.shop4.entity.chat.Message;
import com.example.shop4.entity.chat.UserChatRoom;
import com.example.shop4.repository.MemberRepository;
import com.example.shop4.repository.chat.ChatRoomRepository;
import com.example.shop4.repository.chat.MessageRepository;
import com.example.shop4.repository.chat.UserChatRoomRepository;
import com.example.shop4.service.chat.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.shop4.entity.chat.ChatRoom;
import com.example.shop4.entity.Member;
import org.springframework.transaction.annotation.Transactional;


@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private UserChatRoomRepository userChatRoomRepository;

    @Override
    @Transactional
    public void createMessage(MessageDto messageDto) {
        // ChatRoom 및 Member 조회
        ChatRoom chatRoom = chatRoomRepository.findById(messageDto.getChatRoom())
                .orElseThrow(() -> new RuntimeException("ChatRoom not found"));
        Member sender = memberRepository.findById(messageDto.getSender())
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        // 보낸 사람의 UserChatRoom 조회
        UserChatRoom senderUserChatRoom = userChatRoomRepository.findById(messageDto.getSenderUserChatRoomId())
                .orElseThrow(() -> new RuntimeException("Sender's UserChatRoom not found"));

        // 받는 사람의 UserChatRoom 조회
        UserChatRoom recipientUserChatRoom = userChatRoomRepository.findById(messageDto.getRecipientUserChatRoomId())
                .orElseThrow(() -> new RuntimeException("Recipient's UserChatRoom not found"));

        // 메시지 생성 및 저장 (보낸 사람)
        Message senderMessage = new Message();
        senderMessage.setChatRoom(chatRoom);
        senderMessage.setSender(sender);
        senderMessage.setContent(messageDto.getContent());
        senderMessage.setTimestamp(messageDto.getTimestamp());
        senderMessage.setUserChatRoom(senderUserChatRoom);
        messageRepository.save(senderMessage);

        // 메시지 생성 및 저장 (받는 사람)
        Message recipientMessage = new Message();
        recipientMessage.setChatRoom(chatRoom);
        recipientMessage.setSender(sender); // 보낸 사람은 동일
        recipientMessage.setContent(messageDto.getContent());
        recipientMessage.setTimestamp(messageDto.getTimestamp());
        recipientMessage.setUserChatRoom(recipientUserChatRoom);
        messageRepository.save(recipientMessage);
    }
}
