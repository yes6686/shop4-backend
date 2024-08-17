package com.example.shop4.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private long id;
    private String name;
    private String email;
    private String address;
    private String phone;
    private int age;
    private LocalDate birth;
    private String gender;
    private String userId;
    private String userPw;
    private Long cash;
}
