package com.example.shop4.dto;

import com.example.shop4.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private long id;
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
                member.getCash()
        );
    }

}
