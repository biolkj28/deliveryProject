package com.lee.order.dto;

import com.lee.order.model.Food;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FoodDto {
    private Long id;
    private String name;
    private int price;

    public FoodDto(Food food) {
        this.id = food.getId();
        this.name = food.getName();
        this.price = food.getPrice();
    }
}
