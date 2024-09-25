package com.example.shop4.repository.chat;

import com.example.shop4.entity.Member;
import com.example.shop4.entity.chat.ChatRoom;
import com.example.shop4.entity.chat.UserChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserChatRoomRepository extends JpaRepository<UserChatRoom, Long> {
    // Member와 ChatRoom을 기반으로 UserChatRoom 조회
    Optional<UserChatRoom> findByMemberAndChatRoom(Member member, ChatRoom chatRoom);

    List<UserChatRoom> findByMember(Member user);

    // 주어진 ChatRoom에서 특정 사용자가 아닌 다른 사용자의 UserChatRoom 조회
    Optional<UserChatRoom> findByChatRoomAndMemberNot(ChatRoom chatRoom, Member member);
}
