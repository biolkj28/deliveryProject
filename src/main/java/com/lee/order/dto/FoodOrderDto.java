package com.lee.order.dto;

import com.lee.order.model.OrderFood;
import lombok.Getter;

@Getter
public class FoodOrderDto {
    String name;
    int quantity;
    int price;

    public FoodOrderDto(OrderFood orderFood) {
        this.name = orderFood.getName();
        this.quantity = orderFood.getQuantity();
        this.price = orderFood.getPrice();
    }
}
