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

import java.util.List;
import java.util.Optional;


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
        senderMessage.setUserChatRoom(senderUserChatRoom);
        messageRepository.save(senderMessage);

        // 메시지 생성 및 저장 (받는 사람)
        Message recipientMessage = new Message();
        recipientMessage.setChatRoom(chatRoom);
        recipientMessage.setSender(sender); // 보낸 사람은 동일
        recipientMessage.setContent(messageDto.getContent());
        recipientMessage.setUserChatRoom(recipientUserChatRoom);
        messageRepository.save(recipientMessage);
    }

    @Override
    public List<Message> getAllMessage(Long userChatRoomId) {
        return messageRepository.findByUserChatRoomIdOrderByTimestampAsc(userChatRoomId);

    }

    @Override
    @Transactional
    public void updateLastReadMessageIds(Long userChatRoomId) {
        // 1. 내 UserChatRoom 조회
        UserChatRoom myUserChatRoom = userChatRoomRepository.findById(userChatRoomId)
                .orElseThrow(() -> new RuntimeException("UserChatRoom not found"));

        // 2. ChatRoom 및 상대방의 UserChatRoom 조회
        ChatRoom chatRoom = myUserChatRoom.getChatRoom();

        UserChatRoom friendUserChatRoom = userChatRoomRepository.findByChatRoomAndMemberNot(chatRoom, myUserChatRoom.getMember())
                .orElseThrow(() -> new RuntimeException("Friend's UserChatRoom not found"));

        // 3. 상대방이 보낸 가장 최신 메시지를 메시지 ID 기준으로 조회
        Member friendMember = friendUserChatRoom.getMember();

        Optional<Message> latestMessageFromFriend = messageRepository.findTopByChatRoomAndSenderOrderByIdDesc(chatRoom, friendMember);

        if (latestMessageFromFriend.isPresent()) {
            Long lastMessageIdFromFriend = latestMessageFromFriend.get().getId();

            // 4. myLastReadMessageId를 상대방이 보낸 가장 최신 메시지의 ID로 업데이트
            myUserChatRoom.setMyLastReadMessageId(lastMessageIdFromFriend);

            // 5. 상대방의 friendLastReadMessageId를 내 myLastReadMessageId로 업데이트
            friendUserChatRoom.setFriendLastReadMessageId(lastMessageIdFromFriend);
        } else {
            myUserChatRoom.setMyLastReadMessageId(0L);
            friendUserChatRoom.setFriendLastReadMessageId(0L);
        }


        userChatRoomRepository.save(myUserChatRoom);
        userChatRoomRepository.save(friendUserChatRoom);
    }

}
