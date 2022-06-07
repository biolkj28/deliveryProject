package com.lee.order.repository;

import com.lee.order.model.Food;
import com.lee.order.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;



public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
    List<Restaurant>findAllByOrderByIdAsc();

    @Query("select this.menu from Restaurant this where this.id=:restaurantId")
    List<Food>findMenus(@Param("restaurantId") Long restaurantId);
}
