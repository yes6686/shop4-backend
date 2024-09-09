package com.example.shop4.controller;

import com.example.shop4.dto.MemberDto;
import com.example.shop4.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/members")
public class MemberController {
    @Autowired
    private MemberService memberService;
    //회원 추가
    @PostMapping
    public ResponseEntity<MemberDto> createMember(@RequestBody MemberDto memberDto) {
        MemberDto created = memberService.createMember(memberDto);

        return new ResponseEntity<>(created, HttpStatus.OK);
    }
    // 회원 조회
    @GetMapping("{id}")
    public ResponseEntity<MemberDto> getMember(@PathVariable("id") Long id) {
        MemberDto memberDto = memberService.getMemberById(id);

        return new ResponseEntity<>(memberDto, HttpStatus.OK);
    }

    // 모든 회원 조회
    @GetMapping
    public ResponseEntity<List<MemberDto>> getMember() {
        List<MemberDto> memberDtos = memberService.getAllMembers();

        return new ResponseEntity<>(memberDtos, HttpStatus.OK);
    }

    // 회원 정보 수정
    @PatchMapping("{id}")
    public ResponseEntity<MemberDto> updateMember(@PathVariable("id") Long id,
                                                  @RequestBody MemberDto updatedMember) {
        MemberDto memberDto = memberService.updateMember(id, updatedMember);
        return ResponseEntity.ok(memberDto);
    }

    // 회원 정보 삭제
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteMember(@PathVariable("id") Long memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.ok("Member deleted successfully..!");
    }

    //로그인 요청
    @PostMapping("login")
    public ResponseEntity<MemberDto> loginMember(@RequestBody MemberDto dto) {
        MemberDto findMember = memberService.loginMember(dto);
        return new ResponseEntity<>(findMember,HttpStatus.OK);
    }

    // userId가 있는지 체크하기
    @GetMapping("check/{userId}")
    public ResponseEntity<Boolean> checkMember(@PathVariable("userId") String userId) {
        boolean exists = memberService.checkUserId(userId);
        return ResponseEntity.ok(exists);
    }

    // 친구목록 조회
    @GetMapping("friendsList/{id}")
    public ResponseEntity<List<MemberDto>> getFriends(@PathVariable("id") Long id) {
        List<MemberDto> friends = memberService.getAllFriends(id);
        return ResponseEntity.ok(friends);
    }
    // 친구 요청 이미 친구이거나 해당 id없으면 fasle 리턴
    @GetMapping("addFriend/{memberId}/{userId}") // 여기서  memberId는 1,2,3 이런거고 userId는 kim1234이런거임
    public ResponseEntity<Boolean> addNewFriend(@PathVariable("memberId") Long memberId,
                                                @PathVariable("userId") String userId) {
        boolean addFriend = memberService.addFriend(memberId, userId); //참고 : 변수 이름과 함수이름이 같음
        return ResponseEntity.ok(addFriend);
    }
    // 친구 요청 목록 조회
    @GetMapping("RequestedFriendList/{id}")
    public ResponseEntity<List<MemberDto>> getRequestedFriends(@PathVariable("id") Long id) {
        List<MemberDto> friends = memberService.getAllRequestedFriends(id);
        return ResponseEntity.ok(friends);
    }
}
