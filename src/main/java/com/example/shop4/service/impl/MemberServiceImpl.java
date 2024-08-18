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
                ()-> new ResourceNotFoundException("Member not exists with given id : " + memberId)
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
                .map(MemberDto::createMemberDto)
                .orElseThrow(() ->
                        new ResourceNotFoundException("아이디 및 비밀번호가 일치하는 유저가 없습니다."));
    }
}