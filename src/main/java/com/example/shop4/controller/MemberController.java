package com.example.shop4.controller;

import com.example.shop4.dto.MemberDto;
import com.example.shop4.service.MemberService;
import lombok.AllArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/members")
public class MemberController {
    private final Tika tika = new Tika(); // Apache Tika 인스턴스 생성

    @Autowired
    private MemberService memberService;
//    //회원 추가
//    @PostMapping
//    public ResponseEntity<MemberDto> createMember(@RequestBody MemberDto memberDto) {
//        MemberDto created = memberService.createMember(memberDto);
//
//        return new ResponseEntity<>(created, HttpStatus.OK);
//    }
    @PostMapping
    public ResponseEntity<MemberDto> createMember(
            @RequestParam("userId") String userId,
            @RequestParam("userPw") String userPw,
            @RequestParam("name") String name,
            @RequestParam("phone") String phone,
            @RequestParam("email") String email,
            @RequestParam("birth") LocalDate birth,
            @RequestParam(value = "file", required = false) MultipartFile file) {
        // MemberDto를 생성 (이 부분은 DTO 생성 로직에 따라 달라질 수 있음)
        MemberDto memberDto = new MemberDto();
        memberDto.setUserId(userId);
        memberDto.setUserPw(userPw);
        memberDto.setName(name);
        memberDto.setEmail(email);
        memberDto.setPhone(phone);
        memberDto.setBirth(birth);

        // 파일 처리 (file은 필요에 따라 설정)
        if (file != null) {
            try {
                byte[] imageBytes = file.getBytes();
                memberDto.setUserImage(imageBytes);

            } catch (IOException e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        // 서비스 호출하여 회원 생성
        MemberDto createdMember = memberService.createMember(memberDto);

        // 생성된 회원 정보 반환
        return new ResponseEntity<>(createdMember, HttpStatus.CREATED);
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
    // 친구 요청 이미 친구이거나 해당 id없으면 fasle 리턴 만일 서로 요청을 보내면 친구됨
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
    //친구요청 거절
    @DeleteMapping("rejectFriend/{memberId}/{userId}") // 친구요청을 거절하면 요청테이블에서 삭제
    public ResponseEntity <String> rejectFriend(@PathVariable("memberId") Long memberId,
                                                @PathVariable("userId") String userId) {
            memberService.deleteRequestedFriend(memberId,userId);
            return ResponseEntity.ok("Friend rejected successfully..!");
    }
    //친구요청 수락
    @GetMapping("acceptFriend/{memberId}/{userId}")
    public ResponseEntity <String> acceptFriend(@PathVariable("memberId") Long memberId,
                                                @PathVariable("userId") String userId){
        memberService.acceptFriend(memberId,userId);
        return ResponseEntity.ok("Friend accepted successfully..!");
    }
    //친구삭제
    @DeleteMapping("deleteFriend/{memberId}/{userId}")
    public ResponseEntity <String> deleteFriend(@PathVariable("memberId") Long memberId,
                                                @PathVariable("userId") String userId){
        memberService.deleteFriend(memberId,userId);
        return ResponseEntity.ok("Friend deleted successfully..!");
    }

    //프로필사진 조회
    @GetMapping("getProfileImage/{memberId}")
    public ResponseEntity<byte[]> getProfileImage(@PathVariable("memberId") Long memberId) {
        byte[] imageData = memberService.getProfileImage(memberId);
        if (imageData == null ||imageData.length == 0) {
            return ResponseEntity.ok().body(null);
        }

        // Apache Tika를 사용해 MIME 타입 감지
        String mimeType = tika.detect(imageData);

        // MIME 타입에 맞춰 MediaType 설정
        MediaType mediaType;
        switch (mimeType) {
            case "image/jpeg":
                mediaType = MediaType.IMAGE_JPEG;
                break;
            case "image/png":
                mediaType = MediaType.IMAGE_PNG;
                break;
            case "image/gif":
                mediaType = MediaType.IMAGE_GIF;
                break;
            default:
                mediaType = MediaType.APPLICATION_OCTET_STREAM; // 기본값
        }
        return ResponseEntity.ok()
                .contentType(mediaType) // MIME 타입 설정
                .body(imageData);
    }
    //프로필 사진 수정
    @PatchMapping("updateProfileImage/{memberId}")
    public ResponseEntity<String> updateProfileImage(@PathVariable("memberId") Long memberId,
                                                     @RequestParam("file") MultipartFile file) {
        try {


            byte[] imageData = file.getBytes();
            memberService.updateProfileImage(memberId, imageData);
            return ResponseEntity.ok("이미지 업데이트");

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("deleteProfileImage/{memberId}")
    public ResponseEntity<String> deleteProfileImage(@PathVariable("memberId") Long memberId) {
        memberService.deleteProfileImage(memberId);
        return ResponseEntity.ok("프로필 이미지 삭제 성공");
    }






}
