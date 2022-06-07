package com.lee.order.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor
public class OrderInfo extends timeStamped{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @ManyToOne(targetEntity = Restaurant.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;


    @OneToMany(targetEntity = OrderFood.class,cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "foods")
    private List<OrderFood> orderList;

    @Column(nullable = false)
    private int TotalPrice;

    public OrderInfo(Restaurant restaurant, List<OrderFood>orders, int total) {
        this.restaurant = restaurant;
        this.orderList = orders;
        this.TotalPrice = total;
    }
}
