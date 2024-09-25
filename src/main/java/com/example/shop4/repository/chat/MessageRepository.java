package com.example.shop4.repository.chat;

import com.example.shop4.entity.Member;
import com.example.shop4.entity.chat.ChatRoom;
import com.example.shop4.entity.chat.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    // 특정 UserChatRoomId에 속하는 메시지를 timestamp 기준으로 오름차순 정렬하여 가져옴
    List<Message> findByUserChatRoomIdOrderByTimestampAsc(Long userChatRoomId);

    // 상대방이 보낸 가장 최신 메시지를 메시지 ID 기준으로 조회
    Optional<Message> findTopByChatRoomAndSenderOrderByIdDesc(ChatRoom chatRoom, Member sender);
}
