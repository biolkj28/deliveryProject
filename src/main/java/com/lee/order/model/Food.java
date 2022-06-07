package com.lee.order.model;

import com.lee.order.dto.FoodDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Food extends timeStamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long Id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

     public Food(FoodDto dto) {
        this.name = dto.getName();
        this.price = dto.getPrice();
    }
}
