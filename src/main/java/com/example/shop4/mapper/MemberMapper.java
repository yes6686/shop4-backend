package com.example.shop4.mapper;

import com.example.shop4.dto.MemberDto;
import com.example.shop4.entity.Member;

public class MemberMapper {
    public static MemberDto mapToMemberDto(Member member){
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
                member.getLikedComments()
        );
    }

    public static Member mapToMember(MemberDto memberDto){
        return new Member(
                memberDto.getId(),
                memberDto.getName(),
                memberDto.getEmail(),
                memberDto.getAddress(),
                memberDto.getPhone(),
                memberDto.getAge(),
                memberDto.getBirth(),
                memberDto.getGender(),
                memberDto.getUserId(),
                memberDto.getUserPw(),
                memberDto.getCash(),
                memberDto.getLikedComments()
        );
    }
}
