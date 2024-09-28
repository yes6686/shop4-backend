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
                            member.getRequested_friends(),
                            member.getFriends(),
                            member.getUserImage());// 여기에 member.getUserImage() 추가


                })
                .collect(Collectors.toList());
    }
    // 친구요청 거절
    @Override
    public void deleteRequestedFriend(Long memberId, String userId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + memberId));
        if (member.getRequested_friends().contains(userId)) {
            member.getRequested_friends().remove(userId); // userId 삭제
        }
        memberRepository.save(member);

    }


    // 친구 요청목록 불러오기
    @Override
    public List<MemberDto> getAllRequestedFriends(Long id) {
        List<String> friendIds = memberRepository.findRequestedFriendsById(id);

        return friendIds.stream()
                .map(friendId -> {
                    Member member = memberRepository.findByUserId(friendId)
                            .orElseThrow(() -> new IllegalArgumentException("No user found for friendId: " + friendId));
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
                            member.getRequested_friends(),
                            member.getFriends(),
                            member.getUserImage()// 여기에 member.getUserImage() 추가

                    );
                })
                .collect(Collectors.toList()); // 여기서 스트림을 리스트로 변환
    }


    //친구삭제
    @Override
    public void deleteFriend(Long memberId, String userId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + memberId));
        Member friend = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with userId: " + userId));

        if (member.getFriends().contains(userId)) {
            member.getFriends().remove(userId); // 내 친구 목록에서 상대방 제거
        }

        if (friend.getFriends().contains(member.getUserId())) {
            friend.getFriends().remove(member.getUserId()); // 상대방의 친구 목록에서 나를 제거
        }
        memberRepository.save(member);
        memberRepository.save(friend);
    }

    // 친구추가 friendId가 없거나 이미 친구이면 false리턴, 아닐시 친구요청테이블에 추가
    @Override
    public boolean addFriend(Long memberId, String friendId) {
        Optional<Member> friendOptional = memberRepository.findByUserId(friendId);

        if (!friendOptional.isPresent()) {
            // 친구 ID가 존재하지 않으면 false 반환
            return false;
        }

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + memberId));
        Member friend = friendOptional.get();

        // 이미 친구인지 확인
        if (memberRepository.isFriend(friendId, memberId)) {
            // 이미 친구라면 false 반환
            return false;
        }

        // 상대방이 이미 나에게 친구 요청을 보냈는지 확인
        if (member.getRequested_friends().contains(friend.getUserId())) {
            // 이미 친구 요청을 보낸 상태이므로 바로 친구로 추가

            // 서로의 friends 리스트에 상대방을 추가
            addFriendIfNotExists(member, friendId);
            addFriendIfNotExists(friend, member.getUserId());

            // 친구 요청 리스트에서 삭제
            friend.getRequested_friends().remove(member.getUserId()); // 친구의 요청 리스트에서 나를 삭제
            member.getRequested_friends().remove(friend.getUserId()); // 나의 요청 리스트에서 친구 삭제

            memberRepository.save(friend);
            memberRepository.save(member);

            return true; // 친구로 성공적으로 추가됨
        }

        // 친구 요청을 중복해서 보내지 않도록 확인
        String requestedUserId = member.getUserId();
        if (!friend.getRequested_friends().contains(requestedUserId)) {
            friend.getRequested_friends().add(requestedUserId);
            memberRepository.save(friend); // 친구 요청 추가
            return true;
        }

        return true; // 친구 요청이 성공적으로 추가됨
    }

    // 중복 친구 추가 방지를 위한 메서드
    private void addFriendIfNotExists(Member member, String friendId) {
        if (!member.getFriends().contains(friendId)) {
            member.getFriends().add(friendId);
        }
    }

    //친구요청 수락
    @Override
    public void acceptFriend(Long memberId, String userId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + memberId));
        Member friend = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with userId: " + userId));

        // 서로의 friends 리스트에 상대방 추가
        if (!member.getFriends().contains(userId)) {
            member.getFriends().add(userId); // 친구의 userId를 member의 친구 목록에 추가
        }

        if (!friend.getFriends().contains(member.getUserId())) {
            friend.getFriends().add(member.getUserId()); // member의 userId를 친구의 친구 목록에 추가
        }
        // 요청 리스트에서 삭제
        if (member.getRequested_friends().contains(userId)) {
            member.getRequested_friends().remove(userId); // 수락했으면 요청에서 삭제
        }
        // 상대방의 요청 리스트에서도 삭제
        if (friend.getRequested_friends().contains(member.getUserId())) {
            friend.getRequested_friends().remove(member.getUserId()); // 요청에서 삭제
        }

        memberRepository.save(member);
        memberRepository.save(friend);
    }

    //프로필 사진 조회
    @Override
    public byte[] getProfileImage(Long memberId) {
        Member memberProfile = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        return memberProfile.getUserImage() != null ? memberProfile.getUserImage() : new byte[0];

    }
    //프로필 업데이트
    @Override
    public void updateProfileImage(Long memberId, byte[] profileImage) {
        Member memberProfile = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        memberProfile.setUserImage(profileImage);
        memberRepository.save(memberProfile);
    }

    @Override
    public void deleteProfileImage(Long memberId) {
        Member memberProfile = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        memberProfile.setUserImage(null);
        memberRepository.save(memberProfile);
    }

    @Override
    public MemberDto getMemberByUserId(String id) {
        Member member = memberRepository.findByUserId(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Member not exists with given Userid : " + id));

        return MemberMapper.mapToMemberDto(member);
    }


}