package com.lee.order.controller;

import com.lee.order.dto.FoodDto;
import com.lee.order.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FoodController {

    private final FoodService service;
    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void register(@PathVariable Long restaurantId, @RequestBody List<FoodDto>foods){

        service.register(restaurantId,foods);
    }

    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<FoodDto>menu(@PathVariable Long restaurantId){
        return service.menu(restaurantId);
    }
}
