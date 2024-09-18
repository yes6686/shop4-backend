package com.example.shop4.dto;

import com.example.shop4.entity.Comment;
import com.example.shop4.entity.Member;
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
public class MemberDto {
    private Long id;
    private String name;
    private String email;
    private String address;
    private String phone;
    private int age;
    private LocalDate birth;
    private String gender;
    private String userId;
    private String userPw;  
    private Long cash;
    private Set<Comment> likedComments = new HashSet<>();
    private List<String> friends;
    private List<String> requested_friends;
    private String userImage;

    public static MemberDto createMemberDto(Member member) {
        return new MemberDto(
                member.getId(),
                member.getName(),
                member.getEmail(),
                member.getAddress(),
                member.getPhone(),
                member.getAge(),
                member.getBirth(),
                member.getGender(),
                member.getUserId(),
                member.getUserPw(),
                member.getCash(),
                member.getLikedComments(),
                member.getFriends(),
                member.getRequested_friends(),
                member.getUserImage()
        );
    }

}
