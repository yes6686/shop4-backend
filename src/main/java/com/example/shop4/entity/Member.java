package com.example.shop4.entity;

import com.example.shop4.dto.MemberDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String address;
    @Column
    private String phone;
    @Column
    private int age;
    @Column
    private LocalDate birth;
    @Column
    private String gender;
    @Column
    private String userId;
    @Column
    private String userPw;
    @Column
    private Long cash;

    public void patch(MemberDto dto) {
        if(dto.getName() != null) {
            this.name = dto.getName();
        }
        if(dto.getEmail() != null) {
            this.email = dto.getEmail();
        }
        if(dto.getAddress() != null) {
            this.address = dto.getAddress();
        }
        if(dto.getPhone() != null) {
            this.phone = dto.getPhone();
        }
        if(dto.getGender() != null) {
            this.gender = dto.getGender();
        }
        if(dto.getUserId() != null) {
            this.userId = dto.getUserId();
        }
        if(dto.getCash() != null) {
            this.cash = dto.getCash();
        }
        if(dto.getBirth() != null) {
            this.birth = dto.getBirth();
        }
        if(dto.getUserPw() != null) {
            this.userPw = dto.getUserPw();
        }
        if(dto.getAge() != 0) {
            this.age = dto.getAge();
        }
    }
}
