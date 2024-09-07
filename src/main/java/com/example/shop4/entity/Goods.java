package com.example.shop4.entity;

import com.example.shop4.dto.GoodsDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long price;
    private Long stock;
    private String name;
    @Lob
    private String description;
    private String url;
    private String category;

    // FetchType.EAGER는 연관된 엔티티를 즉시 로딩하는 전략.
    // CascadeType.REMOVE가 지정되어 있어, 부모 엔티티가 삭제될 때 관련된 모든 자식 엔티티도 함께 삭제된다.
//    @OneToMany(mappedBy = "goods", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
//    @OrderBy("id asc") // 댓글 정렬
//    private List<Comment> comments;

    public void patch(GoodsDto dto){
        if(dto.getName() != null){
            this.name = dto.getName();
        }
        if(dto.getDescription() != null){
            this.description = dto.getDescription();
        }
        if(dto.getUrl() != null){
            this.url = dto.getUrl();
        }
        if(dto.getCategory() != null){
            this.category = dto.getCategory();
        }
        if(dto.getPrice() != null){
            this.price = dto.getPrice();
        }
        if(dto.getStock() != null){
            this.stock = dto.getStock();
        }
    }
}


