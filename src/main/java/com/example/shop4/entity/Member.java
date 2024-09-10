package com.example.shop4.entity;

import com.example.shop4.dto.MemberDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String address;
    @Column
    private String phone;
    @Column
    private int age;
    @Column
    private LocalDate birth;
    @Column
    private String gender;
    @Column
    private String userId;
    @Column
    private String userPw;
    @Column
    private Long cash;
    @Column
    private String userImage;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "member_liked_comments",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id"))
    @JsonIgnore // 무한한 순환 참조 문제를 방지
    private Set<Comment> likedComments = new HashSet<>();


    //여기부터 친구추가 관련 칼럼

    //친구목록
    @ElementCollection
    @CollectionTable(
            name = "user_friends_table", // 친구 목록을 저장할 테이블 이름
            joinColumns = @JoinColumn(name = "user_id") // 주 테이블의 user_id를 참조하는 외래키
    )

    @Column(name = "friend_id") // 친구 아이디를 저장하는 컬럼
    private List<String> friends; // 친구 목록 (친구들의 userId 리스트)

    // 받은친구요청
    @ElementCollection
    @CollectionTable(
            name = "requested_friends_table", // 친구 목록을 저장할 테이블 이름
            joinColumns = @JoinColumn(name = "user_id") // 주 테이블의 user_id를 참조하는 외래키
    )
    @Column(name = "requested_friends_id") // 친구 아이디를 저장하는 컬럼
    private List<String> requested_friends; // 친구 목록 (친구들의 userId 리스트)


    public void patch(MemberDto dto) {
        if (dto.getName() != null) {
            this.name = dto.getName();
        }
        if (dto.getEmail() != null) {
            this.email = dto.getEmail();
        }
        if (dto.getAddress() != null) {
            this.address = dto.getAddress();
        }
        if (dto.getPhone() != null) {
            this.phone = dto.getPhone();
        }
        if (dto.getGender() != null) {
            this.gender = dto.getGender();
        }
        if (dto.getUserId() != null) {
            this.userId = dto.getUserId();
        }
        if (dto.getCash() != null) {
            this.cash = dto.getCash();
        }
        if (dto.getBirth() != null) {
            this.birth = dto.getBirth();
        }
        if (dto.getUserPw() != null) {
            this.userPw = dto.getUserPw();
        }
        if (dto.getAge() != 0) {
            this.age = dto.getAge();
        }
        if (dto.getRequested_friends() != null) {
            this.requested_friends = dto.getRequested_friends();
        }
        if (dto.getFriends() != null) {
            this.friends = dto.getFriends();
            if (dto.getUserImage() != null) {
                this.userImage = dto.getUserImage();
            }
        }
    }
}
