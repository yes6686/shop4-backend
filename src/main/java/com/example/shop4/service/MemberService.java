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

    //친구추가 기능
    //친구 조회
    List<MemberDto> getAllFriends(Long id);
    //요청목록 조회
    List<MemberDto> getAllRequestedFriends(Long id);
    //친구추가
    boolean addFriend(Long memberId, String friendId);
}
