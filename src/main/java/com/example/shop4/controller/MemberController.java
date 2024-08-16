package com.example.shop4.controller;

import com.example.shop4.dto.MemberDto;
import com.example.shop4.entity.Member;
import com.example.shop4.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/members")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberDto> createMember(@RequestBody MemberDto memberDto) {
        MemberDto created = memberService.createMember(memberDto);

        return new ResponseEntity<>(created, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<MemberDto> getMember(@PathVariable("id") Long id) {
        MemberDto memberDto = memberService.getMemberById(id);

        return new ResponseEntity<>(memberDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<MemberDto>> getMember() {
        List<MemberDto> memberDtos = memberService.getAllMembers();

        return new ResponseEntity<>(memberDtos, HttpStatus.OK);
    }

}
