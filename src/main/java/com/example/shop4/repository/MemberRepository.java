package com.example.shop4.repository;


import com.example.shop4.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    // 유저 아이디,비밀번호 데이터베이스에서 조회를 위한
    Optional<Member> findByUserIdAndUserPw(String userId, String userPw);
}
