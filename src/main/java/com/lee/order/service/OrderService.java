package com.lee.order.service;


import com.lee.order.dto.FoodOrderRequestDto;
import com.lee.order.dto.OrderDto;
import com.lee.order.dto.OrderRequestDto;
import com.lee.order.exceptions.ExceptionMsg;
import com.lee.order.model.Food;
import com.lee.order.model.OrderFood;
import com.lee.order.model.OrderInfo;
import com.lee.order.model.Restaurant;
import com.lee.order.repository.OrderFoodRepository;
import com.lee.order.repository.OrderRepository;
import com.lee.order.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final RestaurantRepository restaurantRepository;
    private final OrderFoodRepository orderFoodRepository;


    @Transactional
    public List<OrderDto> orderList() {
        List<OrderDto> res = new LinkedList<>();
        List<OrderInfo> orders = repository.findAll();
        for (OrderInfo order : orders) {
            res.add(new OrderDto(order));
        }
        return res;
    }

    // 주문 등록
    @Transactional
    public OrderDto register(OrderRequestDto dto) {

        List<OrderFood> orders = new LinkedList<>();
        Map<Long, Food> menuMap = new HashMap<>();
        int total = 0;

        Restaurant restaurant = restaurantRepository.findById(dto.getRestaurantId()).orElseThrow(
                () -> new IllegalArgumentException(ExceptionMsg.RESTAURANT_IS_NULL.getMsg())
        );
        restaurant.getMenu().forEach(System.out::println);
        for (Food menu : restaurant.getMenu()) {
            menuMap.put(menu.getId(), menu);
        }

        for (FoodOrderRequestDto food : dto.getFoods()) {
            if (menuMap.containsKey(food.getId())) {

                Food menu = menuMap.get(food.getId());
                int qty = food.getQuantity();
                int price = menu.getPrice();
                String name = menu.getName();

                if (qty < 1 || qty > 100)
                    throw new IllegalArgumentException(ExceptionMsg.ILLEGAL_FOOD_ORDER_QUANTITY.getMsg());

                orders.add(new OrderFood(name, qty, (qty * price)));
                total += qty * price;
            }
        }
        if (total < restaurant.getMinOrderPrice()) {
            throw new IllegalArgumentException(ExceptionMsg.ILLEGAL_TOTAL_PRICE.getMsg());
        } else {
            total += restaurant.getDeliveryFee();
        }
        orders = orderFoodRepository.saveAll(orders);
        return new OrderDto(repository.save(new OrderInfo(restaurant, orders, total)));

    }


}
