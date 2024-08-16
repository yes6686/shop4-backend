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

    @Override
    public MemberDto createMember(MemberDto memberDto) {
        Member member = MemberMapper.mapToMember(memberDto);
        return MemberMapper.mapToMemberDto(memberRepository.save(member));
    }

    @Override
    public List<MemberDto> getAllMembers() {
        List<Member> members = memberRepository.findAll();
        return members.stream().map((member) -> MemberMapper.mapToMemberDto(member))
                .collect(Collectors.toList());
    }

    @Override
    public MemberDto getMemberById(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Member not exists with given id : " + memberId));

        return MemberMapper.mapToMemberDto(member);
    }

    @Override
    public MemberDto updateMember(Long memberId, MemberDto updatedMember) {
        return null;
    }

    @Override
    public void deleteMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Member not exists with given id : " + memberId));
        memberRepository.deleteById(memberId);
    }
}