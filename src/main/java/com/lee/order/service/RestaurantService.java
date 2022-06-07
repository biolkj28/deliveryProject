package com.lee.order.service;

import com.lee.order.dto.RestaurantDto;
import com.lee.order.exceptions.ExceptionMsg;
import com.lee.order.model.Restaurant;
import com.lee.order.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository repository;

    public List<RestaurantDto> getList() {
        List<RestaurantDto> list = new LinkedList<>();
        for (Restaurant restaurant : repository.findAllByOrderByIdAsc()) {
            list.add(new RestaurantDto(restaurant));
        }
        return list;
    }

    public RestaurantDto register(RestaurantDto dto) {
        RestaurantDto response = null;
        if (validateRestaurantRequestDto(dto)) {
            Restaurant restaurant = new Restaurant(dto);
            response = new RestaurantDto(repository.save(restaurant));
        }
        return response;
    }

    public boolean validateRestaurantRequestDto(RestaurantDto dto) {
        int min_price = dto.getMinOrderPrice();
        int deliveryFee = dto.getDeliveryFee();

        if (min_price < 1000 || min_price > 100000) {
            throw new IllegalArgumentException(ExceptionMsg.ILLEGAL_MIN_ORDER_PRICE_RANGE.getMsg());
        } else if (min_price % 100 != 0) {
            throw new IllegalArgumentException(ExceptionMsg.ILLEGAL_MIN_ORDER_PRICE_UNIT.getMsg());
        } else if (deliveryFee < 0 || deliveryFee > 10000) {
            throw new IllegalArgumentException(ExceptionMsg.ILLEGAL_DELIVERY_FEE_RANGE.getMsg());
        } else if (deliveryFee % 500 != 0) {
            throw new IllegalArgumentException(ExceptionMsg.ILLEGAL_DELIVERY_FEE_UNIT.getMsg());
        }
        return true;
    }
}
