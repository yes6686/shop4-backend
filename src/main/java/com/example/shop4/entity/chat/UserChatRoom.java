package com.example.shop4.entity.chat;

import com.example.shop4.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class UserChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Member 테이블의 외래키 참조
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Member member;  //'나'임

    @ManyToOne
    @JoinColumn(name = "chat_room_id", nullable = false)
    private ChatRoom chatRoom; //ManyToOne이지만 사실 2대1관계

    @Column
    private Long myLastReadMessageId; // 내가 가장 마지막에 읽은 메세지

    @Column
    private Long friendLastReadMessageId; // 상대가 가장 마지막에 읽은 메세지

    @Column
    private String friendId; //가입 당시 입력한 id임 ex) kim00

    @Column
    private String friendName; //대화 상대방의 이름 <- 이게 대화방 제목임

    @Column
    private Boolean hide = false; // 해당 chatRoom_id을 외래키로 갖는 레코드가 없다면 true(채팅방 숨김)

    @Column
    private Long countMessage; //안읽은 메세지 숫자




}
