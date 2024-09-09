package com.example.shop4.service.impl;

import com.example.shop4.dto.MemberDto;
import com.example.shop4.entity.Member;
import com.example.shop4.exception.ResourceNotFoundException;
import com.example.shop4.mapper.MemberMapper;
import com.example.shop4.repository.MemberRepository;
import com.example.shop4.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepository memberRepository;

    // 회원 추가
    @Override
    public MemberDto createMember(MemberDto memberDto) {
        Member member = MemberMapper.mapToMember(memberDto);
        Member savedMember = memberRepository.save(member);
        return MemberMapper.mapToMemberDto(savedMember);
    }

    // 모든 회원 조회
    @Override
    public List<MemberDto> getAllMembers() {
        List<Member> members = memberRepository.findAll();
        return members
                .stream()
                .map(MemberMapper::mapToMemberDto)
                .collect(Collectors.toList());
    }

    // 회원 조회
    @Override
    public MemberDto getMemberById(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Member not exists with given id : " + memberId));

        return MemberMapper.mapToMemberDto(member);
    }

    // 회원정보 수정
    @Override
    public MemberDto updateMember(Long memberId, MemberDto updatedMember) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new ResourceNotFoundException("Member not exists with given id : " + memberId)
        );
        member.patch(updatedMember);
        Member updatedMemberObj = memberRepository.save(member);
        return MemberMapper.mapToMemberDto(updatedMemberObj);
    }

    // 회원정보 삭제
    @Override
    public void deleteMember(Long memberId) {
        memberRepository.findById(memberId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Member not exists with given id : " + memberId));
        memberRepository.deleteById(memberId);
    }

    // 회원 로그인 기능 서비스
    @Override
    public MemberDto loginMember(MemberDto dto) {
        // 입력받은 userId 부터 검색하고

        // 해당 유저의 비밀번호 비교

        //loginDto에 있는 userId, userPw로 member 찾기
        return memberRepository.findByUserIdAndUserPw(dto.getUserId(), dto.getUserPw())
                .map(MemberDto::createMemberDto) //userId랑 userPw가 있으면 해당 엔티티를 dto로 만든다!
                .orElseThrow(() ->
                        new ResourceNotFoundException("아이디 및 비밀번호가 일치하는 유저가 없습니다."));
    }


    // userId 중복 확인
    @Override
    public boolean checkUserId(String userId) {
        return memberRepository.findByUserId(userId).isPresent();
    }


    //친구 목록 불러오기
    @Override
    public List<MemberDto> getAllFriends(Long id) {
        List<String> friendIds = memberRepository.findFriendsById(id);

        return friendIds.stream()
                .map(friendId -> {
                    Member member = memberRepository.findByUserId(friendId)
                            .orElseThrow(() -> new IllegalArgumentException("No user found for friendId: " + friendId));
                    return new MemberDto( member.getId(),
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
                            member.getRequested_friends());
                })
                .collect(Collectors.toList());
    }


    // 친구 요청목록 불러오기
    @Override
    public List<MemberDto> getAllRequestedFriends(Long id) {
        List<String> friendIds = memberRepository.findRequestedFriendsById(id);

        return friendIds.stream()
                .map(friendId -> {
                    Member member = memberRepository.findByUserId(friendId)
                            .orElseThrow(() -> new IllegalArgumentException("No user found for friendId: " + friendId));
                    return new MemberDto( member.getId(),
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
                            member.getRequested_friends());
                })
                .collect(Collectors.toList());
    }


    // 친구추가 friendId가 없거나 이미 친구이면 false리턴, 아닐시 친구요청테이블에 추가
    @Override
    public boolean addFriend(Long memberId, String friendId) {
        Optional<Member> friendOptional = memberRepository.findByUserId(friendId);

        if (!friendOptional.isPresent()) {
            // 친구 ID가 존재하지 않으면 false 반환
            return false;
        }

        // 이미 친구인지 확인
        if (memberRepository.isFriend(friendId, memberId)) {
            // 이미 친구라면 false 반환
            return false;
        }
        String requestedUserId = memberRepository.findById(memberId).get().getUserId();
        Member friend = friendOptional.get();
        if (!friend.getRequested_friends().contains(requestedUserId)) { // 친구 중복 요청 방지
            friend.getRequested_friends().add(requestedUserId);
            memberRepository.save(friend); // 친구요청 테이블에 추가
        }

       return true;
    }


}

