package com.example.shop4.entity.chat;

import com.example.shop4.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 채팅방 고유 아이디 (얘는 대화 참여자 2명이 공유함)

    // A와 B 사용자가 속한 채팅방의 사용자 목록
    @ManyToMany
    @JoinTable(
            name = "chat_room_members",
            joinColumns = @JoinColumn(name = "chat_room_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<Member> users = new HashSet<>();;  // 채팅방에 참여한 사용자 목록 (A, B)
}
