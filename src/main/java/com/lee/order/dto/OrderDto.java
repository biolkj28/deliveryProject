package com.lee.order.dto;

import com.lee.order.model.OrderFood;
import com.lee.order.model.OrderInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Getter
@NoArgsConstructor
public class OrderDto {
    private String restaurantName;
    private List<FoodOrderDto> foods;
    private int deliveryFee;
    private int totalPrice;

    public OrderDto(OrderInfo orderInfo) {
        this.restaurantName = orderInfo.getRestaurant().getName();
        this.foods = new LinkedList<>();
        for (OrderFood orderFood : orderInfo.getOrderList()) {
            this.foods.add(new FoodOrderDto(orderFood));
        }
        this.deliveryFee = orderInfo.getRestaurant().getDeliveryFee();
        this.totalPrice = orderInfo.getTotalPrice();
    }
}
