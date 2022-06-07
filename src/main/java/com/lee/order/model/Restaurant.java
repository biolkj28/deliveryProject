package com.lee.order.model;

import com.lee.order.dto.RestaurantDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Restaurant extends timeStamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int minOrderPrice;

    @Column(nullable = false)
    private int deliveryFee;

    @OneToMany(targetEntity = Food.class)
    @JoinColumn(name ="foods")
    private List<Food>menu;

    public Restaurant(RestaurantDto dto) {
        this.name = dto.getName();
        this.minOrderPrice = dto.getMinOrderPrice();
        this.deliveryFee = dto.getDeliveryFee();
        this.menu = null;
    }

}
