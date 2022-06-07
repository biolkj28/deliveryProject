package com.lee.order.controller;

import com.lee.order.dto.OrderDto;
import com.lee.order.dto.OrderRequestDto;
import com.lee.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @GetMapping("/orders")
    public List<OrderDto> orderList(){
        return service.orderList();
    }

    @PostMapping("/order/request")
    public OrderDto register(@RequestBody OrderRequestDto dto){
        return service.register(dto);
    }
}
