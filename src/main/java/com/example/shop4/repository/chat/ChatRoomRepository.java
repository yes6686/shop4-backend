package com.example.shop4.repository.chat;

import com.example.shop4.entity.Member;
import com.example.shop4.entity.chat.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    @Query("SELECT c FROM ChatRoom c WHERE :userA MEMBER OF c.users AND :userB MEMBER OF c.users")
    Optional<ChatRoom> findByUsers(@Param("userA") Member userA, @Param("userB") Member userB);
}
