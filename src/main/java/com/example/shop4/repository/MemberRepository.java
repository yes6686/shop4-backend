package com.example.shop4.repository;


import com.example.shop4.dto.MemberDto;
import com.example.shop4.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    // 유저 아이디,비밀번호 데이터베이스에서 조회를 위한
    Optional<Member> findByUserIdAndUserPw(String userId, String userPw);

    Optional<Member> findByUserId(String userId);

    // 친구 목록을 id로 조회
    @Query("SELECT m.friends FROM Member m WHERE m.id = :id")
    List<String> findFriendsById(@Param("id") Long id);

   // 친구 요청 목록 id로 조회
    @Query("SELECT m.requested_friends FROM  Member m WHERE  m.id = :id")
    List<String> findRequestedFriendsById(@Param("id") Long id);

    // 친구 목록을 id로 조회
    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM Member m WHERE :id MEMBER OF m.friends AND m.id = :memberId")
    boolean isFriend(@Param("id") String id, @Param("memberId") Long memberId);


}
