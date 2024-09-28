package com.example.shop4.service.impl.chat;

import com.example.shop4.entity.Member;
import com.example.shop4.entity.chat.ChatRoom;
import com.example.shop4.entity.chat.UserChatRoom;
import com.example.shop4.repository.MemberRepository;
import com.example.shop4.repository.chat.ChatRoomRepository;
import com.example.shop4.repository.chat.UserChatRoomRepository;
import com.example.shop4.service.chat.ChatRoomService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class ChatRoomServiceImpl implements ChatRoomService {
    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private UserChatRoomRepository userChatRoomRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Override
    @Transactional
    public ChatRoom getOrCreateChatRoom(Long userIdA, Long userIdB) {
        // 사용자 A와 B 조회
        Member userA = memberRepository.findById(userIdA)
                .orElseThrow(() -> new RuntimeException("User A not found"));
        Member userB = memberRepository.findById(userIdB)
                .orElseThrow(() -> new RuntimeException("User B not found"));

        // A와 B 간의 채팅방이 이미 있는지 확인
        Optional<ChatRoom> existingChatRoom = chatRoomRepository.findByUsers(userA, userB);

        // 채팅방이 존재하면 그 채팅방 반환
        if (existingChatRoom.isPresent()) {
            return existingChatRoom.get();
        }

        // 채팅방이 존재하지 않으면 새로 생성
        ChatRoom newChatRoom = new ChatRoom();
        newChatRoom.getUsers().add(userA);  // 사용자 추가
        newChatRoom.getUsers().add(userB);  // 사용자 추가

        // 채팅방 저장
        chatRoomRepository.save(newChatRoom);

        // 메시지를 보낸 사람과 받는 사람 모두에 대해 UserChatRoom 생성
        createUserChatRoom(userA, userB, newChatRoom); // userA의 UserChatRoom 생성
        createUserChatRoom(userB, userA, newChatRoom); // userB의 UserChatRoom 생성

        return newChatRoom;
    }

    @Override
    public List<UserChatRoom> getAllChatList(Long userId) {
        Member user = memberRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User A not found"));

        List<UserChatRoom> userChatRooms = userChatRoomRepository.findByMember(user);

        return userChatRooms;
    }

    private void createUserChatRoom(Member member, Member friend, ChatRoom chatRoom) {

        String friendId = friend.getUserId();
        Long realFriendId = memberRepository.findByUserId(friendId).get().getId();
        UserChatRoom userChatRoom = new UserChatRoom();
        userChatRoom.setMember(member);
        userChatRoom.setChatRoom(chatRoom);
        userChatRoom.setMyLastReadMessageId(null);
        userChatRoom.setFriendLastReadMessageId(null);
        userChatRoom.setFriendId(realFriendId);
        userChatRoom.setFriendName(friend.getName());
        userChatRoom.setHide(false);
        userChatRoom.setCountMessage(0L);

        userChatRoomRepository.save(userChatRoom);
    }
}


