package com.example.shop4.dto;

import com.example.shop4.entity.Comment;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CouponsDto {
    private Long id;
    private String name;
    private Long discount;
    private LocalDate createDate;
    private LocalDate limitDate;
    private Long count;
}
