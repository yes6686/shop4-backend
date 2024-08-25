package com.example.shop4.service;

import com.example.shop4.dto.MemberDto;

import java.util.List;

public interface MemberService {
    MemberDto createMember(MemberDto memberDto);
    List<MemberDto> getAllMembers();
    MemberDto getMemberById(Long memberId);
    MemberDto updateMember(Long memberId, MemberDto updatedMember);
    void deleteMember(Long memberId);

    //로그인 서비스 기능
    MemberDto loginMember(MemberDto dto);

    boolean checkUserId(String userId);
}
