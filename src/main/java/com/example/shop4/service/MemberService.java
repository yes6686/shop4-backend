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
    //친구요청 거부
    void deleteRequestedFriend(Long memberId,String userId);
    //친구 수락
    void acceptFriend(Long memberId,String userId);
    //친구 삭제
    void deleteFriend(Long memberId,String userId);
    //프로필 사진 조회
    byte[] getProfileImage(Long memberId);
    //프로필 사진 업데이트
    void updateProfileImage(Long memberId, byte[] profileImage);
    //프로필 사진 삭제
    void deleteProfileImage(Long memberId);

    MemberDto getMemberByUserId(String id);
}
