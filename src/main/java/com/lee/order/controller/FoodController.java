package com.lee.order.controller;

import com.lee.order.dto.FoodDto;
import com.lee.order.service.FoodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FoodController {

    private final FoodService service;

    @Operation(summary ="food register", description = "food register api")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void register(
            @PathVariable Long restaurantId,
            @RequestBody List<FoodDto>foods){

        service.register(restaurantId,foods);
    }

    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<FoodDto>menu(@PathVariable Long restaurantId){
        return service.menu(restaurantId);
    }
}
