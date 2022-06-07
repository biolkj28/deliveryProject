package com.lee.order.controller;


import com.lee.order.dto.RestaurantDto;
import com.lee.order.repository.RestaurantRepository;
import com.lee.order.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestaurantController {


    private final RestaurantService service;
    private final RestaurantRepository repository;
    @PostMapping("/restaurant/register")
    public RestaurantDto register(@RequestBody RestaurantDto dto) {
    return service.register(dto);
    }

    @GetMapping("/restaurants")
    public List<RestaurantDto>getList(){
        return service.getList();
    }
}
